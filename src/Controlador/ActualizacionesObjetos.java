/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Controlador;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import javax.swing.JOptionPane;
import sistemamedico.Cita;
import sistemamedico.ConsultaSubsecuente;
import sistemamedico.HistoriaClinica;
import sistemamedico.Paciente;
import sistemamedico.UtilsEntradas;

/**
 *
 * @author angel
 */
public class ActualizacionesObjetos {
    private PreparedStatement pstmt;
    private ResultSet resultado;
    private Connection con;
    private ConexionBD objBD;      

    public ActualizacionesObjetos() {
        objBD= ConexionBD.getInstance();
        objBD.conectar();
        con = objBD.getConexion();
    } 
    
    public void actualizarHistoriaClinica(
        HistoriaClinica historia
    ) throws Exception {
        try{
            pstmt = con.prepareStatement(
                "update historiaClinica set" +                
                "    ahf = ?," +
                "    apnp = ?," +
                "    perinatales = ?," +
                "    talla = ?," +
                "    apgar = ?," +
                "    hospitalizado = ?," +
                "    complicaciones = ?," +
                "    tamiz_metabolico = ?," +
                "    tamiz_auditivo = ? " +
                "where id_historia = ?"
            );
            pstmt.setString(1, historia.getAhf());
            pstmt.setString(2, historia.getApnp());
            pstmt.setString(3, historia.getPerinatales());
            pstmt.setDouble(4, historia.getTalla());
            pstmt.setString(5, historia.getApgar());
            pstmt.setString(6, UtilsEntradas.getStringMySQLDeFecha(
                historia.getHospitalizado()
            ));
            pstmt.setString(7, historia.getComplicaciones());
            pstmt.setString(8, historia.getTamizMetabolico());
            pstmt.setString(9, historia.getTamizAuditivo());
            pstmt.setInt(10, historia.getIDHistoria());
                        
            int columnas = pstmt.executeUpdate();                        
            if(columnas == 0){
                throw new Exception("Error. Ninguna columna se modificó");
            }
        } catch (SQLException e) {
            System.out.println(e.toString());
            throw new Exception(ConexionBD.MENSAJE_ERROR);
        } 
    }
    
    public void actualizarCita(Cita cita) throws Exception {
        try{
            pstmt = con.prepareStatement(
                "update cita set" +                
                "    fecha = ? " +                
                "where id_cita = ?"
            );
            
            pstmt.setString(
                1,
                UtilsEntradas.getStringFHMySQLDeFecha(cita.getFecha())
            );
            
            pstmt.setInt(2, cita.getId());
            
            int columnas = pstmt.executeUpdate();                        
            if(columnas == 0){
                throw new Exception("Error. Ninguna columna se modificó");
            }
        } catch (SQLException e) {
            System.out.println(e.toString());
            throw new Exception(ConexionBD.MENSAJE_ERROR);
        } 
    }
    
    public void actualizarConsulta(
        ConsultaSubsecuente consulta
    ) throws Exception {
        try{
            pstmt = con.prepareStatement(
                "update consultaSubsecuente set" +
                "    pa = ?," +
                "    ef = ?," +
                "    dx = ?," +
                "    tx = ?," +
                "    estudios = ?," +
                "    fecha = ? " +
                "where id_consulta = ?"
            );
            System.out.println(pstmt.toString());
            
            pstmt.setString(1, consulta.getPa());
            pstmt.setString(2, consulta.getEf());
            pstmt.setString(3, consulta.getDx());
            pstmt.setString(4, consulta.getTx());
            pstmt.setString(5, consulta.getEstudios());
            pstmt.setString(
                6,
                UtilsEntradas.getStringMySQLDeFecha(consulta.getFecha())
            );
            pstmt.setInt(7, consulta.getIDConsultaSubsecuente());
                                    
            int columnas = pstmt.executeUpdate();                        
            if(columnas == 0){
                throw new Exception("Error. Ninguna columna se modificó");
            }
        } catch (SQLException e) {
            System.out.println(e.toString());
            throw new Exception(ConexionBD.MENSAJE_ERROR);
        } 
    }
    
    public void actualizarPaciente(int IDpaciente,Paciente paciente) throws Exception {
        try{
            pstmt = con.prepareStatement(
                "update paciente set" +                
                "    fecha_n = ?," +
                "    contacto_e = ?" +
                "where id_paciente = ?"
            );
            
            
            SimpleDateFormat formato =  new SimpleDateFormat("yyyy-MM-dd");
            pstmt.setString(1, formato.format(paciente.getFechaN()));
            pstmt.setString(2, paciente.getContactoEmergencia());
            pstmt.setString(3, String.valueOf(IDpaciente));
            
            int columnas = pstmt.executeUpdate();                        
            if(columnas == 0){
                throw new Exception("Error. Ninguna columna se modificó");
            }else{
                this.actualizarPersonaPaciente(IDpaciente, paciente);
            }
        } catch (SQLException e) {
            System.out.println(e.toString());
            throw new Exception(ConexionBD.MENSAJE_ERROR);
        } 
    }
    
    public void actualizarPersonaPaciente(int IDpaciente, Paciente paciente) throws Exception {
        try{
            pstmt = con.prepareStatement(
                "update persona set" +                
                "    nombre = ?," + 
                "    apellido_p = ?," + 
                "    apellido_m = ?," + 
                "    edad = ?," + 
                "    telefono = ?" + 
                "where id_persona = (select id_persona from paciente where id_paciente = ?)"
            );
            
            pstmt.setString(1, paciente.getNombre());
            pstmt.setString(2, paciente.getApellidoPaterno());
            pstmt.setString(3, paciente.getApellidoMaterno());
            pstmt.setString(4, String.valueOf(paciente.getEdad()));
            pstmt.setString(5, String.valueOf(paciente.getTelefono()));
            pstmt.setString(6, String.valueOf(IDpaciente));
            
            int columnas = pstmt.executeUpdate();                        
            if(columnas == 0){
                throw new Exception("Error. Ninguna columna se modificó");
            }else{
                JOptionPane.showMessageDialog(null, "Operación realizada correctamente");
            }
        } catch (SQLException e) {
            System.out.println(e.toString());
            throw new Exception(ConexionBD.MENSAJE_ERROR);
        } 
    }
}
