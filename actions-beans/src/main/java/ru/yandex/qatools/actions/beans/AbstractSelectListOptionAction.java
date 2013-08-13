package ru.yandex.qatools.actions.beans;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import ru.yandex.qatools.htmlelements.element.Select;

/**
 * @author Alexander Tolmachev starlight@yandex-team.ru
 *         Date: 11.07.12
 */
public abstract class AbstractSelectListOptionAction extends WebElementAction {

    public abstract int getOptionIndex();

    @Override
    public void perform(WebDriver webDriver) {
        WebElement selectElement = webDriver.findElement(buildBy());
        Select select = new Select(selectElement);
        select.selectByIndex(getOptionIndex());
    }

    @Override
    public String toString() {
        return String.format("Select list option number %s from selection list '%s'",
                getOptionIndex(), buildBy());
    }
}
