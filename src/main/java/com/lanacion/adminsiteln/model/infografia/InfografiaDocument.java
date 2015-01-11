/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.lanacion.adminsiteln.model.infografia;

import java.util.Date;

/**
 *
 * @author Luxo
 */
public class InfografiaDocument {
    
    private Integer idInfografia;
    
    private String titulo;
    
    private String descripcion;
    
    private Integer tipo_infografia;
    
    private Date fecha_creacion;
    
    private Date fecha_modificacion;
    
    private Integer estado;

    public Integer getIdInfografia() {
        return idInfografia;
    }

    public void setIdInfografia(Integer idInfografia) {
        this.idInfografia = idInfografia;
    }

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public Integer getTipo_infografia() {
        return tipo_infografia;
    }

    public void setTipo_infografia(Integer tipo_infografia) {
        this.tipo_infografia = tipo_infografia;
    }

    public Date getFecha_creacion() {
        return fecha_creacion;
    }

    public void setFecha_creacion(Date fecha_creacion) {
        this.fecha_creacion = fecha_creacion;
    }

    public Date getFecha_modificacion() {
        return fecha_modificacion;
    }

    public void setFecha_modificacion(Date fecha_modificacion) {
        this.fecha_modificacion = fecha_modificacion;
    }

    public Integer getEstado() {
        return estado;
    }

    public void setEstado(Integer estado) {
        this.estado = estado;
    }
    
}
