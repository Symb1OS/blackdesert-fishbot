package ru.namibios.bdofishbot.bot.service;

import org.apache.log4j.Logger;
import ru.namibios.bdofishbot.bot.Stats;
import ru.namibios.bdofishbot.cli.config.Path;
import ru.namibios.bdofishbot.utils.ExceptionUtils;
import ru.namibios.bdofishbot.utils.JSON;

import java.io.File;
import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class StatsService {

    private static final Logger LOG = Logger.getLogger(StatsService.class);

    private Stats stats;

    public StatsService() {
        this.stats = new Stats();
    }

    public void export() {

        String exportDate = LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd-hh-mm-ss"));
        try {
            String filename = Path.RESOURCES + exportDate + ".json";
            LOG.info("export stats to " + filename);
            JSON.getInstance().writeValue(new File(filename), stats);
        } catch (IOException e) {
            LOG.error(ExceptionUtils.getString(e));
        }

    }

    public void initSeries(){
        LOG.debug("init new stat series");
        stats.newSeries();
    }

    public void failCaptcha() {
        stats.setStatusCaptcha(false);
    }

    public void okCaptcha() {
        stats.setStatusCaptcha(true);

    }

    public void recognizedCaptcha(boolean isRecognized) {
        stats.setRecognizedCaptcha(isRecognized);
    }

    public void perfectCut() {
        stats.setStatusCut("PERFECT");

    }

    public void goodCut() {
        stats.setStatusCut("GOOD");
    }

    public void badCut() {
        stats.setStatusCut("BAD");
    }

    public void endWork() {
        stats.updateEndWork();
    }

    public void initWaitFishStart() {
        stats.initWaitFishStart();
    }

    public void initWaitFishEnd() {
        stats.initWaitFishEnd();
    }

    public void initCutFishStart() {
        stats.initCutFishStart();
    }

    public void initCutFishEnd() {
        stats.initCutFishEnd();
    }

    public void initStatusCutFishStart() {
        stats.initStatusCutFishStart();
    }

    public void initStatusCutFishEnd() {
        stats.initStatusCutFishEnd();
    }

    public void initCaptchaStart() {
        stats.initCaptchaStart();
    }

    public void initCaptchaEnd() {
        stats.initCaptchaEnd();
    }

    public void initStatusCaptchaStart() {
        stats.initStatusCaptchaStart();
    }

    public void initStatusCaptchaEnd() {
        stats.initStatusCaptchaEnd();
    }

    public void initFilterLootStart() {
        stats.initFilterLootStart();
    }

    public void initFilterLootEnd() {
        stats.initFilterLootEnd();
    }
}