/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package br.unisc.pesquisadados.model;

import java.util.Date;

/**
 *
 * @author tauame
 */
public class Consulta {
    private String dadosCsv, textoConsultado;
    private int idConsulta;
    private Date hora;
    
    public Consulta(){};

    public String getDadosCsv() {
        return dadosCsv;
    }

    public void setDadosCsv(String dadosCsv) {
        this.dadosCsv = dadosCsv;
    }

    public String getTextoConsultado() {
        return textoConsultado;
    }

    public void setTextoConsultado(String textoConsultado) {
        this.textoConsultado = textoConsultado;
    }

    public int getIdConsulta() {
        return idConsulta;
    }

    public void setIdConsulta(int idConsulta) {
        this.idConsulta = idConsulta;
    }

    public Date getHora() {
        return hora;
    }

    public void setHora(Date hora) {
        this.hora = hora;
    }
    
    
}
