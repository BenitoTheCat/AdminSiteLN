/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.lanacion.adminsiteln.controllers;

import org.springframework.stereotype.Controller;

/**
 *
 * @author Luxo
 */
import com.lanacion.adminsiteln.model.infografia.InfografiaDocument;
import com.lanacion.adminsiteln.service.infografia.InfografiaService;
import com.lanacion.adminsiteln.services.PdfIndexerService.PdfIndexerService;
import java.io.UnsupportedEncodingException;
import java.util.List;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;


@Controller
public class AjaxController {
   
   @Autowired InfografiaService infoService;
   
   @RequestMapping(value = "/ajax/countQuerySolr", method = RequestMethod.GET)
   @ResponseBody
   public int documentToIndex(ModelMap map, @RequestParam(value = "query", required = false) String query) {
       
       System.out.println("Query que llega: "+query);
       return infoService.getCountQuery(query);
   }
   
   @RequestMapping(value = "/ajax/saveElementos", method = RequestMethod.GET)
   @ResponseBody
   public int documentToIndex(ModelMap map, 
           @RequestParam(value = "elementos", required = false) String[] elementos
           ,@RequestParam(value = "titulos", required = false) String[] titulos
           ,@RequestParam(value = "titulo_info", required = false) String titulo_info
           ,@RequestParam(value = "descripcion_info", required = false) String descripcion_info
            ) throws UnsupportedEncodingException {
       
       
         
       byte[] a1 = titulo_info.getBytes("ISO-8859-1");
       String titulo_infooUTF8 = new String(a1, "UTF-8");
       
       byte[] b1 = descripcion_info.getBytes("ISO-8859-1");
       String ddescripcion_infoUTF8 = new String(b1, "UTF-8");
       
       infoService.create(titulo_infooUTF8, ddescripcion_infoUTF8, 0, 0);
       Integer lastIdInfografia  = infoService.getLastIdInfografia();
       System.out.println("El ultimo id ingresado en infografia es: "+ lastIdInfografia);
       for(int i = 0;i<elementos.length;i++){
           
           byte[] ai = titulos[i].getBytes("ISO-8859-1");
           String tituloiUTF8 = new String(ai, "UTF-8");
       
           byte[] bi = elementos[i].getBytes("ISO-8859-1");
           String diUTF8 = new String(bi, "UTF-8");
       
           System.out.println("Elemento: "+elementos[i] + " Titulo: "+titulos[i]);
           infoService.createVistas(tituloiUTF8, diUTF8);
           Integer lastidVista = infoService.getLastIdVista();
           System.out.println("El ultimo id ingresado es: "+lastidVista);
           infoService.createRelacionInfografiaVista(lastIdInfografia, lastidVista, i);
       }
       return 0;
   }
   
   @RequestMapping(value = "/ajax/updateEstado", method = RequestMethod.GET)
   @ResponseBody
   public int updateEstado(ModelMap map
           ,@RequestParam(value = "estado", required = false) Integer estado
           ,@RequestParam(value = "idInfografia", required = false) Integer idInfografia
            ) {
       
       infoService.updateEstado(idInfografia, estado);
       return 0;
   }
           
   @RequestMapping(value = "/ajax/updatePublicadoVideo", method = RequestMethod.GET)
   @ResponseBody
   public int updateEstadoVideo(ModelMap map
           ,@RequestParam(value = "estado", required = false) Integer estado
           ,@RequestParam(value = "idVideo", required = false) Integer idVideo
            ) {
       
       infoService.updateEstadoVideo(idVideo, estado);
       return 0;
   }
   
   @RequestMapping(value = "/ajax/deleteVideo", method = RequestMethod.GET)
   @ResponseBody
   public int deleteVideo(ModelMap map           
           ,@RequestParam(value = "idVideo", required = false) Integer idVideo
            ) {
       
       infoService.deleteVideo(idVideo);
       return 0;
   }
   
   @RequestMapping(value = "/ajax/updateDescripcion", method = RequestMethod.GET)
   @ResponseBody
   public int updateDescripcion(ModelMap map
           ,@RequestParam(value = "idInfografia", required = false) Integer idInfografia
           ,@RequestParam(value = "titulo", required = false) String titulo
           ,@RequestParam(value = "descripcion", required = false) String descripcion
            ) throws UnsupportedEncodingException {
       
       
       byte[] a1 = titulo.getBytes("ISO-8859-1");
       String tituloUTF8 = new String(a1, "UTF-8");
       
       byte[] b1 = descripcion.getBytes("ISO-8859-1");
       String descripcionUTF8 = new String(b1, "UTF-8");
       
       System.out.println("id: "+idInfografia + " titulo: " + tituloUTF8 + " descripcion: "+ descripcionUTF8);
       infoService.updateDescripcion(idInfografia, tituloUTF8, descripcionUTF8);
       return 0;
   }
   
   @RequestMapping(value = "/ajax/createVideo", method = RequestMethod.GET)
   @ResponseBody
   public int createVideo(ModelMap map
          ,@RequestParam(value = "titulo_video", required = false) String titulo
           ,@RequestParam(value = "descripcion_video", required = false) String descripcion
           ,@RequestParam(value = "url_video", required = false) String url
           ,@RequestParam(value = "servidor_video", required = false) String servidor
           ,@RequestParam(value = "autor_video", required = false) String autor
           ,@RequestParam(value = "comentarios_video", required = false) String comentarios
           ,@RequestParam(value = "html_video", required = false) String html
            ) throws UnsupportedEncodingException {
       
       
       byte[] a1 = titulo.getBytes("ISO-8859-1");
       String tituloUTF8 = new String(a1, "UTF-8");
       
       byte[] b1 = descripcion.getBytes("ISO-8859-1");
       String descripcionUTF8 = new String(b1, "UTF-8");
       
       byte[] c1 = url.getBytes("ISO-8859-1");
       String urlUTF8 = new String(c1, "UTF-8");
       
       byte[] d1 = servidor.getBytes("ISO-8859-1");
       String servidorUTF8 = new String(d1, "UTF-8");
       
       byte[] e1 = autor.getBytes("ISO-8859-1");
       String autorUTF8 = new String(e1, "UTF-8");
       
       byte[] f1 = comentarios.getBytes("ISO-8859-1");
       String comentariosUTF8 = new String(f1, "UTF-8");
       
       byte[] g1 = html.getBytes("ISO-8859-1");
       String htmlUTF8 = new String(g1, "UTF-8");
       
    
       infoService.createVideo( urlUTF8,  servidorUTF8,  autorUTF8,  tituloUTF8,  descripcionUTF8,  comentariosUTF8,  htmlUTF8);
       return 0;
   }
   
   @RequestMapping(value = "/ajax/editVideo", method = RequestMethod.GET)
   @ResponseBody
   public int editVideo(ModelMap map
           ,@RequestParam(value = "idVideo", required = false) Integer idVideo
          ,@RequestParam(value = "titulo_video", required = false) String titulo
           ,@RequestParam(value = "descripcion_video", required = false) String descripcion
           ,@RequestParam(value = "url_video", required = false) String url
           ,@RequestParam(value = "servidor_video", required = false) String servidor
           ,@RequestParam(value = "autor_video", required = false) String autor
           ,@RequestParam(value = "comentarios_video", required = false) String comentarios
           ,@RequestParam(value = "html_video", required = false) String html
            ) throws UnsupportedEncodingException {
       
       
       byte[] a1 = titulo.getBytes("ISO-8859-1");
       String tituloUTF8 = new String(a1, "UTF-8");
       
       byte[] b1 = descripcion.getBytes("ISO-8859-1");
       String descripcionUTF8 = new String(b1, "UTF-8");
       
       byte[] c1 = url.getBytes("ISO-8859-1");
       String urlUTF8 = new String(c1, "UTF-8");
       
       byte[] d1 = servidor.getBytes("ISO-8859-1");
       String servidorUTF8 = new String(d1, "UTF-8");
       
       byte[] e1 = autor.getBytes("ISO-8859-1");
       String autorUTF8 = new String(e1, "UTF-8");
       
       byte[] f1 = comentarios.getBytes("ISO-8859-1");
       String comentariosUTF8 = new String(f1, "UTF-8");
       
       byte[] g1 = html.getBytes("ISO-8859-1");
       String htmlUTF8 = new String(g1, "UTF-8");
       
    
       infoService.editVideo(idVideo, urlUTF8,  servidorUTF8,  autorUTF8,  tituloUTF8,  descripcionUTF8,  comentariosUTF8,  htmlUTF8);
       return 0;
   }
   
   /*
   @RequestMapping(value = "/ajax/deleteInfografia", method = RequestMethod.GET)
   @ResponseBody
   public int deleteInfografia(ModelMap map
           ,@RequestParam(value = "idInfografia", required = false) Integer idInfografia
            ) {
       
       infoService.delete(idInfografia);
       return 0;
   }
   */
    
}
