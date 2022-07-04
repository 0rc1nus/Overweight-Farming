package net.orcinus.overweightfarming.common.util;

public enum OverweightType {

    DEFAULT("default"),
    SIMPLE("simple"),
    SPROUT("sprout"),
    INVERTED("inverted"),
    TALL("tall");

    private final String name;

    OverweightType(String name) {
        this.name = name;
    }

    public String getName() {
        return this.name;
    }
}
