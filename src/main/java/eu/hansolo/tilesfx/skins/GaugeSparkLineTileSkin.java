/*
 * Copyright (c) 2017 by Gerrit Grunwald
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package eu.hansolo.tilesfx.skins;

import com.fxexperience.javafx.animation.ShakeTransition;
import eu.hansolo.tilesfx.Section;
import eu.hansolo.tilesfx.Tile;
import eu.hansolo.tilesfx.fonts.Fonts;
import eu.hansolo.tilesfx.tools.*;
import javafx.beans.InvalidationListener;
import javafx.geometry.VPos;
import javafx.scene.CacheHint;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.effect.BlurType;
import javafx.scene.effect.InnerShadow;
import javafx.scene.paint.Color;
import javafx.scene.paint.CycleMethod;
import javafx.scene.paint.LinearGradient;
import javafx.scene.paint.Stop;
import javafx.scene.shape.*;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.scene.text.TextAlignment;
import javafx.scene.text.TextFlow;
import javafx.util.Pair;

import java.time.format.DateTimeFormatter;
import java.util.*;

import static eu.hansolo.tilesfx.tools.Helper.clamp;
import static eu.hansolo.tilesfx.tools.Helper.enableNode;
import static org.testjfx.util.GuiColors.DROPSHADOW_TEXT;


/**
 * Created by hansolo on 26.05.17.
 */
public class GaugeSparkLineTileSkin extends TileSkin {
    private static final int MONTH = 2_592_000;
    private static final int DAY = 86_400;
    private static final int HOUR = 3_600;
    private static final int MINUTE = 60;
    private DateTimeFormatter timeFormatter = DateTimeFormatter.ofPattern("HH:mm:ss");
    private Text titleText;
    private Text valueText;
    private Text unitText;
    private TextFlow valueUnitFlow;
    private Text averageText;
    private Text text;
    private Text timeSpanText;
    private Rectangle graphBounds;
    private List<PathElement> pathElements;
    private Path sparkLine;
    private Circle dot;
    private Rectangle stdDeviationArea;
    private Line averageLine;
    private LinearGradient gradient;
    private GradientLookup gradientLookup;
    private Text minValueText;
    private Text maxValueText;
    private double low;
    private double high;
    private double stdDeviation;
    private int noOfDatapoints;
    private List<Double> dataList;
    private MovingAverage movingAverage;
    private InvalidationListener averagingListener;
    private InvalidationListener highlightSectionListener;
    private NiceScale niceScaleY;
    private List<Line> horizontalTickLines;
    private double horizontalLineOffset;
    private double tickLabelFontSize;
    private List<Text> tickLabelsY;
    private Color tickLineColor;
    private Canvas sectionCanvas;
    private GraphicsContext sectionCtx;
    private Canvas highlightSectionCanvas;
    private GraphicsContext highlightSectionCtx;
    private Arc barBackground;
    private Arc bar;
    private Path minThreshold;
    private Path maxThreshold;


    // ******************** Constructors **************************************
    public GaugeSparkLineTileSkin(final Tile TILE) {
        super(TILE);
    }


    // ******************** Initialization ************************************
    @Override
    protected void initGraphics() {
        super.initGraphics();

        averagingListener = o -> handleEvents("AVERAGING_PERIOD");
        highlightSectionListener = o -> handleEvents("HIGHLIGHT_SECTIONS");

        timeFormatter = DateTimeFormatter.ofPattern("HH:mm:ss", tile.getLocale());

        if (tile.isAutoScale()) tile.calcAutoScale();

        niceScaleY = new NiceScale(tile.getMinValue(), tile.getMaxValue());
        niceScaleY.setMaxTicks(5);
        tickLineColor = Color.color(Tile.FOREGROUND.getRed(), Tile.FOREGROUND.getGreen(), Tile.FOREGROUND.getBlue(), 0.50);
        horizontalTickLines = new ArrayList<>(5);
        tickLabelsY = new ArrayList<>(5);
        for (int i = 0; i < 5; i++) {
            Line hLine = new Line(0, 0, 0, 0);
            hLine.getStrokeDashArray().addAll(1.0, 2.0);
            hLine.setStroke(Color.TRANSPARENT);
            horizontalTickLines.add(hLine);
            Text tickLabelY = new Text("" + i);
            tickLabelY.setFill(Color.TRANSPARENT);
            tickLabelsY.add(tickLabelY);
        }

        gradientLookup = new GradientLookup(tile.getGradientStops());
        low = tile.getMaxValue();
        high = tile.getMinValue();
        stdDeviation = 0;
        movingAverage = tile.getMovingAverage();
        noOfDatapoints = tile.getAveragingPeriod();
        dataList = new LinkedList<>();

        // To get smooth lines in the chart we need at least 4 values
        if (noOfDatapoints < 4)
            throw new IllegalArgumentException("Please increase the averaging period to a value larger than 3.");

        graphBounds = new Rectangle(PREFERRED_WIDTH * 0.05, PREFERRED_HEIGHT * 0.5, PREFERRED_WIDTH * 0.9, PREFERRED_HEIGHT * 0.45);

        titleText = new Text(tile.getTitle());
        titleText.setFill(tile.getTitleColor());
        titleText.setEffect(DROPSHADOW_TEXT);
        Helper.enableNode(titleText, !tile.getTitle().isEmpty());

        valueText = new Text(String.format(locale, formatString, tile.getValue()));
        valueText.setFill(tile.getValueColor());
        valueText.setEffect(DROPSHADOW_TEXT);
        Helper.enableNode(valueText, tile.isValueVisible());

        unitText = new Text(tile.getUnit());
        unitText.setFill(tile.getUnitColor());
        unitText.setEffect(DROPSHADOW_TEXT);
        Helper.enableNode(unitText, !tile.getUnit().isEmpty());

        valueUnitFlow = new TextFlow(valueText, unitText);
        valueUnitFlow.setTextAlignment(TextAlignment.CENTER);

        averageText = new Text(String.format(locale, formatString, tile.getAverage()));
        averageText.setFill(Tile.FOREGROUND);
        Helper.enableNode(averageText, tile.isAverageVisible());

        text = new Text();
        text.setTextOrigin(VPos.TOP);
        text.setFill(tile.getTextColor());

        timeSpanText = new Text("");
        timeSpanText.setTextOrigin(VPos.TOP);
        timeSpanText.setFill(tile.getTextColor());
        Helper.enableNode(timeSpanText, !tile.isTextVisible());

        stdDeviationArea = new Rectangle();
        Helper.enableNode(stdDeviationArea, tile.isAverageVisible());

        averageLine = new Line();
        averageLine.setStroke(Tile.FOREGROUND);
        averageLine.getStrokeDashArray().addAll(PREFERRED_WIDTH * 0.005, PREFERRED_WIDTH * 0.005);
        Helper.enableNode(averageLine, tile.isAverageVisible());

        pathElements = new ArrayList<>(noOfDatapoints);
        pathElements.add(0, new MoveTo());
        for (int i = 1; i < noOfDatapoints; i++) {
            pathElements.add(i, new LineTo());
        }

        sparkLine = new Path();
        sparkLine.getElements().addAll(pathElements);
        sparkLine.setFill(null);
        sparkLine.setStroke(tile.getBarColor());
        sparkLine.setStrokeWidth(PREFERRED_WIDTH * 0.0075);
        sparkLine.setStrokeLineCap(StrokeLineCap.ROUND);
        sparkLine.setStrokeLineJoin(StrokeLineJoin.ROUND);

        dot = new Circle();
        dot.setFill(tile.getBarColor());

        sectionCanvas = new Canvas(PREFERRED_WIDTH, PREFERRED_HEIGHT);
        sectionCtx = sectionCanvas.getGraphicsContext2D();

        highlightSectionCanvas = new Canvas(PREFERRED_WIDTH, PREFERRED_HEIGHT);
        highlightSectionCtx = sectionCanvas.getGraphicsContext2D();

        barBackground = new Arc(PREFERRED_WIDTH * 0.5, PREFERRED_HEIGHT * 0.5, PREFERRED_WIDTH * 0.4, PREFERRED_HEIGHT * 0.4, tile.getStartAngle() - 45, tile.getAngleRange());
        barBackground.setType(ArcType.OPEN);
        barBackground.setStroke(tile.getBarBackgroundColor());
        barBackground.setStrokeWidth(PREFERRED_WIDTH * 0.125);
        barBackground.setStrokeLineCap(StrokeLineCap.BUTT);
        barBackground.setFill(null);

        bar = new Arc(PREFERRED_WIDTH * 0.5, PREFERRED_HEIGHT * 0.5, PREFERRED_WIDTH * 0.4, PREFERRED_HEIGHT * 0.4, tile.getStartAngle() - 135, 0);
        bar.setType(ArcType.OPEN);
        bar.setStroke(tile.getBarColor());
        bar.setStrokeWidth(PREFERRED_WIDTH * 0.125);
        bar.setStrokeLineCap(StrokeLineCap.BUTT);
        bar.setFill(null);

        minValueText = new Text();
        maxValueText = new Text();

        //todo calculations
        minThreshold = new Path();
        double centerX = size * 0.5;
        double centerY = size * 0.5;
        if (true) {//is threshold visible
            // Draw threshold
            minThreshold.getElements().clear();
            double thresholdAngle;
            //if (Gauge.ScaleDirection.CLOCKWISE == scaleDirection) {
            //thresholdAngle = startAngle - (gauge.getThreshold() - minValue) * angleStep;
            thresholdAngle = 90;
            /*} else {
                thresholdAngle = startAngle + (gauge.getThreshold() - minValue) * angleStep;
            }*/
            double thresholdSize = eu.hansolo.medusa.tools.Helper.clamp(3.0, 3.5, 0.01 * size);
            double sinValue = Math.sin(Math.toRadians(thresholdAngle));
            double cosValue = Math.cos(Math.toRadians(thresholdAngle));
            /*switch (tickLabelLocation) {
                case OUTSIDE:*/
            minThreshold.getElements().add(new MoveTo(centerX + size * 0.38 * sinValue, centerY + size * 0.38 * cosValue));
            sinValue = Math.sin(Math.toRadians(thresholdAngle - thresholdSize));
            cosValue = Math.cos(Math.toRadians(thresholdAngle - thresholdSize));
            minThreshold.getElements().add(new LineTo(centerX + size * 0.34 * sinValue, centerY + size * 0.34 * cosValue));
            sinValue = Math.sin(Math.toRadians(thresholdAngle + thresholdSize));
            cosValue = Math.cos(Math.toRadians(thresholdAngle + thresholdSize));
            minThreshold.getElements().add(new LineTo(centerX + size * 0.34 * sinValue, centerY + size * 0.34 * cosValue));
            minThreshold.getElements().add(new ClosePath());
            //break;
                /*case INSIDE:
                default:
                    minThreshold.getElements().add(new MoveTo(centerX + size * 0.465 * sinValue, centerY + size * 0.465 * cosValue));
                    sinValue = Math.sin(Math.toRadians(thresholdAngle - thresholdSize));
                    cosValue = Math.cos(Math.toRadians(thresholdAngle - thresholdSize));
                    minThreshold.getElements().add(new LineTo(centerX + size * 0.425 * sinValue, centerY + size * 0.425 * cosValue));
                    sinValue = Math.sin(Math.toRadians(thresholdAngle + thresholdSize));
                    cosValue = Math.cos(Math.toRadians(thresholdAngle + thresholdSize));
                    minThreshold.getElements().add(new LineTo(centerX + size * 0.425 * sinValue, centerY + size * 0.425 * cosValue));
                    minThreshold.getElements().add(new ClosePath());
                    break;
            }*/
            minThreshold.setFill(Color.BLACK);//gauge.getThresholdColor());
            minThreshold.setStroke(Color.BLACK);//gauge.getTickMarkColor());
        }


        //todo dot has been eliminated
        getPane().getChildren().addAll(sectionCanvas, highlightSectionCanvas, barBackground, bar, minValueText, maxValueText, titleText, valueUnitFlow, stdDeviationArea, averageLine, sparkLine, averageText, timeSpanText, text, minThreshold);
        getPane().getChildren().addAll(horizontalTickLines);
        getPane().getChildren().addAll(tickLabelsY);
    }

    @Override
    protected void registerListeners() {
        super.registerListeners();
        tile.averagingPeriodProperty().addListener(averagingListener);
        tile.highlightSectionsProperty().addListener(highlightSectionListener);
    }


    // ******************** Methods *******************************************
    @Override
    protected void handleEvents(final String EVENT_TYPE) {
        super.handleEvents(EVENT_TYPE);

        if ("VISIBILITY".equals(EVENT_TYPE)) {
            Helper.enableNode(titleText, !tile.getTitle().isEmpty());
            Helper.enableNode(text, tile.isTextVisible());
            Helper.enableNode(valueText, tile.isValueVisible());
            Helper.enableNode(unitText, !tile.getUnit().isEmpty());
            Helper.enableNode(timeSpanText, !tile.isTextVisible());
            Helper.enableNode(averageLine, tile.isAverageVisible());
            Helper.enableNode(averageText, tile.isAverageVisible());
            Helper.enableNode(stdDeviationArea, tile.isAverageVisible());
            redraw();
        } else if ("VALUE".equals(EVENT_TYPE)) {
            if (!tile.isAveragingEnabled()) {
                tile.setAveragingEnabled(true);
            }
            addData(clamp(minValue, maxValue, tile.getValue()));
        } else if ("AVERAGING".equals(EVENT_TYPE)) {
            noOfDatapoints = tile.getAveragingPeriod();

            // To get smooth lines in the chart we need at least 4 values
            if (noOfDatapoints < 4)
                throw new IllegalArgumentException("Please increase the averaging period to a value larger than 3.");
            for (int i = 0; i < noOfDatapoints; i++) {
                dataList.add(minValue);
            }
            pathElements.clear();
            pathElements.add(0, new MoveTo());
            for (int i = 1; i < noOfDatapoints; i++) {
                pathElements.add(i, new LineTo());
            }
            sparkLine.getElements().setAll(pathElements);
            redraw();
        } else if ("HIGHLIGHT_SECTIONS".equals(EVENT_TYPE)) {
            boolean isHighlightSections = tile.isHighlightSections();
            sectionCanvas.setVisible(!isHighlightSections);
            sectionCanvas.setManaged(!isHighlightSections);
            highlightSectionCanvas.setVisible(isHighlightSections);
            highlightSectionCanvas.setManaged(isHighlightSections);
        }
    }

    ;
    Section lastSection = null;

    @Override
    protected void handleCurrentValue(final double VALUE) {
        lastSection = null;
        low = Statistics.getMin(dataList);
        high = Statistics.getMax(dataList);
        if (Helper.equals(low, high)) {
            low = minValue;
            high = maxValue;
        }
        range = high - low;

        double minX = graphBounds.getX();
        double maxX = minX + graphBounds.getWidth();
        double minY = graphBounds.getY();
        double maxY = minY + graphBounds.getHeight();
        double stepX = graphBounds.getWidth() / (noOfDatapoints - 1);
        double stepY = graphBounds.getHeight() / range;

        niceScaleY.setMinMax(low, high);
        int lineCountY = 0;
        int tickLabelOffsetY = 1;
        double tickSpacingY = niceScaleY.getTickSpacing();
        double tickStepY = tickSpacingY * stepY;
        double tickStartY = maxY - (tickSpacingY - low) * stepY;
        if (tickSpacingY < low) {
            tickLabelOffsetY = (int) (low / tickSpacingY) + 1;
            tickStartY = maxY - (tickLabelOffsetY * tickSpacingY - low) * stepY;
        }

        horizontalTickLines.forEach(line -> line.setStroke(Color.TRANSPARENT));
        tickLabelsY.forEach(label -> label.setFill(Color.TRANSPARENT));
        horizontalLineOffset = 0;
        for (double y = tickStartY; Math.round(y) > minY; y -= tickStepY) {
            Line line = horizontalTickLines.get(lineCountY);
            Text label = tickLabelsY.get(lineCountY);
            label.setText(String.format(locale, "%.0f", (tickSpacingY * (lineCountY + tickLabelOffsetY))));
            label.setY(y + graphBounds.getHeight() * 0.03);
            label.setFill(tickLineColor);
            horizontalLineOffset = Math.max(label.getLayoutBounds().getWidth(), horizontalLineOffset);

            line.setStartX(minX);
            line.setStartY(y);
            line.setEndY(y);
            line.setStroke(tickLineColor);
            lineCountY++;
            lineCountY = clamp(0, 4, lineCountY);
        }
        if (tickLabelFontSize < 6) {
            horizontalLineOffset = 0;
        }
        horizontalTickLines.forEach(line -> line.setEndX(maxX - horizontalLineOffset));
        tickLabelsY.forEach(label -> label.setX(maxX - label.getLayoutBounds().getWidth() + size * 0.02));

        if (!dataList.isEmpty()) {
            if (tile.isSmoothing()) {
                smooth(dataList);
            } else {
                MoveTo begin = (MoveTo) pathElements.get(0);
                begin.setX(minX);
                begin.setY(maxY - Math.abs(low - dataList.get(0)) * stepY);
                for (int i = 1; i < (noOfDatapoints - 1); i++) {
                    LineTo lineTo = (LineTo) pathElements.get(i);
                    lineTo.setX(minX + i * stepX);
                    lineTo.setY(maxY - Math.abs(low - dataList.get(i)) * stepY);
                }
                LineTo end = (LineTo) pathElements.get(noOfDatapoints - 1);
                end.setX(maxX);
                end.setY(maxY - Math.abs(low - dataList.get(noOfDatapoints - 1)) * stepY);

                dot.setCenterX(maxX);
                dot.setCenterY(end.getY());

                if (tile.isStrokeWithGradient()) {
                    setupGradient();
                    dot.setFill(gradient);
                    sparkLine.setStroke(gradient);
                }
            }

            double average = tile.getAverage();
            double averageY = clamp(minY, maxY, maxY - Math.abs(low - average) * stepY);

            averageLine.setStartX(minX);
            averageLine.setEndX(maxX);
            averageLine.setStartY(averageY);
            averageLine.setEndY(averageY);

            stdDeviationArea.setY(averageLine.getStartY() - (stdDeviation * 0.5 * stepY));
            stdDeviationArea.setHeight(stdDeviation * stepY);

            averageText.setText(String.format(locale, formatString, average));
        }
        valueText.setText(String.format(locale, formatString, VALUE));

        if (!tile.isTextVisible() && null != movingAverage.getTimeSpan()) {
            //todo se ha quitado par ano mostrar mas info
            /*timeSpanText.setText(createTimeSpanText());
            text.setText(timeFormatter.format(movingAverage.getLastEntry().getTimestampAsDateTime(tile.getZoneId())));*/
        }
        resizeDynamicText();

        setBar(VALUE);

        if (tile.isHighlightSections()) {
            drawHighLightSections(VALUE);
        }

        if (lastSection != null && (sections.indexOf(lastSection) == 0 || sections.indexOf(lastSection) == 2)) {
            //todo launch in a separate thread
            new ShakeTransition(valueText).play();
            new ShakeTransition(unitText).play();
        }
    }

    private void markMinMaxValues(final double VALUE) {
        double barLength = 0;
        double startAngle = 0;
        double min = tile.getMinValue();
        double max = tile.getMaxValue();
        double step = tile.getAngleStep();
        double clampedValue = Helper.clamp(min, max, VALUE);

        if (tile.isStartFromZero()) {
            if ((VALUE > min || min < 0) && (VALUE < max || max > 0)) {
                if (max < 0) {
                    startAngle = tile.getStartAngle() - 135 - tile.getAngleRange();
                    barLength = (max - clampedValue) * step;
                } else if (min > 0) {
                    startAngle = tile.getStartAngle() - 135;
                    barLength = (min - clampedValue) * step;
                } else {
                    startAngle = tile.getStartAngle() - 135 + min * step;
                    barLength = -clampedValue * step;
                }
            }
        } else {
            startAngle = tile.getStartAngle() - 135;
            barLength = (min - clampedValue) * step;
        }

        //bar.setStartAngle(startAngle);
        //bar.setLength(barLength);

    }

    private void setBar(final double VALUE) {
        double barLength = 0;
        double barStart = 0;
        double min = tile.getMinValue();
        double max = tile.getMaxValue();
        double step = tile.getAngleStep();
        double clampedValue = Helper.clamp(min, max, VALUE);

        if (tile.isStartFromZero()) {
            if ((VALUE > min || min < 0) && (VALUE < max || max > 0)) {
                if (max < 0) {
                    barStart = tile.getStartAngle() - 135 - tile.getAngleRange();
                    barLength = (max - clampedValue) * step;
                } else if (min > 0) {
                    barStart = tile.getStartAngle() - 135;
                    barLength = (min - clampedValue) * step;
                } else {
                    barStart = tile.getStartAngle() - 135 + min * step;
                    barLength = -clampedValue * step;
                }
            }
        } else {
            barStart = tile.getStartAngle() - 135;
            barLength = (min - clampedValue) * step;
        }

        bar.setStartAngle(barStart);
        bar.setLength(barLength);


        if (tile.getSectionsVisible() && !sections.isEmpty()) {
            bar.setStroke(tile.getBarColor());
            for (Section section : sections) {
                if (section.contains(VALUE)) {
                    bar.setStroke(section.getColor());
                    //todo added the color to value and unit text
                    valueText.setFill(section.getColor());
                    unitText.setFill(section.getColor());
                    barBackground.setFill(new Color(section.getColor().getRed(), section.getColor().getGreen(), section.getColor().getBlue(), 0.20));
                    barBackground.setEffect(new InnerShadow(BlurType.TWO_PASS_BOX, new Color(0, 0, 0, 0.35), 0.07 * size, 0, 0, 0));
                    lastSection = section;
                    //titleText.setFill(section.getColor());
                    //text.setFill(section.getColor());
                    break;
                }
            }
        }

    }

    private void drawHighLightSections(final double VALUE) {
        highlightSectionCtx.setLineCap(StrokeLineCap.BUTT);
        highlightSectionCtx.clearRect(0, 0, width, height);

        if (tile.getSectionsVisible() && !sections.isEmpty()) {
            double x = (width - size * 0.7) * 0.5;
            double y = (height - size * 0.7) * 0.5;
            double wh = size * 0.7;
            double minValue = tile.getMinValue();
            double maxValue = tile.getMaxValue();
            double angleStep = tile.getAngleStep();
            highlightSectionCtx.setLineWidth(size * 0.01);
            highlightSectionCtx.setLineCap(StrokeLineCap.BUTT);
            for (int i = 0; i < sections.size(); i++) {
                Section section = sections.get(i);
                double sectionStartAngle;
                if (Double.compare(section.getStart(), maxValue) <= 0 && Double.compare(section.getStop(), minValue) >= 0) {
                    if (Double.compare(section.getStart(), minValue) < 0 && Double.compare(section.getStop(), maxValue) < 0) {
                        sectionStartAngle = 15;
                    } else {
                        sectionStartAngle = (section.getStart() - minValue) * angleStep + 15;
                    }
                    double sectionAngleExtend;
                    if (Double.compare(section.getStop(), maxValue) > 0) {
                        sectionAngleExtend = (maxValue - section.getStart()) * angleStep;
                    } else if (Double.compare(section.getStart(), minValue) < 0) {
                        sectionAngleExtend = (section.getStop() - minValue) * tile.getAngleStep();
                    } else {
                        sectionAngleExtend = (section.getStop() - section.getStart()) * angleStep;
                    }
                    highlightSectionCtx.save();
                    highlightSectionCtx.setStroke(section.contains(VALUE) ? section.getColor() : section.getColor().darker().darker());
                    highlightSectionCtx.strokeArc(x, y, wh, wh, -(120 + sectionStartAngle), -sectionAngleExtend, ArcType.OPEN);
                    highlightSectionCtx.restore();
                }
            }
        }
    }

    private void addData(final double VALUE) {
        if (dataList.isEmpty()) {
            for (int i = 0; i < noOfDatapoints; i++) {
                dataList.add(VALUE);
            }
        }
        if (dataList.size() <= noOfDatapoints) {
            Collections.rotate(dataList, -1);
            dataList.set((noOfDatapoints - 1), VALUE);
        } else {
            dataList.add(VALUE);
        }
        stdDeviation = Statistics.getStdDev(dataList);
    }

    private void setupGradient() {
        double loFactor = (low - minValue) / tile.getRange();
        double hiFactor = (high - minValue) / tile.getRange();
        Stop loStop = new Stop(loFactor, gradientLookup.getColorAt(loFactor));
        Stop hiStop = new Stop(hiFactor, gradientLookup.getColorAt(hiFactor));
        gradient = new LinearGradient(0, graphBounds.getY() + graphBounds.getHeight(), 0, graphBounds.getY(), false, CycleMethod.NO_CYCLE, loStop, hiStop);
    }

    private String createTimeSpanText() {
        long timeSpan = movingAverage.getTimeSpan().getEpochSecond();
        StringBuilder timeSpanBuilder = new StringBuilder(movingAverage.isFilling() ? "\u22a2 " : "\u2190 ");
        if (timeSpan > MONTH) { // 1 Month (30 days)
            int months = (int) (timeSpan / MONTH);
            double days = timeSpan % MONTH;
            timeSpanBuilder.append(months).append("M").append(String.format(Locale.US, "%.0f", days)).append("d").append(" \u2192");
        } else if (timeSpan > DAY) { // 1 Day
            int days = (int) (timeSpan / DAY);
            double hours = (timeSpan - (days * DAY)) / HOUR;
            timeSpanBuilder.append(days).append("d").append(String.format(Locale.US, "%.0f", hours)).append("h").append(" \u2192");
        } else if (timeSpan > HOUR) { // 1 Hour
            int hours = (int) (timeSpan / HOUR);
            double minutes = (timeSpan - (hours * HOUR)) / MINUTE;
            timeSpanBuilder.append(hours).append("h").append(String.format(Locale.US, "%.0f", minutes)).append("m").append(" \u2192");
        } else if (timeSpan > MINUTE) { // 1 Minute
            int minutes = (int) (timeSpan / MINUTE);
            double seconds = (timeSpan - (minutes * MINUTE));
            timeSpanBuilder.append(minutes).append("m").append(String.format(Locale.US, "%.0f", seconds)).append("s").append(" \u2192");
        } else {
            int seconds = (int) timeSpan;
            timeSpanBuilder.append(seconds).append("s").append(" \u2192");
        }
        return timeSpanBuilder.toString();
    }

    @Override
    public void dispose() {
        tile.averagingPeriodProperty().removeListener(averagingListener);
        tile.highlightSectionsProperty().removeListener(highlightSectionListener);
        super.dispose();
    }


    // ******************** Smoothing *****************************************
    public void smooth(final List<Double> DATA_LIST) {
        int size = DATA_LIST.size();
        double[] x = new double[size];
        double[] y = new double[size];

        low = Statistics.getMin(DATA_LIST);
        high = Statistics.getMax(DATA_LIST);
        if (Helper.equals(low, high)) {
            low = minValue;
            high = maxValue;
        }
        range = high - low;

        double minX = graphBounds.getX();
        double maxX = minX + graphBounds.getWidth();
        double minY = graphBounds.getY();
        double maxY = minY + graphBounds.getHeight();
        double stepX = graphBounds.getWidth() / (noOfDatapoints - 1);
        double stepY = graphBounds.getHeight() / range;

        for (int i = 0; i < size; i++) {
            x[i] = minX + i * stepX;
            y[i] = maxY - Math.abs(low - DATA_LIST.get(i)) * stepY;
        }

        Pair<Double[], Double[]> px = computeControlPoints(x);
        Pair<Double[], Double[]> py = computeControlPoints(y);

        sparkLine.getElements().clear();
        for (int i = 0; i < size - 1; i++) {
            sparkLine.getElements().add(new MoveTo(x[i], y[i]));
            sparkLine.getElements().add(new CubicCurveTo(px.getKey()[i], py.getKey()[i], px.getValue()[i], py.getValue()[i], x[i + 1], y[i + 1]));
        }
        dot.setCenterX(maxX);
        dot.setCenterY(y[size - 1]);
    }

    private Pair<Double[], Double[]> computeControlPoints(final double[] K) {
        int n = K.length - 1;
        Double[] p1 = new Double[n];
        Double[] p2 = new Double[n];

	    /*rhs vector*/
        double[] a = new double[n];
        double[] b = new double[n];
        double[] c = new double[n];
        double[] r = new double[n];

	    /*left most segment*/
        a[0] = 0;
        b[0] = 2;
        c[0] = 1;
        r[0] = K[0] + 2 * K[1];

	    /*internal segments*/
        for (int i = 1; i < n - 1; i++) {
            a[i] = 1;
            b[i] = 4;
            c[i] = 1;
            r[i] = 4 * K[i] + 2 * K[i + 1];
        }

	    /*right segment*/
        a[n - 1] = 2;
        b[n - 1] = 7;
        c[n - 1] = 0;
        r[n - 1] = 8 * K[n - 1] + K[n];

	    /*solves Ax = b with the Thomas algorithm*/
        for (int i = 1; i < n; i++) {
            double m = a[i] / b[i - 1];
            b[i] = b[i] - m * c[i - 1];
            r[i] = r[i] - m * r[i - 1];
        }

        p1[n - 1] = r[n - 1] / b[n - 1];
        for (int i = n - 2; i >= 0; --i) {
            p1[i] = (r[i] - c[i] * p1[i + 1]) / b[i];
        }

        for (int i = 0; i < n - 1; i++) {
            p2[i] = 2 * K[i + 1] - p1[i + 1];
        }
        p2[n - 1] = 0.5 * (K[n] + p1[n - 1]);

        return new Pair<>(p1, p2);
    }


    // ******************** Resizing ******************************************
    @Override
    protected void resizeDynamicText() {
        double maxWidth = unitText.isVisible() ? width - size * 0.275 : width - size * 0.1;
        double fontSize = size * 0.12;
        valueText.setFont(Fonts.latoRegular(fontSize));
        if (valueText.getLayoutBounds().getWidth() > maxWidth) {
            Helper.adjustTextSize(valueText, maxWidth, fontSize);
        }

        maxWidth = width - size * 0.7;
        fontSize = size * 0.06;
        averageText.setFont(Fonts.latoRegular(fontSize));
        if (averageText.getLayoutBounds().getWidth() > maxWidth) {
            Helper.adjustTextSize(averageText, maxWidth, fontSize);
        }
        if (averageLine.getStartY() < graphBounds.getY() + graphBounds.getHeight() * 0.5) {
            averageText.setY(averageLine.getStartY() + (size * 0.0425));
        } else {
            averageText.setY(averageLine.getStartY() - (size * 0.0075));
        }

        maxWidth = width - size * 0.25;
        fontSize = size * 0.06;
        text.setFont(Fonts.latoRegular(fontSize));
        /*if (text.getLayoutBounds().getWidth() > maxWidth) { Helper.adjustTextSize(text, maxWidth, fontSize); }
        text.relocate(width - size * 0.05 - text.getLayoutBounds().getWidth(), height - size * 0.1);*/

        if (text.getLayoutBounds().getWidth() > maxWidth) {
            Helper.adjustTextSize(text, maxWidth, fontSize);
        }
        switch (tile.getTextAlignment()) {
            default:
            case LEFT:
                text.setX(size * 0.05);
                break;
            case CENTER:
                text.setX((width - text.getLayoutBounds().getWidth()) * 0.5);
                break;
            case RIGHT:
                text.setX(width - (size * 0.05) - text.getLayoutBounds().getWidth());
                break;
        }
        text.setY(height - size * 0.09);

        maxWidth = width - size * 0.25;
        fontSize = size * 0.06;
        timeSpanText.setFont(Fonts.latoRegular(fontSize));
        if (timeSpanText.getLayoutBounds().getWidth() > maxWidth) {
            Helper.adjustTextSize(timeSpanText, maxWidth, fontSize);
        }
        timeSpanText.relocate((width - timeSpanText.getLayoutBounds().getWidth()) * 0.5, height - size * 0.1);
    }

    @Override
    protected void resizeStaticText() {
        double maxWidth = width - size * 0.1;
        double fontSize = size * textSize.factor;

        titleText.setFont(Fonts.latoRegular(fontSize));
        if (titleText.getLayoutBounds().getWidth() > maxWidth) {
            Helper.adjustTextSize(titleText, maxWidth, fontSize);
        }
        switch (tile.getTitleAlignment()) {
            default:
            case LEFT:
                titleText.relocate(size * 0.05, size * 0.025);
                break;
            case CENTER:
                titleText.relocate((width - titleText.getLayoutBounds().getWidth()) * 0.5, size * 0.025);
                break;
            case RIGHT:
                titleText.relocate(width - (size * 0.05) - titleText.getLayoutBounds().getWidth(), size * 0.025);
                break;
        }

        maxWidth = width - size * 0.85;
        fontSize = size * 0.12;
        unitText.setFont(Fonts.latoRegular(fontSize));
        if (unitText.getLayoutBounds().getWidth() > maxWidth) {
            Helper.adjustTextSize(unitText, maxWidth, fontSize);
        }

        averageText.setX(size * 0.05);

        fontSize = size * 0.04;
        minValueText.setFont(Fonts.latoRegular(fontSize));
        minValueText.setText(String.format(locale, "%.0f", tile.getMinValue()));
        minValueText.setX(width * 0.5 - size * 0.2);
        minValueText.setY(height * 0.5 + size * 0.25);

        maxValueText.setFont(Fonts.latoRegular(fontSize));
        maxValueText.setText(String.format(locale, "%.0f", tile.getMaxValue()));
        maxValueText.setX(width * 0.5 + size * 0.2 - maxValueText.getLayoutBounds().getWidth());
        maxValueText.setY(height * 0.5 + size * 0.25);
    }

    private void drawBackground() {
        sectionCanvas.setCache(false);
        sectionCtx.setLineCap(StrokeLineCap.BUTT);
        sectionCtx.clearRect(0, 0, width, height);

        if (tile.getSectionsVisible() && !sections.isEmpty()) {
            double x = (width - size * 0.7) * 0.5;
            double y = (height - size * 0.7) * 0.5;
            double wh = size * 0.7;
            double minValue = tile.getMinValue();
            double maxValue = tile.getMaxValue();
            double angleStep = tile.getAngleStep();
            sectionCtx.setLineWidth(size * 0.01);
            sectionCtx.setLineCap(StrokeLineCap.BUTT);
            for (int i = 0; i < sections.size(); i++) {
                Section section = sections.get(i);
                double sectionStartAngle;
                if (Double.compare(section.getStart(), maxValue) <= 0 && Double.compare(section.getStop(), minValue) >= 0) {
                    if (Double.compare(section.getStart(), minValue) < 0 && Double.compare(section.getStop(), maxValue) < 0) {
                        sectionStartAngle = 15;
                    } else {
                        sectionStartAngle = (section.getStart() - minValue) * angleStep + 15;
                    }
                    double sectionAngleExtend;
                    if (Double.compare(section.getStop(), maxValue) > 0) {
                        sectionAngleExtend = (maxValue - section.getStart()) * angleStep;
                    } else if (Double.compare(section.getStart(), minValue) < 0) {
                        sectionAngleExtend = (section.getStop() - minValue) * tile.getAngleStep();
                    } else {
                        sectionAngleExtend = (section.getStop() - section.getStart()) * angleStep;
                    }
                    sectionCtx.save();

                    sectionCtx.setStroke(section.getColor());

                    sectionCtx.strokeArc(x, y, wh, wh, -(120 + sectionStartAngle), -sectionAngleExtend, ArcType.OPEN);
                    sectionCtx.restore();
                }
            }
        }

        sectionCanvas.setCache(true);
        sectionCanvas.setCacheHint(CacheHint.QUALITY);
        barBackground.setStroke(tile.getBarBackgroundColor());
    }

    @Override
    protected void resize() {
        super.resize();
        graphBounds = new Rectangle((width - (size * 0.35)) * 0.5, (height - (size * 0.35)) * 0.5, size * 0.35, size * 0.35);

        tickLabelFontSize = graphBounds.getHeight() * 0.1;
        Font tickLabelFont = Fonts.latoRegular(tickLabelFontSize);
        tickLabelsY.forEach(label -> {
            enableNode(label, tickLabelFontSize >= 6);
            label.setFont(tickLabelFont);
        });
        horizontalTickLines.forEach(line -> line.setStrokeWidth(0.5));

        stdDeviationArea.setX(graphBounds.getX());
        stdDeviationArea.setWidth(graphBounds.getWidth());

        averageLine.getStrokeDashArray().setAll(graphBounds.getWidth() * 0.01, graphBounds.getWidth() * 0.01);

        handleCurrentValue(tile.getValue());
        if (tile.getAveragingPeriod() < 250) {
            sparkLine.setStrokeWidth(size * 0.005);
            dot.setRadius(size * 0.01);
        } else if (tile.getAveragingPeriod() < 500) {
            sparkLine.setStrokeWidth(size * 0.003);
            dot.setRadius(size * 0.0105);
        } else {
            sparkLine.setStrokeWidth(size * 0.002);
            dot.setRadius(size * 0.007);
        }

        if (tile.isStrokeWithGradient()) {
            setupGradient();
        }

        resizeStaticText();
        resizeDynamicText();

        valueUnitFlow.setPrefWidth(width - size * 0.1);
        valueUnitFlow.relocate(size * 0.05, height * 0.5 + size * 0.25);

        sectionCanvas.setWidth(width);
        sectionCanvas.setHeight(height);

        highlightSectionCanvas.setWidth(width);
        highlightSectionCanvas.setHeight(height);

        barBackground.setCenterX(width * 0.5);
        barBackground.setCenterY(height * 0.5);
        barBackground.setRadiusX(size * 0.3);
        barBackground.setRadiusY(size * 0.3);
        barBackground.setStrokeWidth(size * 0.07);

        bar.setCenterX(width * 0.5);
        bar.setCenterY(height * 0.5);
        bar.setRadiusX(size * 0.3);
        bar.setRadiusY(size * 0.3);
        bar.setStrokeWidth(size * 0.07);
    }

    ;

    @Override
    protected void redraw() {
        super.redraw();
        drawBackground();
        setBar(tile.getCurrentValue());
        titleText.setText(tile.getTitle());
        unitText.setText(tile.getUnit());
        if (!tile.getDescription().isEmpty()) {
            //text.setText(tile.getDescription());
        }
        if (tile.isTextVisible()) {
            String format = "";
            /*if(movingAverage.getWindow().poll()!=null) {
                format = timeFormatter.format(movingAverage.getWindow().poll().getTimestampAsDateTime(tile.getZoneId()));
            }*/
            text.setText(tile.getText());
        } else if (!tile.isTextVisible() && null != movingAverage.getTimeSpan()) {
            //todo controlar si se quiere mostrar los tiempos
            //timeSpanText.setText(createTimeSpanText());
            //text.setText(timeFormatter.format(movingAverage.getLastEntry().getTimestampAsDateTime(tile.getZoneId())));
        }

        resizeStaticText();

        titleText.setFill(tile.getTitleColor());
        text.setFill(tile.getTextColor());
        timeSpanText.setFill(tile.getTextColor());
        minValueText.setFill(tile.getTextColor());
        maxValueText.setFill(tile.getTextColor());
        if (tile.isStrokeWithGradient()) {
            setupGradient();
            sparkLine.setStroke(gradient);
        } else {
            sparkLine.setStroke(tile.getBarColor());
        }
        stdDeviationArea.setFill(Helper.getTranslucentColorFrom(Tile.FOREGROUND, 0.1));
        dot.setFill(tile.isStrokeWithGradient() ? gradient : tile.getBarColor());
    }

    ;
}
