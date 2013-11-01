package ru.yandex.qatools.actions.beans;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import ru.yandex.qatools.htmlelements.element.TextInput;

/**
 * @author Pavel Zorin pazone@yandex-team.ru
 */

public abstract class AbstractClearAction extends WebElementAction {

    @Override
    public void perform(WebDriver webDriver) {
        WebElement textElement = webDriver.findElement(buildBy());
        TextInput textInput = new TextInput(textElement);
        textInput.clear();
    }

    @Override
    public String toString() {
        return String.format("Clear text input in element '%s'%s", buildBy(), metaInformationToString());
    }
}
