FROM nginx:latest
RUN apt-get update && apt-get install unzip && rm -rf /tmp/*
ADD site/build/distributions/sdkman-website.zip /tmp/
RUN rm -rf /usr/share/nginx/html && unzip /tmp/sdkman-website.zip -d /usr/share/nginx/html/
expose 80
ENTRYPOINT /usr/sbin/nginx -g "daemon off;"
