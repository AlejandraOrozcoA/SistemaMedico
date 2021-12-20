/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sistemamedico;

import java.util.ArrayList;
import java.util.Date;

/**
 *
 * @author Ale
 */
public class ConsultaSubsecuente {
    
    private String pa;
    private String ef;
    private String dx;
    private String tx;
    private String estudios;
    private Date fecha;
    private int IDConsultaSubsecuente;   
    
    public ConsultaSubsecuente(
        int IDConsultaSubsecuente,
        String pa,
        String ef,
        String dx,
        String tx,
        String estudios,
        Date fecha
    ) {
        this.IDConsultaSubsecuente = IDConsultaSubsecuente;
        this.pa = pa;
        this.ef = ef;
        this.dx = dx;
        this.tx = tx;
        this.estudios = estudios;
        this.fecha = fecha;
    }

    public String getPa() {
        return pa;
    }

    public void setPa(String pa) {
        this.pa = pa;
    }

    public String getEf() {
        return ef;
    }

    public void setEf(String ef) {
        this.ef = ef;
    }

    public String getDx() {
        return dx;
    }

    public void setDx(String dx) {
        this.dx = dx;
    }

    public String getTx() {
        return tx;
    }

    public void setTx(String tx) {
        this.tx = tx;
    }

    public String getEstudios() {
        return estudios;
    }

    public void setEstudios(String estudios) {
        this.estudios = estudios;
    }

    public Date getFecha() {
        return fecha;
    }

    public void setFecha(Date fecha) {
        this.fecha = fecha;
    }

    public int getIDConsultaSubsecuente(){
        return this.IDConsultaSubsecuente;
    }       
}
