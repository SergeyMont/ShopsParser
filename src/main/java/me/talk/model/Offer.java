package me.talk.model;

import lombok.Data;

import javax.persistence.*;

@Entity
@Data
@Table(name = "offers")
public class Offer {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private String name;
    private String model;
    private Double price;
    private String picture;
    private String url;
}
