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
import java.util.Locale;

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
                        BufferedImage tempImg = ImageIO.read(fileChooser.getSelectedFile());
                        if (tempImg == null) {
                            throw new IOException();
                        }
                        BufferedImage img = new BufferedImage(tempImg.getWidth(), tempImg.getHeight(), BufferedImage.TYPE_INT_RGB);
                        for (int i = 0; i < tempImg.getHeight(); ++i) {
                            for (int j = 0; j < tempImg.getWidth(); ++j) {
                                img.setRGB(j, i, tempImg.getRGB(j, i));
                            }
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
                BufferedImage image = imageFrame.getModifiedImage();
                if (image == null) {
                    JOptionPane.showMessageDialog(null, "No image to save!");
                    return;
                }
                JFileChooser fileChooser = new JFileChooser();
                FileNameExtensionFilter pngFilter = new FileNameExtensionFilter("PNG file", "png");
                FileNameExtensionFilter jpgFilter = new FileNameExtensionFilter("JPEG file", "jpg", "jpeg");
                FileNameExtensionFilter gifFilter = new FileNameExtensionFilter("GIF file", "gif");
                fileChooser.setFileFilter(jpgFilter);
                fileChooser.addChoosableFileFilter(pngFilter);
                fileChooser.addChoosableFileFilter(jpgFilter);
                fileChooser.addChoosableFileFilter(gifFilter);
                int res = fileChooser.showSaveDialog(action);
                if (res == JFileChooser.APPROVE_OPTION) {
                    File out = new File(fileChooser.getSelectedFile().getAbsolutePath());
                    final String extension;
                    if (fileChooser.getFileFilter() == pngFilter) {
                        extension = "PNG";
                    } else if (fileChooser.getFileFilter() == gifFilter) {
                        extension = "GIF";
                    }  else {
                        extension = "jpg";
                    }
                    if (!out.exists() && out.getName().lastIndexOf(".") == -1) {
                        File  nOut = new File(out.getParentFile(), out.getName() + "." + extension.toLowerCase(Locale.ENGLISH));
                        if (!nOut.exists())
                            out = nOut;
                    }
                    try {
                        if (!ImageIO.write(image, extension, out)) {
                            JOptionPane.showMessageDialog(null, "Can't write this image in selected format");
                        }
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
        fileChooser.setFileFilter(jpgFilter);
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
