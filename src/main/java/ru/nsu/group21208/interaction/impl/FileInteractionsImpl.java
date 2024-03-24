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
    private ImageFrame _imageFrame;

    public FileInteractionsImpl(@NotNull ImageFrame imageFrame) {
        _imageFrame = imageFrame;
    }
    @Override
    public Interaction openFileInteraction() {
        return new Interaction() {
            @Override
            public void apply(JComponent action) {
                JFileChooser fileChooser = new JFileChooser();
                FileNameExtensionFilter pngFilter = new FileNameExtensionFilter("PNG file", "png");
                FileNameExtensionFilter jpgFilter = new FileNameExtensionFilter("JPEG file", "jpg", "jpeg");
                FileNameExtensionFilter gifFilter = new FileNameExtensionFilter("GIF file", "gif");
                FileNameExtensionFilter bmpFilter = new FileNameExtensionFilter("BMP file", "bmp");
                fileChooser.addChoosableFileFilter(pngFilter);
                fileChooser.addChoosableFileFilter(jpgFilter);
                fileChooser.addChoosableFileFilter(gifFilter);
                fileChooser.addChoosableFileFilter(bmpFilter);
                int res = fileChooser.showOpenDialog(action);
                if (res == JFileChooser.APPROVE_OPTION) {
                    try {
                        BufferedImage img = ImageIO.read(fileChooser.getSelectedFile());
                        if (img == null) {
                            throw new IOException();
                        }
                        _imageFrame.setOriginalImage(img);
                    }
                    catch (IOException ex) {
                        JOptionPane.showMessageDialog(action, "Wrong file!");
                    }
                }
            }

            @Override
            public @NotNull BufferedImage actionImage() {
                return new BufferedImage(1, 1, BufferedImage.TYPE_INT_RGB); //TODO Add image
            }

            @Override
            public @NotNull String name() {
                return "Open";
            }

            @Override
            public @NotNull String description() {
                return "Open image file";
            }
        };
    }

    @Override
    public Interaction saveFileInteraction() {
        return new Interaction() {
            @Override
            public void apply(JComponent action) {
                JFileChooser fileChooser = new JFileChooser();
                int res = fileChooser.showSaveDialog(action);
                if (res == JFileChooser.APPROVE_OPTION) {
                    File out = new File(fileChooser.getSelectedFile().getAbsolutePath());
                    try {
                        ImageIO.write(_imageFrame.getModifiedImage(), "JPG", out);
                    }
                    catch (IOException ex) {
                        JOptionPane.showMessageDialog(null, "Wrong file!");
                    }
                }
            }

            @Override
            public @NotNull BufferedImage actionImage() {
                return new BufferedImage(1, 1, BufferedImage.TYPE_INT_RGB); //TODO Add image
            }

            @Override
            public @NotNull String name() {
                return "Save";
            }

            @Override
            public @NotNull String description() {
                return "Save filtered image to file";
            }
        };
    }
}
