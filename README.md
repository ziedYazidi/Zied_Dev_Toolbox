**Zied_Dev_Toolbox** is a personal repository used to explore new tools, experiment with integrations, and develop small proof-of-concepts (PoCs). It serves as a space for learning, testing ideas, and refining approaches before applying them to larger projects.

Useful commands:

```console
# Run jenkins container
docker run -p 8080:8080 -p 50000:50000 --restart=on-failure -v jenkins_home:/var/jenkins_home jenkins/jenkins:lts-jdk11

# Expose local server on port 8080 to the internet via Serveo using a reverse SSH tunnel.
ssh -R 80:localhost:8080 serveo.net
ngrok http 8080 

# Connect to container as root.
docker exec -u 0 -it logstash bash
apt-get update && apt-get install -y iputils-ping
 ```
