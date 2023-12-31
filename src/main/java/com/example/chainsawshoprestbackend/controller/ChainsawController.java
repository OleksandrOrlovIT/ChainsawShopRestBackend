package com.example.chainsawshoprestbackend.controller;

import com.example.chainsawshoprestbackend.model.Chainsaw;
import com.example.chainsawshoprestbackend.services.ChainsawService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;

@RestController
public class ChainsawController {
    private final ChainsawService chainsawService;

    public ChainsawController(ChainsawService chainsawService) {
        this.chainsawService = chainsawService;
    }

    @GetMapping("/chainsaws")
    public List<Chainsaw> retrieveChainsaws(){
        return chainsawService.findAll();
    }

    @GetMapping("/chainsaw/{id}")
    public Chainsaw retrieveChainsawById(@PathVariable Long id){
        return chainsawService.findById(id);
    }

    @DeleteMapping("/chainsaw/{id}")
    public ResponseEntity<Void> deleteChainsaw(@PathVariable Long id){
        chainsawService.deleteById(id);
        return ResponseEntity.noContent().build();
    }

    @PutMapping("/chainsaw/{id}")
    public Chainsaw updateChainsaw(@PathVariable Long id, @RequestBody Chainsaw chainsaw){
        if(!chainsaw.getId().equals(id) || chainsawService.findById(id) == null)
            return null;
        return chainsawService.save(chainsaw);
    }

    @PostMapping("/chainsaw")
    public ResponseEntity<Chainsaw> createChainsaw(@RequestBody Chainsaw chainsaw){
        chainsaw.setId(null);
        Chainsaw savedChainsaw = chainsawService.save(chainsaw);

        URI location = ServletUriComponentsBuilder.fromCurrentRequest()
                .path("/{id}")
                .buildAndExpand(savedChainsaw.getId())
                .toUri();

        return ResponseEntity.created(location).build();
    }
}
