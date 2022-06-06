package com.lpnu.easysmarthome.model.enums;

public enum ControlFunctions {
    CLIMATE("Клімат", 700, "https://www.control4.com/files/large/6de6c8addd9f85a"),
    SECURITY("Безпека", 1200, "https://www.digitalhomesystems.com/wp-content/uploads/2019/09/top-benefits-of-a-smart-home-security-system.jpg"),
    LIGHTING("Освітлення", 500, "https://svetilnikof.com.ua/uploads/category-image/kak-razmestit-svetodiodnuiu-lentu-dlia-effektnogo-osveshcheniia.jpg?1521192697924"),
    WINDOWS("Вікна/Штори", 600, "https://sunset.kiev.ua/wp-content/uploads/2020/11/zhalyuzi-svoimi-rukami.jpg"),
    AUDIO("Аудіо/Відео", 900, "https://static7.depositphotos.com/1047404/712/i/450/depositphotos_7123040-stock-photo-home-cinema.jpg"),
    DIFFERENT("Різні сценарії", 300, "https://homehub.com.ua/images/companies/1/categories/GWRENS310F/fibaro-nasil-calisir-b1abb695624-bymdfc.jpg?1615982308028"),
    OTHER("Інше", 500, "https://www.smarthouse.ua/wp-content/uploads/2021/03/drugoe.jpg");

    private final String name;
    private final double price;
    private final String img;

    ControlFunctions(String name, double price, String img) {
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
