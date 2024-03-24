package org.example.springmvcproductcategory.model;

import lombok.*;
@Data
@Builder // set and get data in this class
@NoArgsConstructor // no arguments constructor
@AllArgsConstructor // all arguments constructor
public class Category {
    private int id;
    private String title;
    private String description;
}
