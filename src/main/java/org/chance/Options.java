package org.chance;

import java.util.Collection;
import java.util.function.Consumer;

public class Options {
    
    private Boolean alpha; 
    private String casing;
    private Integer likelihood;
    private Long min;
    private Long max;
    private Boolean numeric; 
    private Collection<Object> pool;
    public Boolean symbols;


    public enum Casing {
        ANY("any"),
        LOWER("lower"), 
        UPPER("upper");

        private String value;
        private Casing(String value) {
            this.value = value;
        }

        public String value() {
            return this.value;
        }
    }

    public static class Builder {
        
        public Boolean alpha;
        public String casing; 
        public Integer likelihood;
        public Long min;
        public Long max;
        public Boolean numeric;
        public Collection<Object> pool;
        public Boolean symbols;

        public Builder() {

        }

        public Builder alpha(Boolean alpha) {
            this.alpha = alpha;
            return this;
        }  

        public Builder casing(String casing) {
            this.casing = casing;
            return this;
        }

        public Builder likelihood(Integer likelihood) {
            this.likelihood = likelihood;
            return this;
        }

        public Builder min(Long min) {
            this.min = min;
            return this;
        } 

        public Builder max(Long max) {
            this.max = max;
            return this;
        } 
        
        public Builder numeric(Boolean numeric) {
            this.numeric = numeric;
            return this;
        } 

        public Builder pool(Collection<Object> pool) {
            this.pool = pool;
            return this;
        }

        public Builder symbols(Boolean symbols) {
            this.symbols = symbols;
            return this;
        } 

        public Builder with(
            Consumer<Builder> builderFunction) {
            builderFunction.accept(this);
            return this;
        }

        public Options build() {
            return new Options(alpha, casing, likelihood, min, max, numeric, pool, symbols);
        }

    }

    public Boolean alpha() {
        return this.alpha;
    }
    public String casing() {
        return this.casing;
    }

    public Integer likelihood() {
        return this.likelihood;
    }

    public Long min() {
        return this.min;
    }

    public Long max() {
        return this.max;
    }

    public Boolean numeric() {
        return this.numeric;
    }

    public Collection<Object> pool() {
        return this.pool;
    }

    public Boolean symbols() {
        return this.symbols;
    }

    public Options() {}

    public Options(Options defaults) {
        this.likelihood = defaults.likelihood;
    }




    public Options(Options options, Options defaults) {
        this.alpha = options.getAlpha() == null ? defaults.getAlpha() : options.getAlpha();
        this.casing = options.getCasing() == null ? defaults.getCasing() : options.getCasing();
        this.likelihood = options.getLikelihood() == null ? defaults.getLikelihood() : options.getLikelihood();
        this.min = options.getMin() == null ? defaults.getMin() : options.getMin();
        this.max = options.getMax() == null ? defaults.getMax() : options.getMax();
        this.numeric = options.getNumeric() == null ? defaults.getNumeric() : options.getNumeric();
        this.pool = options.getPool() == null ? defaults.getPool() : options.getPool();
    }

    public Options(Boolean alpha, String casing, Integer likelihood, Long min, Long max, Boolean numeric,
            Collection<Object> pool, Boolean symbols) {
        this.alpha = alpha;
        this.casing = casing;
        this.likelihood = likelihood;
        this.min = min;
        this.max = max;
        this.numeric = numeric;
        this.pool = pool;
        this.symbols = symbols;
    }

    public Boolean getAlpha() {
        return alpha;
    }

    public void setAlpha(Boolean alpha) {
        this.alpha = alpha;
    }

    public String getCasing() {
        return casing;
    }

    public void setCasing(String casing) {
        this.casing = casing;
    }

    public Integer getLikelihood() {
        return likelihood;
    }

    public void setLikelihood(Integer likelihood) {
        this.likelihood = likelihood;
    }

    public Long getMin() {
        return min;
    }

    public void setMin(Long min) {
        this.min = min;
    }

    public Long getMax() {
        return max;
    }

    public void setMax(Long max) {
        this.max = max;
    }

    public Boolean getNumeric() {
        return numeric;
    }

    public void setNumeric(Boolean numeric) {
        this.numeric = numeric;
    }

    public Collection<Object> getPool() {
        return pool;
    }

    public void setPool(Collection<Object> pool) {
        this.pool = pool;
    }

    public Boolean getSymbols() {
        return symbols;
    }

    public void setSymbols(Boolean symbols) {
        this.symbols = symbols;
    }

}