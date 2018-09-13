package com.cubic.lunch.services.Impl;

import java.io.File;
import java.io.FileOutputStream;
import java.io.OutputStream;
import java.util.Date;
import java.util.Scanner;

import com.itextpdf.text.BaseColor;
import com.itextpdf.text.Chunk;
import com.itextpdf.text.Document;
import com.itextpdf.text.Element;
import com.itextpdf.text.Image;
import com.itextpdf.text.List;
import com.itextpdf.text.ListItem;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;

public class PdfGen {

    public static void main(String[] args) {

    	Scanner sc=new Scanner(System.in);
    	System.out.println("Enter how many columns you want ??");
    	int l=sc.nextInt();
        try {

              OutputStream file = new FileOutputStream(new File("src/pdf/abc.pdf"));
	          Document document = new Document();
	          PdfWriter.getInstance(document, file);

			/*//Inserting Image in PDF
			     Image image = Image.getInstance ("src/pdf/sign.jpeg");
			     image.scaleAbsolute(120f, 60f);//image width,height	
*/
			//Inserting Table in PDF
			     PdfPTable table=new PdfPTable(l);   // how many coloumns you want 3
			     	for (int i = 1; i <= l; i++) {
	

	                     PdfPCell cell = new PdfPCell (new Paragraph ("Name "+i));
	                     cell.setHorizontalAlignment (Element.ALIGN_CENTER);
	                     cell.setPadding (10.0f);
	                     cell.setBackgroundColor (new BaseColor (140, 221, 8));
	                     table.addCell(cell);
			     		}  
	                    /* PdfPCell cell1 = new PdfPCell (new Paragraph ("Address"));
	                     PdfPCell cell2 = new PdfPCell (new Paragraph ("Country"));
				      //cell.setColspan (3); //to colspan coliumns
				      
				      cell1.setHorizontalAlignment (Element.ALIGN_CENTER);
				      cell2.setHorizontalAlignment (Element.ALIGN_CENTER);
				      
				      cell1.setPadding (10.0f);
				      cell2.setPadding (10.0f);
				      					               
				      cell1.setBackgroundColor (new BaseColor (140, 221, 8));
				      cell2.setBackgroundColor (new BaseColor (140, 221, 8));
				      
				      
				      table.addCell(cell1);
				      table.addCell(cell2);*/

				      /*table.addCell("Name");
				      table.addCell("Address");
				      table.addCell("Country");*/
				      for (int i = 0; i < 15; i++) {
				    	  for (int j = 1; j < l; j++) {
				    		  table.addCell(""+i);
						      table.addCell(""+i);
						      table.addCell(""+i);
						      table.setSpacingBefore(30.0f);       // Space Before table starts, like margin-top in CSS
						      table.setSpacingAfter(30.0f);        // Space After table starts, like margin-Bottom in CSS								          

						}
				    	  
					}
                      
			 /*//Inserting List in PDF
				      List list=new List(true,30);
			          list.add(new ListItem("Java4s"));
				      list.add(new ListItem("Php4s"));
				      list.add(new ListItem("Some Thing..."));		
*/
			 /*//Text formating in PDF
	                Chunk chunk=new Chunk("Welecome To Java4s Programming Blog...");
					chunk.setUnderline(+1f,-2f);//1st co-ordinate is for line width,2nd is space between
					Chunk chunk1=new Chunk("Php4s.com");
					chunk1.setUnderline(+4f,-8f);
					chunk1.setBackground(new BaseColor (17, 46, 193));      
*/
			 //Now Insert Every Thing Into PDF Document
		         document.open();//PDF document opened........			       

				//	document.add(image);

					document.add(Chunk.NEWLINE);   //Something like in HTML 🙂

                    document.add(new Paragraph("Dear Java4s.com"));
	                document.add(new Paragraph("Document Generated On - "+new Date().toString()));	

					document.add(table);

					/*document.add(chunk);
					document.add(chunk1);

					document.add(Chunk.NEWLINE);   //Something like in HTML 🙂							    
*/
       				document.newPage();            //Opened new page
			//		document.add(list);            //In the new page we are going to add list

		         document.close();

			             file.close();

            System.out.println("Pdf created successfully..");

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}