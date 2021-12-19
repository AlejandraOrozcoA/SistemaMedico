/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Controlador;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Date;
import java.util.UUID;
import javax.swing.JOptionPane;
import sistemamedico.Login;
import sistemamedico.UtilsEntradas;

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
    ) throws Exception{                
        int id_persona;
        
        try{            
            pstmt = con.prepareStatement(
                "INSERT INTO persona("
                    + "    id_direccion,"
                    + "    nombre,"
                    + "    apellido_p,"
                    + "    apellido_m,"
                    + "    edad,"
                    + "    telefono"
                    + ") VALUES(?, ?, ?, ?, ?, ?)",
                Statement.RETURN_GENERATED_KEYS
            );
                        
            pstmt.setInt(1, 1);
            // TODO ingresar dirección
            pstmt.setString(2, nombre);
            pstmt.setString(3, apellido_paterno);
            pstmt.setString(4, apellido_materno);
            pstmt.setInt(5, edad);
            pstmt.setString(6, telefono);
            
            int columnas = pstmt.executeUpdate();                        
            if(columnas == 0){
                throw new Exception("Error. Ninguna columna se creó");
            } else {
                ResultSet ids_generados = pstmt.getGeneratedKeys();
                if(ids_generados.next()){
                    id_persona = ids_generados.getInt(1);
                } else {
                    throw new Exception("Error. Ningún ID fue generado");
                }
            }
        } catch(java.sql.SQLException e){  
            throw new Exception("Error conectándose a la BD: " + e.toString());
        }  
        
        System.out.println(id_persona);
        
        return id_persona;
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
    ) throws Exception{
        int id_persona = registrarPersona(
            nombre,
            apellido_paterno,
            apellido_materno,
            edad,
            telefono          
        );                
        
        try{            
            pstmt = con.prepareStatement(
                "INSERT INTO medico("
                    + "    id_persona,"
                    + "    cedula,"
                    + "    consultorio,"
                    + "    turno,"
                    + "    especialidad,"
                    + "    contrasenia)"
                    + "VALUES(?, ?, ?, ?, ?, ?)"                
            );
                        
            pstmt.setInt(1, id_persona);
            pstmt.setString(2, cedula);
            pstmt.setString(3, consultorio);
            pstmt.setString(4, turno);
            pstmt.setString(5, especialidad);
            pstmt.setString(6, contra);
            
            System.out.println(pstmt.toString());
            
            int columnas = pstmt.executeUpdate();                        
            if(columnas == 0){
                throw new Exception("Error. Ninguna columna se creó");
            } else {                                    
                JOptionPane.showMessageDialog(
                    null,
                    "Se ha registrado. El id es " + id_persona
                );                
            }       
        } catch(java.sql.SQLException e){  
            System.out.println(e.toString());  
            throw new Exception("Error conectándose a la BD");
        }
    }
    
    public void registrarSecretaria(
        String nombre,
        String apellido_paterno,
        String apellido_materno,
        int edad,
        String telefono,
        String turno,
        String contra
    ) throws Exception {
        int id_persona = registrarPersona(
            nombre,
            apellido_paterno,
            apellido_materno,
            edad,
            telefono          
        );                
        
        try{            
            pstmt = con.prepareStatement(
                "INSERT INTO secretaria("
                    + "    id_persona,"
                    + "    turno,"                    
                    + "    contrasenia)"
                    + "VALUES(?, ?, ?)"                
            );
                        
            pstmt.setInt(1, id_persona);
            pstmt.setString(2, turno);
            pstmt.setString(3, contra);            
                                   
            int columnas = pstmt.executeUpdate();                        
            if(columnas == 0){
                throw new Exception("Error. Ninguna columna se creó");
            } else {                                    
                JOptionPane.showMessageDialog(
                    null,
                    "Se ha registrado. El id es " + id_persona
                );                
            }       
        } catch(java.sql.SQLException e){  
            System.out.println(e.toString());  
            throw new Exception("Error conectándose a la BD");
        }                
    }
    
    public void registrarHistoriaClinica(int id_paciente) throws Exception {
        try {
            pstmt = con.prepareStatement(
                "INSERT INTO historiaClinica(id_paciente) VALUES(?)"
            );
            
            pstmt.setInt(1, id_paciente);            
            
            int columnas = pstmt.executeUpdate();
            if(columnas == 0){
                throw new Exception("Error. Ninguna columna se creó");
            } 
        } catch(Exception SQLException) {
            throw new Exception("Error conectándose a la BD");
        }
    }
    
    public int registrarPaciente(
        String nombre,
        String apellido_paterno,
        String apellido_materno,
        int edad,
        String telefono,
        int id_medico,
        Date fecha_nacimiento,
        String contacto_emergencia
    ) throws Exception {
        int id_persona = registrarPersona(
            nombre,
            apellido_paterno,
            apellido_materno,
            edad,
            telefono          
        );
        
        int id_paciente;
        try {
            pstmt = con.prepareStatement(
                "INSERT INTO paciente(" + 
                    "   id_persona," +
                    "   id_medico," +
                    "   fecha_n," +
                    "   contacto_e" +
                    ") VALUES(?, ?, ?, ?)",
                Statement.RETURN_GENERATED_KEYS
            );
            
            pstmt.setInt(1, id_persona);
            pstmt.setInt(2, id_medico);
            pstmt.setString(
                3,
                UtilsEntradas.getStringMySQLDeFecha(fecha_nacimiento)
            );
            pstmt.setString(4, contacto_emergencia);
            
            int columnas = pstmt.executeUpdate();
            if(columnas == 0){
                throw new Exception("Error. Ninguna columna se creó");
            } else {
                ResultSet ids_generados = pstmt.getGeneratedKeys();
                if(ids_generados.next()){
                    id_paciente = ids_generados.getInt(1);
                } else {
                    throw new Exception("Error. Ningún ID fue generado");
                }
            }
        } catch(SQLException e) {
            System.out.println(e.toString());
            throw new Exception("Error conectándose a la BD");
        }
        
        registrarHistoriaClinica(id_paciente);                
        
        return id_paciente;
    }
    
    public int registrarCita(int IDPaciente, Date fecha_hora) throws Exception {        
        try{
            pstmt = con.prepareStatement(
                "INSERT INTO cita(" + 
                    "   id_paciente," +
                    "   fecha" +                    
                    ") VALUES(?, ?)",
                Statement.RETURN_GENERATED_KEYS
            );
            
            pstmt.setInt(1, IDPaciente);
            pstmt.setString(
                2,
                UtilsEntradas.getStringFHMySQLDeFecha(fecha_hora)
            );
            
            int columnas = pstmt.executeUpdate();
            if(columnas == 0){
                throw new Exception("Error. Ninguna columna se creó");
            } else {
                ResultSet ids_generados = pstmt.getGeneratedKeys();
                if(ids_generados.next()){
                    return ids_generados.getInt(1);
                } else {
                    throw new Exception("Error. Ningún ID fue generado");
                }
            }
        } catch(SQLException e){
            System.out.println(e.toString());
            throw new Exception(ConexionBD.MENSAJE_ERROR);
        }
    }
    
    public void registrarVacuna(int IDHistoria, String nombre) throws Exception {
         try{
            pstmt = con.prepareStatement(
                "INSERT INTO vacuna(" + 
                    "   id_historia," +
                    "   nombre" +                    
                    ") VALUES(?, ?)",
                Statement.RETURN_GENERATED_KEYS
            );
            
            pstmt.setInt(1, IDHistoria);
            pstmt.setString(2, nombre);
            
            int columnas = pstmt.executeUpdate();
            if(columnas == 0){
                throw new Exception("Error. Ninguna columna se creó");
            }
        } catch(SQLException e){
            System.out.println(e.toString());
            throw new Exception(ConexionBD.MENSAJE_ERROR);
        }
    }
    
    public void registrarConsultaSubsecuente(
            int IDHistoria,
            String pa,
            String ef,
            String dx,
            String tx,
            String estudios,
            Date fecha
    ) throws Exception {
         try{
            pstmt = con.prepareStatement(
                "INSERT INTO consultaSubsecuente(" + 
                    "   id_historia," +
                    "   pa," +
                    "   ef," +
                    "   dx," +
                    "   tx," +
                    "   estudios," +
                    "   fecha" +
                    ") VALUES(?, ?, ?, ?, ?, ?, ?)",
                Statement.RETURN_GENERATED_KEYS
            );
            
            pstmt.setInt(1, IDHistoria);
            pstmt.setString(2, pa);
            pstmt.setString(3, ef);
            pstmt.setString(4, dx);
            pstmt.setString(5, tx);
            pstmt.setString(6, estudios);
            pstmt.setString(7, UtilsEntradas.getStringMySQLDeFecha(fecha));
            
            int columnas = pstmt.executeUpdate();
            if(columnas == 0){
                throw new Exception("Error. Ninguna columna se creó");
            }
        } catch(SQLException e){
            System.out.println(e.toString());
            throw new Exception(ConexionBD.MENSAJE_ERROR);
        }
    }
}
