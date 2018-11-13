package ru.namibios.arduino.config;

import java.io.File;

public class Path {
	public static final String RESOURCES = "resources/";
	public static final String SCRIPT_PATH_WIN = RESOURCES + "model/parsing.bat";
	public static final String SCRIPT_PATH_LINUX = RESOURCES + "model/parsing.sh";
	
	public static final String ROOT_ICON = RESOURCES + "icon.png";
	
	public static final String DEBUG = "debug/";
	public static final String DEBUG_LINE = "debug/line/";
	
	public static final String TEMPLATES = RESOURCES + "templates/";
	public static final String STATUS_CUT = TEMPLATES + "statuscut/";
	public static final String STATUS_KAPCHA = TEMPLATES + "statuskapcha/";

	public static final String TEST_RESOURCES = "src/test/resources/";

	public static final String STORE_CAPTCHA = System.getProperty("user.home") + "/bdo/captcha/store";
	public static final String STORE_CAPTCHA_DIRTY = System.getProperty("user.home") + "/bdo/captcha/store/dirty/";
	public static final String STORE_CAPTCHA_CLEAN = System.getProperty("user.home") + "/bdo/captcha/store/clean/";

	static {

		File storeCaptcha = new File(STORE_CAPTCHA);
		File storeCaptchaDirty = new File(STORE_CAPTCHA_DIRTY);
		File storeCaptchaClean = new File(STORE_CAPTCHA_CLEAN);

		if (!storeCaptcha.exists()) {
			storeCaptcha.mkdir();
			storeCaptchaDirty.mkdir();
			storeCaptchaClean.mkdir();
		}
	}

}