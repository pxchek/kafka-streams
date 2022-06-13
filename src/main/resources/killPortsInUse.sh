#!/usr/bin/env bash

#If the port is already in use and the process is unable to start, try the following commands
#and kill those ports

lsof -t -i tcp:2181 | xargs kill -9
lsof -t -i tcp:9000 | xargs kill -9
lsof -t -i tcp:9001 | xargs kill -9
lsof -t -i tcp:9002 | xargs kill -9