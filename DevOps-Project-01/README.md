# Deploy your code on a Docker Container using Jenkins on AWS

**This guide shows how to deploy a Java web application in a Docker container running on an AWS EC2 instance, using Jenkins to automate build and deployment.**

### Agenda

- Setup Jenkins
- Setup & configure Maven and Git
- Integrate GitHub and Maven with Jenkins
- Setup Docker host
- Integrate Docker with Jenkins
- Automate build and deployment using Jenkins
- Test the deployment

### Prerequisites

- AWS account
- Git / GitHub account with the source code
- A local machine with SSH/CLI access
- Basic familiarity with Docker, Git, Maven, and Jenkins

## Step 1: Setup Jenkins Server on AWS EC2 Instance

1. Launch a Linux EC2 instance (Amazon Linux 2 or similar).
2. SSH into the instance using your key pair.

Install Jenkins repository and import the key:

```bash
sudo wget -O /etc/yum.repos.d/jenkins.repo https://pkg.jenkins.io/redhat-stable/jenkins.repo
sudo rpm --import https://pkg.jenkins.io/redhat-stable/jenkins.io-2023.key
```

Install EPEL (if on Amazon Linux) and Java OpenJDK 11, then install Jenkins:

```bash
# Example (Amazon Linux / RHEL-based)
sudo yum install -y epel-release
sudo yum install -y java-11-openjdk-devel
sudo yum install -y jenkins
sudo systemctl enable --now jenkins
```

Open port 8080 in the instance security group to access Jenkins Web UI: http://<EC2_PUBLIC_IP>:8080

Unlock Jenkins (retrieve initial admin password):

```bash
sudo cat /var/lib/jenkins/secrets/initialAdminPassword
```

Follow the web UI to install suggested plugins and create an admin user.

## Step 2: Integrate GitHub with Jenkins

1. Install Git on the Jenkins instance:

```bash
sudo yum install -y git
git --version
```

2. In Jenkins UI, go to Manage Jenkins > Manage Plugins and install the GitHub-related plugins (e.g., GitHub Integration, Git plugin).
3. Configure Git under Manage Jenkins > Global Tool Configuration (add a Git installation name and path if needed).
4. Configure GitHub credentials (personal access token or SSH key) in Jenkins Credentials and add GitHub repository integrations to your jobs.

## Step 3: Integrate Maven with Jenkins

1. Install Maven on the Jenkins server. Example (download Apache Maven 3.9.1):

```bash
cd /opt
sudo wget https://archive.apache.org/dist/maven/maven-3/3.9.1/binaries/apache-maven-3.9.1-bin.tar.gz
sudo tar -xzvf apache-maven-3.9.1-bin.tar.gz
sudo mv apache-maven-3.9.1 /opt/maven
```

2. Set environment variables in the Jenkins server (e.g., in /etc/profile or /home/jenkins/.bash_profile):

```bash
export JAVA_HOME=/usr/lib/jvm/java-11-openjdk
export M2_HOME=/opt/maven
export M2=$M2_HOME/bin
export PATH=$M2:$PATH
```

Reload the profile and verify:

```bash
source /etc/profile
mvn -version
```

3. In Jenkins UI, install the Maven Integration Plugin and set up Maven and Java paths under Manage Jenkins > Global Tool Configuration.

## Step 4: Setup a Docker Host

1. Launch another Linux EC2 instance to act as the Docker host (or use the same instance if appropriate).
2. Install Docker and start the service:

```bash
sudo yum install -y docker
sudo systemctl enable --now docker
sudo docker --version
```

3. Allow the desired host ports in the EC2 security group (for example, 8081–9000).

Create a basic Tomcat container to verify Docker:

```bash
sudo docker pull tomcat:latest
sudo docker run -d --name tomcat-container -p 8081:8080 tomcat:latest
```

If Tomcat shows 404 due to empty webapps, copy defaults inside the container or build a custom image (see below).

Create a custom Tomcat Dockerfile to populate webapps:

```dockerfile
FROM tomcat:latest
RUN cp -R /usr/local/tomcat/webapps.dist/* /usr/local/tomcat/webapps
```

Build and run:

```bash
docker build -t tomcatserver .
docker run -d --name tomcat-server -p 8085:8080 tomcatserver
```

## Step 5: Integrate Docker with Jenkins

1. On the Docker host, create a dedicated user for CI (example: dockeradmin) and add it to the docker group:

```bash
sudo useradd -m dockeradmin
sudo passwd dockeradmin   # set a password if using password auth (SSH key is recommended)
sudo usermod -aG docker dockeradmin
```

2. (Optional) Enable password authentication in /etc/ssh/sshd_config if you will use password-based SSH (recommended to use SSH keys instead). Then reload SSH:

```bash
sudo sed -i 's/^PasswordAuthentication no/PasswordAuthentication yes/' /etc/ssh/sshd_config
sudo systemctl reload sshd
```

3. In Jenkins, install the "Publish Over SSH" plugin (Manage Jenkins > Manage Plugins).
4. Configure the Docker host in Manage Jenkins > Configure System > Publish over SSH: add SSH server details (hostname/IP, credentials). Prefer SSH keys over passwords.

## Step 6: Create a New Jenkins Job to Build and Copy Artifacts to Docker Host

1. Create a new Freestyle job in Jenkins (do not copy an existing job). Configure the job as follows:
   - Source Code Management: Git
     - Repository URL: https://github.com/Kunalv04/DevOps-Projects.git
     - Credentials: None (public access)
     - Branches to build: main
   - Build:
     - Add "Execute shell" build step and run the build script from this repository: DevOps-Project-01/Build script (use the exact script contents from that file in the repo). Example:
       - Either copy the file contents into the "Execute shell" field, or add a step that runs the script if it's available in the workspace:
         ```bash
         chmod +x "DevOps-Project-01/Build script"
         ./DevOps-Project-01/Build\ script
         ```
   - Post-build Actions:
     - Add "Send files or execute commands over SSH" (Publish over SSH)
       - SSH server: select your configured Docker host
       - Source files: DevOps-Project-01/**/target/*.war
       - Remove prefix: DevOps-Project-01
       - Remote directory: /opt/docker (or the directory you configured on the Docker host)
       - (Optional) Add Exec command to build/run the Docker image on the remote host (see Step 8)

2. Enable SCM polling or configure a GitHub webhook so the job triggers on commits to main.
3. Run the job manually once to verify the build completes and the WAR appears on the Docker host at /opt/docker/ (and that the remove prefix correctly strips the DevOps-Project-01 path).

## Step 7: Update Dockerfile to include Application Artifact

On the Docker host, create a docker directory (e.g., /opt/docker) and place a Dockerfile that copies the WAR into Tomcat's webapps. Example Dockerfile:

```dockerfile
FROM tomcat:latest
RUN cp -R /usr/local/tomcat/webapps.dist/* /usr/local/tomcat/webapps
COPY ./*.war /usr/local/tomcat/webapps
```

Build and run the image:

```bash
cd /opt/docker
docker build -t tomcat:v1 .
docker run -d --name tomcatv1 -p 8086:8080 tomcat:v1
```

Access your app at: http://<DOCKER_HOST_PUBLIC_IP>:8086/<your-app-context>/

## Step 8: Automate Build and Deployment on Docker Container

To automate end-to-end deployment from commit to running container, add remote SSH commands in the Jenkins job (after copying the WAR) to build and run the Docker image on the Docker host. Example "Exec command" in the Publish over SSH step:

```bash
cd /opt/docker
docker build -t regapp:v1 .
docker rm -f registerapp || true
docker run -d --name registerapp -p 8087:8080 regapp:v1
```

When changes are pushed to GitHub, a Jenkins build should:
1. Checkout code
2. Run Maven build and produce the WAR (per the build script)
3. Copy the WAR to the Docker host
4. Build the Docker image and run the container

Verify the new container is running and accessible at http://<DOCKER_HOST_PUBLIC_IP>:8087/<your-app-context>/

### Security notes and best practices

- Use SSH keys (not passwords) for Jenkins to access the Docker host.
- Use Jenkins credentials store to securely manage secrets.
- Consider using container registries (private or public) rather than copying images manually between hosts.
- Use versioned image tags (avoid using latest in production pipelines).
- Clean up old containers and images to avoid disk pressure on the Docker host.

### Conclusion

This guide demonstrates how to automate building and deploying a Java web application using GitHub, Jenkins, Docker, and AWS EC2. The pipeline builds the application, copies artifacts to a Docker host, builds a Docker image, and runs the container automatically.

Happy learning!

## Author & Community

This project is maintained by **[@Kunalv04](https://github.com/Kunalv04)**.

If you found this helpful, consider starring ⭐ the repository and sharing it.
