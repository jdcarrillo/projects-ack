<%-- 
    Document   : actualizarProveedor
    Created on : 06/12/2010, 01:11:49 PM
    Author     : MAFER
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
   "http://www.w3.org/TR/html4/loose.dtd">

<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
       <script type="text/javascript" src="./JavaScript/json.js"></script>
       <script type="text/javascript" src="./JavaScript/findToUpdateproveedor.js"></script>

        <link rel="stylesheet" href="style.css" type="text/css" charset="utf-8" />
        <title>Actualizar Proveedor</title>

    </head>

        <body class="body">
            <div id="wrapper">
                <div id="body">
		<div id="head"></div>
		<h1><img src="images/logo.jpg" width="401" height="230" alt="Logo" /></h1>
		<div id="MainContainer"style="overflow: auto; font-size: 16px; color: #999; font-weight: bold;">

		<div id="SubContainer">
                    <p class="description" style="text-align:center">Selecciones la Operacion que desea Realizar:</p>
		        <center>
		          <a class="link" href="buscarProveedor.jsp">Buscar Proveedor</a>
		            &nbsp;&nbsp;<a class="link" href="eliminarProveedor.jsp">Eliminar Proveedor</a>
		            &nbsp;&nbsp;<a class="link" href="registrarProveedor.jsp">Registrar Proveedor</a>
		            &nbsp;&nbsp;<a class="link" href="actualizarProveedor.jsp">Actualizar Proveedor</a>
                            &nbsp;&nbsp;<a class="link" href="index.jsp">Menu Principal</a>
                        </center>
                </div>

               <center>
		<h4>Buscar Proveedores		</h4>


                <label class="description">Proveedor Id:</label>
		<input type="text" id="inputbox" style="margin-left:16px"/>

		<p>
			<input type="button" name="submit" value="Buscar" onclick="findToUpdateproveedor()"/>
                        <input type="submit" value="Update" onclick="updateproveedor()"/>
		</p>


                </center>
                    <div id="display" class="div_table"></div>
		</div>
			</div>
			</div>
    </body>
</html>