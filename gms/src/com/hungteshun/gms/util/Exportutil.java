package com.hungteshun.gms.util;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Iterator;
import java.util.List;

import javax.swing.JOptionPane;

import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFCellStyle;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;

import com.hungteshun.gms.manager.StudentManager;
import com.hungteshun.gms.manager.StudentManagerImpl;
import com.hungteshun.gms.model.Student;

/**
 * 导出文件
 * 
 * @author 黄调聪
 *
 */
public class Exportutil {

	/**
	 * 导出成excel
	 * 
	 * @param studentList
	 */
	
	private static StudentManager studentManager = new StudentManagerImpl();
	
	public static void ExportToExcel() {
		// 声明一个工作簿
		HSSFWorkbook wb = new HSSFWorkbook();
		// 声明一个sheet表
		HSSFSheet sheet = wb.createSheet();
		sheet.setDefaultColumnWidth((int) 15);
		// 生成一个样式
		HSSFCellStyle style = wb.createCellStyle();
		style.setAlignment(HSSFCellStyle.ALIGN_CENTER);
		// 创建表头
		HSSFRow row = null;
		row = sheet.createRow(0);
		HSSFCell cell = null;
		cell = row.createCell(0);
		cell.setCellValue("学生编号");
		cell.setCellStyle(style);

		cell = row.createCell(1);
		cell.setCellValue("学生姓名");
		cell.setCellStyle(style);

		cell = row.createCell(2);
		cell.setCellValue("性别");
		cell.setCellStyle(style);

		cell = row.createCell(3);
		cell.setCellValue("年龄");
		cell.setCellStyle(style);

		cell = row.createCell(4);
		cell.setCellValue("出生日期");
		cell.setCellStyle(style);

		cell = row.createCell(5);
		cell.setCellValue("联系电话");
		cell.setCellStyle(style);

		cell = row.createCell(6);
		cell.setCellValue("家庭地址");
		cell.setCellStyle(style);

		cell = row.createCell(7);
		cell.setCellValue("班级名称");
		cell.setCellStyle(style);
		List<Student> studentList = studentManager.findStudentList();
		for (int i = 0; i < studentList.size(); i++) {
			row = sheet.createRow(i + 1);
			row.createCell(0).setCellValue(studentList.get(i).getStudentId());
			row.createCell(1).setCellValue(studentList.get(i).getStudentName());
			row.createCell(2).setCellValue(studentList.get(i).getSex());
			row.createCell(3)
					.setCellValue((int) ((System.currentTimeMillis() - studentList.get(i).getBirthday().getTime())/1000/60/60/24/365));
			row.createCell(4).setCellValue(DateUtil.formatDate(studentList.get(i).getBirthday()));
			row.createCell(5).setCellValue(studentList.get(i).getContactTel());
			row.createCell(6).setCellValue(studentList.get(i).getAddress());
			row.createCell(7).setCellValue(studentList.get(i).getClasses().getClassesName());
		}
		File filePath = new File("D:\\student");
		if (!filePath.exists()) {
			filePath.mkdirs();
		}
		String fileName = new SimpleDateFormat("yyyy年MM月dd日HH时mm分ss秒").format(new Date()) + ".xls";
		FileOutputStream outputStream = null;
		try {
			outputStream = new FileOutputStream(new File(filePath, fileName));
			wb.write(outputStream);
			JOptionPane.showMessageDialog(null, "导出成功！");
		} catch (IOException e) {
			JOptionPane.showMessageDialog(null, "导出失败！");
			e.printStackTrace();
		} finally {
			if (outputStream != null) {
				try {
					outputStream.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}
	}
	
	public static void export() {
		List<Student> studentList = studentManager.findStudentList();
		StringBuffer sb = new StringBuffer();
		for (Iterator<Student> iterator = studentList.iterator(); iterator.hasNext();) {
			Student student = (Student) iterator.next();
			int age = (int)((System.currentTimeMillis() - student.getBirthday().getTime()) / 1000 / 60 / 60 / 24 / 365);
			sb.append("学生编号:").append(student.getStudentId())
				.append(",学生姓名:").append(student.getStudentName())
					.append(",性别:").append(student.getSex())
					.append(",出生日期:").append(DateUtil.formatDate(student.getBirthday()))
					.append(",联系电话:").append(student.getContactTel())
					.append(",家庭住址:").append(student.getAddress())
					.append(",年龄:").append(age)
					.append(",班级名称 ").append(student.getClasses().getClassesName())
					.append("\n");
		}

		File filePath = new File("D:\\student");
		if (!filePath.exists()) {
			filePath.mkdirs();
		}
		String fileName = new SimpleDateFormat("yyyy年MM月dd日HH时mm分ss秒").format(new Date()) + ".txt";
		BufferedWriter bw = null;
		try {
			bw = new BufferedWriter(new FileWriter(new File(filePath, fileName)));
			bw.write(sb.toString());
			System.out.println("导出学生信息成功!文件位置："+ filePath.getAbsolutePath()+"\\"+ fileName);
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			if (bw != null) {
				try {
					bw.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}
	}
}
