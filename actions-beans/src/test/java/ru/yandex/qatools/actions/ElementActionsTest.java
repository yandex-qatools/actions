package ru.yandex.qatools.actions;

import org.junit.Test;
import org.openqa.selenium.*;
import ru.yandex.qatools.actions.beans.*;
import ru.yandex.qatools.actions.mockfactory.MockFactory;
import ru.yandex.qatools.htmlelements.element.Radio;
import ru.yandex.qatools.htmlelements.element.Select;

import static org.mockito.Mockito.*;
import static ru.yandex.qatools.actions.ActionsTestData.*;
import static ru.yandex.qatools.actions.util.SelectorUtils.buildFindBy;

/**
 * @author Alexander Tolmachev starlight@yandex-team.ru
 *         Date: 05.09.12
 */
public class ElementActionsTest {
    @Test
    public void clickTest() {
        WebDriver driver = MockFactory.mockDriver();
        WebElement elementToClick = MockFactory.mockButton(driver, BUTTON_XPATH);

        ClickAction click = new ClickAction();
        click.setFindBy(buildFindBy(How.XPATH, BUTTON_XPATH));
        click.perform(driver);

        verify(elementToClick).click();
    }

    @Test
    public void typeTextTest() {
        WebDriver driver = MockFactory.mockDriver();
        WebElement elementToSendKeys = MockFactory.mockTextInput(driver, TEXT_INPUT_XPATH);

        TypeTextAction typeText = new TypeTextAction();
        typeText.setFindBy(buildFindBy(How.XPATH, TEXT_INPUT_XPATH));
        typeText.setText(TEST_TEXT);
        typeText.perform(driver);

        verify(elementToSendKeys).sendKeys(TEST_TEXT);
    }

    @Test
    public void selectCheckboxTest() {
        WebDriver driver = MockFactory.mockDriver();
        WebElement notSelectedCheckbox = MockFactory.mockNotSelectedCheckbox(driver, NOT_SELECTED_CHECKBOX_XPATH);
        WebElement selectedCheckbox = MockFactory.mockSelectedCheckbox(driver, SELECTED_CHECKBOX_XPATH);

        SelectCheckBoxAction selectCheckBox = new SelectCheckBoxAction();
        selectCheckBox.setFindBy(buildFindBy(How.XPATH, NOT_SELECTED_CHECKBOX_XPATH));
        selectCheckBox.perform(driver);

        verify(notSelectedCheckbox).click();

        selectCheckBox.setFindBy(buildFindBy(How.XPATH, SELECTED_CHECKBOX_XPATH));
        selectCheckBox.perform(driver);

        verify(selectedCheckbox, never()).click();
    }

    @Test
    public void deselectCheckboxTest() {
        WebDriver driver = MockFactory.mockDriver();
        WebElement selectedCheckbox = MockFactory.mockSelectedCheckbox(driver, SELECTED_CHECKBOX_XPATH);
        WebElement notSelectedCheckbox = MockFactory.mockNotSelectedCheckbox(driver, NOT_SELECTED_CHECKBOX_XPATH);

        DeselectCheckBoxAction deselectCheckBox = new DeselectCheckBoxAction();
        deselectCheckBox.setFindBy(buildFindBy(How.XPATH, SELECTED_CHECKBOX_XPATH));
        deselectCheckBox.perform(driver);

        verify(selectedCheckbox).click();

        deselectCheckBox.setFindBy(buildFindBy(How.XPATH, NOT_SELECTED_CHECKBOX_XPATH));
        deselectCheckBox.perform(driver);

        verify(notSelectedCheckbox, never()).click();
    }

    @Test
    public void selectListOptionTest() {
        WebDriver driver = MockFactory.mockDriver();
        Select select = MockFactory.mockSelect(driver, SELECT_XPATH, SELECT_OPTIONS_NUMBER);

        SelectListOptionAction selectListOption = new SelectListOptionAction();
        selectListOption.setFindBy(buildFindBy(How.XPATH, SELECT_XPATH));
        selectListOption.setOptionIndex(TEST_INDEX);
        selectListOption.perform(driver);

        verify(select.getOptions().get(TEST_INDEX)).click();
    }

    @Test
    public void selectRadioButtonTest() {
        WebDriver driver = MockFactory.mockDriver();
        Radio radio = MockFactory.mockRadio(driver, RADIO_NAME, RADIO_BUTTONS_NUMBER);
        String radioXpath = String.format("//input[@type='radio' and @name='%s']", RADIO_NAME);

        SelectRadioButtonAction selectRadioButton = new SelectRadioButtonAction();
        selectRadioButton.setFindBy(buildFindBy(How.XPATH, radioXpath));
        selectRadioButton.setButtonIndex(TEST_INDEX);
        selectRadioButton.perform(driver);

        verify(radio.getButtons().get(TEST_INDEX)).click();
    }

    @Test
    public void waitForElementTest() {
        WebDriver fakeDriver = MockFactory.mockDriver();
        WebElement fakeButton = MockFactory.mockButton(fakeDriver, BUTTON_XPATH);

        when(fakeDriver.findElement(By.xpath(BUTTON_XPATH))).thenReturn(null).thenReturn(fakeButton);

        WaitForElementAction waitForElement = new WaitForElementAction();
        waitForElement.setFindBy(buildFindBy(How.XPATH, BUTTON_XPATH));
        waitForElement.setMaxWaitTime(1);
        waitForElement.perform(fakeDriver);

        verify(fakeDriver, times(2)).findElement(waitForElement.buildBy());
    }

    @Test(expected = TimeoutException.class)
    public void waitForNonexistentElementTest() {
        WebDriver fakeDriver = MockFactory.mockDriver();

        when(fakeDriver.findElement(By.xpath(BUTTON_XPATH))).thenReturn(null);

        WaitForElementAction waitForElement = new WaitForElementAction();
        waitForElement.setFindBy(buildFindBy(How.XPATH, BUTTON_XPATH));
        waitForElement.setMaxWaitTime(1);
        waitForElement.perform(fakeDriver);

        verify(fakeDriver, times(waitForElement.getMaxWaitTime() * 1000 / WaitForElementAction.CHECK_INTERVAL)).findElement(waitForElement.buildBy());
    }
}
