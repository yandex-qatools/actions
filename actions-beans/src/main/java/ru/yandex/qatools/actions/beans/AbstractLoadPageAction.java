package ru.yandex.qatools.actions.beans;

import org.openqa.selenium.WebDriver;

/**
 * @author Alexander Tolmachev starlight@yandex-team.ru
 *         Date: 27.07.12
 */
public abstract class AbstractLoadPageAction extends WebDriverAction {
    public abstract String getUrl();

    @Override
    public void perform(WebDriver webDriver) {
        webDriver.get(getUrl());
    }

    @Override
    public String toString() {
        return String.format("Load page at URL '%s'", getUrl());
    }
}
