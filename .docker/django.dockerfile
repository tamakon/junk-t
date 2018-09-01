# Use environment of debian python3.7
FROM python:3.7.0-stretch

# Install requred command
RUN pip install pipenv

# Deploy web application
RUN mkdir /junk-t
COPY ./server /junk-t/server
COPY ./client /junk-t/client
WORKDIR /junk-t/server
ENV DJANGO_SETTINGS_MODULE server.settings.localhost
RUN pipenv install
RUN pipenv run python manage.py migrate

# Start web application
CMD pipenv run uwsgi --socket :8001 --module server.wsgi
EXPOSE 8001