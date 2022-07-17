package ru.namibios.bdofishbot.bot;

import ru.namibios.bdofishbot.cli.Application;
import ru.namibios.bdofishbot.utils.DateUtils;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class Stats {

    public Stats() {
        this.startWork = new Date().getTime();
        this.hash = Application.getUser().getHash();
        this.series = new ArrayList<>();
    }

    private String hash;
    private long startWork;
    private long endWork;

    private StatSeries current;
    private List<StatSeries> series;

    private long startFish;

    private long cutFish;
    private long statusCutFish;
    private long cutBad;
    private long cutGood;
    private long cutPerfect;

    private long captcha;
    private long notRecognizedCaptcha;

    private long statusCaptcha;
    private long okCaptcha;
    private long failCaptcha;

    private long lootFish;
    private long trashLoot;
    private long okLoot;
    private long goldLoot;
    private long blueLoot;
    private long greenLoot;
    private long grayLoot;

    public void newSeries() {
        if (current != null) {
            series.add(current);
        }
        current = new StatSeries();
    }

    public String getHash() {
        return hash;
    }

    public void setHash(String hash) {
        this.hash = hash;
    }

    public void incStartFish(){
        startFish++;
    }

    public void incCutFish(){
        cutFish++;
    }

    public void incStatusCutFish(){
        statusCutFish++;
    }

    public void incCutBad(){
        cutBad++;
    }

    public void incCutGood(){
        cutGood++;
    }

    public void incCutPerfect() {
        cutPerfect++;
    }

    public void incCaptcha() {
        captcha++;
    }

    public void incNotRecognizedCaptcha() {
        notRecognizedCaptcha++;
    }

    public void incStatusCaptcha() {
        statusCaptcha++;
    }

    public void incOkCapthca() {
        okCaptcha++;
    }

    public void incFailCapthca() {
        failCaptcha++;
    }

    public void incLootFish() {
        lootFish++;
    }

    public void incTrashLoot(){
        trashLoot++;
    }

    public void incOkLoot(){
        okLoot++;
    }

    public void incGoldLoot(){
        goldLoot++;
    }

    public void incBlueLoot(){
        blueLoot++;
    }

    public void incGreenLoot(){
        greenLoot++;
    }

    public void incGrayLoot(){
        grayLoot++;
    }

    public void updateEndWork() {
        endWork = new Date().getTime();
    }

    public long getStartWork() {
        return startWork;
    }

    public void setStartWork(long startWork) {
        this.startWork = startWork;
    }

    public long getEndWork() {
        return endWork;
    }

    public void setEndWork(long endWork) {
        this.endWork = endWork;
    }

    public long getStartFish() {
        return startFish;
    }

    public void setStartFish(long startFish) {
        this.startFish = startFish;
    }

    public long getCutFish() {
        return cutFish;
    }

    public void setCutFish(long cutFish) {
        this.cutFish = cutFish;
    }

    public long getStatusCutFish() {
        return statusCutFish;
    }

    public void setStatusCutFish(long statusCutFish) {
        this.statusCutFish = statusCutFish;
    }

    public long getCutBad() {
        return cutBad;
    }

    public void setCutBad(long cutBad) {
        this.cutBad = cutBad;
    }

    public long getCutGood() {
        return cutGood;
    }

    public void setCutGood(long cutGood) {
        this.cutGood = cutGood;
    }

    public long getCutPerfect() {
        return cutPerfect;
    }

    public void setCutPerfect(long cutPerfect) {
        this.cutPerfect = cutPerfect;
    }

    public long getCaptcha() {
        return captcha;
    }

    public void setCaptcha(long captcha) {
        this.captcha = captcha;
    }

    public long getNotRecognizedCaptcha() {
        return notRecognizedCaptcha;
    }

    public void setNotRecognizedCaptcha(long notRecognizedCaptcha) {
        this.notRecognizedCaptcha = notRecognizedCaptcha;
    }

    public long getStatusCaptcha() {
        return statusCaptcha;
    }

    public void setStatusCaptcha(long statusCaptcha) {
        this.statusCaptcha = statusCaptcha;
    }

    public long getOkCaptcha() {
        return okCaptcha;
    }

    public void setOkCaptcha(long okCaptcha) {
        this.okCaptcha = okCaptcha;
    }

    public long getFailCaptcha() {
        return failCaptcha;
    }

    public void setFailCaptcha(long failCaptcha) {
        this.failCaptcha = failCaptcha;
    }

    public long getLootFish() {
        return lootFish;
    }

    public void setLootFish(long lootFish) {
        this.lootFish = lootFish;
    }

    public long getTrashLoot() {
        return trashLoot;
    }

    public void setTrashLoot(long trashLoot) {
        this.trashLoot = trashLoot;
    }

    public long getOkLoot() {
        return okLoot;
    }

    public void setOkLoot(long okLoot) {
        this.okLoot = okLoot;
    }

    public long getGoldLoot() {
        return goldLoot;
    }

    public void setGoldLoot(long goldLoot) {
        this.goldLoot = goldLoot;
    }

    public long getBlueLoot() {
        return blueLoot;
    }

    public void setBlueLoot(long blueLoot) {
        this.blueLoot = blueLoot;
    }

    public long getGreenLoot() {
        return greenLoot;
    }

    public void setGreenLoot(long greenLoot) {
        this.greenLoot = greenLoot;
    }

    public long getGrayLoot() {
        return grayLoot;
    }

    public void setGrayLoot(long grayLoot) {
        this.grayLoot = grayLoot;
    }

    public List<StatSeries> getSeries() {
        return series;
    }

    public void setSeries(List<StatSeries> series) {
        this.series = series;
    }

    @Override
    public String toString() {
        return "Stats{" +
                "startWork=" + startWork +
                ", endWork=" + endWork +
                ", startFish=" + startFish +
                ", cutFish=" + cutFish +
                ", statusCutFish=" + statusCutFish +
                ", cutBad=" + cutBad +
                ", cutGood=" + cutGood +
                ", cutPerfect=" + cutPerfect +
                ", captcha=" + captcha +
                ", notRecognizedCaptcha=" + notRecognizedCaptcha +
                ", statusCaptcha=" + statusCaptcha +
                ", okCaptcha=" + okCaptcha +
                ", failCaptcha=" + failCaptcha +
                ", lootFish=" + lootFish +
                ", trashLoot=" + trashLoot +
                ", okLoot=" + okLoot +
                ", goldLoot=" + goldLoot +
                ", blueLoot=" + blueLoot +
                ", greenLoot=" + greenLoot +
                ", grayLoot=" + grayLoot +
                '}';
    }

    public void initWaitFishStart() {
        current.startWaitFish();
    }

    public void initWaitFishEnd() {
        current.endWaitFish();
    }

    public void initCutFishStart() {
        current.startCutFish();
    }

    public void initCutFishEnd() {
        current.endCutFish();
    }

    public void initStatusCutFishStart() {
        current.startStatusCutFish();
    }

    public void setStatusCut(String statusCut) {
        current.setStatusCut(statusCut);
    }

    public void initStatusCutFishEnd() {
        current.endStatusCutFish();
    }

    public void initCaptchaStart() {
        current.startCaptcha();
    }

    public void setRecognizedCaptcha(boolean isRecognized) {
        current.setRecognizedCaptcha(isRecognized);
    }

    public void initCaptchaEnd() {
        current.endCaptcha();
    }

    public void initStatusCaptchaStart() {
        current.setStatusCaptchaStart(DateUtils.now());
    }

    public void initStatusCaptchaEnd() {
        current.setStatusCaptchaEnd(DateUtils.now());
    }

    public void setStatusCaptcha(boolean isParsed) {
        current.setStatusCaptcha(isParsed);
    }

    public void initFilterLootStart() {
        current.setFilterLootStart(DateUtils.now());
    }

    public void initFilterLootEnd() {
        current.setFilterLootEnd(DateUtils.now());
    }
}