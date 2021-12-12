/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Controlador;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import sistemamedico.Medico;

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
