<%-- 
    Document   : dashboard_create
    Created on : Jan 16, 2015, 11:34:48 PM
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

        <script type="text/javascript" src="http://ajax.googleapis.com/ajax/libs/jquery/1.3.2/jquery.min.js"></script>
        <script type="text/javascript" src="<c:url value="/resources/js/jquery.tablesorter.min.js"/>"></script>
        <script type="text/javascript" src="<c:url value="/resources/js/scripts.js"/>"></script>
        <script type="text/javascript" src="<c:url value="/resources/js/facebox.js"/>"></script>

        <link href="<c:url value="/resources/css/styles.css"/>" rel="stylesheet" type="text/css" />
        <link href="<c:url value="/resources/css/facebox.css"/>" rel="stylesheet" type="text/css" />

        <!-- Latest compiled and minified CSS -->
        <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.1/css/bootstrap.min.css">

            <!-- Optional theme -->
            <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.1/css/bootstrap-theme.min.css">

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
                                <li><a href="<c:url value="/"/>" class="active">PDF</a></li>
                                <li><a href="<c:url value="/dashboard"/>" class="active">Infografía</a></li>
                                <li class="active"><a href="<c:url value="/home_video"/>" class="active">Video</a></li>
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
                                <h2>Subida Video</h2>
                                
                                <div id="descripcion" class="divDescripcion">
                                    <label for="male">Titulo</label><br>
                                    <input type="text" name="titulo_video" id="titulo_video" value="${infografia.titulo}"><br>
                                    <label for="female">Descripcion</label><br>
                                    <textarea  rows="5" cols="83" name="descripcion_video" id="descripcion_video">${infografia.descripcion}</textarea><br>
                                    <label for="male">URL</label><br>
                                    <input type="text" name="url_video" id="url_video" value="${infografia.titulo}"><br>
                                    <label for="male">Servidor</label><br>
                                    <input type="text" name="servidor_video" id="servidor_video" value="${infografia.titulo}"><br>
                                    <label for="male">Autor</label><br>
                                    <input type="text" name="autor_video" id="autor_video" value="${infografia.titulo}"><br>
                                    <label for="male">Comentarios</label><br>
                                    <input type="text" name="comentarios_video" id="comentarios_video" value="${infografia.titulo}"><br>
                                    <label for="male">Codigo HTML</label><br>
                                    <input type="text" name="html_video" id="html_video" value="${infografia.titulo}"><br>
                                    <br>
                                    <br>
                                    
                                </div>

                               
                                <button onclick="crearVideo()">Guardar</button>
                            </div><!--  end div #primary_content -->
                            <!--  END PRIMARY CONTENT -->
                            

                        </div><!--  end div #wconent_rapper -->
                        <!--  END CONTENT WRAPPER -->
                    </div>
                    <!--  END WRAPPER -->

                </body>
                </html>

                <script>
                    var contador_elementos = 0;


                    function active_controler(prueba){
                        
                        if(prueba===1){
                            $('.divDescripcion').removeClass('active');
                            $('.divElementos').addClass('active');
                            $('#descripcion').hide();
                            $('#elementos').show();
                        }else{
                              $('.divElementos').removeClass('active');
                              $('.divDescripcion').addClass('active');
                              $('#elementos').hide();
                              $('#descripcion').show();
                        }
                    }
                    
                    function getContador_elementos() {
                        return contador_elementos;
                    }

                    function sumContador_elementos() {
                        contador_elementos = contador_elementos + 1;
                        return contador_elementos;
                    }

                    function getStructureElementos() {
                        var id_li = sumContador_elementos() + "_li";
                        var elemento = "<li id=\"" + id_li + "\"> ";
                        elemento += "Titulo: <input type=\"text\" name=\"titulo_" + getContador_elementos() + "\" id=\"titulo_" + getContador_elementos() + "\">";
                        elemento += "<a  href=\"javascript:eliminarElemento('" + id_li + "')\"> Eliminar <br> </a>";
                        elemento += "Consulta: <input type=\"text\" name=\"elemento_" + getContador_elementos() + "\" id=\"elemento_" + getContador_elementos() + "\">";
                        elemento += "<a  href=\"javascript:getConsultaElemento('" + getContador_elementos() + "')\"> Consultar <br></a>";
                        elemento += "Cantidad documuentos encontrados: <span id=\"count_element_" + getContador_elementos() + "\"> </span>";
                        elemento += "</li>";

                        return elemento;
                    }

                    function agregarElemento() {
                        $("#elementos_ids").append(getStructureElementos());
                    }

                    function eliminarElemento(elemento) {
                        $("#" + elemento).remove();
                    }


                    
                    function crearVideo() {

                        
                        var titulo_video = $("#titulo_video").val();
                        var descripcion_video = $("#descripcion_video").val();
                        var url_video = $("#url_video").val();
                        var servidor_video = $("#servidor_video").val();
                        var autor_video = $("#autor_video").val();
                        var comentarios_video = $("#comentarios_video").val();
                        var html_video = $("#html_video").val();
                        
                        
                        if(titulo_video.length>0 && descripcion_video.length>0 && url_video.length>0 && servidor_video.length>0 && autor_video.length>0 && html_video.length>0){
                            $.ajax({
                                url: '<c:url value="/ajax/createVideo"/>',
                                async: false,
                                data: {
                                    titulo_video: titulo_video,
                                    descripcion_video: descripcion_video,
                                    url_video: url_video,
                                    servidor_video: servidor_video,
                                    autor_video: autor_video,
                                    comentarios_video: comentarios_video,
                                    html_video: html_video,
                                    
                                },
                                success: function (data) {
                                    alert('Video guardado');

                                },
                                error: function () {
                                    alert('Ocurrio un error guardando el video');
                                },
                            });
                        }else{
                                alert("Necesitas completar los campos para poder subir el video al sitio");
                        }

                    }
                </script>

