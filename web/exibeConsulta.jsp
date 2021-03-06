<%-- 
    Document   : exibeConsulta
    Created on : Sep 11, 2014, 11:08:37 AM
    Author     : tauame
--%>

<%@page import="java.io.StringReader"%>
<%@page import="java.io.BufferedReader"%>
<%@page import="java.util.List"%>
<%@page import="br.unisc.pesquisadados.model.Consulta"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Exibição de Consulta</title>
        <%
            Consulta consulta = (Consulta) request.getAttribute("consulta");
            
            %>
    </head>
    <body>
        <h1>Resultado da consulta por "<% out.print(consulta.getTextoConsultado()); %>"</h1>
        
        <form method="POST">
            <input type="hidden" name="id" value="<% out.print(consulta.getIdConsulta()); %>"/>
            Filtrar por: <input type="text" name="filtro" maxlength="100" style="width:300px"/>
            <input type="submit" value="Filtrar"/>
            <br>
            <a href="index.jsp">Início</a>
            <a href="Consulta.do?acao=listar">Lista</a>
            <br>
            <%
                String filtro = request.getParameter("filtro");
                if(filtro!=null && !filtro.equals("")){
                    out.println("Exibindo dados filtrando por: \"" + filtro + "\"<br>");
                }
            %>
        
        <table border="1">
            <thead>
                <tr>
                    <%
                        BufferedReader in = new BufferedReader(new StringReader(consulta.getDadosCsv()));
                        String primeiraLinha = in.readLine();
                        
                        //remove as aspas do inicio e do fim
                        primeiraLinha = primeiraLinha.substring(1, primeiraLinha.length()-1);
                        
                        String[] colunas = primeiraLinha.split("\",\"");
                        for(int i=0; i<colunas.length; i++){
                            out.println("<td>");
                                out.println(colunas[i]);
                            out.println("</td>");
                        }
                    %>
                    
                </tr>
            </thead>
            <tbody>
                <% 
                    
                    String linha = in.readLine();
                    while(linha != null){
                        linha = linha.substring(1, linha.length()-1);
                        if(filtro!=null && !filtro.equals("")){
                            if(!linha.toUpperCase().contains(filtro.toUpperCase())){
                                linha = in.readLine();
                                continue;
                            }
                        }
                        colunas = linha.split("\",\"");
                        
                        out.println("<tr>");
                            for(int i=0; i<colunas.length; i++){
                                out.println("<td>");
                                    out.println(colunas[i]);
                                out.println("</td>");
                            }
                        out.println("</tr>");
                        
                        linha = in.readLine();
                    }
                    
                %>
            </tbody>
        </table>
    </form>
    </body>
</html>
