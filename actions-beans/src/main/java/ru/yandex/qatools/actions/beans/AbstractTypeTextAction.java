package ru.yandex.qatools.actions.beans;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import ru.yandex.qatools.htmlelements.element.TextInput;

/**
 * @author Alexander Tolmachev starlight@yandex-team.ru
 *         Date: 11.07.12
 */
public abstract class AbstractTypeTextAction extends WebElementAction {

    public abstract String getText();

    @Override
    public void perform(WebDriver webDriver) {
        WebElement textElement = webDriver.findElement(buildBy());
        TextInput textInput = new TextInput(textElement);
        textInput.sendKeys(getText());
    }

    @Override
    public String toString() {
        return String.format("Type text '%s' in text input '%s'", getText(), buildBy());
    }
}