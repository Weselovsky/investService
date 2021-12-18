package org.exampple.controller;

import lombok.RequiredArgsConstructor;
import org.exampple.dto.*;
import org.exampple.manager.PapersManager;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping("/papers")
@RequiredArgsConstructor
public class PapersController {
    private final PapersManager manager;

    @GetMapping("/getById/{id}")
    public PaperGetByIdResponseDTO getById(@PathVariable long id){

        return manager.getById(id);
    }

    @GetMapping("/getAll")
    public PaperGetAllResponseDTO getAll() {

        return manager.getAll();
    }

    @PostMapping ("/save")
    public PaperCreateResponseDTO save(@RequestBody PaperCreateRequestDTO requestDTO){
        return manager.save(requestDTO);
    }

    @PostMapping("/removeById/{id}")
    public void removeById (@PathVariable long id){
        manager.removeById(id);
    }


    @GetMapping("/getByName/{name}")
    public PaperGetByNameResponseDTO getByName(@PathVariable String name){

        return manager.getByName(name);
    }




}
