/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sistemamedico;

import java.util.Date;

/**
 *
 * @author Ale
 */
public class Cita {
    private int id;
    private Date fecha;
    private int IDPaciente;
    private Paciente paciente;
    private Medico medico;

    public Cita(int id, Date fecha, Paciente paciente, Medico medico) {
        this.id = id;
        this.fecha = fecha;
        this.paciente = paciente;
        this.medico = medico;
    }
    
    public Cita(int IDPaciente, Date fecha){
        this.IDPaciente = IDPaciente;
        this.fecha = fecha;
    }
    
    public int getIDPaciente(){
        return this.IDPaciente;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Date getFecha() {
        return fecha;
    }

    public void setFecha(Date fecha) {
        this.fecha = fecha;
    }

    public Paciente getPaciente() {
        return paciente;
    }

    public void setPaciente(Paciente paciente) {
        this.paciente = paciente;
    }

    public Medico getMedico() {
        return medico;
    }

    public void setMedico(Medico medico) {
        this.medico = medico;
    }
    
    
    
}
