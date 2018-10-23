# Use environment of debian python3.7
FROM python:3.7.0-stretch

# Install requred command
RUN pip install pipenv

# Create user
RUN useradd --uid 1000 --create-home --shell /bin/bash --password junk-t-passwd junk-t
USER junk-t

WORKDIR /home/junk-t/
RUN mkdir static
COPY --chown=junk-t ./.docker/server /home/junk-t/.docker
ENV DJANGO_SETTINGS_MODULE server.settings.localhost-docker

# Start web application after db server has started
WORKDIR /home/junk-t/server/
CMD chmod u+x ~/.docker/bin/*.sh && \
    ~/.docker/bin/wait-for-it.sh mysql:3306 -- ~/.docker/bin/django-entrypoint.sh