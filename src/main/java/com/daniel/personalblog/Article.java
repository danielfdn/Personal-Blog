package com.daniel.personalblog;

import jakarta.persistence.*;
import lombok.Data;

import java.time.LocalDate;

@Data
@Entity
@Table(name="articles")
public class Article {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY) //auto-increments the value in PostgreSQL
    private Long id;
    private String title;
    private LocalDate date = LocalDate.now();
    private String text;

    public Article() {};


    public Article(String title, String text) {
        this.title = title;
        this.text = text;
    }

    public String getTitle() {
        return title;
    }

    public LocalDate getDate() {
        return date;
    }

    public String getText() {
        return text;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }

    public void setText(String text) {
        this.text = text;
    }


    public void setId(long id) {
        this.id = id;
    }

    public long getId() {
        return id;
    }



    @Override
    public String toString() {
        return "Article{" +
                "title='" + title + '\'' +
                ", date=" + date +
                ", text='" + text + '\'' +
                '}';
    }
}




