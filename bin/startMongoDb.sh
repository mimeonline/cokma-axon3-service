#!/bin/sh

docker run --name mongodb -d -p 27017:27017 -v /Users/michaelmeierhoff/development/var/mongodb:/data/db mongo