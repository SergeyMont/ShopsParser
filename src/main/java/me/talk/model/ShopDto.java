package me.talk.model;

import lombok.Data;

import java.util.List;

@Data
public class ShopDto {
    private int id;
    private String name;
    private String details;
    private String image;
    private List<CategoryDto> categories;
    private String gotolink;
    private String products_xml_link;
}
