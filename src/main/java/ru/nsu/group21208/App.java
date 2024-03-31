package ru.nsu.group21208;

import ru.nsu.group21208.filter.FilterParams;
import ru.nsu.group21208.filter.dyachenko.*;
import ru.nsu.group21208.filter.general.SharpeningFilter;
import ru.nsu.group21208.filter.general.InvertFilter;
import ru.nsu.group21208.filter.general.GrayFilter;
import ru.nsu.group21208.filter.naida.DitheringFilter;
import ru.nsu.group21208.filter.naida.GlassBallFilter;
import ru.nsu.group21208.filter.ponomarev.EmbossingFilter;
import ru.nsu.group21208.filter.ponomarev.RotateFilter;
import ru.nsu.group21208.interaction.impl.*;
import ru.nsu.group21208.interaction.impl.filter.FilterGroup;
import ru.nsu.group21208.interaction.impl.filter.FilterInfo;
import ru.nsu.group21208.interaction.impl.filter.FilterInteractionImpl;
import ru.nsu.group21208.panel.PanelInteractionStorage;
import ru.nsu.group21208.panel.menubar.MenuBar;
import ru.nsu.group21208.panel.toolbar.ToolBar;
import ru.nsu.group21208.visualization.ImageFrame;
import ru.nsu.group21208.visualization.ImageFrameImpl;

import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.util.List;

/**
 * Hello world!
 *
 */
public class App extends JFrame {
    public static void main( String[] args ) {
        new App();
    }

    public App() {
        setTitle("ICG Filter");
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setSizeAndPosition();
        setFocusable(true);

        ImageFrame imageFrame = new ImageFrameImpl();

        PanelInteractionStorage storage = new PanelInteractionStorage();

        storage.setFileInteractions(new FileInteractionsImpl(
                imageFrame,
                new ButtonInfo(createTextBufferedImage("Open"), "Open", "Open image from file"),
                new ButtonInfo(createTextBufferedImage("Save"), "Save", "Save image to file")
        ));

        storage.setOriginalToggleInteraction(new OriginalToggleInteractionImpl(
                imageFrame,
                new ButtonInfo(createTextBufferedImage("Orig"), "Original", "Show original image"),
                new ButtonInfo(createTextBufferedImage("Filtered"), "Filtered", "Show filtered image")
        ));

        storage.setShowModeInteractions(new ShowModeInteractionsImpl(
                imageFrame,
                new ButtonInfo(createTextBufferedImage("Real"), "Real", "Show image in real size"),
                new ButtonInfo(createTextBufferedImage("Adapt"), "Adaptable", "Show image in adaptable size")
        ));

        storage.setInterpolationModInteractions(new InterpolationModInteractionsImpl(
                imageFrame,
                new ButtonInfo(createTextBufferedImage("Bili"), "Bilinear", "Change interpolation mode fro adaptable image size into Bilinear"),
                new ButtonInfo(createTextBufferedImage("Bicu"), "Bicubic", "Change interpolation mode fro adaptable image size into Bicubic"),
                new ButtonInfo(createTextBufferedImage("Near"), "Nearest neighbor", "Change interpolation mode fro adaptable image size into Nearest neighbor")
        ));

        storage.setFilterInteractions(new FilterInteractionImpl(
                imageFrame,
                List.of(
                        new FilterGroup(
                                List.of(
                                        new FilterInfo<>(
                                                new EmbossingFilter(),
                                                createTextBufferedImage("Embos"),
                                                "Embossing",
                                                "Embossing filter"
                                        )
                                ),
                                "kernel"
                        ),
                        new FilterGroup(
                                List.of(
                                        new FilterInfo<>(
                                                new RotateFilter(),
                                                createTextBufferedImage("Rot"),
                                                "Rotate",
                                                "Rotate filter"
                                        )
                                ),
                                "rotate"
                        ),
                        new FilterGroup(
                                List.of(
                                        new FilterInfo<>(
                                                new FSDitheringFilter(),
                                                createTextBufferedImage("FDith"),
                                                "FSDDithering",
                                                "FSD Dithering filter"
                                        ),
                                        new FilterInfo<>(
                                                new GammaFilter(),
                                                createTextBufferedImage("G"),
                                                "Gamma",
                                                "Gamma filter"
                                        ),
                                        new FilterInfo<>(
                                                new OrderedDitheringFilter(),
                                                createTextBufferedImage("ODith"),
                                                "Ordered dithering",
                                                "Ordered dithering filter"
                                        ),
                                        new FilterInfo<>(
                                                new RotationFilter(),
                                                createTextBufferedImage("Rot"),
                                                "Rotation",
                                                "Rotation filter"
                                        ),
                                        new FilterInfo<>(
                                                new RobertsFilter(),
                                                createTextBufferedImage("Rob"),
                                                "Roberts",
                                                "Roberts border filter"
                                        ),
                                        new FilterInfo<>(
                                                new SobelFilter(),
                                                createTextBufferedImage("Sob"),
                                                "Sobel",
                                                "Sobel border filter"
                                        ),
                                        new FilterInfo<>(
                                                new BlurFilter(),
                                                createTextBufferedImage("Blur"),
                                                "Blur",
                                                "Gaussian and Box Blur"
                                        )
                                ),
                                "dyachenko"
                        ),
                        new FilterGroup(
                                List.of(
                                        new FilterInfo<>(
                                                new SharpeningFilter(),
                                                createTextBufferedImage("Sharp"),
                                                "Sharp",
                                                "Sharp"
                                          ),
                                          new FilterInfo<>(
                                                new InvertFilter(),
                                                createTextBufferedImage("Invert"),
                                                "Invert",
                                                "Invert colors"
                                          ),
                                          new FilterInfo<>(
                                                new GrayFilter(),
                                                createTextBufferedImage("Gray"),
                                                "Gray",
                                                "Gray filter"
                                        )
                                ),
                                "general"
                        ),
                        new FilterGroup(
                                List.of(
                                        new FilterInfo<>(
                                                new DitheringFilter(),
                                                createTextBufferedImage("Dithering"),
                                                "Dith",
                                                "Dithering"
                                        ),
                                        new FilterInfo<>(
                                                new ru.nsu.group21208.filter.naida.OrderedDitheringFilter(),
                                                createTextBufferedImage("OrderedDithering"),
                                                "OrdD",
                                                "Ordered Dithering"
                                        ),
                                        new FilterInfo<>(
                                                new GlassBallFilter(),
                                                createTextBufferedImage("GlassBallFilter"),
                                                "GBF",
                                                "Glass Ball Filter"
                                        )
                                ),
                                "naida"
                        )
                )
        ));

        imageFrame.setOriginalToggle(storage.getOriginalToggleInteraction());

        setJMenuBar(new MenuBar(storage));
        add(new ToolBar(storage), BorderLayout.NORTH);
        add(imageFrame.getShowComponent());

        pack();

        setVisible(true);

    }

    private BufferedImage createTextBufferedImage(String text) {
        BufferedImage image = new BufferedImage(30, 30, BufferedImage.TYPE_3BYTE_BGR);
        Graphics g = image.getGraphics();
        g.setColor(Color.WHITE);
        g.fillRect(0, 0, 30, 30);
        g.setColor(Color.BLACK);
        g.setFont(g.getFont().deriveFont(15f));
        g.drawString(text, 0, 25);
        g.dispose();
        return image;
    }

    private void setSizeAndPosition() {
        setMinimumSize(new Dimension(640, 480));
        setPreferredSize(new Dimension(800, 800));
        Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
        setLocation(screenSize.width / 2 - 400, screenSize.height / 2 - 400);
    }

}
