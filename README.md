# Swagger-publish

This project helps to generate documentation by including **Swagger annotations** and **maven-plugin** into the project and can also be used to publish the documentation to **Confluence** using the required configurations.  


# Functionality

* Generate documentation to local machine
* Publish documentation to confluence
* Generates documentation during build phase

# Suppourt

* Swagger Spec 2.0
* Swagger Core Version 1.5.1-M2
* JAX-RS

# How to use

This project can be included as maven-plugin with minimum configuration required in the plugins block of **POM.xml**.

```xml
<plugin>
    <groupId>com.flipkart.flap.commons</groupId>
    <artifactId>swagger-publish-maven-plugin</artifactId>
    <version>1.0</version>
    <configuration>
      <!-- Required Parameters -->
        <!-- Location of resources of API -->
        <locations>com.example.resource</locations>
        <apiVersion>version of API</apiVersion>
        <apiName>Name of API</apiName>
        <!-- Optional Parameters -->
        <!-- Details about template -->
        <template>
            <templateName> Name of template </templateName>
            <templateLocation> Location of Template </templateLocation>
        </template>
        <!-- Details about confluence server -->
        <confluence>
            <spaceKey>FT</spaceKey>
            <pageId>1572923</pageId>
            <userName>admin</userName>
            <password>*******</password>
            <BASE_URL>URL for Confluence Server</BASE_URL>
        </confluence>
    </configuration>
    <executions>
        <execution>
            <!-- Phase for compiler to run -->
            <phase>compile</phase>
            <!-- Goals to execute (publish, generate) -->
            <goals>
                <goal>publish</goal>
            </goals>
        </execution>
    </executions>
</plugin>

```

**Dependency to include Swagger Core**

```xml
<dependency>
    <groupId>com.wordnik</groupId>
    <artifactId>swagger-core</artifactId>
    <scope>compile</scope>
    <version>1.5.1-M2</version>
</dependency>
```

**Description of Goals**

* generate - Generates documentation as a html file [Document.html] on local machine.
* publish - Generates documentation and publishes it on confluence.
