package ru.namibios.arduino.utils;

import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.InputStreamReader;
import java.security.InvalidParameterException;

import ru.namibios.arduino.config.Path;

public class PythonExec {

	private PythonExec() {}
	
	private static String[] getParams(String filename, String os) {
		String[] cmd = new String[3];
		
		File file = null;
		if (os.indexOf("win") >= 0) {
			file = new File(Path.SCRIPT_PATH_WIN);
			String absolute = file.getAbsolutePath();
			
			cmd[0] = absolute;
			cmd[1] = absolute.substring(0, absolute.lastIndexOf("\\"));
			cmd[2] = filename;
		} else if ((os.indexOf("nix") >= 0 || os.indexOf("nux") >= 0 || os.indexOf("aix") > 0 )) {
			file = new File(Path.SCRIPT_PATH_LINUX);
			String absolute = file.getAbsolutePath();
			
			cmd[0] = absolute;
			cmd[1] = absolute.substring(0, absolute.lastIndexOf("/"));
			cmd[2] = filename;
		} else {
			throw new InvalidParameterException("OS is not defined: " + os);
		}
		
		return cmd;
	}
	
	public static String exec(String fileKapcha) throws IOException {
		
		String[] cmd = new String[3];
		
		String os = System.getProperty("os.name").toLowerCase();
		cmd = getParams(fileKapcha, os);
		
		Runtime rt = Runtime.getRuntime();
		Process pr = rt.exec(cmd);

		BufferedReader bfr = new BufferedReader(new InputStreamReader(pr.getInputStream()));
		String response = "";
		while ((response = bfr.readLine()) != null) {
			if(response.length() == 10) return response;
		}
		
		return null;
	}
	
	public static void main(String[] args) throws Exception {
		System.out.println(exec("D:/work/test/blackdesert-fishbot_0.1.9/resources/model/13.jpg"));
		System.out.println(exec("/home/symbios/git/blackdesert-fishbot/resources/model/13.jpg"));
		
	}
	
}