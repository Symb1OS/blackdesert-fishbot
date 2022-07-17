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

}
