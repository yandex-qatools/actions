package ru.yandex.qatools.actions.beans;

import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;

/**
 * @author Alexander Tolmachev starlight@yandex-team.ru
 *         Date: 05.05.12
 */
public abstract class Action<T> {

    public abstract void perform(T context);

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
