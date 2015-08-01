# Web Module

A webserver can be started by using the next command:

`$ mvn jetty:run`

## Documentation

Execute the next command:

`$ mvn package site`

Open the next files with a browser:

* `target/site/pmd.html` (it will exists if there are violations detected by the static code analyzer)
* `target/site/cpd.html` (it will exists if there is source code copied and pasted)
* `target/site/jacoco/index.html` to review the code coverage.

## REST API:

* Shorting the url `http://raulexposito.com`

`$ curl -X POST -d url='http://raulexposito.com' localhost:8080/shortener -v`

* Using the short URL:

`$ curl localhost:8080/9cc810cd -v`

* Gathering the amount of visits for the short URL:

`$ curl localhost:8080/amount/9cc810cd -v`

* Gathering the info about the visits for the short URL:

`$ curl localhost:8080/visits/9cc810cd -v`

* Gathering the amount of visits:

`$ curl localhost:8080/amount/visits -v`

* Gathering the amount of hashes:

`$ curl localhost:8080/amount/hashes -v`

* Gathering the amount of visits per short URLs in a JSON notation:

`$ curl localhost:8080/amount -v`
