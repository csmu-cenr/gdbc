#!/bin/bash

# builds mac+linux, on mac.

for db in filemaker; do
    ./run-ci.sh $db
    ./wrapper/scripts/wrap-$db.sh
done