package ru.namibios.bdofishbot.config;

import org.apache.log4j.AppenderSkeleton;
import org.apache.log4j.Layout;
import org.apache.log4j.Level;
import org.apache.log4j.spi.LoggingEvent;

import javax.swing.*;

public class TextAreaAppender extends AppenderSkeleton {
	
	private final JTextArea jTextA;

	public TextAreaAppender(JTextArea jTextArea) {
		this.jTextA = jTextArea;
	}

	@Override
	protected void append(LoggingEvent event) {
		 
		Layout layout = getLayout();
		 if(layout == null)
			 return;
		 
		if (event.getLevel().equals(Level.INFO) || event.getLevel().equals(Level.ERROR)) {
			jTextA.append(layout.format(event));
			jTextA.setCaretPosition(jTextA.getDocument().getLength());
		}
	}

	public void close() {
		//ignore
	}

	public boolean requiresLayout() {
		return false;
	}

}
