package org.chance;

import java.util.HashMap;
import java.util.Map;

public class Option {

    private Map<String,Object> options = new HashMap<>();

    public Option() {
        this.options = new HashMap<>();
    }

    public static Option chanceOptions() {
        return new Option();
    }

    public static Option chanceOpts() {
        return new Option();
    }

    public Option option(Map<String,Object> optionMap) {
        options.putAll(optionMap);
        return this;
    }
    public Option option(String key, Object value) {
        options.put(key, value);
        return this;
        
    }
    
    public Option option(String key, Boolean value) {
        options.put(key, value);
        return this;
    }

    public Option option(String key, Integer value) {
        options.put(key, value);
        return this;
    }

    public Option option(String key, Double value) {
        options.put(key, value);
        return this;
    }

    public Option option(String key, Float value) {
        options.put(key, value);
        return this;
    }
    
    public Object getValue(String key) {
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

// new Options()
// .option("alpha", true)