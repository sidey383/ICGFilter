package ru.nsu.group21208.interaction.impl;

import org.jetbrains.annotations.NotNull;
import ru.nsu.group21208.interaction.FileInteractions;
import ru.nsu.group21208.interaction.Interaction;
import ru.nsu.group21208.visualization.ImageFrame;

import javax.imageio.ImageIO;
import javax.swing.*;
import javax.swing.filechooser.FileNameExtensionFilter;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

public class FileInteractionsImpl implements FileInteractions {
    
    private final Interaction saveButton;

    private final Interaction openButton;

    public FileInteractionsImpl(@NotNull ImageFrame imageFrame, ButtonInfo openButton, ButtonInfo saveButton) {
        this.openButton = new InteractionImpl(openButton) {
            @Override
            public void apply(JComponent action) {
                final JFileChooser fileChooser = getOpenChooser();
                int res = fileChooser.showOpenDialog(action);
                if (res == JFileChooser.APPROVE_OPTION) {
                    try {
                        BufferedImage img = ImageIO.read(fileChooser.getSelectedFile());
                        if (img == null) {
                            throw new IOException();
                        }
                        imageFrame.setOriginalImage(img);
                    }
                    catch (IOException ex) {
                        JOptionPane.showMessageDialog(action, "Wrong file!");
                    }
                }
            }
        };
        this.saveButton = new InteractionImpl(saveButton) {
            @Override
            public void apply(JComponent action) {
                JFileChooser fileChooser = new JFileChooser();
                int res = fileChooser.showSaveDialog(action);
                if (res == JFileChooser.APPROVE_OPTION) {
                    File out = new File(fileChooser.getSelectedFile().getAbsolutePath());
                    try {
                        ImageIO.write(imageFrame.getModifiedImage(), "JPG", out);
                    }
                    catch (IOException ex) {
                        JOptionPane.showMessageDialog(null, "Wrong file!");
                    }
                }
            }
        };
    }

    @NotNull
    private static JFileChooser getOpenChooser() {
        JFileChooser fileChooser = new JFileChooser();
        FileNameExtensionFilter pngFilter = new FileNameExtensionFilter("PNG file", "png");
        FileNameExtensionFilter jpgFilter = new FileNameExtensionFilter("JPEG file", "jpg", "jpeg");
        FileNameExtensionFilter gifFilter = new FileNameExtensionFilter("GIF file", "gif");
        FileNameExtensionFilter bmpFilter = new FileNameExtensionFilter("BMP file", "bmp");
        fileChooser.addChoosableFileFilter(pngFilter);
        fileChooser.addChoosableFileFilter(jpgFilter);
        fileChooser.addChoosableFileFilter(gifFilter);
        fileChooser.addChoosableFileFilter(bmpFilter);
        return fileChooser;
    }
    
    @Override
    public Interaction openFileInteraction() {
        return openButton;
    }

    @Override
    public Interaction saveFileInteraction() {
        return saveButton;
    }
}
