package ru.namibios.arduino.config;

import java.io.File;

public class Path {

	public static final String RESOURCES = "resources/";
	public static final String SCRIPT_PATH_WIN = RESOURCES + "model/parsing.bat";
	public static final String SCRIPT_PATH_LINUX = RESOURCES + "model/parsing.sh";
	
	public static final String ROOT_ICON = RESOURCES + "icon.png";
	
	public static final String DEBUG = "resources/debug/";

	public static final String DEBUG_LINE = "debug/line/";

    public static final String DEBUG_WAITFISH = "debug/waitfish";
    public static final String DEBUG_SUBLINE = "debug/subline";
    public static final String DEBUG_CAPTCHA = "debug/captcha";
    public static final String DEBUG_STATUSCAPTCHA = "debug/statuscaptcha";
    public static final String DEBUG_STATUSCUT = "debug/statuscut";
    public static final String DEBUG_FILTERLOOT = "debug/filterloot";
	public static final String DEBUG_PM_MESSAGE = "debug/pm";
	public static final String DEBUG_DEBUF = "debug/debuf";

    public static final String LOOT_UNSORT = "loot/unsort";
    public static final String LOOT_UNKNOWN = "loot/unknown";

	public static final String TEMPLATES = RESOURCES + "templates/";
	public static final String DEBUF = TEMPLATES + "debuff/";
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
			storeCaptcha.mkdirs();
			storeCaptchaDirty.mkdirs();
			storeCaptchaClean.mkdirs();
		}
	}
}