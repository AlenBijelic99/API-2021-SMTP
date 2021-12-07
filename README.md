# API-2021-SMTP


## Table of contents
* [General info](#general-info)
* [Description](#description)
  * [What is MockMock ?](#what-is-mockmock-)
* [Setting up a SMTP server with Docker](#setting-up-a-smtp-server-with-docker)
* [Launch Prank program](#launch-prank-program)
* [How does it work ?](#how-does-it-work-)

## General info
⚠️

## Description
### What is MockMock ?

## Setting up a SMTP server with Docker

## Launch Prank campaign
Here are the main steps you need to follow in order to send bunch of emails to victims.
1. Set up your config files (in [config](/config) directory).
   1. Change [config.properties](/config/config.properties) to add your SMTP server address and port, the number of groups in total and optionally desired CC email address on which will emails be sent too
   2. In [message.utf8](/config/messages.utf8) you can add your own messages. The programm uses "==" as a separator between messages, so do not forget to put "==" at the end of the message
   3. In [victims.utf8](/config/victims.utf8) you can add victims email addresses. One per line.
2. 

## How does it work ?

## Credits
[AlenBijelic99](https://github.com/AlenBijelic99)

[Pain-da](https://github.com/Pain-da)