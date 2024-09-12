package com.farmaciacic.demo.Models;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;

@Entity
@Table(name = "marcas")
public class Marca {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private int id;

    @Column(name = "nombre", nullable = false, length = 255)
    private String nombre;

    @Column(name = "pais_origen", length = 255)
    private String paisOrigen;

    @Column (name = "fecha_fundacion", length = 10)
    private String fechaFundacion;

    @Column(name = "sitio_web", length = 255)
    private String sitioWeb;

    @Column(name = "descripcion", length = 255)
    private String descripcion;

    @Column(name = "imagen_marca", length = 255)
    private String imagenMarca;

    @JsonIgnore
    @OneToMany(mappedBy = "marca", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<Medicamento> medicamentos;

    public Marca() {
    }

    public Marca(int id, String nombre, String paisOrigen, String fechaFundacion, String sitioWeb, String descripcion,
            String imagenMarca, List<Medicamento> medicamentos) {
        this.id = id;
        this.nombre = nombre;
        this.paisOrigen = paisOrigen;
        this.fechaFundacion = fechaFundacion;
        this.sitioWeb = sitioWeb;
        this.descripcion = descripcion;
        this.imagenMarca = imagenMarca;
        this.medicamentos = medicamentos;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getPaisOrigen() {
        return paisOrigen;
    }

    public void setPaisOrigen(String paisOrigen) {
        this.paisOrigen = paisOrigen;
    }

    public String getFechaFundacion() {
        return fechaFundacion;
    }

    public void setFechaFundacion(String fechaFundacion) {
        this.fechaFundacion = fechaFundacion;
    }

    public String getSitioWeb() {
        return sitioWeb;
    }

    public void setSitioWeb(String sitioWeb) {
        this.sitioWeb = sitioWeb;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public String getImagenMarca() {
        return imagenMarca;
    }

    public void setImagenMarca(String imagenMarca) {
        this.imagenMarca = imagenMarca;
    }

    public List<Medicamento> getMedicamentos() {
        return medicamentos;
    }

    public void setMedicamentos(List<Medicamento> medicamentos) {
        this.medicamentos = medicamentos;
    }

    
   
}