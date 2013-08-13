package ru.yandex.qatools.actions.beans;

import org.openqa.selenium.WebDriver;

/**
 * @author Alexander Tolmachev starlight@yandex-team.ru
 *         Date: 06.09.12
 */
public abstract class AbstractRefreshPageAction extends WebDriverAction {
    @Override
    public void perform(WebDriver webDriver) {
        webDriver.navigate().refresh();
    }

    @Override
    public String toString() {
        return "Refresh page";
    }
}
