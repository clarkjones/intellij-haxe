#!/bin/bash

if [[ $# -eq 0 ]] ; then
    echo 'This script must be called with the version of IDEA to build'
    echo 'example: ./build.sh 2016.3.4'
    exit 1
fi

./fetchIdea.sh "$1"
./fetchGrammarKit.sh

#call the build script along with the path to a code package
#specific to the intellij version which we build against
ant -f build.xml -Dversion="$1"

rm -rf idea-IU
