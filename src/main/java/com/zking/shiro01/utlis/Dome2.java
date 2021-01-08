package com.zking.shiro01.utlis;

import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;

import java.io.FileOutputStream;
import java.io.IOException;

public class Dome2 {
    public static void main(String[] args) {
        //表格导出
        HSSFWorkbook hssfWorkbook = new HSSFWorkbook();

        HSSFSheet sheet = hssfWorkbook.createSheet("sheet");

        HSSFRow row = sheet.createRow(0);

        HSSFCell cell = row.createCell(0);
        cell.setCellValue("这就是个文本");

        HSSFCell cell2 = row.createCell(1);
        cell2.setCellValue("文本2");

        try {
            FileOutputStream out = new FileOutputStream("C:\\Users\\zjjt\\Desktop\\test2.xls");
            hssfWorkbook.write(out);
            out.flush();
            System.out.println("导出成功");

        } catch (IOException e) {
            e.printStackTrace();
        }

    }
}
