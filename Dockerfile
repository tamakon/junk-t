# Use environment of debian python3.7
FROM python:3.7.0-stretch

# Install requred command
RUN pip install pipenv

# Create use for web application
RUN useradd --create-home --shell /bin/bash junk-t
USER junk-t

# Deploy web application
ENV DJANGO_SETTINGS_MODULE server.settings.localhost
COPY --chown=junk-t:junk-t ./server /home/junk-t/server
COPY --chown=junk-t:junk-t ./client /home/junk-t/client
WORKDIR /home/junk-t/server
RUN pipenv install
RUN pipenv run python manage.py migrate
CMD pipenv run uwsgi --http 0.0.0.0:8000 --module server.wsgi
EXPOSE 8000