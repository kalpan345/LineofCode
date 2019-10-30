package com.example.demo;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.LineNumberReader;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;

public class LineofcodeApplication
{
public static void main(String[] args)
{
	try{
		int min=Integer.parseInt(args[1]);  
		Stream<Path> walk = Files.walk(Paths.get(args[0]));
		List<String> result=walk.map(x -> x.toString()).filter(f -> f.endsWith(".java")).collect(Collectors.toList());
		String[] x = result.toArray(new String[0]);
		int size = result.size();
		int[] linecount=new int[size];
		List<fileData> list = new ArrayList<fileData>();
		for(int i=0;i<size;i++)
		{
			int counter = 0;			
			File file =new File(x[i]);
			FileReader fr = new FileReader(file);
			LineNumberReader lnr = new LineNumberReader(fr);
			while (lnr.readLine() != null)
			{
				counter++;
			}
			linecount[i]=counter;
			lnr.close();
			walk.close();
			list.add(new fileData(linecount[i],x[i]));
		}
		Collections.sort(list, Collections.reverseOrder());
		for (fileData p : list)
		{
			if(p.getLineofcode()>min) {
				b(p.getLineofcode(),p.getLoc());
		}}
		System.out.println("Excel sheet has been updated.");
	}
	catch(IOException e){
		e.printStackTrace();} 
	catch (Exception e) {
		e.printStackTrace();}
}
public static void b(int i,String string) throws Exception{	
	final String FILE_NAME = "D:/file write/a.xlsx";
	InputStream inp = new FileInputStream(FILE_NAME); 
	Workbook wb = WorkbookFactory.create(inp); 
	Sheet sheet = wb.getSheetAt(0); 
	Row row1 = sheet.createRow(0);
	Cell cell2 = row1.createCell(0);
	cell2.setCellValue("Lines of code:");
	Cell cell3 = row1.createCell(1);
	cell3.setCellValue("Location: ");
	int num = sheet.getLastRowNum();
	Row row = sheet.createRow(++num);
	Cell cell = row.createCell(0);
	cell.setCellValue(i);
	Cell cell1 = row.createCell(1);
	cell1.setCellValue(string);
	FileOutputStream fileOut = new FileOutputStream(FILE_NAME); 
	wb.write(fileOut); 
	fileOut.close();
}}