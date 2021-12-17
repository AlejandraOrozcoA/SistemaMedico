/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Controlador;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import sistemamedico.Cita;
import sistemamedico.HistoriaClinica;
import sistemamedico.Medico;
import sistemamedico.Paciente;
import sistemamedico.Persona;
import sistemamedico.UtilsEntradas;

/**
 *
 * @author angel
 */
public class ConsultasObjetos {
    private PreparedStatement pstmt;
    private ResultSet resultado;
    private Connection con;
    private ConexionBD objBD;      

    public ConsultasObjetos() {
        objBD= ConexionBD.getInstance();
        objBD.conectar();
        con = objBD.getConexion();
    }  
    
    public HistoriaClinica getHistoriaClinica(int IDPaciente) throws Exception{
        try {
            pstmt = con.prepareStatement(
                "select" +
                "    id_historia," +
                "    ahf," +
                "    apnp," +
                "    perinatales," +
                "    talla," +
                "    apgar," +
                "    hospitalizado," +
                "    complicaciones," +
                "    tamiz_metabolico," +
                "    tamiz_auditivo " +
                "from historiaClinica where id_paciente = ?"
            );
            pstmt.setInt(1, IDPaciente);
                        
            resultado = pstmt.executeQuery();            
            
            if(resultado != null && resultado.next()){                
                
                int id_historia = resultado.getInt("id_historia");                
                String ahf = resultado.getString("ahf");                
                String apnp = resultado.getString("apnp");                
                double talla = resultado.getDouble("talla");                
                String apgar = resultado.getString("apgar");                                
                String complicaciones = resultado.getString("complicaciones");                
                String tamiz_metabolico = resultado.getString(
                    "tamiz_metabolico"
                );                
                String tamiz_auditivo = resultado.getString("tamiz_auditivo");                                               
                
                String hospitalizado_str = resultado.getString("hospitalizado");
                Date hospitalizado;                
                try{
                    hospitalizado = UtilsEntradas.getFechaDeStringSQL(
                        hospitalizado_str
                    );
                } catch(java.lang.NullPointerException e){
                    hospitalizado = new Date();
                } catch(ParseException e){
                    System.out.println(e.toString());
                    throw new Exception(
                        "La fecha de hospitalización almacenada no está en " +
                        "formato aaaa-mm-ddd"
                    );
                }                

                return new HistoriaClinica(
                    id_historia,
                    ahf,
                    apnp,
                    complicaciones,
                    talla,
                    apgar,
                    hospitalizado,
                    complicaciones,
                    tamiz_metabolico,
                    tamiz_auditivo
                );
            } else {
                throw new Exception("No hay paciente con ese ID");
            }
        } catch (SQLException e) {
            System.out.println(e.toString());
            throw new Exception(ConexionBD.MENSAJE_ERROR);
        }          
    }
    
    public Medico getMedico(String IDMedico) throws Exception{
        try {
            pstmt = con.prepareStatement(
                "select" +
                " id_persona,   cedula, consultorio, turno, especialidad " +
                "from medico where id_medico = ?"
            );
            pstmt.setString(1, IDMedico);
                        
            resultado = pstmt.executeQuery();            
            
            if(resultado != null){
                resultado.next();

                Long cedula;
                try{
                    cedula = Long.parseLong(resultado.getString("cedula"));
                } catch(NumberFormatException e){
                    System.out.println(e.toString());
                    throw new Exception("La cédula almacenado no es un número");
                }

                int consultorio;
                try{
                    consultorio = Integer.parseInt(
                        resultado.getString("consultorio")
                    );
                } catch(NumberFormatException e){
                    System.out.println(e.toString());
                    throw new Exception(
                        "El consultorio almacenado no es un número"
                    );
                }

                String turno_str = resultado.getString("turno");
                char turno = turno_str.charAt(0);

                String especialidad = resultado.getString("especialidad");
                int id_persona = resultado.getInt("id_persona");
                ArrayList datosP = this.getPersona(id_persona);
                
                Medico med = new Medico(cedula, consultorio, turno, especialidad); 
                med.setNombre((String)datosP.get(0));
                med.setApellidoPaterno((String)datosP.get(1));
                med.setApellidoMaterno((String)datosP.get(2));
                med.setEdad((int)datosP.get(3));
                med.setTelefono((Long)datosP.get(4));

                return med;    
            } else {
                throw new Exception("No hay médico con ese id");
            }
        } catch (SQLException e) {
            System.out.println(e.toString());
            throw new Exception(ConexionBD.MENSAJE_ERROR);
        }                
    }
    
    public Cita getCita(int IDCita) throws Exception{
        try {
            pstmt = con.prepareStatement(
                "select" +
                "    id_paciente," +
                "    fecha " +                
                "from cita where id_cita = ?"
            );
            pstmt.setInt(1, IDCita);
                        
            resultado = pstmt.executeQuery();            
            
            if(resultado != null && resultado.next()){                
                
                int id_paciente = resultado.getInt("id_paciente");                                                                              
                
                String fecha_str = resultado.getString("fecha");                
                Date fecha;                
                try{
                    fecha = UtilsEntradas.getFechaDeStringFHSQL(
                        fecha_str
                    );
                } catch(java.lang.NullPointerException e){
                    fecha = new Date();
                } catch(ParseException e){
                    System.out.println(e.toString());
                    throw new Exception(
                        "La fecha no está almacenada en formato " +
                        "aaaa-mm-dd hh:mm:ss"
                    );
                }                

                return new Cita(IDCita, id_paciente, fecha);
            } else {
                throw new Exception("No hay cita con ese ID");
            }
        } catch (SQLException e) {
            System.out.println(e.toString());
            throw new Exception(ConexionBD.MENSAJE_ERROR);
        } 
    }
    
    public Paciente getPacente(int IDpaciente) throws Exception{
        try {
            pstmt = con.prepareStatement(
               "select * from paciente where id_paciente = ?"
            );
            pstmt.setInt(1, IDpaciente);
                        
            resultado = pstmt.executeQuery();            
            
            if(resultado != null && resultado.next()){ 
                
                int id_medico = resultado.getInt("id_medico");
                int id_persona = resultado.getInt("id_persona");
                String contactoEmergencia = resultado.getString("contacto_e");
                String fechaN_str = resultado.getString("fecha_n");                
                Date fecha;                
                try{
                    fecha = new SimpleDateFormat("yyyy-MM-dd").parse(fechaN_str); 
                } catch(java.lang.NullPointerException e){
                    fecha = new Date();
                }          
                ArrayList datosP = this.getPersona(id_persona);
                Medico med = this.getMedico(String.valueOf(id_medico));
                
                Paciente paciente = new Paciente(fecha,contactoEmergencia,med);
                paciente.setNombre((String)datosP.get(0));
                paciente.setApellidoPaterno((String)datosP.get(1));
                paciente.setApellidoMaterno((String)datosP.get(2));
                paciente.setEdad((int)datosP.get(3));
                paciente.setTelefono((Long)datosP.get(4));
                
                return  paciente;
            } else {
                throw new Exception("No hay paciente con ese ID");
            }
        } catch (SQLException e) {
            System.out.println(e.toString());
            throw new Exception(ConexionBD.MENSAJE_ERROR);
        } 
    }
    
    public ArrayList getPersona(int IDpersona) throws Exception{
        try {
            pstmt = con.prepareStatement(
               "select * from persona where id_persona = ?"
            );
            pstmt.setInt(1, IDpersona);
                        
            resultado = pstmt.executeQuery();            
            
            if(resultado != null && resultado.next()){ 
                
                String nombre = resultado.getString("nombre");
                String apP = resultado.getString("apellido_p");
                String apM = resultado.getString("apellido_m");
                int edad = resultado.getInt("edad");                
                Long tel= resultado.getLong("telefono");
                
                ArrayList arr = new ArrayList();
                arr.add(nombre);
                arr.add(apP);
                arr.add(apM);
                arr.add(edad);
                arr.add(tel);
                return arr;
            } else {
                throw new Exception("No hay paciente con ese ID");
            }
        } catch (SQLException e) {
            System.out.println(e.toString());
            throw new Exception(ConexionBD.MENSAJE_ERROR);
        } 
    }
}
