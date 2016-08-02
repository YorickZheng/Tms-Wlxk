package com.wlxk.support.cache;

public interface TmsCache {
    boolean set(final String key, Object value);
    boolean set(final String key, Object value, Long expireTime);
    void remove(final String key);
    void remove(final String... keys);
    void removeByPattern(final String pattern);
    boolean exists(final String key);
    Object get(final String key);
}
