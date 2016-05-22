#Sainsbury's Software Engineering Test Implementation - Products Scrapper

[![Build Status](https://travis-ci.org/sgbelim/ProductScrapper.svg?branch=master)](https://travis-ci.org/sgbelim/ProductScrapper)

[![Coverage Status](https://coveralls.io/repos/github/sgbelim/ProductScrapper/badge.svg?branch=master)](https://coveralls.io/github/sgbelim/ProductScrapper?branch=master)

[![Join the chat at https://gitter.im/sgbelim/ProductScrapper](https://badges.gitter.im/sgbelim/ProductScrapper.svg)](https://gitter.im/sgbelim/ProductScrapper?utm_source=badge&utm_medium=badge&utm_campaign=pr-badge&utm_content=badge)


### Introduction

Products Scrapper is a console based application that scrapes the Sainsbury’s grocery site for Ripe Fruits page
(http://hiring-tests.s3-website-eu-west-1.amazonaws.com/2015_Developer_Scrape/5_products.html)
and prints JSON as shown below :

```javascript
{
  "results" : [ {
    "title" : "Sainsbury's Apricot Ripe & Ready x5",
    "description" : "Apricots",
    "size" : "38.27Kb",
    "unit_price" : 3.50
  }, {
    "title" : "Sainsbury's Avocado Ripe & Ready XL Loose 300g",
    "description" : "Avocados",
    "size" : "38.67Kb",
    "unit_price" : 1.50
  }, {
    "title" : "Sainsbury's Avocado, Ripe & Ready x2",
    "description" : "Avocados",
    "size" : "43.44Kb",
    "unit_price" : 1.80
  }, {
    "title" : "Sainsbury's Avocados, Ripe & Ready x4",
    "description" : "Avocados",
    "size" : "38.68Kb",
    "unit_price" : 3.20
  }, {
    "title" : "Sainsbury's Conference Pears, Ripe & Ready x4 (minimum)",
    "description" : "Conference",
    "size" : "38.54Kb",
    "unit_price" : 1.50
  }, {
    "title" : "Sainsbury's Golden Kiwi x4",
    "description" : "Gold Kiwi",
    "size" : "38.56Kb",
    "unit_price" : 1.80
  }, {
    "title" : "Sainsbury's Kiwi Fruit, Ripe & Ready x4",
    "description" : "Kiwi",
    "size" : "38.98Kb",
    "unit_price" : 1.80
  } ],
  "total" : 15.10
}
```

###What is required to use this code
* JAVA_HOME environment variable set to a JDK8
* Eclipse or IntelliJ

### Usage

This application uses a local instance of [Gradle](http://gradle.org/).

### Running the application with JAVA

* git clone https://github.com/sgbelim/ProductScrapper.git
* gradlew fatJar
* cd [PROJECT_ROOT]/build/libs folder
* java -jar sainsbury-productscrapper-all-1.0.jar

### Running the application with [Gradle](http://gradle.org/)

* git clone https://github.com/sgbelim/ProductScrapper.git
* cd [PROJECT_ROOT] folder
* gradlew run (Windows) |  gradle run (Linux)

### CheckStyle

* cd [PROJECT_ROOT] folder
* gradlew checkstyle (Windows) |  gradle checkstyle (Linux)

### Code Coverage

* cd [PROJECT_ROOT] folder
* gradlew clean build jacocoTestReport (Windows) |  gradle clean build jacocoTestReport (Linux)

### Tests

* cd [PROJECT_ROOT] folder
* gradlew clean test (Windows) | gradle clean test (Linux)

### View Reports :

* To view the test report at [PROJECT_ROOT]/build/reports/tests/index.html
* To view the coverage report at [PROJECT_ROOT]/build/jacocoHtml/index.html



