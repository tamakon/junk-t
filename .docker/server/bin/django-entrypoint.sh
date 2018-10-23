#!/bin/bash

echo "Install python packages"
pipenv install

echo "Collect static files"
pipenv run python manage.py collectstatic --noinput

echo "Apply database migrations"
pipenv run python manage.py migrate

echo "Apply database seed"
pipenv run python manage.py loaddata superuser.json

echo "Starting server"
pipenv run uwsgi ~/.docker/conf/uwsgi/uwsgi.ini