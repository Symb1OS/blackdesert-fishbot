package ru.namibios.bdofishbot.bot.service;

import org.apache.log4j.Logger;
import ru.namibios.bdofishbot.bot.Stats;
import ru.namibios.bdofishbot.bot.StatsState;
import ru.namibios.bdofishbot.bot.state.*;
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

    public void update(Class clazz) {

        String simpleName = clazz.getSimpleName();
        if (StartFishState.class.getSimpleName().equals(simpleName)) {
            stats.incStartFish();
        }

        if (CutFishState.class.getSimpleName().equals(simpleName)) {
            stats.incCutFish();
        }

        if (StatusCutState.class.getSimpleName().equals(simpleName)) {
            stats.incStatusCutFish();
        }

        if (CaptchaState.class.getSimpleName().equals(simpleName)) {
            stats.incCaptcha();
        }

        if (FilterLootState.class.getSimpleName().equals(simpleName)) {
            stats.incLootFish();
        }

    }

    public void failCaptcha() {
        stats.incFailCapthca();
    }

    public void okCaptcha() {
        stats.incOkCapthca();
    }

    public void notRecognizedCaptcha() {
        stats.incNotRecognizedCaptcha();
    }

    public void perfectCut() {
        stats.incCutPerfect();
    }

    public void goodCut() {
        stats.incCutGood();
    }

    public void badCut() {
        stats.incCutBad();
    }

    public void endWork() {
        stats.updateEndWork();
    }

}