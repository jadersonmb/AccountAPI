# AccountAPI

[![N|Solid](https://cldup.com/dTxpPi9lDf.thumb.png)](https://nodesource.com/products/nsolid)

[![Build Status](https://travis-ci.org/joemccann/dillinger.svg?branch=master)](https://travis-ci.org/joemccann/dillinger)

API account excist the endpoints.

  - GET
  - POST
  - PUT
  - DELETE
  - DELETEALL/{UUIDS}
  
  # New Features!

  - Implements many validations.
  - Implements bussines roles.
  
  You can also:
  - Create the docker.file to basedados postgresql [X].
  - Create the CI with docker.
  
### Tech
  * [Java] - Backend with Java 11
  * [Docker] - 19.03.13
  * [PostgreSQL] - 1.10
  * [Maven] - 3.6.3
  * [H2] - 1.4.200
  * [MapStruct] - 1.4.0.Final

### Installation
```sh
*[Java](https://www.java.com/en/download/) v11+ to run.
*[Docker](https://docs.docker.com/docker-for-windows/install/) 19.03.13+ to run.
*[PostgreSQL](https://www.postgresql.org/download/) v1.10+ to run.
*[Maven](https://maven.apache.org/download.cgi?Preferred=ftp://apache.cs.utah.edu/apache.org/) v11+ to run.
*[Lombok](https://projectlombok.org/download) 1.18.12+ to run.
```

Install the dependencies and devDependencies and start the server.

## Dock

```sh
mvn clean install -X
```

```sh
docker build -t account .
```

```sh
docker-compose up
```

# AccountAPI Swagger-ui
```sh
http://localhost:8080/swagger-ui/index.html?configUrl=/v3/api-docs/swagger-config#/
```
