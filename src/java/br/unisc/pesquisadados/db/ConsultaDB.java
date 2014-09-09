/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package br.unisc.pesquisadados.db;

import br.unisc.pesquisadados.model.Consulta;
import br.unisc.pesquisadados.utils.Config;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Timestamp;
import java.util.LinkedList;
import java.util.List;

/**
 *
 * @author tauame
 */
public class ConsultaDB {
    public ConsultaDB() throws ClassNotFoundException{
        Class.forName("org.postgresql.Driver");
    }
    
    public Connection getConnection() throws SQLException{
        return DriverManager.getConnection("jdbc:postgresql://" + Config.get("host")
                + ":5432/" + Config.get("database"), Config.get("user"), Config.get("pass"));
    }
    
    public void inserir(Consulta consulta){
        String sql = "insert into resultado_consulta "
                + "(hora, dados_csv, texto_consultado) "
                + " values (?,?,?) ";
        try {
            Connection conn = getConnection();
            PreparedStatement prep = conn.prepareStatement(sql);
            prep.setTimestamp(1, new Timestamp(consulta.getHora().getTime()));
            prep.setString(2, consulta.getDadosCsv());
            prep.setString(3, consulta.getTextoConsultado());
            prep.execute();
            
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
    }
    
    public void deletar(int codigo){
        String sql = "delete from resultado_consulta where id_consulta = ? ";
        try {
            Connection conn = getConnection();
            PreparedStatement prep = conn.prepareStatement(sql);
            prep.setInt(1, codigo);
            prep.execute();
            
            
        } catch (SQLException ex){
            ex.printStackTrace();
        }
    }
    
    public List<Consulta> listar(){
        List<Consulta> consultaList = new LinkedList<Consulta>();
        
        String sql = " select * from resultado_consulta";
        try {
            Connection conn = getConnection();
            Statement state = conn.createStatement();
            ResultSet rs = state.executeQuery(sql);
            
            while (rs.next()){
                Consulta c = new Consulta();
                c.setIdConsulta(rs.getInt("id_consulta"));
                c.setHora(rs.getTimestamp("hora"));
                c.setDadosCsv(rs.getString("dados_csv"));
                c.setTextoConsultado(rs.getString("texto_consultado"));
                
                consultaList.add(c);
            }      
        } catch (SQLException ex){
            ex.printStackTrace();
        }
        
        return consultaList;
    }
}
