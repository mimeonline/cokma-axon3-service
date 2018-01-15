#!/bin/sh

docker run --name mongodb -d -p 27017:27017 -v ~/development/var/mongodb:/data/db mongo
