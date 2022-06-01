package com.lpnu.easysmarthome.model;

public enum Building {
    OFFICE("Офіс"), APARTMENT("Квартира"), INDUSTRIAL("Виробничі приміщення"), HOME("Дім"),
    OTHER("Інше");

    private final String name;

    Building(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    @Override
    public String toString() {
        return name;
    }
}
