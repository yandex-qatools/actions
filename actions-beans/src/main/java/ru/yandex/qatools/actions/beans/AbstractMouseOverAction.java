package ru.yandex.qatools.actions.beans;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.interactions.Actions;

/**
 * @author Pavel Zorin pazone@yandex-team.ru
 */

public abstract class AbstractMouseOverAction extends WebElementAction {

    @Override
    public void perform(WebDriver webDriver) {
        new Actions(webDriver).moveToElement(webDriver.findElement(buildBy())).build().perform();
    }

    @Override
    public String toString() {
        return String.format("Move cursor to element '%s'%s", buildBy(), descriptionToString());
    }
}
