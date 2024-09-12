package com.farmaciacic.demo.Repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.farmaciacic.demo.Models.Marca;
import com.farmaciacic.demo.Models.Medicamento;

@Repository
public interface MedicamentoRepository extends JpaRepository<Medicamento, Integer> {

    List<Medicamento> findByMarca(Marca marca);
}