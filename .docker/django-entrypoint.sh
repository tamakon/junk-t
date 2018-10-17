#!/bin/bash

echo "Collect static files"
pipenv run python manage.py collectstatic --noinput

echo "Apply database migrations"
pipenv run python manage.py migrate

echo "Starting server"
pipenv run uwsgi uwsgi/uwsgi.ini