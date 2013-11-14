package ru.yandex.qatools.actions.beans;

import com.google.common.base.Joiner;
import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import ru.yandex.qatools.actions.util.ListUtils;
import ru.yandex.qatools.actions.util.SelectorUtils;

import java.util.Arrays;
import java.util.List;

/**
 * @author Artem Eroshenko eroshenkoam
 *         4/19/13, 6:07 PM
 */
public abstract class AbstractWebElementAction extends Action<WebDriver> {

    public By buildBy() {
        return SelectorUtils.buildByFromFindBy(getFindBy());
    }

    public abstract FindBy getFindBy();

    public abstract List<String> getDescription();

    public void addDescription(String ... description) {
        List<String> currentDescription = getDescription();

        if (!ListUtils.isNull(currentDescription)) {
            currentDescription.addAll(Arrays.asList(description));
        }
    }

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

    protected String descriptionToString() {
        List<String> description = getDescription();

        return CollectionUtils.isEmpty(description)
                ? ""
                : String.format(" with description [%s]", Joiner.on(", ").join(description));
    }

}
