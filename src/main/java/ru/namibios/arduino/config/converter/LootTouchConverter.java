package ru.namibios.arduino.config.converter;

import org.aeonbits.owner.Converter;
import ru.namibios.arduino.model.Touch;

import java.lang.reflect.Method;

public class LootTouchConverter implements Converter<Touch>{

	@Override
	public Touch convert(Method method, String input) {
		
		return new Touch(Integer.valueOf(input.substring(input.indexOf("{") + 1, input.indexOf(","))),
				Integer.valueOf(input.substring(input.indexOf(",") + 1, input.indexOf("}"))));
	}

}
