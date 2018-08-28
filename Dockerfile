# Use environment of debian python3.7
FROM python:3.7.0-stretch

# Install requred command
RUN apt-get update
RUN apt-get install --assume-yes nginx
RUN pip install pipenv

# Deploy web application
RUN mkdir /junk-t
COPY ./server /junk-t/server
COPY ./client /junk-t/client
WORKDIR /junk-t/server
ENV DJANGO_SETTINGS_MODULE server.settings.localhost
RUN pipenv install
RUN pipenv run python manage.py migrate
RUN pipenv run python manage.py migrate
EXPOSE 8000

# Start web server and web application
CMD nginx -c /junk-t/server/nginx.conf; \
    pipenv run uwsgi --socket :8001 --module server.wsgi