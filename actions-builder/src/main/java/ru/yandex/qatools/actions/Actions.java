package ru.yandex.qatools.actions;

import java.io.*;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBElement;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import javax.xml.bind.Unmarshaller;

import ru.yandex.qatools.actions.beans.*;
import ru.yandex.qatools.actions.listener.ProcessEventListener;

/**
 * User: eroshenkoam, pazone
 * Date: 9/2/12, 5:08 PM
 */
public class Actions {
    private ActionSequence sequence = new ActionSequence();

    public Actions() {
    }

    public Actions(ActionSequence actions) {
        sequence.append(actions);
    }

    public void setListener(ProcessEventListener listener) {
        sequence.setListener(listener);
    }

    public Actions loadPage(String url) {
        LoadPageAction loadPage = new LoadPageAction();
        loadPage.setUrl(url);
        sequence.append(loadPage);
        return this;
    }

    public Actions executeScript(String script) {
        ExecuteJavaScriptAction executeJavaScriptAction = new ExecuteJavaScriptAction();
        executeJavaScriptAction.setJavaScript(script);
        sequence.append(executeJavaScriptAction);
        return this;
    }

    public Actions refreshPage() {
        RefreshPageAction refreshPage = new RefreshPageAction();
        sequence.append(refreshPage);
        return this;
    }

    public Actions alertAccept() {
        AlertAcceptAction alertAccept = new AlertAcceptAction();
        sequence.append(alertAccept);
        return this;
    }

    public Actions navigateForward() {
        NavigateForwardAction navigateForward = new NavigateForwardAction();
        sequence.append(navigateForward);
        return this;
    }

    public Actions navigateBack() {
        NavigateBackAction navigateBack = new NavigateBackAction();
        sequence.append(navigateBack);
        return this;
    }

    public Actions resizeWindow(int width, int height) {
        ResizeWindowAction resizeWindowAction = new ResizeWindowAction();
        resizeWindowAction.setHeight(height);
        resizeWindowAction.setWidth(width);
        sequence.append(resizeWindowAction);
        return this;
    }

    public Actions mouseOver(FindBy locator, String ... description) {
        MouseOverAction mouseOverAction = new MouseOverAction();
        mouseOverAction.setFindBy(locator);
        mouseOverAction.addDescription(description);
        sequence.append(mouseOverAction);
        return this;
    }

    public Actions click(FindBy locator, String ... description) {
        ClickAction clickAction = new ClickAction();
        clickAction.setFindBy(locator);
        clickAction.addDescription(description);
        sequence.append(clickAction);
        return this;
    }

    public Actions typeText(FindBy locator, String text, String ... description) {
        TypeTextAction typeText = new TypeTextAction();
        typeText.setFindBy(locator);
        typeText.setText(text);
        typeText.addDescription(description);
        sequence.append(typeText);
        return this;
    }

    public Actions clearText(FindBy locator, String ... description) {
        ClearAction clear = new ClearAction();
        clear.setFindBy(locator);
        clear.addDescription(description);
        sequence.append(clear);
        return this;
    }

    public Actions selectCheckbox(FindBy locator, String ... description) {
        SelectCheckBoxAction selectCheckBox = new SelectCheckBoxAction();
        selectCheckBox.setFindBy(locator);
        selectCheckBox.addDescription(description);
        sequence.append(selectCheckBox);
        return this;
    }

    public Actions deselectCheckbox(FindBy locator, String ... description) {
        DeselectCheckBoxAction deselectCheckBox = new DeselectCheckBoxAction();
        deselectCheckBox.setFindBy(locator);
        deselectCheckBox.addDescription(description);
        sequence.append(deselectCheckBox);
        return this;
    }

    public Actions waitForElement(FindBy locator, int maxWaitTime, String ... description) {
        WaitForElementAction waitForElementAction = new WaitForElementAction();
        waitForElementAction.setFindBy(locator);
        waitForElementAction.setMaxWaitTime(maxWaitTime);
        waitForElementAction.addDescription(description);
        sequence.append(waitForElementAction);
        return this;
    }

    public Actions takeScreenshot(String path) {
        TakeScreenshotAction takeScreenshotAction = new TakeScreenshotAction();
        takeScreenshotAction.setPath(path);
        sequence.append(takeScreenshotAction);
        return this;
    }

    public Actions waitForElementToDisappear(FindBy locator, int maxWaitTime, String ... description) {
        WaitForElementToDisappearAction action = new WaitForElementToDisappearAction();
        action.setFindBy(locator);
        action.setMaxWaitTime(maxWaitTime);
        action.addDescription(description);
        sequence.append(action);
        return this;
    }


    public Actions append(ActionSequence actions) {
        sequence.append(actions);
        return this;
    }

    public Actions append(Action action) {
        sequence.append(action);
        return this;
    }

    public ActionSequence build() {
        return sequence;
    }

    public Actions read(Reader reader) {
        try {
            JAXBContext jaxbContext = JAXBContext.newInstance(ActionSequence.class.getPackage().getName());
            Unmarshaller unmarshaller = jaxbContext.createUnmarshaller();
            @SuppressWarnings("unchecked")
            JAXBElement<ActionSequence> actions = (JAXBElement<ActionSequence>) unmarshaller.unmarshal(reader);
            sequence.append(actions.getValue());
            return this;
        } catch (JAXBException e) {
            throw new RuntimeException(e);
        }
    }

    public Actions read(File file) throws FileNotFoundException {
        return read(new BufferedReader(new FileReader(file)));
    }

    public Actions read(String fileName) throws FileNotFoundException {
        return read(new File(fileName));
    }

    public Actions alertDismiss() {
        AlertDismissAction alertDismiss = new AlertDismissAction();
        sequence.append(alertDismiss);
        return this;
    }

    public void write(File file) {
        write(file, ActionSequence.class.getPackage().getName());
    }

    public void write(File file, String packageNames) {
        JAXBElement<ActionSequence> actions = new ObjectFactory().createActions(sequence);
        try {
            JAXBContext jaxbContext = JAXBContext.newInstance(packageNames);
            Marshaller marshaller = jaxbContext.createMarshaller();
            marshaller.marshal(actions, file);
        } catch (JAXBException e) {
            throw new RuntimeException(e);
        }
    }

    public void write(String fileName) {
        write(new File(fileName));
    }

}
