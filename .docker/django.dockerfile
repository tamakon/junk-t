# Use environment of debian python3.7
FROM python:3.7.0-stretch

# Install requred command
RUN pip install pipenv

# Create user
RUN useradd --create-home --shell /bin/bash --password junk-t-passwd junk-t
USER junk-t

# Deploy web application
COPY --chown=junk-t ./server /home/junk-t/server
COPY --chown=junk-t ./client /home/junk-t/client
COPY --chown=junk-t ./.docker/conf/uwsgi/ /home/junk-t/server/uwsgi
WORKDIR /home/junk-t/server
ENV DJANGO_SETTINGS_MODULE server.settings.localhost
RUN pipenv install
RUN pipenv run python manage.py migrate

# Start web application
CMD pipenv run uwsgi uwsgi/uwsgi.ini