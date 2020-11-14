FROM mongo:latest

ENV MONGO_INITDB_DATABASE ${mongoDatabase}
ENV MONGO_INITDB_ROOT_USERNAME ${mongoUsername}
ENV MONGO_INITDB_ROOT_PASSWORD ${mongoPassword}

ADD ./mongo-init.js /docker-entrypoint-initdb.d/
