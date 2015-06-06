/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.lanacion.adminsiteln.dao.infografiasrepository;


import com.lanacion.adminsiteln.model.video.VideoDocument;
import java.sql.ResultSet;
import java.sql.SQLException;
import org.springframework.jdbc.core.RowMapper;

/**
 *
 * @author Luxo
 */
public class VideoMapper  implements RowMapper<VideoDocument> {

    public VideoDocument mapRow(ResultSet rs, int rowNum) throws SQLException {
        VideoDocument video = new VideoDocument();
        video.setIdVideo(rs.getInt("idVideo"));
        video.setTitulo(rs.getString("titulo"));
        video.setUrl(rs.getString("url"));
        video.setServidor(rs.getString("servidor"));
        video.setAutor(rs.getString("autor"));
        video.setDescripcion(rs.getString("descripcion"));
        video.setComentarios(rs.getString("comentarios"));
        video.setPublicado(rs.getInt("publicado"));
        video.setHtml_code(rs.getString("html_code"));
        video.setFecha(rs.getDate("fecha"));
        return video;
    }

}