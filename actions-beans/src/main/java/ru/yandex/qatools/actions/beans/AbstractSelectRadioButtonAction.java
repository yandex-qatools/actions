package ru.yandex.qatools.actions.beans;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import ru.yandex.qatools.htmlelements.element.Radio;

/**
 * @author Alexander Tolmachev starlight@yandex-team.ru
 *         Date: 11.07.12
 */
public abstract class AbstractSelectRadioButtonAction extends WebElementAction {

    public abstract int getButtonIndex();

    @Override
    public void perform(WebDriver webDriver) {
        WebElement radioButton = webDriver.findElement(buildBy());
        Radio radio = new Radio(radioButton);
        radio.selectByIndex(getButtonIndex());
    }

    @Override
    public String toString() {
        return String.format("Select radio button number %d from radio group '%s'",
                getButtonIndex(), buildBy());
    }
}
