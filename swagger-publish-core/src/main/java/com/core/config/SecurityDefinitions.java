package com.core.config;

/**
 * Created by shubham.tyagi on 08/05/15.
 */
public class SecurityDefinitions {

    private String name;
    private String type;
    private String json;

    public void setName(String name) {
        this.name = name;
    }

    public void setType(String type) {
        this.type = type;
    }

    public void setJson(String json) {
        this.json = json;
    }

    public String getName() { return name; }

    public String getType() { return type; }

    public String getJson() { return json; }

}

