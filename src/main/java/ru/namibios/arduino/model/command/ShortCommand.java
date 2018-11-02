package ru.namibios.arduino.model.command;

public class ShortCommand {

    public static final Command SPACE = () -> "space";
    public static final Command IGNORE = () -> "";
    public static final Command TAKE = () -> "r";

    public static final Command SKIP_CALENDAR = () -> "Skip_calendar";
    public static final Command EXIT = () -> "Exit";

    public static final Command BEER = () -> "Beer";
    public static final Command MINIGAME = () -> "Minigame";

}
