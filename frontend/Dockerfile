FROM httpd:2.4.59

WORKDIR /app

COPY ./dist/ /usr/local/apache2/htdocs/

EXPOSE 80

CMD ["httpd-foreground"]