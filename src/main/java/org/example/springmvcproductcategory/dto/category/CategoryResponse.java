package org.example.springmvcproductcategory.dto.category;

import lombok.Builder;

/*
this class handle with the response from server to user(client)
in HTTP response. They fulfill the client request
in this class responses into three fields
id, title, and description
 */
@Builder
public record CategoryResponse(
        int id,
        String title,
        String description
) {
}
