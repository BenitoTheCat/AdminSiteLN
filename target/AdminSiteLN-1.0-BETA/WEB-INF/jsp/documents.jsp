<%-- 
    Document   : documents
    Created on : Dec 8, 2014, 4:48:49 PM
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
        
        <!-- Latest compiled and minified CSS -->
        <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.1/css/bootstrap.min.css"/>

        <!-- Optional theme -->
        <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.1/css/bootstrap-theme.min.css"/>

        <!-- Latest compiled and minified JavaScript -->
        <script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.1/js/bootstrap.min.js"></script>

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
                    <li class="active"><a href="<c:url value="/"/>" class="active">PDF</a></li>
                    <li><a href="<c:url value="/dashboard"/>" class="active">Infografía</a></li>
                    <li><a href="<c:url value="/home_video"/>" class="active">Video</a></li>
                </ul>
            </div> <!--  end div #nav -->
            <!--  END NAVIGATION -->

            <!--  START SUB NAV -->

            <!-- the class "active" is used to highlight the current/active linke -->
            <div id="subnav">
            </div><!--  end div #subnav -->
            <!--  END SUB NAV -->

            <!--  START CONTENT -->
            <div id="content_wrapper">

                <!--  START PRIMARY CONTENT -->
                <div id="primary_content">
                    
        <h2>Se indexaran los siguientes documentos: </h2>
        <c:set var="numeroDocumentos" value="0" scope="page"/>
        <c:forEach var="doc" items="${docToIndex}" varStatus="docNum">
            <p>${doc} </p>
            <c:set var="numeroDocumentos" value="${docNum.count}" scope="page"/>
        </c:forEach>


        En ${url_folder} existen ${numeroDocumentos} documentos pdf


        <button onclick="indexarContenido()">Indexar!</button>
        <div class="loading">
            
        </div>

                </div><!--  end div #primary_content -->
                <!--  END PRIMARY CONTENT -->



            </div><!--  end div #wconent_rapper -->
            <!--  END CONTENT WRAPPER -->
        </div>
        <!--  END WRAPPER -->

    </body>
        <script type="text/javascript">
        function indexarContenido(){
        $( ".loading" ).append( "<p>Indexando....</p>" );    
        alert('tratare de indexar');
        if(${numeroDocumentos} !=0){
            $( ".loading" ).append( "<p>Indexando....</p>" );    
            console.log('tratare de indexar');

                $.ajax({
                    url: '<c:url value="/indexDocument"/>',
                    async: false,
                        data: {
                            folder: '<c:out value="${url_folder}"/>'
                        },
                        success: function(data) {
                        $( ".loading" ).append( "<p>"+data+"!</p>" );
                        },
                        error: function() {
                        $('#info').html('<p>Ocurrio un error contacte al administrador</p>');
                        },
                });
            }
        else{
            alert('no se pude indexar, no hay documentos :(');
            $( ".loading" ).append( "<p>No se puede indexar :S</p>" );    
        }
        }
            </script>
</html>

