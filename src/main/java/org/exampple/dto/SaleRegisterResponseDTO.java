package org.exampple.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class SaleRegisterResponseDTO {
    private Sale sale;

    @NoArgsConstructor
    @AllArgsConstructor
    @Data
    public static class Sale {
        private long id;
        private long paperId;
        private String name;
        private int price;
        private int qty;


    }

}
