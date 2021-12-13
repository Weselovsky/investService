package org.exampple.manager;

import lombok.RequiredArgsConstructor;
import org.exampple.Model.SaleModel;
import org.exampple.Model.SalePaperModel;
import org.exampple.dto.SaleRegisterRequestDTO;
import org.exampple.dto.SaleRegisterResponseDTO;
import org.exampple.exception.PaperNoFoundException;
import org.exampple.exception.PaperPriceChangedExeption;
import org.exampple.exception.PaperQtyNotEnoughExeption;
import org.exampple.exception.SaleRegistrationException;
import org.exampple.rowmapper.SalePaperRowMapper;
import org.exampple.rowmapper.SaleRowMapper;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.util.Map;

@Component
@RequiredArgsConstructor
public class SaleManager {
    private final NamedParameterJdbcTemplate template;
    private final SalePaperRowMapper salePaperRowMapper;
    private final SaleRowMapper saleRowMapper;

    @Transactional
    public SaleRegisterResponseDTO register(SaleRegisterRequestDTO requestDTO) {
        try {
            final SalePaperModel item = template.queryForObject(
                    // language=PostgreSQL
                    """
                            SELECT id, name, price, qty, type
                            FROM papers
                            WHERE id = :id AND removed = FALSE
                            """,
                    Map.of("id", requestDTO.getPaperId()),
                    salePaperRowMapper
            );


            if (item.getQty() < requestDTO.getQty()) {
                throw new PaperQtyNotEnoughExeption("paper with id " + item.getId() + " has qty" + item.getQty() + "but requested" + requestDTO.getQty());
            }

            if (item.getPrice() != requestDTO.getPrice()) {
                throw new PaperPriceChangedExeption("paper with id " + item.getId() + " has price" + item.getPrice() + "but requested" + requestDTO.getPrice());
            }

            if (requestDTO.getType().equals("sale")) {
                final int affected = template.update(
                        // language=PostgreSQL
                        """
                                UPDATE papers SET qty = qty - :saleQty 
                                WHERE id = :productId 
                                AND 
                                removed = FALSE
                                """,
                        Map.of(
                                "productId", requestDTO.getPaperId(),
                                "saleQty", requestDTO.getQty()
                        )
                );
                if (affected == 0) {
                    throw new SaleRegistrationException("can't update qty with value " + requestDTO.getQty() + " for product with id " + requestDTO.getPaperId());
                }
            }
            if (requestDTO.getType().equals("buy")) {
                final int affected = template.update(
                        // language=PostgreSQL
                        """
                                UPDATE papers SET qty = qty + :saleQty 
                                WHERE id = :productId 
                                AND 
                                removed = FALSE
                                """,
                        Map.of(
                                "productId", requestDTO.getPaperId(),
                                "saleQty", requestDTO.getQty()
                        )
                );
                if (affected == 0) {
                    throw new SaleRegistrationException("can't update qty with value " + requestDTO.getQty() + " for product with id " + requestDTO.getPaperId());
                }
            }






            final SaleModel sale = template.queryForObject(
                    // language=PostgreSQL
                    """
                            INSERT INTO sales (paper_id, name, price, qty, type) 
                            VALUES (:paper_id, :name, :price, :qty, :type)
                            RETURNING id, paper_id, name, price, qty, type
                            """,
                    Map.of(
                            "paper_id", requestDTO.getPaperId(),
                            "name", item.getName(),
                            "price", requestDTO.getPrice(),
                            "qty", requestDTO.getQty()
                    ),
                    saleRowMapper
            );
            final SaleRegisterResponseDTO responseDTO = new SaleRegisterResponseDTO(new SaleRegisterResponseDTO.Sale(

                    sale.getId(),
                    sale.getPaper_id(),
                    sale.getName(),
                    sale.getPrice(),
                    sale.getQty()
            ));

            return responseDTO;


        } catch (EmptyResultDataAccessException e) {
            throw new PaperNoFoundException(e);
        }




    }
}
