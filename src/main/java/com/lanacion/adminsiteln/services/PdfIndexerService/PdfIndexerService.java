/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.lanacion.adminsiteln.services.PdfIndexerService;

/**
 *
 * @author Luxo
 */
import com.lanacion.adminsiteln.config.SolrConnection;
import com.lanacion.adminsiteln.model.lndocument.LaNacionDocument;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.net.MalformedURLException;
import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.LinkedList;
import java.util.List;
import java.util.Locale;
import org.springframework.stereotype.Service;

import org.apache.pdfbox.cos.COSDocument;
import org.apache.pdfbox.pdfparser.PDFParser;
import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.util.PDFTextStripper;
import org.apache.solr.client.solrj.SolrServerException;
import org.springframework.beans.factory.annotation.Autowired;

@Service
public class PdfIndexerService {

    //@Autowired
    private final SolrConnection solrConn;

    public PdfIndexerService() throws MalformedURLException {
        this.solrConn = new SolrConnection();
    }

    //public String indexPDFToSolr();
    public String indexPDFToSolr(String folderString) {
        //String folderString = "/Users/Luxo/NetBeansProjects/LaNacionIndexer";
        File folder = new File(folderString);
        for (File fileEntry : folder.listFiles()) {
            if (fileEntry.getName().toLowerCase().contains(".pdf")) {
                List<LaNacionDocument> lslnd = new LinkedList<LaNacionDocument>();
                System.out.println(fileEntry.getName() + " es pdf: ");
                int paginasToIndex = pdfgetPages(folderString + "/" + fileEntry.getName());
                System.out.println("Paginas a indexar: "+paginasToIndex);
                if (paginasToIndex == 1) {
                    int pagina = getSinglePage(fileEntry.getName());
                    LaNacionDocument lnd = generateList(pagina, fileEntry.getName(), pdftoText(folderString + "/" + fileEntry.getName(), pagina), paginasToIndex);
                    lslnd.add(lnd);
                } else {
                    for (int i = 1; i <= paginasToIndex; i++) {
                        LaNacionDocument lnd = generateList(i, fileEntry.getName(), pdftoText(folderString + "/" + fileEntry.getName(), i), paginasToIndex);
                        lslnd.add(lnd);
                    }
                }
                indexListDocuments(lslnd);

            }
        }
        System.out.println("Entre al servicio");
        return "Listo";
    }

    public List<String> documentsToIndex(String folderString) {
        List<String> listaDocumentos = new LinkedList<String>();
        File folder = new File(folderString);
        for (File fileEntry : folder.listFiles()) {
            if (fileEntry.getName().toLowerCase().contains(".pdf")) {
                listaDocumentos.add(fileEntry.getName());
            }
        }
        return listaDocumentos;
    }

    /**
     * Metodos privados para la indexación
     */
    private String pdftoText(String fileName, int pagina) {

        PDFParser parser;
        String parsedText = null;;
        PDFTextStripper pdfStripper = null;
        //pdfStripper.setStartPage(0);
        //pdfStripper.setEndPage(0);
        PDDocument pdDoc = null;
        COSDocument cosDoc = null;
        File file = new File(fileName);
        if (!file.isFile()) {
            System.err.println("File " + fileName + " does not exist.");
            return null;
        }
        try {
            parser = new PDFParser(new FileInputStream(file));
        } catch (IOException e) {
            System.err.println("Unable to open PDF Parser. " + e.getMessage());
            return null;
        }
        try {
            parser.parse();
            cosDoc = parser.getDocument();
            pdfStripper = new PDFTextStripper();
            pdDoc = new PDDocument(cosDoc);
            pdfStripper.setStartPage(pagina);
            pdfStripper.setEndPage(pagina);
            parsedText = pdfStripper.getText(pdDoc);
        } catch (Exception e) {
            System.err
                    .println("An exception occured in parsing the PDF Document."
                            + e.getMessage());
        } finally {
            try {
                if (cosDoc != null) {
                    cosDoc.close();
                }
                if (pdDoc != null) {
                    pdDoc.close();
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return parsedText;

    }

    private int pdfgetPages(String fileName) {

        int numero_paginas = 0;
        PDFParser parser;
        String parsedText = null;;
        PDFTextStripper pdfStripper = null;
        //pdfStripper.setStartPage(0);
        //pdfStripper.setEndPage(0);
        PDDocument pdDoc = null;
        COSDocument cosDoc = null;
        File file = new File(fileName);
        if (!file.isFile()) {
            System.err.println("File " + fileName + " does not exist.");
            return 0;
        }
        try {
            parser = new PDFParser(new FileInputStream(file));
        } catch (IOException e) {
            System.err.println("Unable to open PDF Parser. " + e.getMessage());
            return 0;
        }
        try {
            parser.parse();
            cosDoc = parser.getDocument();
            pdfStripper = new PDFTextStripper();
            pdDoc = new PDDocument(cosDoc);
            numero_paginas = pdDoc.getNumberOfPages();
        } catch (Exception e) {
            System.err
                    .println("An exception occured in parsing the PDF Document."
                            + e.getMessage());
        } finally {
            try {
                if (cosDoc != null) {
                    cosDoc.close();
                }
                if (pdDoc != null) {
                    pdDoc.close();
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return numero_paginas;
    }

    /**
     * Arma el documento lanacion para ser indexado
     */
    public LaNacionDocument generateList(int pagina, String file, String diarioString, int edicion) {
        LaNacionDocument lnd = null;
        try {
            String newdiarioString = diarioString.substring((int) (diarioString.length() * 0), (int) (diarioString.length()));//*0.808));
            //newdiarioString = newdiarioString.replaceAll("[\\/\\'\\­\\(\\)\\+\\.\\^:,]","");
            //newdiarioString = newdiarioString.replaceAll("­","hola");
            //newdiarioString = newdiarioString.replaceAll("[^a-zA-Z0-9]+ ","");

            newdiarioString = newdiarioString.replaceAll("[^\\p{Alpha}\\p{Digit}\\ ]+", "");

            lnd = getDocumentFromPDF(pagina, file, newdiarioString, edicion);
            System.out.println("ID a indexar en generateList: " + lnd.getId());
            //System.out.println(newdiarioString);
            //lndList.add(lnd);
        } catch (Exception e) {
            System.out.println("ERROR: IndexerPDFLaNacion:generateList error: " + e.toString());
        }
        return lnd;
    }

    public String indexListDocuments(List<LaNacionDocument> lndList) {
        try {
            solrConn.addDocument(lndList);
            return "Done";
        } catch (Exception e) {
            System.out.println("ERROR: IndexerPDFLaNacion:indexListDocument error: " + e.toString());
            return "Something went wrong in IndexerPDFLaNacion:indexListDocuments, please connact to the administrator";
        }

    }

    public LaNacionDocument getDocumentFromPDF(int pagina, String file, String diarioString, int edicion) throws ParseException {
        LaNacionDocument lnd = new LaNacionDocument();

        //Se setean los atributos de la clase LaNancionDocument para luego indexarlos
        String id = getIDString(file, pagina);
        lnd.setId(id);
        System.out.println("ID: " + id + " file " + file + " pagina " + pagina);

        String titulo = getIDString(file, pagina);
        lnd.setTitulo(titulo);
        System.out.println("titulo: " + lnd.getTitulo() + " file " + file + " pagina " + pagina);

        String cuerpo = diarioString;
        lnd.setCuertpo(cuerpo);

        String fechaString = getFechaString(file);
        Date fecha_diario = new SimpleDateFormat("yyyy/MM/d", Locale.ENGLISH).parse(fechaString);
        lnd.setFecha_diario(fecha_diario);

        Date fecha_indexacion = new Date();
        lnd.setFecha_indexacion(fecha_indexacion);

        Date fecha_actualizacion = new Date();
        lnd.setFecha_actualizacion(fecha_actualizacion);

        int pagina_diario = pagina;
        lnd.setPagina_diario(pagina_diario);

        int edicion_diario = edicion;
        lnd.setEdicion(edicion_diario);

        int ano = getAnoFromFechaString(fechaString);
        lnd.setAno(ano);

        int numero = 0;
        lnd.setNumero(numero);

        String diario = "La Nación";
        lnd.setDiario(diario);

        String url_pdf_pag = id + ".pdf";
        lnd.setUrl_pdf_pag(url_pdf_pag);

        String url_pdf_total = file;
        lnd.setUrl_pdf_total(url_pdf_total);

        return lnd;
    }

    private String getFechaString(String file) {
        String fecha = "";
        try {
            System.out.println("File: " + file);
            String[] fileSplit = file.split(".pdf")[0].split("_");
            fecha = fileSplit[1] + "/" + fileSplit[2] + "/" + fileSplit[3];
        } catch (Exception e) {
            System.out.println("Error en getFechaString, con el siguiente error:" + e.toString());
        }
        return fecha;
    }

    private String getIDString(String file, int pagina) {
        String id = "";
        try {
            System.out.println("File: " + file);
            String fileSplit = file.split(".pdf")[0];
            String[] fileAtrributesArray = fileSplit.split("_");
            id = fileAtrributesArray[0]+ "_" + fileAtrributesArray[1]+ "_" + fileAtrributesArray[2]+ "_" + fileAtrributesArray[3] + "_" + pagina;
        } catch (Exception e) {
            System.out.println("Error en getIDString, con el siguiente error:" + e.toString());
        }
        return id;
    }

    private int getAnoFromFechaString(String fechaString) {
        int ano = 0;
        try {
            String[] fileSplit = fechaString.split("/");
            ano = Integer.parseInt(fileSplit[0]);
        } catch (Exception e) {
            System.out.println("Error en getAnoFromFechaString, con el siguiente error:" + e.toString());
        }
        return ano;
    }
    
    private int getSinglePage(String file) {
        int id = 0;
        try {
            System.out.println("File: " + file);
            String fileSplit = file.split(".pdf")[0];
            String[] fileAtrributesArray = fileSplit.split("_");
            id = Integer.parseInt(fileAtrributesArray[4]);
        } catch (Exception e) {
            System.out.println("Error en getSinglePage, con el siguiente error:" + e.toString());
        }
        return id;
    }

}
