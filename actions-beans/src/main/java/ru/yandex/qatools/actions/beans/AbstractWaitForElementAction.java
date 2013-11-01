package ru.yandex.qatools.actions.beans;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public abstract class AbstractWaitForElementAction extends WebElementAction {
    public abstract int getMaxWaitTime();

    public static final int CHECK_INTERVAL = 100;

    @Override
    public void perform(WebDriver webDriver) {
        WebDriverWait wait = new WebDriverWait(webDriver, getMaxWaitTime(), CHECK_INTERVAL);
        wait.until(ExpectedConditions.presenceOfElementLocated(buildBy()));
    }

    @Override
    public String toString() {
        return String.format("wait for '%s'%s maxWaitTime '%d'",
                buildBy(), metaInformationToString(), getMaxWaitTime());
    }
}
