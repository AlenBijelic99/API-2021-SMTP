# API-2021-SMTP


## Table of contents
* [General info](#general-info)
* [Description](#description)
  * [What is MockMock ?](#what-is-mockmock-)
* [Setting up a SMTP server with Docker](#setting-up-a-smtp-server-with-docker)
  * [Explanation](#explanation)
  * [Build image](#build-image)
  * [Run container](#run-container)
  * [Check connection](#check-connection)
  * [SMTP mails overview](#smtp-mails-overview)
* [Launch Prank campaign](#launch-prank-campaign)
* [How does it work ?](#how-does-it-work-)
  * [SMTP](#smtp)
  * [UML](#uml)
  * [The Prank program](#the-prank-program)
    * [MailRobot](#mailrobot)
    * [PrankGenerator and Prank](#prankgenerator-and-prank)
    * [smtpclient](#smtpclient)
    * [Group, Message and Person](#group-message-and-person)
    * [ConfigManager](#configmanager)
* [Credits](#credits)

## General info
Prank campaign program enables you to send prank emails to people through a SMTP server. You just need to victims email address, add the messages, set the number of groups and add SMTP server information in order to send them prank messages.

Read all descriptions and tutorial to set up a SMTP server with Docker and to send emails. There are links to learn more about each point explained down below.

## Description
### What is MockMock ?
Before sending prank emails on a real SMTP server, you want to test on your machine. MockMock is a SMTP server that can only receive emails and its purpose is to test if your program can send emails before sending to real people. It also provide a web interface which allows you to see all the necessary information like email header, raw mail output and the body plain text.

## Setting up a SMTP server with Docker
> For this chapter we will use Docker Desktop. We assume that you have installed it and you know the basic use of it. Otherwise refere to [Docker website](https://www.docker.com/products/docker-desktop).

### Explanation
For this project, we will use MockMock as a SMTP server. You will find all the files in the following GitHub repo : https://github.com/AlenBijelic99/MockMock

### Build image
After installing it in your local directory you need to launch Docker Desktop and execute [build-image.sh](https://github.com/AlenBijelic99/MockMock/blob/master/docker/build-image.sh) script. This will create a new image of the programm and it will be available in Docker as you can see here.

![Docker image](/figures/dockerImage.PNG)

### Run container
Then you can run the container with the [run-container.sh](https://github.com/AlenBijelic99/MockMock/blob/master/docker/run-container.sh) script. Now you should see something as this in Docker Desktop.

![Docker running container](/figures/dockerRunningContainer.PNG)

The container is configured to automatically run the SMTP Server on port 2525 and the mail overview website on port 8080.

### Check connection
You can now connect to the SMTP server through Telnet or NetCat. In this example we connect to SMTP server and send a message. The prefixed letter indicates who sent the message.

Use this command to connect with Telnet in a command prompt:
```
C:\Users\Alen Bijelic> telnet localhost 2525
```

You just initiate the connection and the server will respond. Now you can send emails through command prompt.

```
S: 220 a2e86142db19 ESMTP MockMock SMTP Server version 1.4
C: EHLO gmail.com
S: 250-a2e86142db19
S: 250-8BITMIME
S: 250 Ok
C: MAIL FROM:<test@gmail.com>
S: 250 Ok
C: RCPT TO:<john@gmail.com>
S: 250 Ok
C: DATA
S: 354 End data with <CR><LF>.<CR><LF>
C: From: Test <test@gmail.com>
C: To: John <john@gmail.com>
C: Subject: Hello
C: 
C: Hi John,
C: 
C: How are you?
C: 
C: Test.
C: .
S: 250 Ok
C: 
C: QUIT
S: 221 Bye
C: Perte de la connexion à l’hôte.
```

### SMTP mails overview
You can see the email that the server received in http://localhost:8080

![New received email](/figures/mockMockReceivedEmail.PNG)

![Email details](/figures/mockMockEmailDetails.PNG)

## Launch Prank campaign
> For this example, we will use IntelliJ with Java 11 (see [here](https://www.jetbrains.com/fr-fr/idea/download/#section=windows) to download) to modify the config files and run the program. Be sure to install all the requirements and follow the procedure to [setting up a SMTP server with Docker](#setting-up-a-smtp-server-with-docker)

Here are the main steps you need to follow in order to send prank emails to victims.
1. Set up your config files (in [config](/config) directory).
   1. Change [config.properties](/config/config.properties) to add your SMTP server address and port, the number of groups in total and optionally desired CC email address on which will emails be sent too.
   2. In [message.utf8](/config/messages.utf8) you can add your own messages. The program uses "==" as a separator between messages, so do not forget to put "==" at the end of the message.
   3. In [victims.utf8](/config/victims.utf8) you can add victims email addresses. One per line.
2. Launch your MockMock SMTP Server (as specified [here](#setting-up-a-smtp-server-with-docker)).
3. Run the program.
4. Check if emails are received by the server on the MockMock web interface.

If you are using this program on a real SMTP server, you do not need to do steps 2 and 4.

⚠️On real SMTP server it might not work. Depending on what address you are sending from, some servers have a SPF record set up which is a list of SMTP server that are allowed to send emails for a specific domain name.

## How does it work ?
### SMTP
SMTP (Simple Mail Transfer Protocol) is a protocol that defines the steps and the specifications on how to send emails. Here is an example of client-server exchanges on a real SMTP server. With MockMock there is less steps as it does not send email to the recipients. So the first request is not done and the last step stops at the server.

![SMTP Example](/figures/connectToSMTP.PNG)

For more information, please check [RFC5321](https://datatracker.ietf.org/doc/html/rfc5321)

### UML

![UML](/figures/UML.PNG)

### The Prank program
#### MailRobot
MailRobot is the entry point of the program. This will start the prank generation (PrankGenerator).

#### PrankGenerator and Prank
PrankGenerator will prepare all pranks with the Prank class. It will create groups of people and set the sender and the victims. After that it will use SmtpClient to send emails to victims.

#### SmtpClient
The SMTP Client manage communication with the server. It will read information sent by the server and send the email.

#### Group, Message and Person
These classes manage the people, the groups and the messages. It enable PrankGenerator to organise the emails to be sent.

#### ConfigManager
The ConfigManage class retrieves all informations from the config files. It uses Property class to read the config.properties file. For .utf8 files it will read with buffers.

## Credits
[AlenBijelic99](https://github.com/AlenBijelic99)

[Pain-da](https://github.com/Pain-da)
