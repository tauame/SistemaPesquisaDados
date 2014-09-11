/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.unisc.pesquisadados.web;

import br.unisc.pesquisadados.db.ConsultaDB;
import br.unisc.pesquisadados.model.Consulta;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.URL;
import java.util.Date;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author tauame
 */
@WebServlet(name = "ConsultaServlet", urlPatterns = {"/ConsultaServlet.do"})
public class ConsultaServlet extends HttpServlet {

    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        try {
            
            
            String acao = request.getParameter("acao");
            if (acao != null){
                if (acao.equals("inserir")) {
                    inserir(request, response);
                }else if(acao.equals("deletar")){
                    deletar(request, response);
                }else if(acao.equals("exibir")){
                    exibir(request, response);
                }else{
                    listar(request, response);
                }
            }

        }catch(ClassNotFoundException e){
            e.printStackTrace();
        }
    }

    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /**
     * Handles the HTTP <code>GET</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Handles the HTTP <code>POST</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

     private void exibir(HttpServletRequest request, HttpServletResponse response) throws IOException, ClassNotFoundException, ServletException {
        ConsultaDB db = new ConsultaDB();

        Integer codigo = Integer.parseInt(request.getParameter("id"));
        
        Consulta consulta = db.buscar(codigo);

        request.setAttribute("consulta", consulta);
        request.getRequestDispatcher("exibeConsulta.jsp").forward(request, response);
     }
     
    
    private void inserir(HttpServletRequest request, HttpServletResponse response) throws IOException, ClassNotFoundException, ServletException {
        
        ConsultaDB db = new ConsultaDB();
        PrintWriter out = response.getWriter();
        URL url = new URL("http://socialmention.com/search?q=" + request.getParameter("busca") + "&t=all&f=csv");

        BufferedReader in = new BufferedReader(new InputStreamReader(url.openStream()));
        String inputLine;
        StringBuilder textoCsv = new StringBuilder();
        while ((inputLine = in.readLine()) != null) {
            textoCsv.append(inputLine + "\n");
        }
        in.close();
        Consulta consulta = new Consulta();
        consulta.setDadosCsv(textoCsv.toString());
        consulta.setHora(new Date());
        consulta.setTextoConsultado(request.getParameter("busca"));

        db.inserir(consulta);

        request.setAttribute("consulta", consulta);
        request.getRequestDispatcher("exibeConsulta.jsp").forward(request, response);
    }
    
    private void listar(HttpServletRequest request, HttpServletResponse response) throws ClassNotFoundException, ServletException, IOException{
        
        ConsultaDB db = new ConsultaDB();
        
        List<Consulta> consultaList = db.listar();
        
        request.setAttribute("lista", consultaList);
        request.getRequestDispatcher("listagem.jsp").forward(request, response);
    }
    
    private void deletar(HttpServletRequest request, HttpServletResponse response) throws IOException, ClassNotFoundException {
    
        ConsultaDB db = new ConsultaDB();
        PrintWriter out = response.getWriter();

        Integer codigo = Integer.parseInt(request.getParameter("id"));
        
        db.deletar(codigo);

        /* TODO output your page here. You may use following sample code. */
        out.println("<!DOCTYPE html>");
        out.println("<html>");
        out.println("<head>");
        out.println("<title>Exclusão de Consulta</title>");
        out.println("</head>");
        out.println("<body>");
        out.println("<h1>Consulta Excluída com Sucesso!</h1>");
        out.println("<a href=\"/\">Início</a>");
        out.println("<a href=\"Consulta.do?acao=listar\">Lista</a>");
        out.println("</body>");
        out.println("</html>");
    }
}
