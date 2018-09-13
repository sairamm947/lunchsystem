package com.cubic.lunch.services.Impl;

import java.io.File;
import java.io.FileOutputStream;
import java.io.OutputStream;
import java.util.Date;
import java.util.List;
import java.util.Scanner;

import javax.transaction.Transactional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cubic.lunch.dao.AdminDao;
import com.cubic.lunch.dao.ProductDao;
import com.cubic.lunch.model.Accounts;
import com.cubic.lunch.model.Products;
import com.cubic.lunch.services.AdminService;
import com.itextpdf.text.BaseColor;
import com.itextpdf.text.Chunk;
import com.itextpdf.text.Document;
import com.itextpdf.text.Element;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;

@Service
@Transactional
public class AdminServiceImpl implements AdminService{

	private static final Logger logger = LoggerFactory.getLogger(AdminServiceImpl.class);

	@Autowired
	private AdminDao admindao;
	
	@Autowired
	private ProductDao productdao;
	
	@Override
	@Transactional(rollbackOn=Exception.class)
	public void register(Accounts account) {
		try {
			admindao.save(account);
		} catch (Exception e) {
			logger.info("empoyee not registered :: repository");
		}
		
	}

	@Override
	public Accounts loginservice(String name, String password,String role) {
		Accounts account=null;
		try {
			logger.info("Entered into loginserviceImpl"+name+" ; "+password+" ;"+role);
			account =admindao.getAccount(name,password,role);
			logger.info("Ended into loginserviceImpl with details :: "+account);
		} catch (Exception e) {
			logger.info("User not Available");
		}
		return account;
	}

	@Override
	public void addproduct(Products products) {
		try {
			productdao.save(products);
		} catch (Exception e) {
			logger.info("product not Added :: repository");
		}
		
	}

	@Override
	public List<Products> showproducts() {
		List<Products> list = null;
		try {
			list=productdao.findAll();
		} catch (Exception e) {
			logger.info("Products not getting:: repository");
		}
		return list;
	}

	@Override
	public boolean checkproduct(String productid) {
		try {
			logger.info(productdao.findByProductId(productid));
			if (productdao.findByProductId(productid)==null) {
				logger.info("Product Not Exits :: repository");
				return true;
			} 
		} catch (Exception e) {
			logger.info("Exception in product Checking is exits or not");
		}
		return false;
	}

	@Override
	public String pdfgen() {
		String status;
		try {
			
			OutputStream file = new FileOutputStream(new File("src/pdf/abc.pdf"));
			Document document = new Document();
			PdfWriter.getInstance(document, file);

			/*
			 * //Inserting Image in PDF Image image = Image.getInstance
			 * ("src/pdf/sign.jpeg"); image.scaleAbsolute(120f, 60f);//image width,height
			 */
			// Inserting Table in PDF
			PdfPTable table = new PdfPTable(3); // how many coloumns you want 3
			PdfPCell cell = new PdfPCell(new Paragraph("Product Id"));
			PdfPCell cell1 = new PdfPCell(new Paragraph("Product Name"));
			PdfPCell cell2 = new PdfPCell(new Paragraph("Product Price"));
			cell.setHorizontalAlignment(Element.ALIGN_CENTER);
			cell1.setHorizontalAlignment(Element.ALIGN_CENTER);
			cell2.setHorizontalAlignment(Element.ALIGN_CENTER);
			cell.setPadding(10.0f);
			cell1.setPadding(10.0f);
			cell2.setPadding(10.0f);
			cell.setBackgroundColor(new BaseColor(140, 221, 8));
			cell1.setBackgroundColor(new BaseColor(140, 221, 8));
			cell2.setBackgroundColor(new BaseColor(140, 221, 8));
			
			table.addCell(cell);
			table.addCell(cell1);
			table.addCell(cell2);
			// cell.setColspan (3); //to colspan coliumns

			List<Products> p=productdao.findAll();
			for (Products products : p) {
				table.addCell(products.getProductid());
				table.addCell(products.getProductName());
				table.addCell(""+products.getPrice());
				table.setSpacingBefore(30.0f); // Space Before table starts, like margin-top in CSS
				table.setSpacingAfter(30.0f); // Space After table starts, like margin-Bottom in CSS

			}
			document.open();// PDF document opened........

			document.add(Chunk.NEWLINE); // Something like in HTML 🙂

			document.add(new Paragraph("Lunch Tracking System"));
			document.add(new Paragraph("Document Generated On - " + new Date().toString()));

			document.add(table);

			document.newPage(); // Opened new page

			document.close();

			file.close();
			status="Document Generated";
			System.out.println("Pdf created successfully..");

		} catch (Exception e) {
			status="Document not Generated";
		}

		return status;
	}

	}
