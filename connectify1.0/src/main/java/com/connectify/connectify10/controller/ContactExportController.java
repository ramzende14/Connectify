package com.connectify.connectify10.controller;

import jakarta.servlet.http.HttpServletResponse;
import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;

import com.connectify.connectify10.Service.ContactService;
import com.connectify.connectify10.entity.Contact;

import org.springframework.stereotype.Controller;

import java.io.IOException;
import java.util.List;

@Controller
public class ContactExportController {

    @Autowired
    private ContactService contactService;

    @GetMapping("/user/contact/export/excel")
    public void exportToExcel(HttpServletResponse response) throws IOException {
        response.setContentType("application/octet-stream");
        String headerKey = "Content-Disposition";
        String headerValue = "attachment; filename=contacts.xlsx";
        response.setHeader(headerKey, headerValue);

        List<Contact> listContacts = contactService.getAllContacts();

        XSSFWorkbook workbook = new XSSFWorkbook();
        Sheet sheet = workbook.createSheet("Contacts");

        // Header
        Row headerRow = sheet.createRow(0);
        CellStyle headerStyle = workbook.createCellStyle();
        Font font = workbook.createFont();
        font.setBold(true);
        headerStyle.setFont(font);

        String[] headers = {"ID", "Name", "Email", "Phone", "Favorite", "LinkedIn", "Website"};
        for (int i = 0; i < headers.length; i++) {
            Cell cell = headerRow.createCell(i);
            cell.setCellValue(headers[i]);
            cell.setCellStyle(headerStyle);
        }

        // Data rows
        int rowCount = 1;
        for (Contact c : listContacts) {
            Row row = sheet.createRow(rowCount++);
            row.createCell(0).setCellValue(c.getId());
            row.createCell(1).setCellValue(c.getName());
            row.createCell(2).setCellValue(c.getEmail());
            row.createCell(3).setCellValue(c.getPhoneNumber());
            row.createCell(4).setCellValue(c.isFavorite() ? "Yes" : "No");
            row.createCell(5).setCellValue(c.getLinkdinLink());
            row.createCell(6).setCellValue(c.getWebsiteLink());
        }

        workbook.write(response.getOutputStream());
        workbook.close();
    }
}
