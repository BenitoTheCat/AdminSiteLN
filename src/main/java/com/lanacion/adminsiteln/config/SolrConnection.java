/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.lanacion.adminsiteln.config;

import com.lanacion.adminsiteln.model.lndocument.LaNacionDocument;
import java.io.IOException;
import java.net.MalformedURLException;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Types;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import org.apache.solr.client.solrj.SolrQuery;

import org.apache.solr.client.solrj.SolrServerException;
import org.apache.solr.client.solrj.impl.HttpSolrServer;
import org.apache.solr.client.solrj.response.QueryResponse;
import org.apache.solr.common.SolrDocumentList;
import org.apache.solr.common.SolrInputDocument;

/**
 *
 * @author Luxo
 */
public class SolrConnection {

    private static int fetchSize = 1000;
    private static String url = "http://localhost:8983/solr/LaNacionIndex/";
    private static HttpSolrServer solrCore;

    public SolrConnection() throws MalformedURLException {
        System.out.println("Me creo*************************");
        solrCore = new HttpSolrServer(url);
    }

    /**
     * Toma un documento de la nacion y lo indexa a solr
     *
     * @param lnd
     * @return
     * @throws SQLException
     * @throws SolrServerException
     * @throws IOException
     */
    public long addDocument(List<LaNacionDocument> lndList) throws SQLException, SolrServerException, IOException {

        for (LaNacionDocument lnd : lndList) {
            SolrInputDocument doc = new SolrInputDocument();
            doc.addField("id", lnd.getId());
            doc.addField("titulo", lnd.getTitulo());
            doc.addField("cuerpo", lnd.getCuertpo());
            doc.addField("fecha_diario", lnd.getFecha_diario());
            doc.addField("fecha_indexacion", lnd.getFecha_indexacion());
            doc.addField("fecha_actualizacion", lnd.getFecha_actualizacion());
            doc.addField("pagina_diario", lnd.getPagina_diario());
            doc.addField("edicion", lnd.getEdicion());
            doc.addField("ano", lnd.getAno());
            doc.addField("numero", lnd.getNumero());
            doc.addField("diario", lnd.getDiario());
            doc.addField("url_pdf_pag", lnd.getUrl_pdf_pag());
            doc.addField("url_pdf_total", lnd.getUrl_pdf_total());
            
            System.out.println("Indexando id: "+lnd.getId());
            try{
            solrCore.add(doc);
            }catch(Exception e){
                System.out.println("ERROR EN INDEXACION: con el id "+lnd.getId()+ " y error:"+ e.toString());
            }
        }
        solrCore.commit();
        return 1;
    }
    
    public int countDocuments(String UserQuery) throws SolrServerException{
        SolrQuery query = new SolrQuery();
        if(UserQuery.length()>0){
            query.setQuery(UserQuery);
        }else{
            query.setQuery("*:*");
        }
        //query.addFilterQuery("cat:electronics","store:amazon.com");
        query.setFields("titulo","cuerpo");
        query.setStart(0);   
        query.setRows(10000);
        query.set("defType", "edismax");

        QueryResponse response = solrCore.query(query);
        SolrDocumentList results = response.getResults();
        
        return results.size();
    }

}
