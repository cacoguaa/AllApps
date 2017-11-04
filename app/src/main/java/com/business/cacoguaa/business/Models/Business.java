package com.business.cacoguaa.business.Models;

/**
 * Created by Ace on 4/11/2017.
 */

public class Business {

    private long id;
    private String name;
    private String url;
    private String phone;
    private String email;
    private String description;
    private String type;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public Business(long id, String name, String url, String phone, String email, String description, String type) {
        this.id = id;
        this.name = name;
        this.url = url;
        this.phone = phone;
        this.email = email;
        this.description = description;
        this.type = type;
    }

    public Business(){

    }

    public String toString(){
        return "id: " + getId()+ "\n" +
                "Nombre: " + getName() + "\n" +
                "Url: " + getUrl() + "\n" +
                "Descripción: " + getDescription() + "\n" +
                "Tipo: " + getType() + "\n" +
                "Telefono: " + getPhone() + "\n" +
                "Email: " + getEmail() + "\n";
    }
}
