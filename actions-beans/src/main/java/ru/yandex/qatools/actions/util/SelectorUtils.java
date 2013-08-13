package ru.yandex.qatools.actions.util;

import org.openqa.selenium.By;
import org.openqa.selenium.support.ByIdOrName;
import ru.yandex.qatools.actions.beans.FindBy;
import ru.yandex.qatools.actions.beans.How;

/**
 * @author Pavel Zorin pazone@yandex-team.ru
 */

@SuppressWarnings("unused")
public class SelectorUtils {

    public static By buildByFromFindBy(FindBy findBy) {

        By ans = buildByFromShortFindBy(findBy);
        if (ans == null) {
            ans = buildByFromLongFindBy(findBy);
        }

        return ans;
    }

    public static By buildByFromLongFindBy(FindBy findBy) {
        How how = findBy.getHow();
        String using = findBy.getUsing();

        switch (how) {
            case CLASS_NAME:
                return By.className(using);

            case CSS:
                return By.cssSelector(using);

            case ID:
                return By.id(using);

            case ID_OR_NAME:
                return new ByIdOrName(using);

            case LINK_TEXT:
                return By.linkText(using);

            case NAME:
                return By.name(using);

            case PARTIAL_LINK_TEXT:
                return By.partialLinkText(using);

            case TAG_NAME:
                return By.tagName(using);

            case XPATH:
                return By.xpath(using);

            default:
                // Note that this shouldn't happen (eg, the above matches all
                // possible values for the How enum)
                throw new IllegalArgumentException("Cannot determine how to locate element " + findBy);
        }
    }

    public static By buildByFromShortFindBy(FindBy findBy) {
        if (null != findBy.getClassName() && !"".equals(findBy.getClassName()))
            return By.className(findBy.getClassName());

        if (null != findBy.getCss() && !"".equals(findBy.getCss()))
            return By.cssSelector(findBy.getCss());

        if (null != findBy.getId() && !"".equals(findBy.getId()))
            return By.id(findBy.getId());

        if (null != findBy.getLinkText() && !"".equals(findBy.getLinkText()))
            return By.linkText(findBy.getLinkText());

        if (null != findBy.getName() && !"".equals(findBy.getName()))
            return By.name(findBy.getName());

        if (null != findBy.getPartialLinkText() && !"".equals(findBy.getPartialLinkText()))
            return By.partialLinkText(findBy.getPartialLinkText());

        if (null != findBy.getTagName() && !"".equals(findBy.getTagName()))
            return By.tagName(findBy.getTagName());

        if (null != findBy.getXpath() && !"".equals(findBy.getXpath()))
            return By.xpath(findBy.getXpath());

        // Fall through
        return null;
    }

    public static FindBy buildFindBy(How findByType, String findByValue) {
        FindBy findBy = new FindBy();
        findBy.setHow(findByType);
        findBy.setUsing(findByValue);
        return findBy;
    }

    public static FindBy css(String path) {
        FindBy findBy = new FindBy();
        findBy.setHow(How.CSS);
        findBy.setUsing(path);
        return findBy;
    }

    public static FindBy xpath(String path) {
        FindBy findBy = new FindBy();
        findBy.setHow(How.XPATH);
        findBy.setUsing(path);
        return findBy;
    }

    public static FindBy className(String className) {
        FindBy findBy = new FindBy();
        findBy.setHow(How.CLASS_NAME);
        findBy.setUsing(className);
        return findBy;
    }

    public static FindBy tagName(String tagName) {
        FindBy findBy = new FindBy();
        findBy.setHow(How.TAG_NAME);
        findBy.setUsing(tagName);
        return findBy;
    }




}
