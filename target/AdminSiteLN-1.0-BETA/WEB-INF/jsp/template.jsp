<%-- 
    Document   : template
    Created on : Dec 9, 2014, 9:40:41 PM
    Author     : Luxo
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<c:set var="cp" value="${pageContext.request.servletContext.contextPath}" scope="request" />
<!DOCTYPE html>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=utf-8" />

        <title>Administrador índice La Nación</title>

        <!--  REQUIRED FOR IE6 SUPPORT -->
        <style type="text/css">img, div { behavior: url("iepngfix.htc") }</style> 

        <script src="<c:url value="/resources/js/jquery-1.11.1.js"/>"></script>
        <script type="text/javascript" src="resources/js/jquery.tablesorter.min.js"></script>
        <script type="text/javascript" src="resources/js/scripts.js"></script>
        <script type="text/javascript" src="resources/js/facebox.js"></script>

        <link href="resources/css/styles.css" rel="stylesheet" type="text/css" />
        <link href="resources/css/facebox.css" rel="stylesheet" type="text/css" />

    </head>

    <body>
        <!--  START WRAPPER -->
        <div id="wrapper">

            <div id="top">La Nacion</div><!--  end topbar -->
            <div id="header"><h1>Administrador índice La Nación</h1></div><!--  end header -->

            <!--  START NAVIGATION -->
            <!-- the class "active" is used to highlight the current/active linke -->
            <div id="nav">
                <ul>
                    <li class="active"><a href="index1.html#" class="active">Home</a></li>
                    <li><a href="index1.html#">Dashboard</a></li>
                </ul>
            </div> <!--  end div #nav -->
            <!--  END NAVIGATION -->

            <!--  START SUB NAV -->

            <!-- the class "active" is used to highlight the current/active linke -->
            <div id="subnav">
                <ul>
                    <li><a href="index1.html#">Lorem Ipsum</a></li>
                    <li><a href="index1.html#">Cras Lorum</a></li>
                    <li><a href="index1.html#" class="active">Nunc Tempus</a></li>
                    <li><a href="index1.html#">Aenean</a></li>
                    <li><a href="index1.html#">Nulla</a></li>
                </ul>
            </div><!--  end div #subnav -->
            <!--  END SUB NAV -->

            <!--  START CONTENT -->
            <div id="content_wrapper">

                <!--  START PRIMARY CONTENT -->
                <div id="primary_content">

                    <h2>Demo indexador Solr</h2>
                    <div class="information canhide" style="height:50px">Por favor, introdusca la url en donde se encuentrar los archivos pdf a indexar del computador en donde se encuentra el server. 
                        Por ejemplo, /Users/Server/Pdf</div>
                  
                    
                    <form action="<c:url value="/documentToIndex"/>">
                         <label>Dirección PDFS </label>
                         <input type="text" name="folder" value="/"><br>
                         <input type="submit" value="Submit">
                    </form>





                </div><!--  end div #primary_content -->
                <!--  END PRIMARY CONTENT -->



            </div><!--  end div #wconent_rapper -->
            <!--  END CONTENT WRAPPER -->
        </div>
        <!--  END WRAPPER -->

    </body>
</html>
