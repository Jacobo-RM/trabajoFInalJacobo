package com.farmaciacic;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import com.farmaciacic.demo.Controller.FarmaciaController;
import com.farmaciacic.demo.Models.Marca;
import com.farmaciacic.demo.Service.FarmaciaService;

public class FarmaciaControllerUnitTest {

    @Mock
    private FarmaciaService farmaciaService;

    @InjectMocks
    private FarmaciaController farmaciaController;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void testGetMarcas() {
        Marca marca1 = new Marca(1, "Marca1", "Paris", "2023-02-02", "http://localhost", "Descripción1", "foto1", null);
    
        when(farmaciaService.getMarcas()).thenReturn(List.of(marca1));
        
        List<Marca> marcas = farmaciaController.getMarcas();
    
        assertEquals(1, marcas.size());
        assertEquals(1, marcas.get(0).getId());
        assertEquals("Marca1", marcas.get(0).getNombre());
        assertEquals("Descripción1", marcas.get(0).getDescripcion());
        assertEquals("Paris", marcas.get(0).getPaisOrigen());
        assertEquals("2023-02-02", marcas.get(0).getFechaFundacion());
        assertEquals("http://localhost", marcas.get(0).getSitioWeb());
        assertEquals("foto1", marcas.get(0).getImagenMarca());
        assertEquals(null, marcas.get(0).getMedicamentos());
    
        verify(farmaciaService).getMarcas();
    }
}
