package ru.namibios.arduino.utils;

import java.awt.Desktop;
import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;

public class ExecUtils {

	private ExecUtils() {}
	
	public static void openUri (String link) {
		
        try {
            URI uri = new URI(link);
            Desktop.getDesktop().browse(uri);
         
        } catch (IOException ex) {
            ex.printStackTrace();
        }  catch (URISyntaxException ex) {
            ex.printStackTrace();
        }
        
	}
	
}