package ru.namibios.bdofishbot.config.converter;

import org.aeonbits.owner.Converter;
import ru.namibios.bdofishbot.bot.Touch;

import java.lang.reflect.Method;

public class TouchConverter implements Converter<Touch>{

	@Override
	public Touch convert(Method method, String input) {

        boolean isActive = !input.startsWith("!");

        Touch touch = new Touch(Integer.valueOf(input.substring(input.indexOf("{") + 1, input.indexOf(","))),
                Integer.valueOf(input.substring(input.indexOf(",") + 1, input.indexOf("}"))));

        touch.setActive(isActive);

        return touch;
	}

}
