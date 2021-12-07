# API-2021-SMTP


## Table of contents
* [General info](#general-info)
* [Description](#description)
  * [What is MockMock ?](#what-is-mockmock-)
* [Setting up a SMTP server with Docker](#setting-up-a-smtp-server-with-docker)
* [Launch Prank campaign](#launch-prank-campaign)
* [How does it work ?](#how-does-it-work-)
* [Credits](#credits)

## General info
⚠️

## Description
### What is MockMock ?

## Setting up a SMTP server with Docker
> For this chapter we will use Docker Desktop. We assume that you have installed it and you know the basic use of it. Otherwise refere to [Docker website](https://www.docker.com/products/docker-desktop).

For this project, we will use MockMock as a SMTP server. You will find all the files in the following GitHub repo : https://github.com/AlenBijelic99/MockMock

After installing it in your local directory you need to launch Docker Desktop and execute [build-image.sh](https://github.com/AlenBijelic99/MockMock/blob/master/docker/build-image.sh). This will create a new image of the programm and it will be available in Docker as you can see here.
![Docker image](/figures/dockerImage.PNG)

Then you can run the container with the following script [run-container.sh](https://github.com/AlenBijelic99/MockMock/blob/master/docker/run-container.sh). Now you should see something as this in Docker Desktop.
![Docker running container](/figures/dockerRunningContainer.PNG)

The container is configured to automatically run MockMock Server on port 2525 and the mail overview website on port 8080.

You can now connect to the SMTP server through telnet or NetCat.

TODO add example

And see emails that the server received in http://localhost:8080

TODO: ADD IMAGE

## Launch Prank campaign
> For this example, we will use IntelliJ with Java 11 (see [here](https://www.jetbrains.com/fr-fr/idea/download/#section=windows) to downlaod) to modify the config files. Be sure to install all the requirements and follow the procedure to [setting up a SMTP server with Docker](#setting-up-a-smtp-server-with-docker)

Here are the main steps you need to follow in order to send bunch of emails to victims.
1. Set up your config files (in [config](/config) directory).
   1. Change [config.properties](/config/config.properties) to add your SMTP server address and port, the number of groups in total and optionally desired CC email address on which will emails be sent too
   2. In [message.utf8](/config/messages.utf8) you can add your own messages. The programm uses "==" as a separator between messages, so do not forget to put "==" at the end of the message
   3. In [victims.utf8](/config/victims.utf8) you can add victims email addresses. One per line.
2. Launch your MockMock SMTP Server
3. 

## How does it work ?

## Credits
[AlenBijelic99](https://github.com/AlenBijelic99)

[Pain-da](https://github.com/Pain-da)