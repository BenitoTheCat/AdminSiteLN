/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.lanacion.adminsiteln.dao.infografiasrepository;

import com.lanacion.adminsiteln.model.infografia.InfografiaDocument;
import com.lanacion.adminsiteln.model.video.VideoDocument;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.sql.DataSource;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import org.apache.commons.dbcp2.BasicDataSource;
import com.mysql.jdbc.Driver;

/**
 *
 * @author Luxo
 */
@Repository
public class InfografiasRepositoryImpl implements InfografiasRepository {

    private DriverManagerDataSource dataConn2;
    private DataSource dataSource;
    private JdbcTemplate jdbcTemplateObject;

    @Override
    public void setDataSource(DataSource dataSource2) {
        /*
         BasicDataSource dataConn=new BasicDataSource();
         dataConn.setDriverClassName("com.mysql.jdbc.Driver");
         dataConn.setUrl("jdbc:mysql://localhost:8889/DiarioLaNacion");
         dataConn.setUsername("root");
         dataConn.setPassword("root");
        
       
       
       
         if(dataSource == null){
         System.out.println("Es nulo loco++++++!!!!!");
         }
         */
        this.dataSource = dataSource2;
        System.out.println("Entro a mysql*************");
        this.jdbcTemplateObject = new JdbcTemplate(this.dataSource);
    }

    @Override
    public void create(String titulo, String descripcion, Integer tipo_infografia, Integer estado) {
        String SQL = "insert into Infografias (titulo, descripcion,tipo_infografia,fecha_creacion,fecha_modificacion,estado) values (?, ?, ?, sysdate(), sysdate(), ?)";

        jdbcTemplateObject.update(SQL, titulo, descripcion, tipo_infografia, estado);
        System.out.println("Created Record Titulo = " + titulo + " Descripcion = " + descripcion);
    }

    @Override
    public InfografiaDocument getInfografia(Integer idInfografia) {
        String SQL = "select * from Infografias where idInfografia = ? and estado >= 0";
        InfografiaDocument infografia = jdbcTemplateObject.queryForObject(SQL,
                new Object[]{idInfografia}, new InfografiaMapper());
        return infografia;
    }

    @Override
    public List<InfografiaDocument> listInfografias() {
        String SQL = "select * from Infografias where estado >= 0 order by fecha_modificacion desc";
        List<InfografiaDocument> infografias = jdbcTemplateObject.query(SQL,
                new InfografiaMapper());
        return infografias;
    }

    @Override
    public void delete(Integer id) {
        String SQL = "delete from Infografias where idInfografia = ?";
        jdbcTemplateObject.update(SQL, id);
        System.out.println("Deleted Record with ID = " + id);
    }

    @Override
    public void update(Integer idInfografia, String titulo, String descripcion, Integer tipo_infografia, Integer estado) {
        String SQL = "update Infografias set titulo = ?, descripcion = ?, tipo_infografia =?, estado=?, fecha_modificacion=sysdate() where idInfografia = ?";
        jdbcTemplateObject.update(SQL, titulo, descripcion, tipo_infografia, estado, idInfografia);
        System.out.println("Updated Record with ID = " + idInfografia);
    }

    @Override
    public void updateEstado(Integer idInfografia, Integer estado) {
        String SQL = "update Infografias set estado=? where idInfografia = ?";
        jdbcTemplateObject.update(SQL, estado, idInfografia);
        System.out.println("Updated Record with ID = " + idInfografia);
    }

    @Override
    public void updateDescripcion(Integer idInfografia, String titulo, String descripcion) {
        String SQL = "update Infografias set titulo=?, descripcion=?,  where idInfografia = ?";
        jdbcTemplateObject.update(SQL, titulo, descripcion, idInfografia);
        System.out.println("Updated Record with ID = " + idInfografia);
    }

    @Override
    public void createVistas(String titulo, String querysolr) {
        String SQL = "insert into Vistas (titulo,fecha_creacion,querysolr) values (?, sysdate(), ?)";
        jdbcTemplateObject.update(SQL, titulo, querysolr);
        System.out.println("Created Record Titulo = " + titulo + " QuerySolr = " + querysolr);
    }

    @Override
    public Integer getLastIdVista() {
        String SQL = "SELECT idVista FROM Vistas ORDER BY idVista DESC LIMIT 1";
        Integer lastid = jdbcTemplateObject.queryForObject(SQL, Integer.class);
        System.out.println("LastID Vista = " + lastid);
        return lastid;
    }

    @Override
    public Integer getLastIdInfografia() {
        String SQL = "SELECT idInfografia FROM Infografias ORDER BY idInfografia DESC LIMIT 1";
        Integer lastid = jdbcTemplateObject.queryForObject(SQL, Integer.class);
        System.out.println("LastID Infografia = " + lastid);
        return lastid;
    }

    @Override
    public void createRelacionInfografiaVista(Integer lastIdInfografia, Integer lastIdVista, Integer id_orden) {
        String SQL = "insert into Infografias_orden (idInfografia,idVista,orden_vista) values (?, ?, ?)";
        jdbcTemplateObject.update(SQL, lastIdInfografia, lastIdVista, id_orden);
        System.out.println("Created Record idInfografia = " + lastIdInfografia + " idVista = " + lastIdVista + " Orden: " + id_orden);
    }

    @Override
    public void createVideo(String url, String servidor, String autor, String titulo, String descripcion, String comentarios, String html_code) {
        Integer lastIdVideo = getLastIdVideo();
        String SQL = "insert into videos (idVideo, url, servidor, autor, titulo, descripcion, comentarios, html_code) values (?,?,?,?,?,?,?,?)";
        jdbcTemplateObject.update(SQL, lastIdVideo, url, servidor, autor, titulo, descripcion, comentarios, html_code);
        System.out.println("Created Record idVideo = " + lastIdVideo + " titulo = " + titulo + " descripcion: " + descripcion);
    }

    public Integer getLastIdVideo() {
        String SQL = "SELECT idVideo FROM videos ORDER BY idVideo DESC LIMIT 1";
        try {
            Integer lastid = jdbcTemplateObject.queryForObject(SQL, Integer.class);
            System.out.println("LastID Video = " + lastid);
            return lastid+1;
        } catch (Exception e) {
            return 1;
        }
    }
    
    @Override
    public List<VideoDocument> listVideos() {
        String SQL = "select * from videos order by idVideo desc";
        List<VideoDocument> infografias = jdbcTemplateObject.query(SQL,
                new VideoMapper());
        return infografias;
    }

}
