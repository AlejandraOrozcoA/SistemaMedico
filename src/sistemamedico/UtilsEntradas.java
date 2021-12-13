/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package sistemamedico;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 *
 * @author angel
 */
public class UtilsEntradas {    
    public static Date getFechaDeString(
        String fecha_str
    ) throws ParseException {
        String patron = "dd/MM/yyyy";
        SimpleDateFormat formato = new SimpleDateFormat(patron);
        return formato.parse(fecha_str);        
    }
    
    public static Date getHoraDeString(
        String hora_str
    ) throws ParseException {
        String patron = "HH:mm";
        SimpleDateFormat formato = new SimpleDateFormat(patron);
        return formato.parse(hora_str);        
    }
    
    public static Date getFechaDeStringSQL(
        String fecha_str
    ) throws ParseException{
        String patron = "yyyy-MM-dd";
        SimpleDateFormat formato = new SimpleDateFormat(patron);
        return formato.parse(fecha_str);
    }
    
    public static Date getFechaDeStringFHSQL(
        String fecha_str
    ) throws ParseException{
        String patron = "yyyy-MM-dd HH:mm:ss";
        SimpleDateFormat formato = new SimpleDateFormat(patron);
        return formato.parse(fecha_str);
    }
    
    public static String getStringDeFecha(Date fecha) {
        String patron = "dd/MM/yyyy";
        SimpleDateFormat formato = new SimpleDateFormat(patron);
        return formato.format(fecha);
    }
    
    public static String getStringFHDeFecha(Date fecha) {
        String patron = "dd/MM/yyyy HH:mm";
        SimpleDateFormat formato = new SimpleDateFormat(patron);
        return formato.format(fecha);
    }
    
    public static String getHoradeFecha(Date fecha){
        String patron = "HH:mm";
        SimpleDateFormat formato = new SimpleDateFormat(patron);
        return formato.format(fecha);
    }
    
    public static String getStringMySQLDeFecha(Date fecha) {
        String patron = "yyyy-MM-dd";
        SimpleDateFormat formato = new SimpleDateFormat(patron);
        return formato.format(fecha);
    }       
    
    public static String getStringFHMySQLDeFecha(Date fecha){
        String patron = "yyyy-MM-dd HH:mm:ss";
        SimpleDateFormat formato = new SimpleDateFormat(patron);
        return formato.format(fecha);
    }
}
