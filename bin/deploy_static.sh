#!/bin/bash
./gradlew clean generateSite --quiet
cd site/build/site
git config --global user.email "build@example.org"
git config --global user.name "Codeship Build"
git init
git add --all
git commit -m 'Committed on CI.'
git remote add origin git@github.com:sdkman/sdkman.github.io.git
git push -f origin master
