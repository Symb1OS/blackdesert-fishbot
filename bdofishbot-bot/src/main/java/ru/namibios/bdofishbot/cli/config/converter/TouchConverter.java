package ru.namibios.bdofishbot.cli.config.converter;

import org.aeonbits.owner.Converter;
import ru.namibios.bdofishbot.bot.Touch;

import java.lang.reflect.Method;

public class TouchConverter implements Converter<Touch>{

	@Override
	public Touch convert(Method method, String input) {

        boolean isActive = !input.startsWith("!");

        Touch touch = new Touch(Integer.parseInt(input.substring(input.indexOf("{") + 1, input.indexOf(","))),
                Integer.parseInt(input.substring(input.indexOf(",") + 1, input.indexOf("}"))));

        touch.setActive(isActive);

        return touch;
	}

}
