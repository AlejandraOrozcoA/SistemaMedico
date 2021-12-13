/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Controlador;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import sistemamedico.Cita;
import sistemamedico.HistoriaClinica;
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
}
