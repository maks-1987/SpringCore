package com.core.Loggers;

import com.core.Beans.Event;

public interface EventLogger {

    public void logEvent(Event event);

    public String getName();
}
