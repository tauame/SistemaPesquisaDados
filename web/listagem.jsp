<%-- 
    Document   : listagem
    Created on : Sep 10, 2014, 1:18:56 PM
    Author     : tauame
--%>

<%@page import="java.text.DateFormat"%>
<%@page import="java.util.List"%>
<%@page import="br.unisc.pesquisadados.model.Consulta"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Lista das Consultas Realizadas</title>
    </head>
    <body>
        <h1>Lista das Consultas Realizadas</h1>
    </body>
    <form method="POST">
        <table border="1">
            <thead>
                <tr>
                    <td style="width:100px">
                        Cód.
                    </td>
                    <td style="width:500px">
                        Termo Pesquisado
                    </td>
                    <td style="width:200px">
                        Data Pesquisa
                    </td>
                    <td style="width:80px">
                        Excluir
                    </td>
                </tr>
            </thead>
            <tbody>
                <% 
                    List<Consulta> consultaList = (List<Consulta>) request.getAttribute("lista");

                    int numLinhas = consultaList.size();

                    for(int i=0;i<numLinhas; i++){
                        out.println("<tr>");
                            out.println("<td style=\"width:100px\">");
                            out.println(consultaList.get(i).getIdConsulta());
                            out.println("</td>");
                            out.println("<td style=\"width:500px\">");
                            out.println(consultaList.get(i).getTextoConsultado());
                            out.println("</td>");
                            out.println("<td style=\"width:200px\">");
                            out.println(DateFormat.getDateTimeInstance().format(consultaList.get(i).getHora()));
                            out.println("</td>");
                            out.println("<td style=\"width:80px\">");
                            out.println("<input type=\"submit\" value=\"Excluir\" formaction=\"Consulta.do?acao=deletar&AMP;id="+consultaList.get(i).getIdConsulta()+"\"/>");
                            out.println("</td>");
                        out.println("</tr>");
                    }
                %>
            </tbody>
        </table>
    </form>
</html>
