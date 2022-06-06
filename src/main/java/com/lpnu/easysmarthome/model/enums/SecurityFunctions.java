package com.lpnu.easysmarthome.model.enums;

public enum SecurityFunctions {
    LEAK("Захист від протікання", 400, "https://pokupkivinternete.ru/poleznye-sovety/img/48895.jpg"),
    GAS("Захист від протікання газу/диму", 500, "https://www.mrsteam.com/media/3772/basicbutler_hero.jpg"),
    VIDEO("Системи відеонагляду", 1200, "https://sg-ukraine.com.ua/wp-content/uploads/2019/01/20708947.jpg"),
    ALL("Усі системи безпеки", 2600, "https://sunset.kiev.ua/wp-content/uploads/2020/11/zhalyuzi-svoimi-rukami.jpg"),
    AUDIO("Аудіозахист (сирена)", 400, "https://cdn-ru.bitrix24.ru/b18613306/landing/12b/12b07a4060f29b44c6ba04aba56195bb/signalizaciya-s-sirenoy-13-1-870x400_2x_1x.jpg"),
    OTHER("Інше", 500, "https://www.smarthouse.ua/wp-content/uploads/2021/03/drugoe.jpg");

    private final String name;
    private final double price;
    private final String img;

    SecurityFunctions(String name, double price, String img) {
        this.name = name;
        this.price = price;
        this.img = img;
    }

    public double getPrice() {
        return price;
    }

    public String getImg() {
        return img;
    }

    public String getName() {
        return name;
    }

}
