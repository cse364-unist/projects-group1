# Use Ubuntu 22.04 as base image
FROM ubuntu:22.04

# Install prerequisites
RUN apt-get update \
    && apt-get install -y wget gnupg2 \
    && rm -rf /var/lib/apt/lists/*

# Import MongoDB public GPG key
RUN wget -qO - https://www.mongodb.org/static/pgp/server-6.0.asc | apt-key add -

# Add MongoDB repository to sources list
RUN echo "deb [ arch=amd64,arm64 ] https://repo.mongodb.org/apt/ubuntu jammy/mongodb-org/6.0 multiverse" | tee /etc/apt/sources.list.d/mongodb-org-6.0.list

# Update package list and install MongoDB
RUN apt-get update \
    && apt-get install -y mongodb-org \
    && rm -rf /var/lib/apt/lists/*

# Create necessary directories
RUN mkdir -p /data/db /data/configdb

# Change ownership of directories
RUN chown -R mongodb:mongodb /data/db /data/configdb

# Add your stuff below:
RUN apt-get update \
    && apt-get install -y vim maven openjdk-17-jdk curl git\
    && rm -rf /var/lib/apt/lists/*

RUN wget https://dlcdn.apache.org/tomcat/tomcat-10/v10.1.24/bin/apache-tomcat-10.1.24.tar.gz -O /tmp/tomcat.tar.gz
RUN cd /tmp && tar xvfz tomcat.tar.gz
WORKDIR /root/project
RUN mkdir tomcat
RUN cp -Rv /tmp/apache-tomcat-10.1.24/* /root/project/tomcat

# Remove existing ROOT directory if it exists
RUN rm -rf /root/project/tomcat/webapps/ROOT

# Copy the WAR file to the Tomcat webapps directory
COPY ROOT.war /root/project/tomcat/webapps/ROOT.war

WORKDIR /root/project
COPY run.sh .
RUN sed -i 's/\r$//' run.sh
RUN chmod +x run.sh
CMD ["./run.sh"]
