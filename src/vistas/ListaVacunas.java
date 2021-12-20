/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package vistas;

import Controlador.ConsultasObjetos;
import java.awt.Dimension;
import java.util.ArrayList;
import sistemamedico.Cita;
import sistemamedico.UtilsEntradas;

/**
 *
 * @author angel
 */
public class ListaVacunas extends javax.swing.JFrame {
    
    int IDHistoria;
    javax.swing.JFrame framePadre;
    javax.swing.JPanel panel;
    javax.swing.JScrollPane scroll;
    javax.swing.JLabel titulo;
    javax.swing.JButton atras;    
        
    ArrayList<String> vacunas;    
    ArrayList<javax.swing.JLabel> labels_vacunas;    

    public void colocaVacunas(javax.swing.JPanel panel) throws Exception{
        ConsultasObjetos co = new ConsultasObjetos();
        vacunas = co.getVacunas(this.IDHistoria);
        
        panel.setSize(370, 200 + 50 * vacunas.size());
        
        labels_vacunas = new ArrayList<javax.swing.JLabel>();        
                                
        int n = vacunas.size();                
        for(int i = 0; i < n; i++){            
            String nombre_vacuna = vacunas.get(i);
            
            labels_vacunas.add(new javax.swing.JLabel(
                nombre_vacuna                    
            ));
            labels_vacunas.get(i).setBounds(30, 80 + 50 * i, 100, 30);                                           
        }
        
        for(javax.swing.JLabel label_vacuna: labels_vacunas){
            panel.add(label_vacuna);            
        }        
        
    }    
    
    private void initComponents() throws Exception {
        setSize(400, 600);
        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
                        
        panel = new javax.swing.JPanel();                
        panel.setPreferredSize(new Dimension(380, 600));        
        panel.setLayout(null);
        
        // l1 = new javax.swing.JLabel("12345678901234567890123456789012456789");
        // l2 = new javax.swing.JLabel("yd");
        
        // b1 = new javax.swing.JButton("Ver");
        // b2 = new javax.swing.JButton("Ver");                    
        titulo = new javax.swing.JLabel("Lista de vacunas");
        titulo.setFont(new java.awt.Font("Ubuntu", 3, 15));
        titulo.setBounds(0, 25, 400, 30);
        titulo.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        titulo.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        panel.add(titulo);        
        
        /*l1.setBounds(30, 80, 100, 30);
        l2.setBounds(30, 130, 100, 30);
        b1.setBounds(260, 80, 100, 30);
        b2.setBounds(260, 130, 100, 30);*/   
        
        colocaVacunas(panel);
        
        atras = new javax.swing.JButton("Atrás");
        atras.setIcon(new javax.swing.ImageIcon(
                getClass().getResource("/Recursos/back.png")));
        atras.addActionListener((java.awt.event.ActionEvent evt) -> {
            this.setVisible(false);
            this.framePadre.setVisible(true);
        });  
        atras.setBounds(30, 100 + 50 * vacunas.size(), 100, 30);
        panel.add(atras);
        
        scroll = new javax.swing.JScrollPane();
        scroll.setViewportView(panel);        
        scroll.setBounds(0, 0, 400, 600);
        
        add(scroll);                            
    }
    
    
    public ListaVacunas(
        int IDHistoria,
        javax.swing.JFrame framePadre
    ) throws Exception{                
        this.IDHistoria = IDHistoria;
        this.framePadre = framePadre;        
        
        initComponents(); 
    }
}
