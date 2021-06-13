package ru.namibios.bdofishbot.bot;

import org.apache.log4j.Logger;
import ru.namibios.bdofishbot.bot.template.MatrixTemplate;
import ru.namibios.bdofishbot.cli.Application;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.util.List;

public class PaletteParser {

    private static final Logger LOG = Logger.getLogger(PaletteParser.class);

    private int[][] screenMatrix;

    private BufferedImage screenShot;

    private MatrixTemplate[] collectionTemplate;

    private double coefIdentification = Application.getInstance().PALETTE_COEF_IDENTITY();

    private int row;
    private int column;

    public PaletteParser(Screen screen, MatrixTemplate[] matrixTemplate) {
        this.screenShot = screen.getScreenShot();
        this.row = screenShot.getHeight();
        this.column = screenShot.getWidth();
        this.screenMatrix = new int[row][column];
        this.collectionTemplate = matrixTemplate;
    }

    public PaletteParser(BufferedImage screenShot){
        this.screenShot = screenShot;
        this.row = screenShot.getHeight();
        this.column = screenShot.getWidth();
        this.screenMatrix = new int[row][column];
    }

    private Color[] palette;

    private static final Color CUSTOM_GRAY = new Color(180,180, 180);

    public static Color[] DEFAULT_PALETTE = {
            Color.WHITE,
            CUSTOM_GRAY,
            Color.RED,
            Color.GREEN,
            Color.BLUE,
    };

    public static Color[] LOOT_FRAME_PALETTE = {
            new Color(24, 24, 26),
            new Color(112, 128, 151),
            new Color(141, 146, 123),
            new Color(203, 192, 138),
            new Color(121,98,104),

    };

    private int getIndexPallete(Color color){

        int red = color.getRed();
        int green = color.getGreen();
        int blue = color.getBlue();

        int minValue = Integer.MAX_VALUE;
        int minIndex = Integer.MAX_VALUE;
        for (int i = 0; i < palette.length; i++) {
            Color pal = palette[i];
            int rez = (int)
                    (Math.pow(red - pal.getRed(), 2) +
                            Math.pow(green - pal.getGreen(), 2) +
                            Math.pow(blue - pal.getBlue(), 2));

            if (rez < minValue) {
                minValue = rez;
                minIndex = i;
            }
        }

        return minIndex;
    }

    public int[][] getImageMatrix() {
        return screenMatrix;
    }

    public void parse(Color[] palette){

        this.palette = palette;

        for (int i = 0; i < row; i++) {
            for (int j = 0; j < column; j++) {
                Color color = new Color(screenShot.getRGB(j, i));
                screenMatrix[i][j] = getIndexPallete(color);
            }
        }
    }

    public MatrixTemplate getValue(){

        MatrixTemplate max = null;
        double maxCoef = 0;

        for (MatrixTemplate matrixTemplate : collectionTemplate) {
            List<int[][]> templates = matrixTemplate.getTemplates();

            for (int[][] template : templates) {
                if (template.length != screenMatrix.length || template[0].length != screenMatrix[0].length) {
                    continue;
                }
                double coef = getCoef(template, screenMatrix);
                if (coef > maxCoef) {
                    maxCoef = coef;
                    max = matrixTemplate;
                }
            }

        }
        LOG.debug("maxCoef = " + maxCoef);
        return maxCoef > coefIdentification ? max : null;
    }

    private double getCoef(int[][] template, int[][] value){

        double all = template.length * template[0].length;
        double compare = 0;
        for (int i = 0; i < template.length; i++) {
            for (int j = 0; j < template[0].length; j++) {
                if (template[i][j] == value[i][j]) {
                    compare++;
                }
            }
        }

        double coef = (compare / all);

        return coef;
    }

}