package ru.nsu.group21208;

import ru.nsu.group21208.interaction.FileInteractions;
import ru.nsu.group21208.interaction.OriginalToggleInteraction;
import ru.nsu.group21208.interaction.ShowModeInteractions;
import ru.nsu.group21208.interaction.impl.ButtonInfo;
import ru.nsu.group21208.interaction.impl.FileInteractionsImpl;
import ru.nsu.group21208.interaction.impl.OriginalToggleInteractionImpl;
import ru.nsu.group21208.interaction.impl.ShowModeInteractionsImpl;
import ru.nsu.group21208.visualization.ImageFrame;
import ru.nsu.group21208.visualization.ImageFrameImpl;

import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;

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

        FileInteractions fileInteractions = new FileInteractionsImpl(
                imageFrame,
                new ButtonInfo(createTextBufferedImage("Open"), "Open", "Open image from file"),
                new ButtonInfo(createTextBufferedImage("Save"), "Save", "Save image to file")
        );
        OriginalToggleInteraction originalToggleInteraction = new OriginalToggleInteractionImpl(
                imageFrame,
                new ButtonInfo(createTextBufferedImage("Orig"), "Original", "Show original image"),
                new ButtonInfo(createTextBufferedImage("Filtered"), "Filtered", "Show filtered image")
        );
        ShowModeInteractions showModeInteractions = new ShowModeInteractionsImpl(
                imageFrame,
                new ButtonInfo(createTextBufferedImage("Real"), "Real", "Show image in real size"),
                new ButtonInfo(createTextBufferedImage("Adapt"), "Adaptable", "Show image in adaptable size")
        );

        //TODO: create filters and menu

        pack();

        setVisible(true);

    }

    private BufferedImage createTextBufferedImage(String text) {
        BufferedImage image = new BufferedImage(40, 40, 0);
        Graphics g = image.getGraphics();
        g.setFont(g.getFont().deriveFont(5f));
        g.drawString(text, 0, 40);
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
