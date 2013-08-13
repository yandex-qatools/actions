package ru.yandex.qatools.actions.beans;

import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import ru.yandex.qatools.actions.util.SelectorUtils;

/**
 * @author Artem Eroshenko eroshenkoam
 *         4/19/13, 6:07 PM
 */
public abstract class AbstractWebElementAction extends Action<WebDriver> {

    public By buildBy() {
        return SelectorUtils.buildByFromFindBy(getFindBy());
    }

    public abstract FindBy getFindBy();

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
