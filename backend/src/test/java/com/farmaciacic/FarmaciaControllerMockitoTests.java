package com.farmaciacic;


import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;

import java.util.Arrays;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.farmaciacic.demo.Controller.FarmaciaController;
import com.farmaciacic.demo.Models.Marca;
import com.farmaciacic.demo.Models.Medicamento;
import com.farmaciacic.demo.Service.FarmaciaService;

@WebMvcTest(FarmaciaController.class)
public class FarmaciaControllerMockitoTests {

    @Autowired
    private MockMvc mvc;

    @Autowired
    private ObjectMapper objectMapper;

    @MockBean
    private FarmaciaService farmaciaService;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testGetMarcas() throws Exception {
        Marca marca1 = new Marca();
        marca1.setId(1);
        marca1.setNombre("Marca 1");
        marca1.setDescripcion("Descripción 1");
        marca1.setPaisOrigen("País 1");
        marca1.setFechaFundacion("2023-02-02");
        marca1.setSitioWeb("http://localhost");
        marca1.setImagenMarca("foto1");


        when(farmaciaService.getMarcas()).thenReturn(Arrays.asList(marca1));

        mvc.perform(get("/farmacia/marcas")
                .contentType(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.length()").value(1))
                .andExpect(jsonPath("$[0].id").value(1))
                .andExpect(jsonPath("$[0].nombre").value("Marca 1"))
                .andExpect(jsonPath("$[0].descripcion").value("Descripción 1"))
                .andExpect(jsonPath("$[0].paisOrigen").value("País 1"))
                .andExpect(jsonPath("$[0].fechaFundacion").value("2023-02-02"))
                .andExpect(jsonPath("$[0].sitioWeb").value("http://localhost"))
                .andExpect(jsonPath("$[0].imagenMarca").value("foto1"));
    }

    @Test
    void testGetMedicamentos() throws Exception {
        Medicamento medicamento1 = new Medicamento();
        medicamento1.setId(1);
        medicamento1.setNombre("Medicamento 1");
        medicamento1.setDescripcion("Descripción 1");

        when(farmaciaService.getMedicamentos()).thenReturn(Arrays.asList(medicamento1));

        mvc.perform(get("/farmacia/medicamentos")
                .contentType(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.length()").value(1))
                .andExpect(jsonPath("$[0].id").value(1))
                .andExpect(jsonPath("$[0].nombre").value("Medicamento 1"));
    }

    @Test
    void testGetMedicamentoById() throws Exception {
        Medicamento medicamento = new Medicamento();
        medicamento.setId(1);
        medicamento.setNombre("Medicamento 1");

        when(farmaciaService.getMedicamentoById(1)).thenReturn(medicamento);

        mvc.perform(get("/farmacia/medicamentos/1")
                .contentType(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value(1))
                .andExpect(jsonPath("$.nombre").value("Medicamento 1"));
    }

    @Test
    void testGetMarcaById() throws Exception {
        Marca marca = new Marca();
        marca.setId(1);
        marca.setNombre("Marca 1");

        when(farmaciaService.getMarcaById(1)).thenReturn(marca);

        mvc.perform(get("/farmacia/marcas/1")
                .contentType(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value(1))
                .andExpect(jsonPath("$.nombre").value("Marca 1"));
    }

    @Test
    void testSaveMedicamento() throws Exception {
        Medicamento medicamento = new Medicamento();
        medicamento.setId(1);
        medicamento.setNombre("Medicamento 1");

        when(farmaciaService.saveMedicamento(any(Medicamento.class))).thenReturn(medicamento);

        String medicamentoJson = objectMapper.writeValueAsString(medicamento);

        mvc.perform(post("/farmacia/medicamentos/save")
                .contentType(MediaType.APPLICATION_JSON)
                .content(medicamentoJson))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value(1))
                .andExpect(jsonPath("$.nombre").value("Medicamento 1"));
    }
    
}