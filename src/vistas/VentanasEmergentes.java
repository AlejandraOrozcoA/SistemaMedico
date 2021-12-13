/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package vistas;

import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;

/**
 *
 * @author angel
 */
public class VentanasEmergentes {
    public static boolean getIDPaciente(
        javax.swing.JFrame framePadre
    ) throws Exception {
        JTextField campoIDPaciente = new JTextField(5);        

        JPanel myPanel = new JPanel();
        myPanel.add(new JLabel("ID paciente:"));
        myPanel.add(campoIDPaciente);        

        int result = JOptionPane.showConfirmDialog(
            null,
            myPanel,
            "Introduzca ID de paciente",
            JOptionPane.OK_CANCEL_OPTION
        );
        
        if (result == JOptionPane.OK_OPTION) {
            String id_paciente = campoIDPaciente.getText();            
            
            System.out.println(id_paciente);                                                                                    
            VistaHistoriaClinica historia =  new VistaHistoriaClinica(                    
                id_paciente,
                framePadre
            );
            historia.setVisible(true);                
            return true;                            
        }
        return false;
    }

    public static boolean getIDCita(
        javax.swing.JFrame framePadre
    ) throws Exception {
        JTextField campoIDCita = new JTextField(5);        

        JPanel myPanel = new JPanel();
        myPanel.add(new JLabel("ID cita:"));
        myPanel.add(campoIDCita);

        int result = JOptionPane.showConfirmDialog(
            null,
            myPanel,
            "Introduzca ID de cita",
            JOptionPane.OK_CANCEL_OPTION
        );
        
        if (result == JOptionPane.OK_OPTION) {
            String id_cita = campoIDCita.getText();            
                        
            VistaCita v_cita =  new VistaCita(
                id_cita,
                framePadre
            );
            v_cita.setVisible(true);                
            return true;                            
        }
        return false;
    }
    
    public static boolean confirmarOperacion(String mensaje){               
        JPanel myPanel = new JPanel();
        myPanel.add(new JLabel(mensaje));        

        int result = JOptionPane.showConfirmDialog(
            null,
            myPanel,
            "Confirmación",
            JOptionPane.OK_CANCEL_OPTION
        );
        
        if (result == JOptionPane.OK_OPTION) {            
            return true;                            
        }
        return false;
    }
}
