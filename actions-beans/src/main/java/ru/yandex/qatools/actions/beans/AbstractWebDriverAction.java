package ru.yandex.qatools.actions.beans;

import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;
import org.openqa.selenium.WebDriver;

/**
 * @author Artem Eroshenko eroshenkoam
 *         4/22/13, 6:06 PM
 */
public abstract class AbstractWebDriverAction extends Action<WebDriver> {

    @Override
    public boolean equals(Object o) {
        return o.getClass() == getClass() && EqualsBuilder.reflectionEquals(this, o);
    }

    @Override
    public int hashCode() {
        HashCodeBuilder builder = new HashCodeBuilder();
        builder.appendSuper(HashCodeBuilder.reflectionHashCode(this));
        builder.append(this.getClass());
        return builder.toHashCode();
    }

}
