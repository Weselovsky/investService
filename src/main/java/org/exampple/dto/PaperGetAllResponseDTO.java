package org.exampple.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class PaperGetAllResponseDTO {
    private List<Paper> papers;

    @NoArgsConstructor
    @AllArgsConstructor
    @Data
    public static class Paper {
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
}
