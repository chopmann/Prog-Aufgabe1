package de.proglabor.aufgabe4.config;

/**
 * @author id261708
 */
public enum WeltConfig {

    DAYS("Days"                             , 30),
    WIDHT("Width"                           , 20),
    HEIGHT("Height"                         , 20),
    JUNGLE_WIDTH("Jungle Width"             , 10),
    JUNGLE_HEIGHT("Jungle Height"           , 10),
    PLANTENERGY("Plant Energy"              , 5),
    INITIALTENERGY("Initial Energy"         , 5),
    REPRODUCTIONENERGY("Reproduction Energy", 5);

    private String displayname;
    private int defaultValue;

    private WeltConfig(String displayname) {
        this.displayname = displayname;
        this.defaultValue = 15;
    }

    WeltConfig(String displayname, int defaultValue) {
        this.displayname = displayname;
        this.defaultValue = defaultValue;
    }

    @Override
    public String toString() {
        return displayname;
    }

    public int getDefaultValue(){
        return defaultValue;
    }

}
