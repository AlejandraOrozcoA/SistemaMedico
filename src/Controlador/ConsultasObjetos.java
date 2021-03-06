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
import sistemamedico.ConsultaSubsecuente;
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
                        "La fecha de hospitalizaci??n almacenada no est?? en " +
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
                    throw new Exception("La c??dula almacenado no es un n??mero");
                }

                int consultorio;
                try{
                    consultorio = Integer.parseInt(
                        resultado.getString("consultorio")
                    );
                } catch(NumberFormatException e){
                    System.out.println(e.toString());
                    throw new Exception(
                        "El consultorio almacenado no es un n??mero"
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
                med.setDireccion((String) datosP.get(5));                                

                return med;    
            } else {
                throw new Exception("No hay m??dico con ese id");
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
                        "La fecha no est?? almacenada en formato " +
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
    
    public ArrayList<Cita> getCitas(Date fecha) throws Exception {
        
        ArrayList<Cita> citas = new ArrayList<Cita>();        
        
        try {
            pstmt = con.prepareStatement(
                "SELECT" +
                "    id_cita," +                
                "    fecha " +                
                "FROM cita where DATE(fecha) = ? " +
                "ORDER BY fecha"
            );            
            pstmt.setString(1, UtilsEntradas.getStringMySQLDeFecha(fecha));
                        
            resultado = pstmt.executeQuery();            
            
            if(resultado != null){                   
                while(resultado.next()){
                
                    int id_cita = resultado.getInt("id_cita"); 

                    String fecha_hora_str = resultado.getString("fecha");
                    Date fecha_hora;                
                    try{
                        fecha = UtilsEntradas.getFechaDeStringFHSQL(
                            fecha_hora_str
                        );
                    } catch(java.lang.NullPointerException e){
                        fecha_hora = new Date();
                    } catch(ParseException e){
                        System.out.println(e.toString());
                        throw new Exception(
                            "La fecha no est?? almacenada en formato " +
                            "aaaa-mm-dd hh:mm:ss"
                        );
                    }                

                    citas.add(new Cita(id_cita, fecha));
                }
                return citas;
            } else {
                throw new Exception("No hay cita con ese ID");
            }
        } catch (SQLException e) {
            System.out.println(e.toString());
            throw new Exception(ConexionBD.MENSAJE_ERROR);
        } 
    }
    
    public ArrayList<String> getVacunas(int IDHistoria) throws Exception {
        
        ArrayList<String> vacunas = new ArrayList<String>();        
        
        try {
            pstmt = con.prepareStatement(
                "SELECT" +
                "    nombre " +                                
                "FROM vacuna where id_historia = ?"
            );            
            pstmt.setInt(1,IDHistoria);
                        
            resultado = pstmt.executeQuery();            
            
            if(resultado != null){                   
                while(resultado.next()){                
                    String nombre = resultado.getString("nombre"); 
                    vacunas.add(nombre);
                }
                return vacunas;
            } else {
                throw new Exception("No hay cita con ese ID");
            }
        } catch (SQLException e) {
            System.out.println(e.toString());
            throw new Exception(ConexionBD.MENSAJE_ERROR);
        } 
    }
    
    public ArrayList<ConsultaSubsecuente> getConsultas(
        int IDHistoria
    ) throws Exception {        
        ArrayList<ConsultaSubsecuente> consultas =
            new ArrayList<ConsultaSubsecuente>();
        
        try {
            pstmt = con.prepareStatement(
                "SELECT" +
                "    id_consulta," +
                "    pa," +                
                "    ef," +
                "    dx," +
                "    tx," +
                "    estudios," +
                "    fecha " +
                "FROM consultaSubsecuente WHERE id_historia = ? " +
                "ORDER BY fecha"
            );            
            pstmt.setInt(1, IDHistoria);
                        
            resultado = pstmt.executeQuery();            
            
            if(resultado != null){                   
                while(resultado.next()){
                
                    int id_consulta = resultado.getInt("id_consulta");
                    String pa = resultado.getString("pa");
                    String ef = resultado.getString("ef");
                    String dx = resultado.getString("dx");
                    String tx = resultado.getString("tx");
                    String estudios = resultado.getString("estudios");
                    String fecha_str = resultado.getString("fecha");
                    
                    Date fecha;
                    try{
                        fecha = UtilsEntradas.getFechaDeStringSQL(fecha_str);
                    } catch(ParseException e){
                        System.out.println(e.toString());
                        throw new Exception(
                            "La fecha no est?? almacenada en formato dd-mm-aaaa"
                        );
                    }

                    consultas.add(new ConsultaSubsecuente(
                        id_consulta,
                        pa,
                        ef,
                        dx,
                        tx,
                        estudios,
                        fecha
                    ));                    
                }
                return consultas;
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
    
    public String getDireccion(int IDDireccion) throws Exception {
        try {
            pstmt = con.prepareStatement(
               "select * from direccion where id_dir = ?"
            );
            pstmt.setInt(1, IDDireccion);
                        
            resultado = pstmt.executeQuery();            
            
            if(resultado != null && resultado.next()){ 
                
                String calle = resultado.getString("calle");
                String colonia = resultado.getString("colonia");
                String municipio = resultado.getString("municipio");
                String estado = resultado.getString("estado");
                int num_int = resultado.getInt("num_int");
                int num_ext = resultado.getInt("num_ext");
                int cp = resultado.getInt("cp");                
                
                String direccion = calle + " " + num_ext + ", int. " + num_int +
                    ". " + colonia + ". " + municipio + ". " + estado + 
                    ". C. P. " + cp ;
                
                return direccion;
            } else {
                throw new Exception("No hay direccion con ese ID");
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
                int id_direccion = resultado.getInt("id_direccion");
                String direccion = getDireccion(id_direccion);
                
                ArrayList arr = new ArrayList();
                arr.add(nombre);
                arr.add(apP);
                arr.add(apM);
                arr.add(edad);
                arr.add(tel);
                arr.add(direccion);
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
