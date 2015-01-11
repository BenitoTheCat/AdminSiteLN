/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.lanacion.adminsiteln.dao.infografiasrepository;

import com.lanacion.adminsiteln.model.infografia.InfografiaDocument;
import java.sql.ResultSet;
import java.sql.SQLException;
import org.springframework.jdbc.core.RowMapper;

/**
 *
 * @author Luxo
 */
public class InfografiaMapper implements RowMapper<InfografiaDocument> {

    public InfografiaDocument mapRow(ResultSet rs, int rowNum) throws SQLException {
        System.out.println("LLevo al Mapper");
        InfografiaDocument infografia = new InfografiaDocument();
        infografia.setIdInfografia(rs.getInt("idInfografia"));
        infografia.setTitulo(rs.getString("titulo"));
        infografia.setDescripcion(rs.getString("descripcion"));
        infografia.setEstado(rs.getInt("estado"));
        infografia.setFecha_creacion(rs.getDate("fecha_creacion"));
        infografia.setFecha_modificacion(rs.getDate("fecha_modificacion"));
        return infografia;
    }

}
