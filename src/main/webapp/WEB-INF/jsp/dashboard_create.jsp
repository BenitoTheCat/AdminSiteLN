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
                                <li class="active"><a href="<c:url value="/dashboard"/>" class="active">Infografía</a></li>
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

                                <div role="tabpanel">
                                    <ul class="nav nav-tabs" role="tablist" id="myTab">
                                        <li  class="divDescripcion active"><a href="javascript:active_controler(0)"  >Descripción</a></li>
                                        <li  class="divElementos"><a href="javascript:active_controler(1)" >Elementos</a></li>
                                    </ul>
                                </div>
                                
                                <div id="descripcion" class="divDescripcion">
                                    <label for="male">Titulo</label><br>
                                    <input type="text" name="titulo_info" id="titulo_info" value=""><br>
                                    <label for="female">Descripcion</label><br>
                                    <textarea  rows="5" cols="83" name="descripcion_info" id="descripcion_info"></textarea>
                                    
                                </div>

                                <div id="elementos" class="divElementos" style="display:none">
                                    <form id="elementos_insertar">
                                        <ul id="elementos_ids">
                                        </ul>
                                    </form>
                                    <a href="javascript:agregarElemento()">Agregar Elemento</a>
                                    <br>
                                    <br>
                                </div>
                                <button onclick="indexarContenido()">Guardar</button>
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

                    function getConsultaElemento(ingreso) {

                        $.ajax({
                            url: '<c:url value="/ajax/countQuerySolr"/>',
                            async: false,
                            data: {
                                query: $("#elemento_" + ingreso).val()
                            },
                            success: function (data) {
                                $("#count_element_" + ingreso).empty();
                                $("#count_element_" + ingreso).append(data);

                            },
                            error: function () {
                            },
                        });
                    }

                    function indexarContenido() {

                        var elementosArray = [];
                        var tituloArray = [];
                        
                        var titulo_info = $("#titulo_info").val();
                        var descripcion_info = $("#descripcion_info").val();

                        var elementos = getContador_elementos();
                        for (i = 1; i <= elementos; i++) {
                            if (typeof $("#elemento_" + i).val() === 'undefined') {
                                console.log("No esta definido el elemento " + i);
                            } else {
                                var elemento = $("#elemento_" + i).val();
                                var titulo = $("#titulo_" + i).val();

                                if (elemento.length > 0 && titulo.length > 0) {
                                    elementosArray.push($("#elemento_" + i).val());
                                    tituloArray.push($("#titulo_" + i).val());
                                }
                            }
                        }
                        if(elementosArray.length>0 && tituloArray.length>0 && titulo_info.length>0 && descripcion_info.length>0){
                            $.ajax({
                                url: '<c:url value="/ajax/saveElementos"/>',
                                async: false,
                                data: {
                                    elementos: elementosArray,
                                    titulos: tituloArray,
                                    titulo_info: titulo_info,
                                    descripcion_info: descripcion_info
                                    
                                },
                                success: function (data) {
                                    alert('Infografia Ingresada');

                                },
                                error: function () {
                                },
                            });
                        }else{
                                alert("Necesitas incluir elementos o completar el titulo y descripcion de la infografia")
                        }

                        return elementosArray;

                    }
                </script>

