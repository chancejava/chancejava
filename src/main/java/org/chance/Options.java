package org.chance;

import java.util.HashMap;
import java.util.Map;

public class Options {

    private Map<String,Object> options = new HashMap<>();

    public Options() {
        this.options = new HashMap<>();
    }

    public Options option(Map<String,Object> optionMap) {
        options.putAll(optionMap);
        return this;
    }
    public Options option(String key, Object value) {
        options.put(key, value);
        return this;
        
    }
    public Object get(String key) {
        return options.get(key);
    }

    public <T> T getOrDefault(String key, Object defaultValue, Class<? extends T> type) {
        try {
            return type.cast(options.getOrDefault(key, defaultValue));
        } catch (ClassCastException e) {
            throw new IllegalArgumentException("Chance:"+key+" must be " + type.getSimpleName());
        }
    }   

}
