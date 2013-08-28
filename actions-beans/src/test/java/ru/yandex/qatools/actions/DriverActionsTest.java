package ru.yandex.qatools.actions;

import org.junit.Test;
import org.openqa.selenium.Alert;
import org.openqa.selenium.WebDriver;
import ru.yandex.qatools.actions.beans.*;
import ru.yandex.qatools.actions.mockfactory.MockFactory;

import static org.mockito.Mockito.*;
import static ru.yandex.qatools.actions.ActionsTestData.TEST_URL;

/**
 * @author Alexander Tolmachev starlight@yandex-team.ru
 *         Date: 05.09.12
 */
public class DriverActionsTest {
    @Test
    public void loadPageTest() {
        WebDriver driver = MockFactory.mockDriver();

        LoadPageAction loadPage = new LoadPageAction();
        loadPage.setUrl(TEST_URL);
        loadPage.perform(driver);

        verify(driver).get(TEST_URL);
    }

    @Test
    public void refreshPageTest() {
        WebDriver driver = MockFactory.mockDriver();

        RefreshPageAction refreshPage = new RefreshPageAction();
        refreshPage.perform(driver);

        verify(driver.navigate()).refresh();
    }

    @Test
    public void navigateForwardTest() {
        WebDriver driver = MockFactory.mockDriver();

        NavigateForwardAction navigateForward = new NavigateForwardAction();
        navigateForward.perform(driver);

        verify(driver.navigate()).forward();
    }

    @Test
    public void navigateBackTest() {
        WebDriver driver = MockFactory.mockDriver();

        NavigateBackAction navigateBack = new NavigateBackAction();
        navigateBack.perform(driver);

        verify(driver.navigate()).back();
    }

    @Test
    public void alertAcceptTest() {
        WebDriver fakeDriver = MockFactory.mockDriver();
        Alert fakeAlert = mock(Alert.class);
        WebDriver.TargetLocator fakeLocator = mock(WebDriver.TargetLocator.class);

        when(fakeDriver.switchTo()).thenReturn(fakeLocator);
        when(fakeDriver.switchTo().alert()).thenReturn(fakeAlert);

        AlertAcceptAction alertAccept = new AlertAcceptAction();
        alertAccept.perform(fakeDriver);

        verify(fakeDriver.switchTo().alert()).accept();
    }

    @Test
    public void alertDismissTest() {
        WebDriver fakeDriver = MockFactory.mockDriver();
        Alert fakeAlert = mock(Alert.class);
        WebDriver.TargetLocator fakeLocator = mock(WebDriver.TargetLocator.class);

        when(fakeDriver.switchTo()).thenReturn(fakeLocator);
        when(fakeDriver.switchTo().alert()).thenReturn(fakeAlert);

        AlertDismissAction alertDismiss = new AlertDismissAction();
        alertDismiss.perform(fakeDriver);

        verify(fakeDriver.switchTo().alert()).dismiss();
    }
}
