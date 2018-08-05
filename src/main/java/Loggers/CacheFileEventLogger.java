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
    }

    @Override
    public void logEvent(Event event) {
        cache.add(event);
        // очищает кэш, перед этим записывае логи в файл
        if (cache.size()==cacheSize) {
            writeEventsFromCache();
            cache.clear();
        }
    }

    private void writeEventsFromCache() {
        // stream() - метод коллекции Java
        cache.stream().forEach(super::logEvent);
    }


}
