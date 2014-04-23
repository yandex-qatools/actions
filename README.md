Actions
=======

Library for user actions in the browser.
Allows you to create various actions to implement them through Selemium WebDriver,
combine them into test scenarios and serialize test scripts to XML.

Maven Dependencies
------------------
To use library add the following dependencies:

```xml
    <dependency>
        <groupId>ru.yandex.qatools.actions</groupId>
        <artifactId>actions-builder</artifactId>
        <version>2.2</version>
    </dependency>
```

Getting Started
---------------
Creating a sequence of actions:

```java
    Actions actions = new Actions();
    actions.loadPage("http://www.yandex.ru").
            typeText("//input[@class='b-morda-search__input']", "Яндекс").
            click("//input[@class='b-form-button__input']");
```

Execute the scenario:

```java
    WebDriver driver = BrowserFactory.webdriver();
    actions.build().perform(driver);
```
    
Record in XML:

```java
    Actions actions = new Actions();
    actions.loadPage("http://www.yandex.ru").
            typeText("//input[@class='b-morda-search__input']", "Яндекс").
            click("//input[@class='b-form-button__input']");
    actions.write("search-request-scenario.xml");
```

Reading serialized scenarios from XML:

```java
    Actions actions = new Actions();
    actions.read("search-request-scenario.xml").
            read("open-advanced-search-scenario.xml");
    actions.build().perform(driver);
```
