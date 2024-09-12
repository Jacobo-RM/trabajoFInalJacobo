package com.farmaciacic.demo.Service;



import java.util.List;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.farmaciacic.demo.Models.Marca;
import com.farmaciacic.demo.Models.Medicamento;
import com.farmaciacic.demo.Repository.MarcaRepository;
import com.farmaciacic.demo.Repository.MedicamentoRepository;



@Service
@Transactional
public class FarmaciaService {

    private final MedicamentoRepository medicamentoRepository;
    private final MarcaRepository marcaRepository;

    public FarmaciaService(MedicamentoRepository medicamentoRepository, MarcaRepository marcaRepository) {
        this.medicamentoRepository = medicamentoRepository;
        this.marcaRepository = marcaRepository;
    }

    public List<Marca> getMarcas() {
        return marcaRepository.findAll();
    }
    
    public List<Medicamento> getMedicamentos() {
        return medicamentoRepository.findAll();
    }

    public Medicamento getMedicamentoById(int id) {
        return medicamentoRepository.findById(id).orElse(null);
    }

    public Marca getMarcaById(int id) {
        return marcaRepository.findById(id).orElse(null);
    }

    @Transactional
    public List<Medicamento> getMedicamentoByMarca(int id) {
        Marca marca = marcaRepository.findById(id).orElse(null);
        return medicamentoRepository.findByMarca(marca);

    }

    public Medicamento saveMedicamento(Medicamento medicamento) {
        return medicamentoRepository.save(medicamento);
    }

    public Marca saveMarca(Marca marca) {
        return marcaRepository.save(marca);
    }

    public Marca deleteMarca(int id) {
        Marca marca = marcaRepository.findById(id).orElse(null);
        if (marca != null) {
            marcaRepository.delete(marca);
        }
        return marca;
    }

    public Medicamento deleteMedicamento(int id) {
        Medicamento medicamento = medicamentoRepository.findById(id).orElse(null);
        if (medicamento != null) {
            medicamentoRepository.delete(medicamento);
        }
        return medicamento;
    }

    public Marca updateMarca(int id) {
        Marca marca = marcaRepository.findById(id).orElse(null);
        if (marca != null) {
            marcaRepository.save(marca);
        }
        return marca;
    };

    public Medicamento updateMedicamento(Medicamento medicamento) {
        return medicamentoRepository.save(medicamento);
    }

    



    
}
