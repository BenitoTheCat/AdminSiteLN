/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.lanacion.adminsiteln.model.video;

/**
 *
 * @author Luxo
 */
public class VideoDocument {
    
    private Integer idVideo;
    
    private String url;
    
    private String servidor;
    
    private String autor;
    
    private String titulo;
    
    private String descripcion;
    
    private String comentarios;
    
    private String html_code;
    
    private int publicado;

    public int getPublicado() {
        return publicado;
    }

    public void setPublicado(int publicado) {
        this.publicado = publicado;
    }

    public Integer getIdVideo() {
        return idVideo;
    }

    public void setIdVideo(Integer idVideo) {
        this.idVideo = idVideo;
    }
    
    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getServidor() {
        return servidor;
    }

    public void setServidor(String servidor) {
        this.servidor = servidor;
    }

    public String getAutor() {
        return autor;
    }

    public void setAutor(String autor) {
        this.autor = autor;
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

    public String getComentarios() {
        return comentarios;
    }

    public void setComentarios(String comentarios) {
        this.comentarios = comentarios;
    }

    public String getHtml_code() {
        return html_code;
    }

    public void setHtml_code(String html_code) {
        this.html_code = html_code;
    }
    
}
