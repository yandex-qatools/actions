package ru.yandex.qatools.actions.beans;

import org.openqa.selenium.WebDriver;

/**
 * @author Alexander Tolmachev starlight@yandex-team.ru
 *         Date: 06.09.12
 */
public abstract class AbstractNavigateBackAction extends WebDriverAction {
    @Override
    public void perform(WebDriver webDriver) {
        webDriver.navigate().back();
    }

    @Override
    public String toString() {
        return "Navigate back";
    }
}
