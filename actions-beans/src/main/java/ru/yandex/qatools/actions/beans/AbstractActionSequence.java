package ru.yandex.qatools.actions.beans;

import org.openqa.selenium.WebDriver;
import ru.yandex.qatools.actions.listener.DefaultPrecessEventListener;
import ru.yandex.qatools.actions.listener.ProcessEventListener;

import java.util.List;

/**
 * @author Alexander Tolmachev starlight@yandex-team.ru
 *         Date: 12.07.12
 */
public abstract class AbstractActionSequence<T> {
    public abstract List<Action<T>> getActions();

    protected ProcessEventListener eventHandler = new DefaultPrecessEventListener();

    public void setListener(ProcessEventListener listener) {
        this.eventHandler = listener;
    }

    public void perform(T context) {
        for (Action<T> action : getActions()) {
            eventHandler.beforePerform(action);
            try {
                action.perform(context);
                eventHandler.afterSuccessPerform(action);
            } catch (Exception e) {
                eventHandler.onException(action, e);
            } finally {
                eventHandler.afterPerform(action);
            }
        }
    }

    public boolean append(Action<T> action) {
        return getActions().add(action);
    }

    public boolean concat(AbstractActionSequence<T> actionSequence) {
        return getActions().addAll(actionSequence.getActions());
    }

    @Override
    public String toString() {
        StringBuilder stringBuilder = new StringBuilder();
        for (Action action : getActions()) {
            stringBuilder.append(action.toString());
            stringBuilder.append("\n");
        }
        return stringBuilder.toString();
    }
}
