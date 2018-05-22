package ru.namibios.arduino.utils;

import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.InputStreamReader;

import ru.namibios.arduino.config.Path;

public class PythonExec {

	private PythonExec() {}
	
	public static String exec(String fileKapcha) throws IOException {
		
		String[] cmd = new String[3];
		File file = new File(Path.SCRIPT_PATH);
		
		String absolute = file.getAbsolutePath();
		cmd[0] = absolute;
		cmd[1] = absolute.substring(0, absolute.lastIndexOf("/"));
		cmd[2] = fileKapcha;
		
		Runtime rt = Runtime.getRuntime();
		Process pr = rt.exec(cmd);

		BufferedReader bfr = new BufferedReader(new InputStreamReader(pr.getInputStream()));
		String response = "";
		while ((response = bfr.readLine()) != null) {
			if(response.length() == 10) return response;
		}
		
		return null;
	}
	
	public static void main(String[] args) throws IOException {
		
		System.out.println(exec("/home/symbios/git/blackdesert-fishbot/resources/model/13.jpg"));
	}
	
}