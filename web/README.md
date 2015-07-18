# Web Module

A webserver can be started by using the next command:

`$ mvn jetty:run`

## Documentation

Execute the next command:

`$ mvn site`

Open the next files with a browser:

* `target/site/pmd.html` (it will exists if there are violations detected by the static code analyzer)
* `target/site/cpd.html` (it will exists if there is source code copied and pasted)
* `target/site/jacoco/index.html` to review the code coverage.

## Brief Cheatsheet:

`$ curl -X POST -d url='http://myurl.com' localhost:8080/shorten` -v


