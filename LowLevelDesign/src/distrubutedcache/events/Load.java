package distrubutedcache.events;

import distrubutedcache.models.Record;

public class Load<K, V> extends Event<K, V> {

    public Load(Record<K, V> element, long timestamp) {
        super(element, timestamp);
    }
}
