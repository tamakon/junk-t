# Use environment of debian python3.7
FROM python:3.7.0-stretch

# Install requred command
RUN pip install pipenv

# Create user
RUN useradd --create-home --shell /bin/bash --password junk-t-passwd junk-t
USER junk-t

# Make static directory for volume
RUN mkdir -p /home/junk-t/static

# Deploy web application
COPY --chown=junk-t ./server /home/junk-t/server
COPY --chown=junk-t ./client /home/junk-t/client
COPY --chown=junk-t ./.docker/conf/uwsgi/ /home/junk-t/server/uwsgi
COPY --chown=junk-t ./.docker/django-entrypoint.sh /home/junk-t/server/
COPY --chown=junk-t ./.docker/wait-for-it.sh /home/junk-t/server/
WORKDIR /home/junk-t/server
RUN chmod u+x ./django-entrypoint.sh
RUN chmod u+x ./wait-for-it.sh
ENV DJANGO_SETTINGS_MODULE server.settings.localhost-docker
RUN pipenv install

# Start web application after db server has started
CMD ./wait-for-it.sh mysql:3306 -- ./django-entrypoint.sh