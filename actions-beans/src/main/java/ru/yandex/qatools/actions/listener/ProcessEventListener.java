package ru.yandex.qatools.actions.listener;

import ru.yandex.qatools.actions.beans.Action;

/**
 * @author Pavel Zorin pazone@yandex-team.ru
 *         4/22/13, 6:33 PM
 */
public interface ProcessEventListener {

    public void beforePerform(Action action);

    public void afterPerform(Action action);

    public void afterSuccessPerform(Action action);

    public void onException(Action action, Throwable e);
}
