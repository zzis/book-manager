package com.cbook.zz.cbookmanager.core.model;

import org.json.JSONException;
import org.json.JSONObject;

/**
 * Created by zz on 28/5/17.
 */

public class Book implements IEntity {
    private String ISBNId;
    private String author;
    private String publishDate;
    private String bookTitle;
    private String price;
    private int status;

    public Book(String ISBNId, String bookTitle, String author, String price, int status) {
        super();
        this.ISBNId = ISBNId;
        this.author = author;
//        this.publishDate = publishDate;
        this.bookTitle = bookTitle;
        this.price = price;
        this.status = status;

    }

    public Book(){

    }



    public JSONObject toJSON(){
        JSONObject json = new JSONObject();
        try {
            json.put("ISBN", this.ISBNId);
            json.put("price", this.price);
            json.put("bookTitle", this.bookTitle);
            json.put("author", this.author);
            json.put("status", this.status);
        } catch (JSONException e) {
            e.printStackTrace();
        }

        return json;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public int getStatus() {
        return status;
    }

    public void setPrice(String price) {
        this.price = price;
    }

    public String getPrice() {
        return price;
    }

    public void setISBNId(String ISBNId) {
        this.ISBNId = ISBNId;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public void setPublishDate(String publishDate) {
        this.publishDate = publishDate;
    }

    public void setBookTitle(String bookTitle) {
        this.bookTitle = bookTitle;
    }

    public String getISBNId() {
        return ISBNId;
    }

    public String getAuthor() {
        return author;
    }

    public String getPublishDate() {
        return publishDate;
    }

    public String getBookTitle() {
        return bookTitle;
    }

    @Override
    public String getId() {
        return null;
    }
}
