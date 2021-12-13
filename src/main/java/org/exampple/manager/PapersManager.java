package org.exampple.manager;

import lombok.RequiredArgsConstructor;
import org.exampple.Model.PaperModel;
import org.exampple.dto.*;
import org.exampple.exception.PaperNoFoundException;
import org.exampple.rowmapper.PaperRowMapper;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Component
@RequiredArgsConstructor
public class PapersManager {
    private final NamedParameterJdbcTemplate template;
    private final PaperRowMapper paperRowMapper;
    private final String defaultImage = "noimage.png";

    public PaperGetAllResponseDTO getAll() {
        // language=PostgreSQL
        final List<PaperModel> items = template.query("""
                        SELECT id, name, type, ticker, price, qty, profit, sector, image
                        FROM papers
                        WHERE removed = FALSE
                        ORDER BY id
                        LIMIT 100
                        """,
                paperRowMapper
        );
        final PaperGetAllResponseDTO responseDTO = new PaperGetAllResponseDTO(new ArrayList<>(items.size()));
        for (PaperModel item : items) {
            responseDTO.getPapers().add(new PaperGetAllResponseDTO.Paper(
                    item.getId(),
                    item.getName(),
                    item.getType(),
                    item.getTicker(),
                    item.getPrice(),
                    item.getQty(),
                    item.getProfit(),
                    item.getSector(),
                    item.getImage()

            ));
        }
        return responseDTO;
    }






    public PaperGetByIdResponseDTO getById(long id) {
        final PaperModel item = template.queryForObject(
                // language=PostgreSQL
                """
                        SELECT id, name, type, ticker, price, qty, profit, sector, image 
                        FROM papers
                        WHERE id = :id AND removed = FALSE
                        """,
                Map.of("id", id),
                paperRowMapper
        );
        final PaperGetByIdResponseDTO responseDTO = new PaperGetByIdResponseDTO(new PaperGetByIdResponseDTO.Paper(
                item.getId(),
                item.getName(),
                item.getType(),
                item.getTicker(),
                item.getPrice(),
                item.getQty(),
                item.getProfit(),
                item.getSector(),
                item.getImage()

        ));
        return responseDTO;
    }

    public PaperCreateResponseDTO save(PaperCreateRequestDTO requestDTO) {
        return requestDTO.getId() == 0 ? add(requestDTO) : update(requestDTO);
    }

    private PaperCreateResponseDTO add(PaperCreateRequestDTO requestDTO) throws PaperNoFoundException {
        final PaperModel item = template.queryForObject(
                // language=PostgreSQL
                """
                        INSERT INTO papers (name, type, ticker, price, qty, profit, sector, image)
                        VALUES (:name, :type, :ticker, :price, :qty, :profit, :sector, :image)     
                        RETURNING id, name, type, ticker, price, qty, profit, sector, image
                        """,
                Map.of(
                        "name", requestDTO.getName(),
                        "type", requestDTO.getType(),
                        "ticker", requestDTO.getTicker(),
                        "price", requestDTO.getPrice(),
                        "qty", requestDTO.getQty(),
                        "profit", requestDTO.getProfit(),
                        "sector", requestDTO.getSector(),
                        "image", requestDTO.getImage() == null ? defaultImage : requestDTO.getImage()
                ),
                paperRowMapper
        );

        final PaperCreateResponseDTO responseDTO = new PaperCreateResponseDTO(new PaperCreateResponseDTO.Paper(
                item.getId(),
                item.getName(),
                item.getType(),
                item.getTicker(),
                item.getPrice(),
                item.getQty(),
                item.getProfit(),
                item.getSector(),
                item.getImage()
        ));
        return responseDTO;
    }

    private PaperCreateResponseDTO update(PaperCreateRequestDTO requestDTO) {
        try {
            final PaperModel item = template.queryForObject(
                    // language=PostgreSQL
                    """
                            UPDATE papers 
                            SET name= :name, 
                            type=:type, 
                            ticker= :ticker, 
                            price= :price, 
                            qty=:qty,
                            profit= :profit, 
                            sector=:sector, 
                            image=:image   
                            WHERE id = :id 
                            AND removed = FALSE
                            RETURNING name, type, ticker, price, profit, sector, image
                             """,
                    Map.of(
                            "id", requestDTO.getId(),
                            "name", requestDTO.getName(),
                            "type", requestDTO.getType(),
                            "ticker", requestDTO.getTicker(),
                            "price", requestDTO.getPrice(),
                            "qty", requestDTO.getQty(),
                            "profit", requestDTO.getProfit(),
                            "sector", requestDTO.getSector(),
                            "image", requestDTO.getImage() == null ? defaultImage : requestDTO.getImage()
                    ),
                    paperRowMapper
            );
            final PaperCreateResponseDTO responseDTO = new PaperCreateResponseDTO(new PaperCreateResponseDTO.Paper(
                    item.getId(),
                    item.getName(),
                    item.getType(),
                    item.getTicker(),
                    item.getPrice(),
                    item.getQty(),
                    item.getProfit(),
                    item.getSector(),
                    item.getImage()
            ));
            return responseDTO;
        } catch (EmptyResultDataAccessException e) {
            throw new PaperNoFoundException(e);
        }
    }

    public void removeById(long id) {
        final int affected = template.update(
                // language=PostgreSQL
                """
                        UPDATE papers
                        SET removed=TRUE
                        WHERE id= :id
                        """,
                Map.of("id", id)
        );
        if (affected == 0) {
            throw new PaperNoFoundException("paper with id " + id + " not found");
        }
    }


    public PaperGetByNameResponseDTO getByName(String name) {
        final PaperModel item = template.queryForObject(
                // language=PostgreSQL
                """
                        SELECT id, name, type, ticker, price, qty, profit, sector, image 
                                                FROM papers
                                                WHERE name = :name AND removed = FALSE
                        """,
                Map.of("name", name),
                paperRowMapper
        );
        final PaperGetByNameResponseDTO responseDTO = new PaperGetByNameResponseDTO(new PaperGetByNameResponseDTO.Paper(
                item.getId(),
                item.getName(),
                item.getType(),
                item.getTicker(),
                item.getPrice(),
                item. getQty(),
                item.getProfit(),
                item.getSector(),
                item.getImage()
        ));
        return responseDTO;
    }



}