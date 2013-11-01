package ru.yandex.qatools.actions.beans;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import ru.yandex.qatools.htmlelements.element.CheckBox;

/**
 * @author Alexander Tolmachev starlight@yandex-team.ru
 *         Date: 11.07.12
 */
public abstract class AbstractDeselectCheckBoxAction extends WebElementAction {
    @Override
    public void perform(WebDriver webDriver) {
        WebElement checkBoxElement = webDriver.findElement(buildBy());
        CheckBox checkBox = new CheckBox(checkBoxElement);
        checkBox.deselect();
    }

    @Override
    public String toString() {
        return String.format("Unset checkbox '%s'%s", buildBy(), metaInformationToString());
    }
}
