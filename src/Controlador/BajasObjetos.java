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

/**
 *
 * @author angel
 */
public class BajasObjetos {
    private PreparedStatement pstmt;
    private ResultSet resultado;
    private Connection con;
    private ConexionBD objBD;      

    public BajasObjetos() {
        objBD= ConexionBD.getInstance();
        objBD.conectar();
        con = objBD.getConexion();
    }  
    
    public void eliminarCita(Cita cita) throws Exception {
        try{
            pstmt = con.prepareStatement("delete from cita where id_cita = ?");            
            pstmt.setInt(1, cita.getId());
            
            int columnas = pstmt.executeUpdate();
            if(columnas == 0){
                throw new Exception("Error. Ninguna columna se eliminó");
            }
        } catch(SQLException e){
            System.out.println(e.toString());
            throw new Exception(ConexionBD.MENSAJE_ERROR);
        }
    }
}
