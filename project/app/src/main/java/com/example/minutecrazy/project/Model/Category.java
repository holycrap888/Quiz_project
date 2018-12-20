package com.example.minutecrazy.project.Model;

public class Category {
    private String id;
    private String name;
    private String Image;
//localhost
    public static final String BASE_URL = "http://172.20.10.4/project/";


    public Category() {
    }

    public Category(String id,String name, String image) {
        this.id = id;
        this.name = name;
        this.Image = image;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getImage() {
        return Image;
    }

    public void setImage(String image) {
        Image = image;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }
}
