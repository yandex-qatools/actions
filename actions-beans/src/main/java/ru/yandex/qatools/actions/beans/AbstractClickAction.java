package ru.yandex.qatools.actions.beans;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

/**
 * @author Alexander Tolmachev starlight@yandex-team.ru
 *         Date: 11.07.12
 */
public abstract class AbstractClickAction extends WebElementAction {
    @Override
    public void perform(WebDriver webDriver) {
        WebElement elementToClick = webDriver.findElement(buildBy());
        elementToClick.click();
    }

    @Override
    public String toString() {
        return String.format("Click on element at element '%s'%s", buildBy(), metaInformationToString());
    }
}
