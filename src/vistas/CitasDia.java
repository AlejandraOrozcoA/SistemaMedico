/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package vistas;

import sistemamedico.Cita;
import Controlador.ConsultasObjetos;
import java.awt.Dimension;
import java.util.stream.Collectors;
import java.util.ArrayList;
import java.util.Date;
import sistemamedico.UtilsEntradas;

/**
 *
 * @author angel
 */
public class CitasDia extends javax.swing.JFrame {
    
    private Date fecha;
    private String fecha_str;    
    javax.swing.JPanel panel;
    javax.swing.JScrollPane scroll;
    javax.swing.JLabel l1, l2, titulo;
    javax.swing.JButton b1, b2, atras;
    javax.swing.JFrame framePadre;
    
    ArrayList<Cita> citas;
    ArrayList<javax.swing.JLabel> fechas;
    ArrayList<javax.swing.JButton> botones;
    
    public javax.swing.JButton creaBoton(Cita cita) {
        javax.swing.JButton boton = new javax.swing.JButton("Ver");
        boton.addActionListener((java.awt.event.ActionEvent evt) -> {
            try {
                VistaCita vista_cita = new VistaCita(
                    Integer.toString(cita.getId()),
                    this
                );
            } catch(Exception e){

            }
        });
        return boton;
    }    
    
    public void colocaHoras(javax.swing.JPanel panel) throws Exception{
        ConsultasObjetos co = new ConsultasObjetos();
        citas = co.getCitas(this.fecha);
        
        panel.setSize(370, 200 + 50 * citas.size());                
        
        fechas = new ArrayList<javax.swing.JLabel>();
        botones = new ArrayList<javax.swing.JButton>();
                                
        int n = citas.size();                
        for(int i = 0; i < n; i++){            
            Cita cita = citas.get(i);
            
            fechas.add(new javax.swing.JLabel(
                UtilsEntradas.getHoradeFecha(cita.getFecha())
            ));
            fechas.get(i).setBounds(30, 80 + 50 * i, 100, 30);
            
            botones.add(new javax.swing.JButton("Ver"));                                    
            botones.get(i).setBounds(260, 80 + 50 * i, 100, 30);
            botones.get(i).addActionListener(
                (java.awt.event.ActionEvent evt) -> {
                    try {
                        VistaCita vista_cita = new VistaCita(
                            Integer.toString(cita.getId()),
                            this.framePadre
                        );
                        vista_cita.setVisible(true);
                        this.setVisible(false);
                    } catch(Exception e){
                        
                    }
                }
            );                        
        }
        
        for(javax.swing.JLabel fecha : fechas){
            panel.add(fecha);            
        }        
        for(javax.swing.JButton boton : botones){
            panel.add(boton);
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
        titulo = new javax.swing.JLabel("Citas de " + fecha_str);
        titulo.setFont(new java.awt.Font("Ubuntu", 3, 15));
        titulo.setBounds(0, 25, 400, 30);
        titulo.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        titulo.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        panel.add(titulo);        
        
        /*l1.setBounds(30, 80, 100, 30);
        l2.setBounds(30, 130, 100, 30);
        b1.setBounds(260, 80, 100, 30);
        b2.setBounds(260, 130, 100, 30);*/
                                
        
        colocaHoras(panel);        
        
        atras = new javax.swing.JButton("Atrás");
        atras.setIcon(new javax.swing.ImageIcon(
                getClass().getResource("/Recursos/back.png")));
        atras.addActionListener((java.awt.event.ActionEvent evt) -> {
            this.setVisible(false);
            this.framePadre.setVisible(true);
        });  
        atras.setBounds(30, 100 + 50 * citas.size() , 100, 30);
        panel.add(atras);
        
        scroll = new javax.swing.JScrollPane();
        scroll.setViewportView(panel);        
        scroll.setBounds(0, 0, 400, 600);
        
        add(scroll);                            
    }
    
    public CitasDia(
        String fecha_str,
        javax.swing.JFrame framePadre
    ) throws Exception{                
        this.fecha_str = fecha_str;
        this.framePadre = framePadre;
        this.fecha = UtilsEntradas.getFechaDeString(fecha_str);        
        
        initComponents();        
    }           
}
