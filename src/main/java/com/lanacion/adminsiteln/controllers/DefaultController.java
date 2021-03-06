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
public class DefaultController {
    
   @Autowired
   private PdfIndexerService indexService;
   
   @Autowired
   private InfografiaService infoService;
    
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
   
   @RequestMapping(value = "/dashboard/create", method = RequestMethod.GET)
   public String getDashboard() {
       
       
       return "dashboard_create";
   }
   
   @RequestMapping(value = "/template", method = RequestMethod.GET)
   public String getTemplate() {
       
       return "template";
   }
   
   @RequestMapping(value = "/test", method = RequestMethod.GET)
   @ResponseBody
   public InfografiaDocument getTest() {
       
       return infoService.getInfografia(1);
   
   }
   
   @RequestMapping(value = "/testlist", method = RequestMethod.GET)
   @ResponseBody
   public List<InfografiaDocument> getList() {
       
       return infoService.listInfografias();
   
   }
   
   @RequestMapping(value = "/testcrea", method = RequestMethod.GET)
   @ResponseBody
   public String getTestcrea() {
       
       infoService.create("titulo desde spring", "desde spring", 1, Integer.SIZE);
       
       return "Donde";
   }
   
   @RequestMapping(value = "/testdel", method = RequestMethod.GET)
   @ResponseBody
   public String getTestdel() {
       
       infoService.delete(3);
       
       return "Donde borrado";
   }
   
   @RequestMapping(value = "/dashboard", method = RequestMethod.GET)
   public String getHomeDashboard(ModelMap map) {
       
       map.put("list_infografias", infoService.listInfografias());
       return "dashboard_home";
   
   }
   
   @RequestMapping(value = "/editInfografia", method = RequestMethod.GET)
   public String editInfografia(ModelMap map, @RequestParam(value = "idInfo", required = true) int idInfo) {
       
       map.put("infografia", infoService.getInfografia(idInfo));
       return "dashboard_edit";
   
   }
   
   @RequestMapping(value = "/createVideo", method = RequestMethod.GET)
   public String createVideo(ModelMap map) {
       
       return "dashboard_create_video";
   
   }
   
   @RequestMapping(value = "/home_video", method = RequestMethod.GET)
   public String homeVideo(ModelMap map) {
       
       map.put("list_videos", infoService.listVideos());
       return "video_home";
   
   }
   
   @RequestMapping(value = "/editVideo", method = RequestMethod.GET)
   public String editVideo(ModelMap map, @RequestParam(value = "idInfo", required = true) int idInfo) {
       
       map.put("infografia", infoService.getVideo(idInfo));
       return "dashboard_edit_video";
   
   }
    
}