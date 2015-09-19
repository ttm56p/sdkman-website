#!/bin/bash
./gradlew clean generateSite
cd site/build/site
git init
git add --all
git commit -m 'Committed on CI.'
git remote add origin git@github.com:sdkman/sdkman.github.io.git
git push origin master
