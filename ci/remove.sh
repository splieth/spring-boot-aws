#!/usr/bin/env bash

set -e

SCRIPT_DIR=$(dirname ${0})

if [ -z ${STACK_NAME} ]; then
  echo "STACK_NAME is empty, exiting..."
  exit 1
fi

REGION=${REGION:-'eu-central-1'}

bundle check || bundle install --quiet

bundle exec autostacker24 delete --stack ${STACK_NAME} \
                                 --region ${REGION}
