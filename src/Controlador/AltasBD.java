/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Controlador;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.UUID;
import javax.swing.JOptionPane;
import sistemamedico.Login;

/**
 *
 * @author angel
 */
public class AltasBD {
    private PreparedStatement pstmt;
    private ResultSet resultado;
    private Connection con;
    private ConexionBD objBD;
    
    private Login log;
    
    private int obtenerEnteroPseudoUnico(){
        long primerosBits = UUID.randomUUID().getMostSignificantBits();
        long ultimosBits = UUID.randomUUID().getLeastSignificantBits();
        long enteroLargo = primerosBits ^ ultimosBits;
        if(enteroLargo < 0) enteroLargo *= -1;
        System.out.println(enteroLargo);
        int entero = (int) Math.sqrt(enteroLargo);
        System.out.println(entero);
        return entero;        
    }

    public AltasBD() {
        objBD= ConexionBD.getInstance();
        objBD.conectar();
        con = objBD.getConexion();
    }
    
    private int registrarPersona(
        String nombre,
        String apellido_paterno,
        String apellido_materno,
        int edad,
        String telefono
    ){
        int id_persona = obtenerEnteroPseudoUnico();
        
        try{            
            pstmt = con.prepareStatement(
                "INSERT INTO persona VALUES(?, ?, ?, ?, ?, ?, ?)"
            );
            
            pstmt.setInt(1, id_persona);
            pstmt.setInt(2, 1);
            // TODO ingresar dirección
            pstmt.setString(3, nombre);
            pstmt.setString(4, apellido_paterno);
            pstmt.setString(5, apellido_materno);
            pstmt.setInt(6, edad);
            pstmt.setString(7, telefono);
            
            pstmt.executeUpdate();            
            
            return id_persona;
        } catch(java.sql.SQLException e){  
            System.out.println(e.toString());
            return -1;
        }           
    }
    
    public void registrarMedico(
        String nombre,
        String apellido_paterno,
        String apellido_materno,
        int edad,
        String telefono,
        String turno,        
        String cedula,
        String consultorio,
        String especialidad,
        String contra
    ){
        int id_persona = registrarPersona(
            nombre,
            apellido_paterno,
            apellido_materno,
            edad,
            telefono          
        );        
        int id_medico = obtenerEnteroPseudoUnico();
        
        try{            
            pstmt = con.prepareStatement(
                "INSERT INTO medico VALUES(?, ?, ?, ?, ?, ?, ?)"
            );
            
            pstmt.setLong(1, id_medico);
            pstmt.setInt(2, id_persona);
            pstmt.setString(3, cedula);
            pstmt.setString(4, consultorio);
            pstmt.setString(5, turno);
            pstmt.setString(6, especialidad);
            pstmt.setString(7, contra);
            
            pstmt.executeUpdate();
            
            JOptionPane.showMessageDialog(
                null,
                "Se ha registrado. El id de Médico es " + id_medico
            );
        } catch(java.sql.SQLException e){  
            System.out.println(e.toString());            
        }
    }
    
    public void registrarSecretaria(
        String nombre,
        String apellido_paterno,
        String apellido_materno,
        int edad,
        String telefono,
        String turno
    ){
        int id_persona = registrarPersona(
            nombre,
            apellido_paterno,
            apellido_materno,
            edad,
            telefono          
        );
        
    }
}
