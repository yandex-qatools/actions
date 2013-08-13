package ru.yandex.qatools.actions.mockfactory;

import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import ru.yandex.qatools.htmlelements.element.Radio;
import ru.yandex.qatools.htmlelements.element.Select;

import java.util.ArrayList;
import java.util.List;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

/**
 * @author Alexander Tolmachev starlight@yandex-team.ru
 *         Date: 05.09.12
 */
public class MockFactory {
    public static WebDriver mockDriver() {
        WebDriver driverMock = mock(WebDriver.class);
        WebDriver.Navigation navigationMock = mock(WebDriver.Navigation.class);
        Alert alert = mock(Alert.class);
        WebDriver.TargetLocator targetLocator = mock(WebDriver.TargetLocator.class);
        when(driverMock.switchTo()).thenReturn(targetLocator);
        when(driverMock.switchTo().alert()).thenReturn(alert);
        when(driverMock.navigate()).thenReturn(navigationMock);
        return driverMock;
    }

    private static WebElement mockElementToLocate(WebDriver driverMock, String xpath) {
        WebElement elementMock = mock(WebElement.class);
        when(driverMock.findElement(By.xpath(xpath))).thenReturn(elementMock);
        return elementMock;
    }

    public static WebElement mockButton(WebDriver driverMock, String xpath) {
        return mockElementToLocate(driverMock, xpath);
    }

    public static WebElement mockTextInput(WebDriver driverMock, String xpath) {
        return mockElementToLocate(driverMock, xpath);
    }

    public static WebElement mockNotSelectedCheckbox(WebDriver driverMock, String xpath) {
        WebElement checkboxMock = mockElementToLocate(driverMock, xpath);
        when(checkboxMock.isSelected()).thenReturn(false);
        return checkboxMock;
    }

    public static WebElement mockSelectedCheckbox(WebDriver driverMock, String xpath) {
        WebElement checkboxMock = mockElementToLocate(driverMock, xpath);
        when(checkboxMock.isSelected()).thenReturn(true);
        return checkboxMock;
    }

    public static Select mockSelect(WebDriver driverMock, String xpath, int optionsNumber) {
        WebElement selectMock = mockElementToLocate(driverMock, xpath);
        when(selectMock.getTagName()).thenReturn("select");

        List<WebElement> options = new ArrayList<WebElement>(optionsNumber);
        for (int i = 0; i < optionsNumber; i++) {
            WebElement optionMock = mock(WebElement.class);
            when(optionMock.getAttribute("index")).thenReturn(Integer.toString(i));
            options.add(optionMock);
        }
        when(selectMock.findElements(By.tagName("option"))).thenReturn(options);

        return new Select(selectMock);
    }

    public static Radio mockRadio(WebDriver driverMock, String radioName, int buttonsNumber) {
        String radioXpath = String.format("//input[@type='radio' and @name='%s']", radioName);
        WebElement radioButtonMock = mockElementToLocate(driverMock, radioXpath);
        when(radioButtonMock.getAttribute("name")).thenReturn(radioName);

        List<WebElement> radioButtons = new ArrayList<WebElement>(buttonsNumber);
        radioButtons.add(radioButtonMock);
        for (int i = 1; i < buttonsNumber; i++) {
            WebElement buttonMock = mock(WebElement.class);
            radioButtons.add(buttonMock);
        }
        String radioButtonsXpath = String.format("self::* | following::input[@type = 'radio' and @name = '%s'] | " +
                "preceding::input[@type = 'radio' and @name = '%s']", radioName, radioName);

        when(radioButtonMock.findElements(By.xpath(radioButtonsXpath))).thenReturn(radioButtons);

        return new Radio(radioButtonMock);
    }
}