package org.exampple;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.testcontainers.containers.DockerComposeContainer;
import org.testcontainers.junit.jupiter.Container;
import org.testcontainers.junit.jupiter.Testcontainers;

import java.nio.file.Path;

@Testcontainers
@SpringBootTest
@DirtiesContext
@AutoConfigureMockMvc

class CRUDTest {
    @Container
    static DockerComposeContainer<?> compose = new DockerComposeContainer<>(
            Path.of("docker-compose.yml").toFile()
    );
    @Autowired
    MockMvc mockMvc;

    @Test
    void shouldPerformCRUD() throws Exception {
        // get all
        mockMvc.perform(
                        MockMvcRequestBuilders.get("/papers/getAll")
                )
                .andExpectAll(
                        MockMvcResultMatchers.status().isOk(),
                        MockMvcResultMatchers.content().json(
                                // language=JSON
                                """
                                        {
                                          "papers": [
                                            {
                                              "id": 1,
                                              "name": "Apple",
                                              "type": "Stock",
                                              "ticker": "AAPL",
                                              "price": 15960,
                                              "qty": 11,
                                              "profit": 27,
                                              "sector": "InfTech",
                                              "image": "noimage.png"
                                            },
                                            {
                                              "id": 2,
                                              "name": "Intel",
                                              "type": "Stock",
                                              "ticker": "INTC",
                                              "price": 48,
                                              "qty": 12,
                                              "profit": -20,
                                              "sector": "InfTech",
                                              "image": "noimage.png"
                                            },
                                            {
                                              "id": 3,
                                              "name": "СберБанк",
                                              "type": "Stock",
                                              "ticker": "SBER",
                                              "price": 325,
                                              "qty": 13,
                                              "profit": 16,
                                              "sector": "Finance",
                                              "image": "noimage.png"
                                            },
                                            {
                                              "id": 4,
                                              "name": "NASDAQ",
                                              "type": "Found",
                                              "ticker": "TECH",
                                              "price": 1,
                                              "qty": 13,
                                              "profit": 14,
                                              "sector": "KKK",
                                              "image": "noimage.png"
                                            },
                                            {
                                              "id": 5,
                                              "name": "S&P500",
                                              "type": "Found",
                                              "ticker": "TSPX",
                                              "price": 2,
                                              "qty": 16,
                                              "profit": 15,
                                              "sector": "LLL",
                                              "image": "noimage.png"
                                            },
                                            {
                                              "id": 6,
                                              "name": "IMOEX",
                                              "type": "Found",
                                              "ticker": "TMOS",
                                              "price": 7,
                                              "qty": 18,
                                              "profit": 16,
                                              "sector": "MMM",
                                              "image": "noimage.png"
                                            },
                                            {
                                              "id": 7,
                                              "name": "ВИТА",
                                              "type": "Bond",
                                              "ticker": "RU000A101DU4",
                                              "price": 995,
                                              "qty": 17,
                                              "profit": 11,
                                              "sector": "Medicine",
                                              "image": "noimage.png"
                                            },
                                            {
                                              "id": 8,
                                              "name": "ОФЗ 26227",
                                              "type": "Bond",
                                              "ticker": "SU26227RMFS7",
                                              "price": 972,
                                              "qty": 18,
                                              "profit": 9,
                                              "sector": "Governments",
                                              "image": "noimage.png"
                                            },
                                            {
                                              "id": 9,
                                              "name": "Газпром",
                                              "type": "Bond",
                                              "ticker": "RU000A0JXFS8",
                                              "price": 992,
                                              "qty": 19,
                                              "profit": 9,
                                              "sector": "Energetics",
                                              "image": "noimage.png"
                                            }
                                          ]
                                        }
                                        """
                        )
                );
    }
}
