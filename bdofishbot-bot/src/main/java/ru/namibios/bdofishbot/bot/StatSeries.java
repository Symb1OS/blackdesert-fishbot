package ru.namibios.bdofishbot.bot;

import java.sql.Timestamp;

public class StatSeries {

    private Timestamp changeRodStart;

    private Timestamp waitFishStart;
    private Timestamp waitFishEnd;

    private Timestamp cutFishStart;
    private Timestamp cutFishEnd;

    private Timestamp statusCutStart;
    private Timestamp statusCutEnd;
    private String statusCut;

    private Timestamp captchaStart;
    private Timestamp captchaEnd;
    private boolean recognizedCaptcha;

    private Timestamp statusCaptchaStart;
    private Timestamp statusCaptchaEnd;
    private boolean statusCaptcha;

    private Timestamp filterLootStart;
    private Timestamp filterLootEnd;

    private long unknown;
    private long usefull;
    private long confirm;
    private long trash;
    private long empty;

    private long red;
    private long gold;
    private long blue;
    private long green;
    private long gray;
    private long unknownFrame;

    public Timestamp getChangeRodStart() {
        return changeRodStart;
    }

    public void setChangeRodStart(Timestamp changeRodStart) {
        this.changeRodStart = changeRodStart;
    }

    public Timestamp getWaitFishStart() {
        return waitFishStart;
    }

    public void setWaitFishStart(Timestamp waitFishStart) {
        this.waitFishStart = waitFishStart;
    }

    public Timestamp getWaitFishEnd() {
        return waitFishEnd;
    }

    public void setWaitFishEnd(Timestamp waitFishEnd) {
        this.waitFishEnd = waitFishEnd;
    }

    public Timestamp getCutFishStart() {
        return cutFishStart;
    }

    public void setCutFishStart(Timestamp cutFishStart) {
        this.cutFishStart = cutFishStart;
    }

    public Timestamp getCutFishEnd() {
        return cutFishEnd;
    }

    public void setCutFishEnd(Timestamp cutFishEnd) {
        this.cutFishEnd = cutFishEnd;
    }

    public Timestamp getStatusCutStart() {
        return statusCutStart;
    }

    public void setStatusCutStart(Timestamp statusCutStart) {
        this.statusCutStart = statusCutStart;
    }

    public Timestamp getStatusCutEnd() {
        return statusCutEnd;
    }

    public void setStatusCutEnd(Timestamp statusCutEnd) {
        this.statusCutEnd = statusCutEnd;
    }

    public String getStatusCut() {
        return statusCut;
    }

    public void setStatusCut(String statusCut) {
        this.statusCut = statusCut;
    }

    public Timestamp getCaptchaStart() {
        return captchaStart;
    }

    public void setCaptchaStart(Timestamp captchaStart) {
        this.captchaStart = captchaStart;
    }

    public Timestamp getCaptchaEnd() {
        return captchaEnd;
    }

    public void setCaptchaEnd(Timestamp captchaEnd) {
        this.captchaEnd = captchaEnd;
    }

    public boolean isRecognizedCaptcha() {
        return recognizedCaptcha;
    }

    public void setRecognizedCaptcha(boolean recognizedCaptcha) {
        this.recognizedCaptcha = recognizedCaptcha;
    }

    public Timestamp getStatusCaptchaStart() {
        return statusCaptchaStart;
    }

    public void setStatusCaptchaStart(Timestamp statusCaptchaStart) {
        this.statusCaptchaStart = statusCaptchaStart;
    }

    public Timestamp getStatusCaptchaEnd() {
        return statusCaptchaEnd;
    }

    public void setStatusCaptchaEnd(Timestamp statusCaptchaEnd) {
        this.statusCaptchaEnd = statusCaptchaEnd;
    }

    public Timestamp getFilterLootStart() {
        return filterLootStart;
    }

    public void setFilterLootStart(Timestamp filterLootStart) {
        this.filterLootStart = filterLootStart;
    }

    public Timestamp getFilterLootEnd() {
        return filterLootEnd;
    }

    public void setFilterLootEnd(Timestamp filterLootEnd) {
        this.filterLootEnd = filterLootEnd;
    }

    public boolean isStatusCaptcha() {
        return statusCaptcha;
    }

    public void setStatusCaptcha(boolean statusCaptcha) {
        this.statusCaptcha = statusCaptcha;
    }

    public long getUnknown() {
        return unknown;
    }

    public void setUnknown(long unknown) {
        this.unknown = unknown;
    }

    public long getTrash() {
        return trash;
    }

    public void setTrash(long trash) {
        this.trash = trash;
    }

    public long getEmpty() {
        return empty;
    }

    public void setEmpty(long empty) {
        this.empty = empty;
    }

    public long getRed() {
        return red;
    }

    public void setRed(long red) {
        this.red = red;
    }

    public long getGold() {
        return gold;
    }

    public void setGold(long gold) {
        this.gold = gold;
    }

    public long getBlue() {
        return blue;
    }

    public void setBlue(long blue) {
        this.blue = blue;
    }

    public long getGreen() {
        return green;
    }

    public void setGreen(long green) {
        this.green = green;
    }

    public long getGray() {
        return gray;
    }

    public void setGray(long gray) {
        this.gray = gray;
    }

    public void incRedFrame() {
        red++;
    }

    public void incGoldFrame() {
        gold++;
    }

    public void incBlueFrame() {
        blue++;
    }

    public void incGreenFrame() {
        green++;
    }

    public long getConfirm() {
        return confirm;
    }

    public void setConfirm(long confirm) {
        this.confirm = confirm;
    }

    public void incGrayFrame() {
        gray++;
    }

    public void incUnknown() {
        unknown++;
    }

    public void incUsefull() {
        usefull++;
    }

    public void incConfirm() {
        confirm++;
    }

    public long getUsefull() {
        return usefull;
    }

    public void setUsefull(long usefull) {
        this.usefull = usefull;
    }

    public void incEmpty() {
        empty++;
    }

    public void incTrash() {
        trash++;
    }

    public void unknownFrame() {
        unknownFrame++;
    }

    public long getUnknownFrame() {
        return unknownFrame;
    }

    public void setUnknownFrame(long unknownFrame) {
        this.unknownFrame = unknownFrame;
    }
}