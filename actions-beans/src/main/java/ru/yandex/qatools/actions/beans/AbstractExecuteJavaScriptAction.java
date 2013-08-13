package ru.yandex.qatools.actions.beans;

import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;

/**
 * @author Alexander Tolmachev starlight@yandex-team.ru
 *         Date: 11.07.12
 */
public abstract class AbstractExecuteJavaScriptAction extends WebDriverAction {
    public abstract String getJavaScript();

    @Override
    @SuppressWarnings("unchecked")
    public void perform(WebDriver webDriver) {
        JavascriptExecutor javascriptExecutor = (JavascriptExecutor) webDriver;
        javascriptExecutor.executeScript(getJavaScript());
    }

    @Override
    public String toString() {
        return "Execute JavaScript";
    }
}
