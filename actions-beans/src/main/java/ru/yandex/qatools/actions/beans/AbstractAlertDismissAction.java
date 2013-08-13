package ru.yandex.qatools.actions.beans;

import org.openqa.selenium.Alert;
import org.openqa.selenium.WebDriver;

/**
 * @author Repin Dmitrii dimmddr@yandex-team.ru
 *         Date: 24.07.13
 */
public abstract class AbstractAlertDismissAction extends WebDriverAction {
    @Override
    public void perform(WebDriver webDriver) {
        Alert alert = webDriver.switchTo().alert();
        alert.dismiss();
    }

    @Override
    public String toString() {
        return "Dismiss alert.";
    }
}
