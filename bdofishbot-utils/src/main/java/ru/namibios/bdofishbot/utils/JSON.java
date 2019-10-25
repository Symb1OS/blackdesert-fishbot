package ru.namibios.bdofishbot.utils;

import com.fasterxml.jackson.databind.ObjectMapper;

public final class JSON {

	private static final ObjectMapper MAPPER = new ObjectMapper();
	
	private JSON(){}
	
	public static ObjectMapper getInstance() {
		return MAPPER;
	}

}
