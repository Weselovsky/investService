package org.exampple.Model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class SaleModel {
    private long id;
    private long paper_id;
    private String name;
    private int price;
    private int qty;
}