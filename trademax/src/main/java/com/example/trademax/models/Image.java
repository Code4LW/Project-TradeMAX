package com.example.trademax.models;

import lombok.*;

import javax.persistence.*;

@Data
@Entity
@Table(name = "image")
public class Image {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    private String fileName;
    private Long size;
    private String contentType;
    private boolean isPreview;


    @Column(columnDefinition = "LONGBLOB")
    private byte [] bytes;
    @ManyToOne(cascade = CascadeType.REFRESH, fetch = FetchType.EAGER)
    private Item item;



    public Image() {
    }

    public boolean isPreview() {
        return isPreview;
    }

    public void setPreview(boolean isPreview) {
        this.isPreview = isPreview;
    }


}
