/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.lanacion.adminsiteln.controllers;

/**
 *
 * @author Luxo
 */
import com.lanacion.adminsiteln.services.PdfIndexerService.PdfIndexerService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

 
/**
*
* @author john
*/
@Controller
public class DefaultController {
    
   @Autowired
   private PdfIndexerService indexService;
    
   @RequestMapping(value = "/", method = RequestMethod.GET)
   public String index(ModelMap map) {
       //map.put("msg", "ASDF! Spring 4 Web MVC!");
       //map.put("docToIndex", indexService.documentsToIndex("/Users/Luxo/NetBeansProjects/LaNacionIndexer"));
       //map.put("prueba", indexService.indexPDFToSolr("/Users/Luxo/NetBeansProjects/LaNacionIndexer"));
       return "index";
   }
   
   @RequestMapping(value = "/documentToIndex", method = RequestMethod.GET)
   public String documentToIndex(ModelMap map, @RequestParam(value = "folder", required = false) String stringFolder) {
       map.put("msg", "ASDF! Spring 4 Web MVC!");
       try{
       map.put("docToIndex", indexService.documentsToIndex(stringFolder));
       }
       catch(Exception e){}
       //map.put("prueba", indexService.indexPDFToSolr(strinFolder));
       map.put("url_folder", stringFolder);
       return "documents";
   }
   
   @RequestMapping(value = "/indexDocument", method = RequestMethod.GET)
   @ResponseBody
   public String indexDocument(@RequestParam(value = "folder", required = false) String stringFolder) {
       return indexService.indexPDFToSolr(stringFolder);
       //return "Dode";
   }
   
   @RequestMapping(value = "/template", method = RequestMethod.GET)
   public String getTemplate() {
       
       return "template";
   }
    
}