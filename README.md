#Sainsbury's Software Engineering Test Implementation - Products Scrapper

### Introduction

Products Scrapper is a console based application that scrapes the Sainsbury’s grocery site for Ripe Fruits page
(http://hiring-tests.s3-website-eu-west-1.amazonaws.com/2015_Developer_Scrape/5_products.html)
and prints out a JSON array in below format :

```javascript
{
    "results":[

            {
            "title":"Sainsbury's Avocado, Ripe & Ready x2",
            "size": "90.6kb",
            "unit_price": 1.80,
            "description": "Great to eat now - refrigerate at home 1 of 5 a day 1 avocado counts as 1 of your 5..."
            },
            {
            "title":"Sainsbury's Avocado, Ripe & Ready x4",
            "size": "87kb",
            "unit_price": 2.00,
            "description": "Great to eat now - refrigerate at home 1 of 5 a day 1 avocado counts as 1 of your 5..."
            }
        ],
    "total": 3.80
}
```

###What is required to use this code
* JAVA_HOME environment variable set to a JDK8
* Eclipse or IntelliJ

### Usage

This application uses a local instance of [Gradle](http://gradle.org/).

### Running the application with JAVA

* git clone https://github.com/salimgbelim/ProductScrapper.git
* gradlew fatJar
* cd [PROJECT_ROOT]/build/libs folder
* java -jar sainsbury-productscrapper-all-1.0.jar

### Running the application with [Gradle](http://gradle.org/)

* git clone https://github.com/salimgbelim/ProductScrapper.git
* cd [PROJECT_ROOT] folder
* gradlew run

### CheckStyle

* cd [PROJECT_ROOT] folder
* gradlew checkstyle

### Code Coverage

* cd [PROJECT_ROOT] folder
* gradlew clean build jacocoTestReport

### Tests

* cd [PROJECT_ROOT] folder
* gradlew clean test

### View Reports :

* To view the test report at [PROJECT_ROOT]/build/reports/tests/index.html
* To view the coverage report at [PROJECT_ROOT]/build/jacocoHtml/index.html



