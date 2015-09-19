#!/bin/bash
./gradlew clean generateSite
cd site/build/site
git config user.email "build@example.org"
git config user.name "Codeship Build"
git init
git add --all
git commit -m 'Committed on CI.'
git remote add origin git@github.com:sdkman/sdkman.github.io.git
git push -f origin master
