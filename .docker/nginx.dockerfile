# Use environment of debian nginx
FROM nginx:1.15.3

# Pass configuration
COPY ./.docker/conf/nginx/nginx.conf /etc/nginx/nginx-junk-t.conf

# Start web server
CMD ["nginx", "-c", "/etc/nginx/nginx-junk-t.conf", "-g", "daemon off;"]