package org.exampple.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class SalePaperModel {
    private long id;
    private String name;
    private int price;
    private int qty;

}
