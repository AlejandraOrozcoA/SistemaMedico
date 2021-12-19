/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package sistemamedico;

import com.itextpdf.text.Chunk;
import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Element;
import com.itextpdf.text.Font;
import com.itextpdf.text.PageSize;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.Phrase;
import com.itextpdf.text.pdf.ColumnText;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfPageEventHelper;
import com.itextpdf.text.pdf.PdfWriter;
import java.awt.Desktop;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Date;

/**
 *
 * @author angel
 */
public class Receta {
    
    private Medico medico;
    private Paciente paciente;
    private String tratamiento;    
    private double peso;
    private double altura;
    
    class FooterEvent extends PdfPageEventHelper {
        public void onEndPage(PdfWriter writer, Document document){                                                                                                
            ColumnText.showTextAligned(
                writer.getDirectContent(),
                Element.ALIGN_CENTER,
                new Phrase(medico.getMensajeFooter()),
                // new Phrase("Calle mixtecas 9, int. 17. Ajusto. Iztapalapa. CDMX. C.P. 43000 - Tel. 5541890302 - Previa cita"),
                (document.right() - document.left()) / 2 + document.leftMargin(),
                document.bottom() - 10,
                0
            );
        }
    }
    
    public Receta(
        Medico medico,
        Paciente paciente,
        double peso,
        double altura,
        String tratamiento
    ){
        this.medico = medico;
        this.paciente = paciente;
        this.peso = peso;       
        this.altura = altura;
        this.tratamiento = tratamiento;        
    }
    
    private PdfPCell getCell(String text, int alignment) {
        PdfPCell cell = new PdfPCell(new Phrase(text));
        cell.setPadding(0);
        cell.setHorizontalAlignment(alignment);
        cell.setBorder(PdfPCell.NO_BORDER);
        return cell;
    }
    
    public void crearPDF() throws FileNotFoundException, DocumentException{
        Document document = new Document(PageSize.A5.rotate(), 25, 25, 25, 25);
        
        PdfWriter pdfw = PdfWriter.getInstance(document, new FileOutputStream("result.pdf"));                               
        FooterEvent event = new FooterEvent();
        pdfw.setPageEvent(event);
        
        document.open();
        
        Paragraph nombre = new Paragraph(medico.getNombreCompleto());
        nombre.setAlignment(Element.ALIGN_CENTER);
        
        Paragraph especialidad = new Paragraph(medico.getEspecialidad());
        especialidad.setAlignment(Element.ALIGN_CENTER);                        
        
        Paragraph cedula = new Paragraph("Ced. Prof. " + medico.getCedula());
        cedula.setAlignment(Element.ALIGN_CENTER);
        
        PdfPTable table = new PdfPTable(2);
        table.setWidthPercentage(100);
        table.addCell(
            getCell(
                "Nombre: " + paciente.getNombreCompleto(),
                PdfPCell.ALIGN_LEFT
            )
        );        
        table.addCell(getCell(
            "Fecha: " + UtilsEntradas.getStringDeFecha(new Date()),
            PdfPCell.ALIGN_RIGHT
        ));        
        table.addCell(getCell(
            "Peso: " + this.peso + " Kg",
            PdfPCell.ALIGN_LEFT
        ));
        table.addCell(getCell(
            "Altura: " + this.altura,
            PdfPCell.ALIGN_RIGHT
        ));  
        
        Font fuente =  new Font();
        fuente.setStyle(Font.BOLD);
        
        Paragraph label_tratamiento = new Paragraph(
            "Tratamiento",
            fuente
        );
        
        Paragraph texto_tratamiento = new Paragraph(this.tratamiento);
                        
        document.add(nombre);
        document.add(especialidad);
        document.add(cedula);
        document.add(Chunk.NEWLINE);
        document.add(table);        
        document.add(Chunk.NEWLINE);        
        document.add(label_tratamiento);
        document.add(texto_tratamiento);
        
        document.close();     
    }
    
    public void abreArchivo() throws Exception{
        try{
            File file = new File("result.pdf");   
            if(!Desktop.isDesktopSupported()){  
                throw new Exception("No hay sistema de archivos disponible");
            }  
            Desktop desktop = Desktop.getDesktop();  
            if(file.exists()){
                desktop.open(file);
            }
        }  catch(IOException e){  
            System.out.println(e.toString());
            throw new Exception("No fue posible abrir el archivo");
        }  
    }
}
