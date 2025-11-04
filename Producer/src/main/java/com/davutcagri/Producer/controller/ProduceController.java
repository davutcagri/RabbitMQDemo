package com.davutcagri.Producer.controller;

import com.davutcagri.Producer.service.ProduceService;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ProduceController {

    private final ProduceService produceService;

    public ProduceController(ProduceService produceService) {
        this.produceService = produceService;
    }

    @PostMapping("/sendMessage")
    public void sendMessage(@RequestBody String message) {
        produceService.produceMessage(message);
    }
}
