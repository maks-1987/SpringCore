package Loggers;

import Beans.Event;

import java.util.ArrayList;
import java.util.List;

class CacheFileEventLogger extends FileEventLogger {

    private int cacheSize;
    private List<Event> cache;

    CacheFileEventLogger(String fileName, int cacheSize) {
        super(fileName);
        this.cacheSize = cacheSize;
        this.cache = new ArrayList<Event>(cacheSize);
    }
    @Override
    public void logEvent(Event event) {
        cache.add(event);
        if (cache.size()==cacheSize) {
            writeEventsFromCache();
            cache.clear();
        }
    }

    private void writeEventsFromCache() {
        cache.stream().forEach(super::logEvent);
    }

    void destroy() {
        if (!cache.isEmpty())
            writeEventsFromCache();
        cache.clear();
    }

}
