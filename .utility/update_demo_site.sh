#!/bin/bash
set -ev
if [ "$TRAVIS_JDK_VERSION" == "oraclejdk8" ] && [ "$TRAVIS_BRANCH" == "master" ]; then

if [[ -z "$GH_TOKEN" ]]; then
echo -e "GH_TOKEN is not set"
exit 1
fi

if [ ! -f $TRAVIS_BUILD_DIR/target/GmdAmCharts4Demo*.war ]; then
echo -e "pattern war file not found."
exit 1
fi

echo -e "Publishing pattern to gh-pages . . .\n"

git config --global user.email "travis@travis-ci.org"
git config --global user.name "travis-ci"

# clone the gh-pages branch.
cd $HOME
rm -rf gh-pages
git clone --quiet --branch=gh-pages https://$GH_TOKEN@github.com/GwtMaterialDesign/gmd-amcharts4-demo gh-pages > /dev/null
cd gh-pages

# remove the GmdAmCharts4Demo Snapshot directories from git.
if [[ -d ./ ]]; then
git rm -rf ./
fi

# copy the new GmdAmCharts4Demo the snapshot dir.
unzip -u $TRAVIS_BUILD_DIR/target/GmdAmCharts4Demo*.war -d ./

git add -f .
git commit -m "Auto-push pattern to gh-pages successful. (Travis build: $TRAVIS_BUILD_NUMBER)"
git push -fq origin gh-pages > /dev/null

echo -e "Published pattern to gh-pages.\n"

fi