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
public class HistoriaClinica {
    private int IDHistoria;
    private Paciente paciente;
    private String ahf;
    private String apnp;
    private String perinatales;
    private double talla;
    private String apgar;
    private Date hospitalizado;
    private String complicaciones;
    private String tamizMetabolico;
    private String tamizAuditivo;
    private ArrayList<String> vacunas = new ArrayList<String>();
    private ConsultaSubsecuente tratamientos;

    public HistoriaClinica(Paciente paciente, String ahf, String apnp, String perinatales, double talla, String apgar, Date hospitalizado, String complicaciones, String tamizMetabolico, String tamizAuditivo, ConsultaSubsecuente tratamientos) {
        this.paciente = paciente;
        this.ahf = ahf;
        this.apnp = apnp;
        this.perinatales = perinatales;
        this.talla = talla;
        this.apgar = apgar;
        this.hospitalizado = hospitalizado;
        this.complicaciones = complicaciones;
        this.tamizMetabolico = tamizMetabolico;
        this.tamizAuditivo = tamizAuditivo;
        this.tratamientos = tratamientos;
    }
    
    public HistoriaClinica(int IDHistoria, String ahf, String apnp, String perinatales, double talla, String apgar, Date hospitalizado, String complicaciones, String tamizMetabolico, String tamizAuditivo) {        
        this.IDHistoria = IDHistoria;
        this.ahf = ahf;
        this.apnp = apnp;
        this.perinatales = perinatales;
        this.talla = talla;
        this.apgar = apgar;
        this.hospitalizado = hospitalizado;
        this.complicaciones = complicaciones;
        this.tamizMetabolico = tamizMetabolico;
        this.tamizAuditivo = tamizAuditivo;        
    }   

    public Paciente getPaciente() {
        return paciente;
    }

    public void setPaciente(Paciente paciente) {
        this.paciente = paciente;
    }
    
    public int getIDHistoria(){
        return this.IDHistoria;
    }

    public String getAhf() {
        return ahf;
    }

    public void setAhf(String ahf) {
        this.ahf = ahf;
    }

    public String getApnp() {
        return apnp;
    }

    public void setApnp(String apnp) {
        this.apnp = apnp;
    }

    public String getPerinatales() {
        return perinatales;
    }

    public void setPerinatales(String perinatales) {
        this.perinatales = perinatales;
    }

    public double getTalla() {
        return talla;
    }

    public void setTalla(double talla) {
        this.talla = talla;
    }

    public String getApgar() {
        return apgar;
    }

    public void setApgar(String apgar) {
        this.apgar = apgar;
    }

    public Date getHospitalizado() {
        return hospitalizado;
    }

    public void setHospitalizado(Date hospitalizado) {
        this.hospitalizado = hospitalizado;
    }

    public String getComplicaciones() {
        return complicaciones;
    }

    public void setComplicaciones(String complicaciones) {
        this.complicaciones = complicaciones;
    }

    public String getTamizMetabolico() {
        return tamizMetabolico;
    }

    public void setTamizMetabolico(String tamizMetabolico) {
        this.tamizMetabolico = tamizMetabolico;
    }

    public String getTamizAuditivo() {
        return tamizAuditivo;
    }

    public void setTamizAuditivo(String tamizAuditivo) {
        this.tamizAuditivo = tamizAuditivo;
    }

    public ArrayList<String> getVacunas() {
        return vacunas;
    }

    public void setVacunas(ArrayList<String> vacunas) {
        this.vacunas = vacunas;
    }

    public ConsultaSubsecuente getTratamientos() {
        return tratamientos;
    }

    public void setTratamientos(ConsultaSubsecuente tratamientos) {
        this.tratamientos = tratamientos;
    }
    
    
    
}
