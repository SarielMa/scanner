package com.sql;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class FileTools {
	public List<String> getData(String fileName) throws IOException{//�������ֵ���ļ��ж�ȡ���ݴ洢�������У����ظ�����
		List<String> list = new ArrayList<String>();
		FileReader fr = new FileReader(fileName);
		BufferedReader  br = new BufferedReader(fr);
		String line = br.readLine();
		while(line!=null){
			list.add(line);
			line = br.readLine();
		}
		br.close();
		fr.close();
		return list;
	}
	
}
