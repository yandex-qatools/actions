package ru.yandex.qatools.actions.beans;

import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.remote.Augmenter;
import org.openqa.selenium.remote.RemoteWebDriver;

import javax.imageio.ImageIO;
import java.io.ByteArrayInputStream;
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
        final WebDriver preparedDriver;
        if (webDriver instanceof RemoteWebDriver) {
            preparedDriver = new Augmenter().augment(webDriver);
        } else {
            preparedDriver = webDriver;
        }
        byte[] screenBytes = ((TakesScreenshot) preparedDriver).getScreenshotAs(OutputType.BYTES);
        ByteArrayInputStream stream = null;
        try {
            stream = new ByteArrayInputStream(screenBytes);
            ImageIO.write(ImageIO.read(stream), "jpg", new File(getPath()));
        } catch (IOException e) {
            throw new RuntimeException(e);
        } finally {
            if (stream != null) {
                try {
                    stream.close();
                } catch (IOException ignored) {
                }
            }
        }
    }

    @Override
    public boolean equals(Object o) {
        if (o == null) {
            return false;
        }
        if (o == this) {
            return true;
        }
        if (o.getClass() != getClass()) {
            return false;
        }

        AbstractTakeScreenshotAction otherTakeScreenshotAction = (AbstractTakeScreenshotAction) o;
        return new EqualsBuilder()
                .append(getPath(), otherTakeScreenshotAction.getPath())
                .isEquals();
    }

    @Override
    public int hashCode() {
        int initialHash = 17;
        int multiplier = 37;
        return new HashCodeBuilder(initialHash, multiplier)
                .append(getPath())
                .hashCode();
    }

    @Override
    public String toString() {
        return String.format("Take screenshot, save it as '%s'", getPath());
    }
}
