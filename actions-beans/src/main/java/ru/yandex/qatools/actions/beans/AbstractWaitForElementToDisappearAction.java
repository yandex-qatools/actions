package ru.yandex.qatools.actions.beans;


import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public abstract class AbstractWaitForElementToDisappearAction extends WebElementAction {
    public abstract int getMaxWaitTime();

    public static final int CHECK_INTERVAL = 100;

    @Override
    public void perform(WebDriver webDriver) {
        WebDriverWait wait = new WebDriverWait(webDriver, getMaxWaitTime(), CHECK_INTERVAL);
        wait.until(
            new ExpectedCondition<Boolean>() {
                @Override
                public Boolean apply(WebDriver driver) {
                    try {
                        return ExpectedConditions.presenceOfElementLocated(buildBy()).apply(driver) == null;
                    } catch (NoSuchElementException e) {
                        return true;
                    }
                }
            }
        );
    }

    @Override
    public String toString() {
        return String.format("wait for '%s'%s to disappear maxWaitTime '%d'",
                buildBy(), metaInformationToString(), getMaxWaitTime());
    }
}
