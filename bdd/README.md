# BDD Module

To execute those tests the application must be running. Please, use two terminals as follows:

### Terminal 1:
```
$ cd <module web directory>
$ mvn jetty:run
```

### Terminal 2:
```
$ cd <module bdd directory>
$ mvn test
```

Please, notice this module is not a child of the main `yaus` project (of type `pom`) on purpose. However,
this module uses the main `yaus` project (of type `pom`) as a parent.