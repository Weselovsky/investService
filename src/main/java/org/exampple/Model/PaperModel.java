package org.exampple.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class PaperModel {
    private long id;
    private String name;
    private String type;
    private String ticker;
    private int price;
    private int qty;
    private int profit;
    private String sector;
    private String image;
}
