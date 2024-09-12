package com.farmaciacic.demo.Repository;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.farmaciacic.demo.Models.Marca;

@Repository
public interface MarcaRepository extends JpaRepository<Marca, Integer> {
}