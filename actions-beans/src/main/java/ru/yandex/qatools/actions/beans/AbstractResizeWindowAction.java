package ru.yandex.qatools.actions.beans;

import org.openqa.selenium.Dimension;
import org.openqa.selenium.WebDriver;

/**
 * author Pavel Zorin
 * pazone@yandex-team.ru
 */
public abstract class AbstractResizeWindowAction extends WebDriverAction {

    public abstract int getWidth();

    public abstract int getHeight();

    @Override
    public void perform(WebDriver webDriver) {
        webDriver.manage().window().setSize(new Dimension(getWidth(), getHeight()));
    }

    @Override
    public String toString() {
        return String.format("Resizing browser window to (%d:%d).", getWidth(), getHeight());
    }
}
