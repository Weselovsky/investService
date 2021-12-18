package org.exampple.controller;

import lombok.RequiredArgsConstructor;
import org.exampple.dto.SaleRegisterRequestDTO;
import org.exampple.dto.SaleRegisterResponseDTO;
import org.exampple.manager.SaleManager;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/sales")
@RequiredArgsConstructor
public class SaleController {
private final SaleManager manager;

@PostMapping ("/register")
    public SaleRegisterResponseDTO register (@RequestBody SaleRegisterRequestDTO requestDTO){
    return manager.register(requestDTO);
}

}
