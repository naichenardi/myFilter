package com.korber.myfilter.db.entities;

import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.util.UUID;

@Table(name = "screen")
@Entity
public class Screen {
    @Id
    @Column(name = "id", nullable = false)
    @GenericGenerator(name = "UUIDGenerator", strategy = "uuid2")
    @GeneratedValue(generator = "UUIDGenerator")
    private UUID id;

    @Column(name = "name")
    private String name;

    @Lob
    @Column(name = "contentjson")
    private String contentjson;

    public Screen(String name, String contentjson) {
        this.name = name;
        this.contentjson = contentjson;
    }

    public Screen() {
    }

    public String getContentjson() {
        return contentjson;
    }

    public void setContentjson(String contentjson) {
        this.contentjson = contentjson;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    @Override
    public String toString() {
        return "Screen{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", contentjson='" + contentjson + '\'' +
                '}';
    }
}