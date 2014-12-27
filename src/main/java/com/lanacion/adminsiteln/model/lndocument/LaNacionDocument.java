/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.lanacion.adminsiteln.model.lndocument;

import java.util.Date;

/**
 *
 * @author Luxo
 */
public class LaNacionDocument {

    private String id;

    private String titulo;

    private String cuertpo;

    private Date fecha_diario;

    private Date fecha_indexacion;

    private Date fecha_actualizacion;

    private int pagina_diario;

    private int edicion;

    private int ano;

    private int numero;

    private String diario;

    private String url_pdf_pag;

    private String url_pdf_total;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public String getCuertpo() {
        return cuertpo;
    }

    public void setCuertpo(String cuertpo) {
        this.cuertpo = cuertpo;
    }

    public Date getFecha_diario() {
        return fecha_diario;
    }

    public void setFecha_diario(Date fecha_diario) {
        this.fecha_diario = fecha_diario;
    }

    public Date getFecha_indexacion() {
        return fecha_indexacion;
    }

    public void setFecha_indexacion(Date fecha_indexacion) {
        this.fecha_indexacion = fecha_indexacion;
    }

    public Date getFecha_actualizacion() {
        return fecha_actualizacion;
    }

    public void setFecha_actualizacion(Date fecha_actualizacion) {
        this.fecha_actualizacion = fecha_actualizacion;
    }

    public int getPagina_diario() {
        return pagina_diario;
    }

    public void setPagina_diario(int pagina_diario) {
        this.pagina_diario = pagina_diario;
    }

    public int getEdicion() {
        return edicion;
    }

    public void setEdicion(int edicion) {
        this.edicion = edicion;
    }

    public int getAno() {
        return ano;
    }

    public void setAno(int ano) {
        this.ano = ano;
    }

    public int getNumero() {
        return numero;
    }

    public void setNumero(int numero) {
        this.numero = numero;
    }

    public String getDiario() {
        return diario;
    }

    public void setDiario(String diario) {
        this.diario = diario;
    }

    public String getUrl_pdf_pag() {
        return url_pdf_pag;
    }

    public void setUrl_pdf_pag(String url_pdf_pag) {
        this.url_pdf_pag = url_pdf_pag;
    }

    public String getUrl_pdf_total() {
        return url_pdf_total;
    }

    public void setUrl_pdf_total(String url_pdf_total) {
        this.url_pdf_total = url_pdf_total;
    }

}
