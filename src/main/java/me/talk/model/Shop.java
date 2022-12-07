package me.talk.model;

import lombok.Data;

import javax.persistence.*;
import java.util.List;

@Entity
@Data
@Table(name = "shops")
public class Shop {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private String name;
    private String details;
    private String image;
    @OneToMany
    @Column(name = "category_id")
    private List<Category> categories;
    @Column(name = "go_to_link")
    private String gotolink;
    @OneToMany
    @Column(name = "offer_id")
    private List<Offer> offers;
}
