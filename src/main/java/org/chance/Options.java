package org.chance;

public class Options {
    
    private Integer likelihood;

    public static class Builder {
        public Integer likelihood;

        public Builder() {

        }

        public Builder likelihood(Integer likelihood) {
            this.likelihood = likelihood;
            return this;
        }

        public Options build() {
        
            Options options = new Options();  //Since the builder is in the Bankoptions class, we can invoke its private constructor.
            options.likelihood = this.likelihood;
            return options;
        }
    }

    public Integer likelihood() {
        return this.likelihood;
    }
    public Options() {}

    public Options(Options defaults) {
        this.likelihood = defaults.likelihood;
    }

    public Options(Options options, Options defaults) {
        
        this.likelihood = defaults.likelihood;
        this.likelihood = options.likelihood;
    }

}