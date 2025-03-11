[<img src="./images/logo.png" width="400" height="200"/>](./images/logo.png)

# NoSQL-DAO
[![Build And Sonar scan](https://github.com/eclipse-ecsp/nosql-dao/actions/workflows/maven-build.yml/badge.svg)](https://github.com/eclipse-ecsp/nosql-dao/actions/workflows/maven-build.yml)
[![License Compliance](https://github.com/eclipse-ecsp/nosql-dao/actions/workflows/license-compliance.yml/badge.svg)](https://github.com/eclipse-ecsp/nosql-dao/actions/workflows/license-compliance.yml)
[![Deployment](https://github.com/eclipse-ecsp/nosql-dao/actions/workflows/maven-deploy.yml/badge.svg)](https://github.com/eclipse-ecsp/nosql-dao/actions/workflows/maven-deploy.yml)

`nosql-dao` library is an enabler for NoSQL databases such as MongoDB and Azure CosmosDB. It allows to bootstrap the project with following functionalities - 

- Provides base DAO interface to perform MongoDB related queries.
- Provides base DAO interface to perform CosmosDB related queries.
- Centralizes all database-related code within dedicated DAO implementation classes.
- Provides implementations of create, read, update, delete (CRUD), and many other database operation.
- Provides a configurable way to fetch MongoDB and CosmosDB properties.
- Provides a way to extend custom interfaces for implementing custom Mongo query operations.
- Provides a configurable way to choose the NoSQL database to be used, between CosmosDB and MongoDB.

# Table of Contents
* [Getting Started](#getting-started)
* [Usage](#usage)
* [How to contribute](#how-to-contribute)
* [Built with Dependencies](#built-with-dependencies)
* [Code of Conduct](#code-of-conduct)
* [Authors](#authors)
* [Security Contact Information](#security-contact-information)
* [Support](#support)
* [Troubleshooting](#troubleshooting)
* [License](#license)
* [Announcements](#announcements)


## Getting Started

To build the project in the local working directory after the project has been cloned/forked, run:

```mvn clean install```

from the command line interface.


### Prerequisites

1. Maven
2. Java 17

### Installation

[How to set up maven](https://maven.apache.org/install.html)

[Install Java](https://stackoverflow.com/questions/52511778/how-to-install-openjdk-11-on-windows)

### Running the tests

```mvn test```

Or run a specific test

```mvn test -Dtest="TheFirstUnitTest"```

To run a method from within a test

```mvn test -Dtest="TheSecondUnitTest#whenTestCase2_thenPrintTest2_1"```

### Deployment

`nosql-dao` project serves as a library for the services. It is not meant to be deployed as a service in any cloud 
environment.

## Usage
Add the following dependency in the target project
```
<dependency>
  <groupId>org.eclipse.ecsp</groupId>
	<artifactId>nosql-dao</artifactId>
  <version>1.x.x</version>
</dependency>

```
#### Selecting the NoSQL Database

NoSQL-DAO library  provides support for integration with Azure CosmosDB as well as MongoDB. Integrating services can define the type of database they need to connect to, with current supported database being Mongo DB and Cosmos DB. Ignite DAO library supports RU, as well as vCore, database instance types for CosmosDB.
You can configure the name of NoSQL database service against the property - "no.sql.database.type". Supported values for no.sql.database.type are -
mongoDB
cosmosDB

```properties
no.sql.database.type=mongoDB or cosmosDB
```

Default connection would be attempted to mongo DB, if no database type defined.
If, MongoDB is set as database type, respective configuration for mongo DB need to be configured by the integrating service. Details for the same can be found in the sections below.

#### CosmosDB configuration

Following properties have been exposed for Azure CosmosDB configuration -

```properties
cosmos.db.connection.string=Complete connection string for vCore or RU instance of CosmosDB
cosmosdb.name=Name of the database
```


#### Fetching Mongo Property From Properties

The class `MongoPropsCondition` enables `@Configuration` class which allows to fetch mongodb properties when vault is 
disabled.


#### Extending Custom interfaces

To represent a custom interface for implementing custom Mongo query operations, the services needs to implement 
`CustomDao` interface.

Example:

```java
public interface CustomDao<K, E extends IgniteEntity> extends IgniteBaseDAO<K, E> {}
```

#### Query Translator Morphia Implementation

To Perform translation of query `QueryTranslatorMorphiaImpl` class in implemented which consists translate method 
for performing on basis of ignite query and collectionName.

Example:

```java
public class QueryTranslatorMorphiaImpl<E extends IgniteEntity> implements QueryTranslator<Query<E>> {
    
    public Query<E> translate(IgniteQuery igniteQuery, Optional<String> collectionName) {}
}
```
#### Ignite Base DAO Implementation

To Perform various basic database operations `IgniteBaseDAOMongoImpl` class is implemented which contains
below methods:

|      Method Names       | Purpose                                          |
|:-----------------------:|:-------------------------------------------------|
|         findAll         | To find all entities.                            |
|          save           | To save an entity.                               |
|         saveAll         | To save a list of entities.                      |
|        findById         | To find an entity by primary key.                |
|        findByIds        | To find list of entities by primary keys.        |
|         upsert          | To insert and update the value in any operation. |
|          find           | To find an entity using IgniteQuery.             |
|   findWithPagingInfo    | To find with pagination.                         |
|       deleteById        | To delete an entity by primary key.              |
|       deleteByIds       | To delete multiple entities by primary keys.     |
|      deleteByQuery      | To delete entities by Query.                     |
|      countByQuery       | To find the count of entities by Query.          |
|         update          | To update an existing entity.                    |
|      getAndUpdate       | To fetch and update the same entity.             |
|        updateAll        | To update multiple entities.                     |
|        deleteAll        | To delete all the entities.                      |
|        removeAll        | To delete entities by Query.                     |
|         delete          | To delete and entity.                            |
|        distinct         | To find distinct entities.                       |
|      streamFindAll      | To fetch the reactive stream of all entries.     |
|       streamFind        | To fetch the reactive stream using Query.        |
|        countAll         | To get the count of all existing entries.        |
|    collectionExists     | To check if a collection exists.                 |

#### Ignite Query Creation

To perform any query operation in mongo you need to create `igniteQuery`.

Example:

```java
@Repository
public class CustomDaoImpl extends IgniteBaseDAOMongoImpl<String, CollectionName>
implements CustomDao {

    @Override
    public Optional<List<CollectionName>> findById(String id) {
        IgniteCriteria idCriteria = new IgniteCriteria("id", "=", id);
        IgniteCriteriaGroup criteriaGroup = new IgniteCriteriaGroup();
        criteriaGroup.and(idCriteria);
        IgniteQuery query = new IgniteQuery(criteriaGroup);
        return Optional.ofNullable(super.find(query));
    }
    
}
```

#### Updates Translator Morphia Implementation

To Perform translation of query `UpdatesTranslatorMorphiaImpl` class in implemented which consists translate method
for performing on basis of updates and collectionName.

```java
public class UpdatesTranslatorMorphiaImpl<E extends IgniteEntity> implements UpdatesTranslator<List<UpdateOperator>> {

    public List<UpdateOperator> translate(Updates updates , Optional<String> collectionName) {}
}
```

## Built With Dependencies

|                                                  Dependency                                                   | Purpose                                                                                                                                                 |
|:-------------------------------------------------------------------------------------------------------------:|:--------------------------------------------------------------------------------------------------------------------------------------------------------|
|                          [Morphia Core](https://morphia.dev/morphia/2.1/index.html)                           | ODM for MongoDB                                                                                                                                         |
|                [Mongo DB Driver Sync](https://www.mongodb.com/docs/drivers/java/sync/current/)                | MongoDB Java Driver                                                                                                                                     |
|                              [Mongo DB Driver legacy](https://maven.apache.org/)                              | MongoDB Support for the Legacy API                                                                                                                      |
|                                      [Junit](https://junit.org/junit5/)                                       | Testing framework                                                                                                                                       |
|                                     [Mockito](https://site.mockito.org/)                                      | Test Mocking framework                                                                                                                                  |
| [Reflections](https://www.javadoc.io/doc/org.reflections/reflections/0.9.10/org/reflections/Reflections.html) | Scans the classpath, indexes the metadata, allows to query it on runtime and may save and collect that information for many modules within the project. |
|                         [Ignite utils](https://github.com/HARMANInt/ics/ignite-utils)                         | Centralized logging, Health checks and Diagnostic data reporting library.                                                                               |
|                             [Vault Apis](https://github.com/HARMANInt/ics/vault)                              | Secrets Storage Provider.                                                                                                                               |
|                                [Reactor Core](https://projectreactor.io/docs)                                 | Reactive Programming Framework.                                                                                                                         |
|  [Flapdoodle Embbed Mongo](https://github.com/flapdoodle-oss/de.flapdoodle.embed.mongo/blob/main/README.md)   | Platform for running mongodb in unit tests.                                                                                                             |
|                                 [Apache Commons](https://commons.apache.org/)                                 | Library focused on algorithms working on strings.                                                                                                       |

## How to contribute

Please read [CONTRIBUTING.md](./CONTRIBUTING.md) for details on our contribution guidelines, and the process for submitting pull requests to us.

## Code of Conduct

Please read [CODE_OF_CONDUCT.md](./CODE_OF_CONDUCT.md) for details on our code of conduct.

## Authors

<!-- ALL-CONTRIBUTORS-LIST:START - Do not remove or modify this section -->
<!-- prettier-ignore-start -->
<!-- markdownlint-disable -->
<table>
  <tbody>
    <tr>
	  <td align="center" valign="top" width="14.28%"><a href="https://github.com/kaushalaroraharman"><img src="https://github.com/kaushalaroraharman.png" width="100px;" alt="Kaushal Arora"/><br /><sub><b>Kaushal Arora</b></sub></a><br /><a href="https://github.com/all-contributors/all-contributors/commits?author=kaushalaroraharman" title="Code and Documentation">📖</a> <a href="https://github.com/all-contributors/all-contributors/pulls?q=is%3Apr+reviewed-by%3Akaushalaroraharman" title="Reviewed Pull Requests">👀</a></td>
    </tr>
  </tbody>
</table>

See also the list of [contributors](https://github.com/eclipse-ecsp/nosql-dao/graphs/contributors) who participated in this project.

## Security Contact Information

Please read [SECURITY.md](./SECURITY.md) to raise any security related issues.

## Support
Please write to us at [csp@harman.com](mailto:csp@harman.com)

## Troubleshooting

If, CosmosDB is set as database type, there could be a possible issue where spring tries to autoconfigure a mongo client with localhost, depending upon the spring version. This could lead to below error being observed in the application -
Updating cluster description to {type=UNKNOWN, servers=[{address=localhost:27017, type=UNKNOWN, state=CONNECTING, exception={com.mongodb.MongoSocketOpenException: Exception opening socket}, caused by {java.net.ConnectException: Connection refused}}]
To avoid this, please exclude MongoAutoConfiguration class from the spring boot application launcher class.

Example -
Excluding MongoAutoConfiguration

```java
@SpringBootApplication(scanBasePackages = {"org.eclipse.ecsp"}, exclude={MongoAutoConfiguration.class})
public class ApiGatewayTestApplication {

    public static void main(String[] args) {
        SpringApplication.run(ApiGatewayTestApplication.class, args);
    }
}
```
Or,
Excluding MongoAutoConfiguration

```java
@EnableAutoConfiguration(exclude={MongoAutoConfiguration.class})
public class Application {
// ...
}
```

Please read [CONTRIBUTING.md](./CONTRIBUTING.md) for details on how to raise an issue and submit a pull request to us.

## License

This project is licensed under the Apache-2.0 License - see the [LICENSE](./LICENSE) file for details.

## Announcements
All updates to this library are documented in our [Release Notes](./release_notes.txt) and [releases](https://github.com/eclipse-ecsp/nosql-dao/releases).
For the versions available, see the [tags on this repository](https://github.com/eclipse-ecsp/nosql-dao/tags).