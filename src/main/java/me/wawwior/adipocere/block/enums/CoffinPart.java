package me.wawwior.adipocere.block.enums;

import net.minecraft.util.StringIdentifiable;

public enum CoffinPart implements StringIdentifiable {

    HEAD("head"),
    FOOT("foot")
    ;

    private final String name;

    CoffinPart(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return name;
    }

    @Override
    public String asString() {
        return name;
    }
}
