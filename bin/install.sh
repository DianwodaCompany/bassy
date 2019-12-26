#!/usr/bin/env bash

# bassy's target dir
BASSY_TARGET_DIR=../target/bassy
BASSY_VIEW_BUNDLE=../bassy-view/dist/bundle.js
# exit shell with err_code
# $1 : err_code
# $2 : err_msg
exit_on_err()
{
    [[ ! -z "${2}" ]] && echo "${2}" 1>&2
    exit ${1}
}

# build bassy view
cd ../bassy-view && npm run build || exit_on_err 1 "bassy view build failed."

#if [ ! -d ${BASSY_VIEW_BUNDLE} ]; then
#      exit_on_err 1 "build bassy view failed !"
#fi
# copy bundle.js to bassy web
cp ../bassy-view/dist/* ../bassy-web/src/main/resources/static/

# maven package the bassy
mvn clean package -Dmaven.test.skip=true -f ../pom.xml || exit_on_err 1 "package bassy failed."

# make target package
mkdir -p ${BASSY_TARGET_DIR}

# copy bassy-web.jar to target package
cp ../bassy-web/target/bassy-web-*-SNAPSHOT.jar ${BASSY_TARGET_DIR}/bassy-web.jar

# start application
java -jar ${BASSY_TARGET_DIR}/bassy-web.jar
