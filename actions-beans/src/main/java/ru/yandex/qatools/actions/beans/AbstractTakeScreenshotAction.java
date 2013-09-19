package ru.yandex.qatools.actions.beans;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.remote.Augmenter;
import org.openqa.selenium.remote.RemoteWebDriver;

import java.io.File;
import java.io.IOException;

/**
 * @author Artemii Chugreev achugr@yandex-team.ru
 *         30.08.13
 */
public abstract class AbstractTakeScreenshotAction extends WebDriverAction {

    public abstract String getPath();

    @Override
    public void perform(final WebDriver webDriver) {
        try {
            final WebDriver preparedDriver;
            if (webDriver instanceof RemoteWebDriver) {
                preparedDriver = new Augmenter().augment(webDriver);
            } else {
                preparedDriver = webDriver;
            }
            File screenshotFile = ((TakesScreenshot) preparedDriver).getScreenshotAs(OutputType.FILE);
            FileUtils.copyFile(screenshotFile, new File(getPath()));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public String toString() {
        return String.format("Take screenshot, save it as '%s'", getPath());
    }
}
