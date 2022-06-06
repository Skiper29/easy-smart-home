package com.lpnu.easysmarthome.model.enums;

public enum Building {
    OFFICE("Офіс", 0.95, "https://t4.ftcdn.net/jpg/03/84/55/29/360_F_384552930_zPoe9zgmCF7qgt8fqSedcyJ6C6Ye3dFs.jpg"),
    APARTMENT("Квартира", 1.05, "https://dmn-dallas-news-prod.cdn.arcpublishing.com/resizer/dn9JmbyHvw0DTUWlpE8V9Z0K4lU=/1660x934/smart/filters:no_upscale()/cloudfront-us-east-1.images.arcpublishing.com/dmn/S4ZX2DV7BZDW5HW54OMMISE4Y4.jpg"),
    INDUSTRIAL("Виробничі приміщення", 1.05, "https://5.imimg.com/data5/FL/TW/MY-14744625/industrial-flooring-500x500.jpg"),
    HOME("Дім", 1, "https://media.istockphoto.com/photos/brown-two-story-all-american-home-picture-id1158713117?k=20&m=1158713117&s=612x612&w=0&h=s_aoDM4KNoixI9qBLmJOBPMccoWsC11zxuBGGgFRiKY="),
    OTHER("Інше", 1.1, "https://www.smarthouse.ua/wp-content/uploads/2021/03/drugoe.jpg");

    private final String name;
    private final double coefficient;
    private final String img;

    Building(String name, double coefficient, String img) {
        this.name = name;
        this.coefficient = coefficient;
        this.img = img;
    }

    public double getCoefficient() {
        return coefficient;
    }

    public String getName() {
        return name;
    }

    public String getImg() {
        return img;
    }

}
