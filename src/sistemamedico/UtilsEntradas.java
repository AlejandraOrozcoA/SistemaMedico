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
    public static Date getFechaDeString(String fecha_str) throws ParseException {
        String patron = "dd/MM/yyyy";
        SimpleDateFormat formato = new SimpleDateFormat(patron);
        return formato.parse(fecha_str);        
    }
    
    public static String getStringDeFecha(Date fecha) {
        String patron = "dd/MM/yyyy";
        SimpleDateFormat formato = new SimpleDateFormat(patron);
        return formato.format(fecha);
    }
    
    public static String getStringMySQLDeFecha(Date fecha) {
        String patron = "yyyy-MM-dd";
        SimpleDateFormat formato = new SimpleDateFormat(patron);
        return formato.format(fecha);
    }
}
