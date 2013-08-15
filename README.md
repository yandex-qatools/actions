Actions
=======

Библиотека для работы с пользовательскими действиями в браузере. Позволяет создавать различные действия, 
выполнять их через Selemium WebDriver, объединять их в тестовые сценарии, а также сериализовать тестовые сценарии в XML.

Maven Dependencies
------------------
Для использования библиотеки требуются следующие зависимости:

```xml
    <dependency>
        <groupId>ru.yandex.qatools.actions</groupId>
        <artifactId>actions-builder</artifactId>
        <version>1.2</version>
    </dependency>
```

Getting Started
---------------
Создание последовательности действий:

```java
    Actions actions = new Actions();
    actions.loadPage("http://www.yandex.ru").
            typeText("//input[@class='b-morda-search__input']", "Яндекс").
            click("//input[@class='b-form-button__input']");
```

Выполнение созданного сценария:

```java
    WebDriver driver = BrowserFactory.webdriver();
    actions.build().perform(driver);
```
    
Запись в XML:

```java
    Actions actions = new Actions();
    actions.loadPage("http://www.yandex.ru").
            typeText("//input[@class='b-morda-search__input']", "Яндекс").
            click("//input[@class='b-form-button__input']");
    actions.write("search-request-scenario.xml");
```

Чтение сериализованных сценариев из XML:

```java
    Actions actions = new Actions();
    actions.read("search-request-scenario.xml").
            read("open-advanced-search-scenario.xml");
    actions.build().perform(driver);
```
