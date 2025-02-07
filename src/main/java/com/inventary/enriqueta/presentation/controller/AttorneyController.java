package com.inventary.enriqueta.presentation.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import com.inventary.enriqueta.domain.model.Attorney;
import com.inventary.enriqueta.application.service.AttorneyService;
import reactor.core.publisher.Flux;

@CrossOrigin(origins = "*")
@RestController
@RequestMapping("/public/attorney${api.version}")
public class AttorneyController {

    private final AttorneyService attorneyService;

    @Autowired
    public AttorneyController(AttorneyService attorneyService) {
        this.attorneyService = attorneyService;
    }

    @GetMapping
    public Flux<Attorney> getAllAttorney() {
        return attorneyService.listAllActive();
    }
}