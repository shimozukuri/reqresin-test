# reqresin-test

The project implements API autotests for the website reqres.in . POJO classes created for each entity, Allure descriptions implemented, and in case of a failure, a screenshot is uploaded to the report. Implemented retry tests in case of Flaky occurrence.

## Stack
- Java 17
- REST Assured
- JUnit 5
- Allure
- Lombock

## Deployment
Now you can run the tests locally using the `mvn clean test allure:serve` command.

As a result, you will receive an **Allure** report in your browser.