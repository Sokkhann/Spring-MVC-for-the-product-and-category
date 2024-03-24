package org.example.springmvcproductcategory.dto.category;

/*
this class use for handle with the client sent request to sever
with the HTTP request. use input, form, text...
in this class it take only two
title and description need user input (from client side)
 */
public record CategoryRequest(
        String title,
        String description
){
}
