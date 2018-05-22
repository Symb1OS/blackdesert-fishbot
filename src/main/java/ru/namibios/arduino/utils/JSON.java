package ru.namibios.arduino.utils;

import org.codehaus.jackson.map.ObjectMapper;

public final class JSON {

	private static final ObjectMapper MAPPER = new ObjectMapper();
	
	private JSON(){}
	
	public static ObjectMapper getInstance() {
		return MAPPER;
	}

}
