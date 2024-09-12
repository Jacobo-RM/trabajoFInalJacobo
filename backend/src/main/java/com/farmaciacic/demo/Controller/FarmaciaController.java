package com.farmaciacic.demo.Controller;

import java.util.List;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;

import com.farmaciacic.demo.Models.Marca;
import com.farmaciacic.demo.Models.Medicamento;


import com.farmaciacic.demo.Service.FarmaciaService;

@RestController
@RequestMapping("/farmacia")
@CrossOrigin(origins = "http://localhost:5173")
public class FarmaciaController {
    

    private final FarmaciaService farmaciaService;

    public FarmaciaController(FarmaciaService farmaciaService) {
        this.farmaciaService = farmaciaService;
    }

    @GetMapping("/marcas")
    public List<Marca> getMarcas() {
        return farmaciaService.getMarcas();
    }

    @GetMapping("/medicamentos")
    public List<Medicamento> getMedicamentos() {
        return farmaciaService.getMedicamentos();
    }

    @GetMapping("/medicamentos/{id}")
    public Medicamento getMedicamentoById(@PathVariable int id) {
        return farmaciaService.getMedicamentoById(id);
    }

    @GetMapping("/marcas/{id}")
    public Marca getMarcaById(@PathVariable int id) {
        return farmaciaService.getMarcaById(id);
    }

    @GetMapping("/medicamentos/marca/{id}")
    public List<Medicamento> getMedicamentoByMarca(@PathVariable int id) {
        return farmaciaService.getMedicamentoByMarca(id);
    }

    @PostMapping("/medicamentos/save")
    public Medicamento saveMedicamento(@RequestBody Medicamento medicamento) {
        return farmaciaService.saveMedicamento(medicamento);
    }

    @PostMapping("/marcas/save")
    public Marca saveMarca(@RequestBody Marca marca) {
        return farmaciaService.saveMarca(marca);
    }

    @DeleteMapping("/marcas/delete/{id}")
    public Marca deleteMarca(@PathVariable int id) {
        return farmaciaService.deleteMarca(id);
    }

    @DeleteMapping("/medicamentos/delete/{id}")
    public Medicamento deleteMedicamento(@PathVariable int id) {
        return farmaciaService.deleteMedicamento(id);
    }

    @PutMapping("/medicamentos/update")
    public Medicamento updateMedicamento(@RequestBody Medicamento medicamento) {
        return farmaciaService.saveMedicamento(medicamento);
    }

    @PutMapping("/marcas/update/{id}")
    public Marca updateMarca(@PathVariable int id, @RequestBody Marca marca) {
        marca.setId(id);
        return farmaciaService.saveMarca(marca);
    }





    

    
    
}
