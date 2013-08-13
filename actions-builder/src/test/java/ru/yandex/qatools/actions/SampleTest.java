package ru.yandex.qatools.actions;

import org.junit.Before;
import org.junit.Test;
import org.mockito.InOrder;
import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import ru.yandex.qatools.actions.beans.FindBy;

import java.io.File;
import java.io.FileNotFoundException;

import static org.mockito.Mockito.*;

/**
 * @author Alexander Tolmachev starlight@yandex-team.ru
 *         Date: 05.09.12
 */
public class SampleTest {
    private static final String PAGE_URL = "http://www.yandex.ru";
    private static final String SEARCH_INPUT_XPATH = "//input[@class='b-form-input__input']";
    private static final String SEARCH_BUTTON_XPATH = "//input[@class='b-form-button__input']";
    private static final String TEST_REQUEST = "Яндекс";

    private static WebDriver driver;
    private static File actionsFile = new File("search-request-scenario.xml");
    private static WebDriver.TargetLocator targetLocatorMock;
    private static InOrder inOrder;

    @Before
    public void createMockObject() {
        driver = mock(WebDriver.class);
        WebElement elementSearchInputMock = mock(WebElement.class);
        WebElement elementSearchButtonMock = mock(WebElement.class);
        Alert alertMock = mock(Alert.class);
        WebDriver.TargetLocator targetLocatorMock = mock(WebDriver.TargetLocator.class);
        inOrder = inOrder(driver, elementSearchInputMock, elementSearchButtonMock, alertMock);

        when(driver.switchTo()).thenReturn(targetLocatorMock);
        when(driver.switchTo().alert()).thenReturn(alertMock);
        when(driver.findElement(By.xpath(SEARCH_INPUT_XPATH))).thenReturn(elementSearchInputMock);
        when(driver.findElement(By.xpath(SEARCH_BUTTON_XPATH))).thenReturn(elementSearchButtonMock);
    }

    @Test
    public void createAndPerformScenarioTest() {
        Actions actions = new Actions();
        actions.loadPage(PAGE_URL).
                typeText(FindBy.xpath(SEARCH_INPUT_XPATH), TEST_REQUEST).
                click(FindBy.xpath(SEARCH_BUTTON_XPATH)).
                alertAccept();
        actions.build().perform(driver);

        inOrder.verify(driver).get(PAGE_URL);
        inOrder.verify(driver.findElement(By.xpath(SEARCH_INPUT_XPATH))).sendKeys(TEST_REQUEST);
        inOrder.verify(driver.findElement(By.xpath(SEARCH_BUTTON_XPATH))).click();
        inOrder.verify(driver.switchTo().alert()).accept();
    }

    @Test
    public void createAndSerializeScenarioTest() {
        Actions actions = new Actions();
        actions.loadPage(PAGE_URL).
                typeText(FindBy.xpath(SEARCH_INPUT_XPATH), TEST_REQUEST).
                click(FindBy.xpath(SEARCH_BUTTON_XPATH)).
                alertAccept();
        actions.write(actionsFile.getPath());
    }

    @Test
    public void readAndPerformScenarioTest() throws FileNotFoundException {
        Actions actions = new Actions();
        actions.read(actionsFile.getPath()).build().perform(driver);

        inOrder.verify(driver).get(PAGE_URL);
        inOrder.verify(driver.findElement(By.xpath(SEARCH_INPUT_XPATH))).sendKeys(TEST_REQUEST);
        inOrder.verify(driver.findElement(By.xpath(SEARCH_BUTTON_XPATH))).click();
        inOrder.verify(driver.switchTo().alert()).accept();
    }
}
