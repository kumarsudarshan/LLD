package cache.policies;

import cache.algoritms.DoublyLinkedList;
import cache.algoritms.DoublyLinkedListNode;

import java.util.HashMap;
import java.util.Map;

/**
 * Eviction policy based on LRU algorithm.
 *
 * @param <K> Key type.
 */
public class LRUEvictionPolicy<K> implements EvictionPolicy<K> {

    private DoublyLinkedList<K> dll;
    private Map<K, DoublyLinkedListNode<K>> mapper;

    public LRUEvictionPolicy() {
        this.dll = new DoublyLinkedList<>();
        this.mapper = new HashMap<>();
    }

    @Override
    public void keyAccessed(K key) {
        if (mapper.containsKey(key)) {
            dll.detachNode(mapper.get(key));
            dll.addNodeAtLast(mapper.get(key));
        } else {
            DoublyLinkedListNode<K> newNode = dll.addElementAtLast(key);
            mapper.put(key, newNode);
        }
    }

    @Override
    public K evictKey() {
        DoublyLinkedListNode<K> first = dll.getFirstNode();
        if(first == null) {
            return null;
        }
        dll.detachNode(first);
        return first.getElement();
    }
}
