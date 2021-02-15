## Pre-requisites
- Make sure the latest versions of [Java](https://www.java.com/en/download) and [Maven](https://maven.apache.org/download.cgi) are installed
- Make sure you have [Homebrew](https://brew.sh) (for Mac) or [Scoop](https://scoop.sh) (for Windows) command-line installer
- Install [Allure](https://docs.qameta.io/allure/) framework

## Running tests
To run tests from command line use `mvn clean test` command

## Reporting
- Generate allure report (will be saved in _/allure-report_ folder): `allure generate --clean`
- Generate and immediately open report in default browser: `allure serve`