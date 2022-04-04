FROM mysql:8.0.27

MAINTAINER me

ENV MYSQL_ROOT_PASSWORD=Admin@123

ADD ./init.sql /docker-entrypoint-initdb.d

EXPOSE 3306