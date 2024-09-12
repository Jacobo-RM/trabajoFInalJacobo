package com.farmaciacic.demo.Models;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;



@Entity
@Table(name = "medicamentos")
public class Medicamento {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private int id;

    @Column(name = "nombre", nullable = false, length = 255)
    private String nombre;

    @Column(name = "precio", nullable = false)
    private Double precio;

    @Column(name = "descripcion", length = 255)
    private String descripcion;

    @Column(name = "stock", columnDefinition = "int default 0")
    private int stock;

    @ManyToOne
    @JoinColumn(name = "marca_id")
    private Marca marca;

    @Column(name = "fecha_vencimiento")
    private String fechaVencimiento;

    @Column(name = "receta", nullable = false)
    private Boolean necesitaReceta;

    public Medicamento() {
    }

    public Medicamento(int id, String nombre, Double precio, String descripcion, int stock, Marca marca,
            String fechaVencimiento, Boolean necesitaReceta) {
        this.id = id;
        this.nombre = nombre;
        this.precio = precio;
        this.descripcion = descripcion;
        this.stock = stock;
        this.marca = marca;
        this.fechaVencimiento = fechaVencimiento;
        this.necesitaReceta = necesitaReceta;
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


    public Double getPrecio() {
        return precio;
    }


    public void setPrecio(Double precio) {
        this.precio = precio;
    }


    public String getDescripcion() {
        return descripcion;
    }


    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }


    
    public int getStock() {
        return stock;
    }


    public void setStock(int stock) {
        this.stock = stock;
    }


    public Marca getMarca() {
        return marca;
    }


    public void setMarca(Marca marca) {
        this.marca = marca;
    }


    public String getFechaVencimiento() {
        return fechaVencimiento;
    }


    public void setFechaVencimiento(String fechaVencimiento) {
        this.fechaVencimiento = fechaVencimiento;
    }

    public Boolean getNecesitaReceta() {
        return necesitaReceta;
    }

    public void setNecesitaReceta(Boolean necesitaReceta) {
        this.necesitaReceta = necesitaReceta;
    }


}

