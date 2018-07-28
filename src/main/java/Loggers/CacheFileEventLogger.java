package Loggers;

import Beans.Event;

import java.util.ArrayList;
import java.util.List;

class CacheFileEventLogger extends FileEventLogger {

    private int cacheSize;
    private List<Event> cache;

    public CacheFileEventLogger(String fileName, int cacheSize) {
        super(fileName);
        this.cacheSize = cacheSize;
        this.cache = new ArrayList<Event>(cacheSize);
    }

    public void destroy() {
        if (!cache.isEmpty())
            writeEventsFromCache();
        cache.clear();
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


}
