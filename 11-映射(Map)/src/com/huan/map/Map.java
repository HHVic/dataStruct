package com.huan.map;

public interface Map<K, V> {
    /**
     * 获得Map的长度
     * @return
     */
    int size();

    /**
     * Map是否是空
     * @return
     */
    boolean isEmpty();

    /**
     * 清空Map
     */
    void clear();

    /**
     * 向Map中放元素
     * @param key
     * @param value
     * @return 如果key相同返回覆盖之前的value
     */
    V put(K key, V value);

    /**
     * 通过key获取value
     * @param key
     * @return
     */
    V get(K key);

    /**
     * 删除key以及key对应的元素
     * @param key
     * @return 删除的value
     */
    V remove(K key);

    /**
     * Map中是否包含key
     * @param key
     * @return
     */
    boolean containsKey(K key);

    /**
     * Map中是否包含value
     * @param value
     * @return
     */
    boolean containsValue(V value);

    /**
     * 遍历Map
     */
    void traversal(Visitor<K, V> visitor);

    abstract class Visitor<K, V> {
        boolean stop;

        public abstract boolean visit(K key, V value);
    }

}
