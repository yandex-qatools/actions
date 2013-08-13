package ru.yandex.qatools.actions.beans;

/**
 * @author Artem Eroshenko eroshenkoam
 * @author <a href="pazone@yandex-team.ru"> Pavel Zorin </a>
 *         4/22/13, 2:59 PM
 */

@SuppressWarnings("UnusedDeclaration")
public class FindByFactory {

    public static FindBy id(String value) {
        FindBy findBy = new FindBy();
        findBy.setId(value);
        return findBy;
    }

    public static FindBy css(String value) {
        FindBy findBy = new FindBy();
        findBy.setCss(value);
        return findBy;
    }

    public static FindBy xpath(String value) {
        FindBy findBy = new FindBy();
        findBy.setXpath(value);
        return findBy;
    }

    public static FindBy name(String value) {
        FindBy findBy = new FindBy();
        findBy.setName(value);
        return findBy;
    }

    public static FindBy className(String value) {
        FindBy findBy = new FindBy();
        findBy.setClassName(value);
        return findBy;
    }

    public static FindBy tagName(String value) {
        FindBy findBy = new FindBy();
        findBy.setTagName(value);
        return findBy;
    }

    public static FindBy partialLinkText(String value) {
        FindBy findBy = new FindBy();
        findBy.setPartialLinkText(value);
        return findBy;
    }

    public static FindBy linkText(String value) {
        FindBy findBy = new FindBy();
        findBy.setLinkText(value);
        return findBy;
    }
}
