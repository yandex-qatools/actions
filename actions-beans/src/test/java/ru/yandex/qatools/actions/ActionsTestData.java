package ru.yandex.qatools.actions;

/**
 * @author Alexander Tolmachev starlight@yandex-team.ru
 *         Date: 06.09.12
 */
public class ActionsTestData {
    public static final String BUTTON_XPATH = "//input[@type='submit']";
    public static final String TEXT_INPUT_XPATH = "//input[@type='text']";
    public static final String SELECTED_CHECKBOX_XPATH = "//input[@type='checkbox' and @checked='checked']";
    public static final String NOT_SELECTED_CHECKBOX_XPATH = "//input[@type='checkbox' and not(@checked)]";
    public static final String SELECT_XPATH = "//select";
    public static final String RADIO_NAME = "radio";
    public static final int SELECT_OPTIONS_NUMBER = 3;
    public static final int RADIO_BUTTONS_NUMBER = 5;
    public static final String TEST_URL = "http://www.yandex.ru";
    public static final String TEST_TEXT = "test text";
    public static final int TEST_INDEX = 1;
}
