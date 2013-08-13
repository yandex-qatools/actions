package ru.yandex.qatools.actions.beans;

import org.openqa.selenium.WebDriver;

/**
 * @author Repin Dmitrii dimmddr@yandex-team.ru
 *         Date: 24.07.13
 */
public class AbstractAlertAcceptAction extends WebDriverAction {
    @Override
    public void perform(WebDriver webDriver) {
        webDriver.switchTo().alert().accept();
    }

    @Override
    public String toString() {
        return "Accept alert";
    }
}
