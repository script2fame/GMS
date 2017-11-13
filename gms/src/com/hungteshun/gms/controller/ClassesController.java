package com.hungteshun.gms.controller;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

import com.hungteshun.gms.manager.ClassesManager;

/**
 * �༶������
 * @author Administrator
 *
 */
public class ClassesController {

	private static final String ADD = "1";
	
	private static final String DEL = "2";
	
	private static final String MODIFY = "3";
	
	private static final String QUERY = "4";
	
	private static final String QUIT = "q";
	
	private static String state = "";
	
	public static void main(String[] args) {
		System.out.println("1-���Ӱ༶");
		System.out.println("2-ɾ���༶");
		System.out.println("3-�޸İ༶");
		System.out.println("4-��ѯ�༶");
		System.out.println("q-�˳�");
		BufferedReader br = null;
		try {
			br = new BufferedReader(new InputStreamReader(System.in));
			String s = null;
			while ((s = br.readLine()) != null) {
				if (ADD.equals(s)) {
					state = ADD;
				}else if (DEL.equals(s)) {
					state = DEL;
				}else if (MODIFY.equals(s)) {
					state = MODIFY;
				}else if (QUERY.equals(s)) {
					System.out.println("����س���ѯ���еİ༶");
					state = QUERY;
				}else if (QUIT.equalsIgnoreCase(s)){
					break;
				}else if (ADD.equals(state)) {
				}else if (DEL.equals(state)) {
				}else if (MODIFY.equals(state)) {
				}else if (QUERY.equals(state)) {
					ClassesManager.getInstance().outClassesList();
				}
			}
			System.err.println("�����˳�");
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			if (br != null) {
				try {
					br.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}		
	}

}