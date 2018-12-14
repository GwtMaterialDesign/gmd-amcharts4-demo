#!/bin/bash
set -ev
echo -e "Demo deploy started"

if [ "$TRAVIS_JDK_VERSION" == "oraclejdk8" ] && [ "$TRAVIS_PULL_REQUEST" == "false" ] && [ "$TRAVIS_BRANCH" == "master" ]; then

if [[ -z "$GH_TOKEN" ]]; then
echo -e "GH_TOKEN is not set"
exit 1
fi

if [ ! -f $TRAVIS_BUILD_DIR/target/GmdAm4ChartsDemo-*.war ]; then
echo -e "pattern war file not found."
exit 1
fi

echo -e "Publishing pattern to gh-pages . . .\n"

git config --global user.email "travis@travis-ci.org"
git config --global user.name "travis-ci"

# clone the gh-pages branch.
cd $HOME
rm -rf gh-pages
git clone --quiet --branch=gh-pages https://$GH_TOKEN@github.com/GwtMaterialDesign/gmd-am4charts-demo gh-pages > /dev/null
cd gh-pages

# remove the GwtMaterialDemo directories from git.
if [[ -d ./GmdAm4ChartsDemo ]]; then
git rm -rf ./GmdAm4ChartsDemo
fi
if [[ -f ./index.html ]]; then
git rm -rf ./index.html
fi
if [[ -d ./META-INF ]]; then
git rm -rf ./META-INF
fi
if [[ -d ./WEB-INF ]]; then
git rm -rf ./WEB-INF
fi

# copy the new GWTMaterialPattern the snapshot dir.
unzip -u $TRAVIS_BUILD_DIR/target/GmdAm4ChartsDemo-*.war -d ./
rm -rf ./META-INF
rm -rf ./WEB-INF

git add -f .
git commit -m "Auto-push pattern to gh-pages successful. (Travis build: $TRAVIS_BUILD_NUMBER)"
git push -fq origin gh-pages > /dev/null

echo -e "Published pattern to gh-pages.\n"

fi