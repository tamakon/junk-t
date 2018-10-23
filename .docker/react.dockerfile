# Use environment of debian node8.12
FROM node:8.12.0-stretch

RUN cat /etc/passwd
# Create user
RUN useradd --uid 1001 --create-home --shell /bin/bash --password junk-t-passwd junk-t
USER junk-t

WORKDIR /home/junk-t/
COPY --chown=junk-t ./.docker/client /home/junk-t/.docker

WORKDIR /home/junk-t/client/
CMD chmod u+x ~/.docker/bin/*.sh && ~/.docker/bin/react-start.sh
