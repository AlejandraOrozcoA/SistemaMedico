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
import java.util.Date;
import sistemamedico.HistoriaClinica;
import sistemamedico.Medico;
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
    
    public HistoriaClinica getHistoriaClinica(int id_paciente) throws Exception{
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
            pstmt.setInt(1, id_paciente);
                        
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
    
    public Medico getMedico(String id_medico) throws Exception{
        try {
            pstmt = con.prepareStatement(
                "select" +
                "    cedula, consultorio, turno, especialidad " +
                "from medico where id_medico = ?"
            );
            pstmt.setString(1, id_medico);
                        
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

                return new Medico(cedula, consultorio, turno, especialidad);        
            } else {
                throw new Exception("No hay médico con ese id");
            }
        } catch (SQLException e) {
            System.out.println(e.toString());
            throw new Exception(ConexionBD.MENSAJE_ERROR);
        }                
    }
}
