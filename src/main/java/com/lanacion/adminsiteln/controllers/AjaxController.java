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
            ) {
       
       infoService.create(titulo_info, descripcion_info, 0, 0);
       Integer lastIdInfografia  = infoService.getLastIdInfografia();
       System.out.println("El ultimo id ingresado en infografia es: "+ lastIdInfografia);
       for(int i = 0;i<elementos.length;i++){
           System.out.println("Elemento: "+elementos[i] + " Titulo: "+titulos[i]);
           infoService.createVistas(titulos[i], elementos[i]);
           Integer lastidVista = infoService.getLastIdVista();
           System.out.println("El ultimo id ingresado es: "+lastidVista);
           infoService.createRelacionInfografiaVista(lastIdInfografia, lastidVista, i);
       }
       return 0;
   }
    
}
