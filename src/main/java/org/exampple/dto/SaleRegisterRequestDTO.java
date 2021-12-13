package org.exampple.dto;

import jdk.jfr.DataAmount;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class SaleRegisterRequestDTO {
    private long paperId;
    private int price;
    private int qty;
    private String type;
}
