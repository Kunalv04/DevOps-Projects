import json
import os
import paramiko

def lambda_handler(event, context):
    print("EVENT:", event)

    body = event.get('body')

    if body:
        try:
            body = json.loads(body)
        except:
            pass

    print("Parsed Body:", body)

    host = os.environ['EC2_HOST']
    user = os.environ['EC2_USER']
    key = os.environ['EC2_KEY']

    key = os.environ['EC2_KEY']

    # Convert \n to actual newlines
    key = key.replace("\\n", "\n")
    
    key_file = "/tmp/key.pem"
    with open(key_file, "w") as f:
        f.write(key)

    os.chmod(key_file, 0o400)

    ssh = paramiko.SSHClient()
    ssh.set_missing_host_key_policy(paramiko.AutoAddPolicy())

    private_key = paramiko.RSAKey.from_private_key_file(key_file)

    ssh.connect(hostname=host, username=user, pkey=private_key)

    stdin, stdout, stderr = ssh.exec_command("cd /home/ubuntu && ./deploy.sh")

    print(stdout.read().decode())
    print(stderr.read().decode())

    ssh.close()

    return {
        "statusCode": 200,
        "body": "Deployment triggered"
    }