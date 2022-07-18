package ru.namibios.bdofishbot.bot;

import ru.namibios.bdofishbot.bot.template.Loot;
import ru.namibios.bdofishbot.bot.template.LootFrame;
import ru.namibios.bdofishbot.bot.template.MatrixTemplate;
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

    public List<StatSeries> getSeries() {
        return series;
    }

    public void setSeries(List<StatSeries> series) {
        this.series = series;
    }

    public void initWaitFishStart() {
        current.setWaitFishStart(DateUtils.now());
    }

    public void initWaitFishEnd() {
        current.setWaitFishEnd(DateUtils.now());
    }

    public void initCutFishStart() {
        current.setCutFishStart(DateUtils.now());
    }

    public void initCutFishEnd() {
        current.setCutFishEnd(DateUtils.now());
    }

    public void initStatusCutStart() {
        current.setStatusCutStart(DateUtils.now());
    }

    public void setStatusCut(String statusCut) {
        current.setStatusCut(statusCut);
    }

    public void initStatusCutEndAndStatus(String name) {
        current.setStatusCut(name);
        current.setStatusCutEnd(DateUtils.now());
    }

    public void initCaptchaStart() {
        current.setCaptchaStart(DateUtils.now());
    }

    public void setRecognizedCaptcha(boolean isRecognized) {
        current.setRecognizedCaptcha(isRecognized);
    }

    public void initCaptchaEnd() {
        current.setCaptchaEnd(DateUtils.now());
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

    public void incLoot(MatrixTemplate loot) {
        if (loot == null) {
            current.incUnknown();
        } else if (loot == Loot.USEFULL) {
            current.incUsefull();
        } else if (loot == Loot.CONFIRM) {
            current.incConfirm();
        } else if (loot == Loot.EXCEPTION) {
            current.incTrash();
        } else if (loot == Loot.EMPTY) {
            current.incEmpty();
        }
    }

    public void incFrame(MatrixTemplate lootFrame) {
        if (lootFrame == null) {
            current.unknownFrame();
        } else if (lootFrame == LootFrame.RED) {
            current.incRedFrame();
        } else if (lootFrame == LootFrame.GOLD) {
            current.incGoldFrame();
        } else if (lootFrame == LootFrame.BLUE) {
            current.incBlueFrame();
        } else if (lootFrame == LootFrame.GREEN) {
            current.incGreenFrame();
        } else if (lootFrame == LootFrame.GRAY) {
            current.incGrayFrame();
        }
    }

}