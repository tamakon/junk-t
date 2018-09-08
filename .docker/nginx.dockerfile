# Use environment of debian nginx
FROM nginx:1.15.3

# Pass configuration
COPY ./.docker/conf/nginx/nginx.conf /etc/nginx/conf.d/junk-t/

# Start web server
CMD ["nginx", "-c", "/etc/nginx/conf.d/junk-t/nginx.conf", "-g", "daemon off;"]