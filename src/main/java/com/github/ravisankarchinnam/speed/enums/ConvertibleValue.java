package com.github.ravisankarchinnam.speed.enums;

public enum ConvertibleValue
{
    YES(true), NO(false);

    private final boolean value;


    ConvertibleValue(boolean value)
    {
        this.value = value;
    }


    public boolean value()
    {
        return value;
    }
}
