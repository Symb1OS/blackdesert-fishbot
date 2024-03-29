package ru.namibios.bdofishbot.gui.view;

import com.intellij.uiDesigner.core.GridConstraints;
import com.intellij.uiDesigner.core.GridLayoutManager;
import org.apache.log4j.Logger;
import ru.namibios.bdofishbot.bot.StatSeries;
import ru.namibios.bdofishbot.bot.Stats;
import ru.namibios.bdofishbot.bot.state.FishBot;
import ru.namibios.bdofishbot.cli.Application;
import ru.namibios.bdofishbot.cli.Bot;
import ru.namibios.bdofishbot.gui.UI;
import ru.namibios.bdofishbot.utils.DelayUtils;
import ru.namibios.bdofishbot.utils.ExceptionUtils;

import javax.swing.*;
import java.awt.*;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.util.List;
import java.util.OptionalDouble;
import java.util.function.Predicate;

public class StatsGui extends JDialog {

    private static final Logger LOG = Logger.getLogger(StatsGui.class);

    private JTextArea area;
    private JPanel panel;

    private final Bot bot;
    private boolean isOpen = true;

    public StatsGui(Bot bot) {
        this.bot = bot;
        this.setContentPane(panel);
        this.setIconImage(new ImageIcon(UI.IMG_STATS).getImage());
        this.setLocation(0, 600);
        this.setSize(200, 300);
        this.setResizable(false);
        this.setAlwaysOnTop(true);
        this.setVisible(true);
        this.setModal(true);
        this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

        area.setEditable(false);

        addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                isOpen = false;
            }
        });

        new Thread(() -> {
            LOG.debug(getName() + " start");
            while (isOpen) {
                if (bot.getFishBot() != null && bot.getFishBot().isRunned()) {
                    try {
                        update();
                        DelayUtils.delay(Application.getInstance().DELAY_STATS_GUI());
                    } catch (Exception e) {
                        LOG.debug(ExceptionUtils.getString(e));
                    }
                } else {
                    break;
                }
            }
            LOG.debug(getName() +  " stop");
        }).start();
    }

    private void update() {
        FishBot fishBot = bot.getFishBot();
        if (fishBot != null) {
            Stats stats = fishBot.getStats();
            List<StatSeries> series = stats.getSeries();
            if (series.isEmpty()) {
                LOG.debug("empty series..");
                return;
            }

            long countSeries = series.size();

            Predicate<StatSeries> successCycle = statSeries -> statSeries.getStatusCut() != null && statSeries.getStatusCut().equals("PERFECT") || (statSeries.isRecognizedCaptcha() && statSeries.isStatusCaptcha());

            long successSeries = series.stream().filter(successCycle).count();
            long perfectSeries = series.stream().filter(statSeries -> statSeries.getStatusCut() != null).filter(statSeries -> statSeries.getStatusCut().equals("PERFECT")).count();
            long parsingSeries = series.stream().filter(statSeries -> statSeries.isRecognizedCaptcha() && statSeries.isStatusCaptcha()).count();

            OptionalDouble avgCycles = series.stream().filter(statSeries -> statSeries.getFilterLootEnd() != null && statSeries.getWaitFishStart() != null).filter(successCycle).mapToLong(statSeries -> statSeries.getFilterLootEnd().getTime() - statSeries.getWaitFishStart().getTime()).average();

            long usefull = series.stream().mapToLong(StatSeries::getUsefull).sum();
            long confirm = series.stream().mapToLong(StatSeries::getConfirm).sum();
            long unknown = series.stream().mapToLong(StatSeries::getUnknown).sum();
            long trash = series.stream().mapToLong(StatSeries::getTrash).sum();
            long empty = series.stream().mapToLong(StatSeries::getEmpty).sum();

            long red = series.stream().mapToLong(StatSeries::getRed).sum();
            long gold = series.stream().mapToLong(StatSeries::getGold).sum();
            long blue = series.stream().mapToLong(StatSeries::getBlue).sum();
            long green = series.stream().mapToLong(StatSeries::getGreen).sum();
            long gray = series.stream().mapToLong(StatSeries::getGray).sum();

            StringBuilder sb = new StringBuilder();
            sb.append("Uptime: ").append(fishBot.getUptime() / 1000 / 60).append(" minutes")
                    .append("\nFree fishing rods: ").append(fishBot.getRodService().getCountAvailableRods()).append(" of ").append(Application.getInstance().COUNT_ROD())
                    .append("\nSlot: ").append(fishBot.getSlotService().info())
                    .append("\n----------------------------")
                    .append("\nAverage fishing time: ").append(Math.round(avgCycles.isPresent() ? avgCycles.getAsDouble() / 1000 : 0)).append(" seconds")
                    .append("\nFishing cycles: ").append(countSeries)
                    .append("\nSuccess fishing: ").append(successSeries)
                    .append("\nPerfect cycles: ").append(perfectSeries)
                    .append("\nParsing cycles: ").append(parsingSeries)
                    .append("\n----------------------------")
                    .append("\nUsefull: ").append(usefull)
                    .append("\nConfirm: ").append(confirm)
                    .append("\nUnknown: ").append(unknown)
                    .append("\nTrash: ").append(trash)
                    .append("\nEmpty: ").append(empty)
                    .append("\n----------------------------")
                    .append("\nRed: ").append(red)
                    .append("\nGold: ").append(gold)
                    .append("\nBlue: ").append(blue)
                    .append("\nGreen: ").append(green)
                    .append("\nGray: ").append(gray);

            area.setText(sb.toString());

            pack();
        }
    }

    {
// GUI initializer generated by IntelliJ IDEA GUI Designer
// >>> IMPORTANT!! <<<
// DO NOT EDIT OR ADD ANY CODE HERE!
        $$$setupUI$$$();
    }

    /**
     * Method generated by IntelliJ IDEA GUI Designer
     * >>> IMPORTANT!! <<<
     * DO NOT edit this method OR call it in your code!
     *
     * @noinspection ALL
     */
    private void $$$setupUI$$$() {
        panel = new JPanel();
        panel.setLayout(new GridLayoutManager(1, 1, new Insets(0, 0, 0, 0), -1, -1));
        area = new JTextArea();
        panel.add(area, new GridConstraints(0, 0, 1, 1, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_BOTH, GridConstraints.SIZEPOLICY_WANT_GROW, GridConstraints.SIZEPOLICY_WANT_GROW, null, new Dimension(150, 50), null, 0, false));
    }

    /**
     * @noinspection ALL
     */
    public JComponent $$$getRootComponent$$$() {
        return panel;
    }

}
