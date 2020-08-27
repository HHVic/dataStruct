package com.huan.model;

public class Key {
    protected int value;

    public Key(int value) {
        this.value = value;
    }

    @Override
    public boolean equals(Object obj) {
        if(this == obj) return true;
        if(obj == null || this.getClass() != obj.getClass()) return false;
        return this.value == ((Key)obj).value;
    }

    @Override
    public int hashCode() {
        return value / 10;
    }

    @Override
    public String toString() {
        return "V(" + value + ")";
    }
}
