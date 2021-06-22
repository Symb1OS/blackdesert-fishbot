package ru.namibios.bdofishbot.cli.config;

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
	public static final String DEBUG_CHALLENGE = "debug/challenge";

    public static final String LOOT_UNSORT = "loot/unsort";
    public static final String LOOT_UNKNOWN = "loot/unknown";
    public static final String LOOT_FRAMES = Path.RESOURCES + "loot/frame/";

	public static final String TEMPLATES = RESOURCES + "templates/";
	public static final String DEBUF = TEMPLATES + "debuff/";
	public static final String CLOSE = TEMPLATES + "close/";
	public static final String CHALLENGE = TEMPLATES + "challenge/";
	public static final String SPACE = TEMPLATES + "space/";
	public static final String STATUS_CUT = TEMPLATES + "statuscut/";
	public static final String STATUS_KAPCHA = TEMPLATES + "statuskapcha/";

	public static final String TEST_RESOURCES = "src/test/resources/";

	public static final String BOT_HOME = System.getProperty("user.home") + "/blackdesert-fishbot/";

}