<%-- 
    Document   : index
    Created on : Sep 11, 2014, 1:11:00 PM
    Author     : tauame
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <title>Pesquisa de Dados em Mídias Sociais</title>
        <meta charset="UTF-8">
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
    </head>
    <body>
        <form action="Consulta.do" method="POST">
            <input type="hidden" name="acao" value="inserir"/>
            Busca: <input type="text" name="busca" maxlength="256" style="width:600px"/>
            <input type="submit" value="Pesquisar"/>
            <br/>
            <input type="submit" formaction="Consulta.do?acao=listar" value="Listar"/>
        </form>
    </body>
</html>
