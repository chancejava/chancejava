package org.chance.types;

public class Month {

    private String name; 
    private String shortName; 
    private String numeric; 
    private Integer days;

    public String getName() {
        return name;
    }

    public String getShortName() {
        return shortName;
    }

    public String getNumeric() {
        return numeric;
    }

    public Integer getDays() {
        return days;
    }

    public Month(
        String name, 
        String shortName, 
        String numeric, 
        Integer days
    ) {
        this.name = name;
        this.shortName = shortName;
        this.numeric = numeric;
        this.days = days;
    }
    
    
}