package com.cbook.zz.cbookmanager.core.model;

/**
 * Created by zz on 31/5/17.
 */

public class Member implements IEntity{

    private String id;
    private String name;
    private String phone;
    private String address;
    private String email;

    public Member(String id, String name, String phone, String address, String email) {
        this.id = id;
        this.name = name;
        this.phone = phone;
        this.address = address;
        this.email = email;
    }

    public Member(String id){
        this.id = id;
    }

    public Member(){

    }

    public void setName(String name) {
        this.name = name;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getName() {
        return name;
    }

    public String getPhone() {
        return phone;
    }

    public String getAddress() {
        return address;
    }

    public String getEmail() {
        return email;
    }

    @Override
    public String getId() {
        return this.id;
    }
}
