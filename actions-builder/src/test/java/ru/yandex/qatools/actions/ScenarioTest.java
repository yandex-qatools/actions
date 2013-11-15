package ru.yandex.qatools.actions;

import static org.mockito.Mockito.inOrder;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;
import static org.junit.Assert.assertThat;
import static org.hamcrest.Matchers.hasSize;
import static org.hamcrest.Matchers.instanceOf;
import static org.hamcrest.Matchers.is;

import java.io.File;
import java.io.FileNotFoundException;
import java.net.URISyntaxException;

import java.util.Arrays;
import java.util.List;

import org.junit.Before;
import org.junit.Test;
import org.mockito.InOrder;
import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import ru.yandex.qatools.actions.beans.AbstractWebElementAction;
import ru.yandex.qatools.actions.beans.Action;
import ru.yandex.qatools.actions.beans.FindBy;

/**
 * @author Alexander Tolmachev starlight@yandex-team.ru
 *         Date: 05.09.12
 */
public class ScenarioTest {
    private static final String PAGE_URL = "http://www.yandex.ru";
    private static final String SEARCH_INPUT_XPATH = "//input[@class='b-form-input__input']";
    private static final String SEARCH_BUTTON_XPATH = "//input[@class='b-form-button__input']";
    private static final String TEST_REQUEST = "Yandex";
    private static final String DESCRIPTION_1 = "Description 1";
    private static final String DESCRIPTION_2 = "Description 2";
    private static final List<String> EXPECTED_DESCRIPTION = Arrays.asList(DESCRIPTION_1, DESCRIPTION_2);

    private static WebDriver driver;

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
                click(FindBy.xpath(SEARCH_BUTTON_XPATH), DESCRIPTION_1, DESCRIPTION_2).
                alertAccept();
        File actionsFile = new File("search-request-scenario.xml");
        actions.write(actionsFile.getPath());
    }

    @Test
    public void readAndPerformScenarioTest() throws FileNotFoundException, URISyntaxException {
        Actions actions = new Actions();
        File actionsFile = new File(this.getClass().getResource("/search-request-scenario.xml").toURI());
        actions.read(actionsFile.getPath()).build().perform(driver);

        inOrder.verify(driver).get(PAGE_URL);
        inOrder.verify(driver.findElement(By.xpath(SEARCH_INPUT_XPATH))).sendKeys(TEST_REQUEST);
        inOrder.verify(driver.findElement(By.xpath(SEARCH_BUTTON_XPATH))).click();
        inOrder.verify(driver.switchTo().alert()).accept();

        List<Action> readActions = actions.build().getActions();
        assertThat("Check the number of deserialized actions", readActions, hasSize(4));
        assertThat("Check that the third action can contain description",
                readActions.get(2), instanceOf(AbstractWebElementAction.class));
        assertThat("Check read description",
                ((AbstractWebElementAction) readActions.get(2)).getDescription(), is(EXPECTED_DESCRIPTION));
    }
}