package ru.namibios.bdofishbot.bot;

public class StatSeries {

    private long waitFishStart;
    private long waitFishEnd;

    private long cutFishStart;
    private long cutFishEnd;

    private long statusCutStart;
    private long statusCutEnd;
    private String statusCut;

    private long captchaStart;
    private long captchaEnd;
    private boolean recognizedCaptcha;

    private long statusCaptchaStart;
    private long statusCaptchaEnd;
    private boolean statusCaptcha;

    private long filterLootStart;
    private long filterLootEnd;

    private long unknown;
    private long ok;
    private long trash;
    private long empty;

    private long red;
    private long gold;
    private long blue;
    private long green;
    private long gray;

    public long getFilterLootStart() {
        return filterLootStart;
    }

    public void setFilterLootStart(long filterLootStart) {
        this.filterLootStart = filterLootStart;
    }

    public long getFilterLootEnd() {
        return filterLootEnd;
    }

    public void setFilterLootEnd(long filterLootEnd) {
        this.filterLootEnd = filterLootEnd;
    }

    public long getWaitFishStart() {
        return waitFishStart;
    }

    public void setWaitFishStart(long waitFishStart) {
        this.waitFishStart = waitFishStart;
    }

    public long getWaitFishEnd() {
        return waitFishEnd;
    }

    public void setWaitFishEnd(long waitFishEnd) {
        this.waitFishEnd = waitFishEnd;
    }

    public long getCutFishStart() {
        return cutFishStart;
    }

    public void setCutFishStart(long cutFishStart) {
        this.cutFishStart = cutFishStart;
    }

    public long getCutFishEnd() {
        return cutFishEnd;
    }

    public void setCutFishEnd(long cutFishEnd) {
        this.cutFishEnd = cutFishEnd;
    }

    public long getStatusCutStart() {
        return statusCutStart;
    }

    public void setStatusCutStart(long statusCutStart) {
        this.statusCutStart = statusCutStart;
    }

    public long getStatusCutEnd() {
        return statusCutEnd;
    }

    public void setStatusCutEnd(long statusCutEnd) {
        this.statusCutEnd = statusCutEnd;
    }

    public String getStatusCut() {
        return statusCut;
    }

    public void setStatusCut(String statusCut) {
        this.statusCut = statusCut;
    }

    public long getCaptchaStart() {
        return captchaStart;
    }

    public void setCaptchaStart(long captchaStart) {
        this.captchaStart = captchaStart;
    }

    public long getCaptchaEnd() {
        return captchaEnd;
    }

    public void setCaptchaEnd(long captchaEnd) {
        this.captchaEnd = captchaEnd;
    }

    public boolean isRecognizedCaptcha() {
        return recognizedCaptcha;
    }

    public void setRecognizedCaptcha(boolean recognizedCaptcha) {
        this.recognizedCaptcha = recognizedCaptcha;
    }

    public long getStatusCaptchaStart() {
        return statusCaptchaStart;
    }

    public void setStatusCaptchaStart(long statusCaptchaStart) {
        this.statusCaptchaStart = statusCaptchaStart;
    }

    public long getStatusCaptchaEnd() {
        return statusCaptchaEnd;
    }

    public void setStatusCaptchaEnd(long statusCaptchaEnd) {
        this.statusCaptchaEnd = statusCaptchaEnd;
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

    public long getOk() {
        return ok;
    }

    public void setOk(long ok) {
        this.ok = ok;
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
}
