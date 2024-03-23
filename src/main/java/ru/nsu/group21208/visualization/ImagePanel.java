package ru.nsu.group21208.visualization;

import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import javax.swing.*;
import javax.swing.border.Border;
import java.awt.*;
import java.awt.event.InputEvent;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.awt.image.BufferedImage;

public class ImagePanel extends JPanel implements MouseListener, MouseMotionListener {
    private final JScrollPane scrollPane;
    private BufferedImage image = null;
    private boolean isAdaptive = false;
    private boolean isDraggingEnabled = true;
    private final Color BACKGROUND = Color.WHITE;
    private final int EMPTY_PADDING = 4;
    private final int EFFECTIVE_PADDING = EMPTY_PADDING + 1;
    private Object interpolationMode = RenderingHints.VALUE_INTERPOLATION_BILINEAR;

    public ImagePanel(JScrollPane scrollPane) {
        Border border = BorderFactory.createCompoundBorder(
                BorderFactory.createEmptyBorder(EMPTY_PADDING, EMPTY_PADDING, EMPTY_PADDING, EMPTY_PADDING),
                BorderFactory.createDashedBorder(Color.BLACK, 5, 2)
        );
        setBorder(border);
        setBackground(BACKGROUND);
        addMouseListener(this);
        addMouseMotionListener(this);
        this.scrollPane = scrollPane;
        scrollPane.setViewportView(this);
    }

    public ImagePanel(@NotNull JScrollPane scrollPane, @NotNull BufferedImage image) {
        this(scrollPane);
        setImage(image);
    }

    /**
     * Sets image to draw in panel
     * @param image
     */
    public void setImage(@Nullable BufferedImage image) {
        this.image = image;
        if (image == null) {
            setPreferredSize(new Dimension());
            repaint();
            scrollPane.updateUI();
            return;
        }
        if (!isAdaptive) {
            int width = image.getWidth() + 2 * (EFFECTIVE_PADDING);
            int height = image.getHeight() + 2 * (EFFECTIVE_PADDING);
            setPreferredSize(new Dimension(width, height));
        }
        else {
            setPreferredSize(new Dimension());
        }
        repaint();
        scrollPane.updateUI();
    }

    /**
     * Sets adaptive mode <br>
     * If {@link ImagePanel} is adaptive, then image is fully visible on panel<br>
     * If it is not, then picture is shown pixel-to-pixel
     * @param adaptive - default value is false
     */
    public void setAdaptive(boolean adaptive) {
        isAdaptive = adaptive;
        if (adaptive) {
            setPreferredSize(new Dimension());
        }
        else if (image != null) {
            int width = image.getWidth() + 2 * (EFFECTIVE_PADDING);
            int height = image.getHeight() + 2 * (EFFECTIVE_PADDING);
            setPreferredSize(new Dimension(width, height));
        }
        repaint();
        scrollPane.updateUI();
    }

    /**
     * Sets dragging mode, if panel is dragging, than user can drag picture with LMB
     * @param draggingEnabled - default value is true
     */
    public void setDraggingEnabled(boolean draggingEnabled) {
        isDraggingEnabled = draggingEnabled;
    }

    /**
     * Sets interpolation method that will be used for resampling image when adaptive mode is enabled
     * <br> Values should be one of {@link RenderingHints}'s VALUE_INTERPOLATION_* values:
     * <br> RenderingHints.VALUE_INTERPOLATION_BILINEAR
     * <br> RenderingHints.VALUE_INTERPOLATION_BICUBIC
     * <br> RenderingHints.VALUE_INTERPOLATION_NEAREST_NEIGHBOR
     * @param interpolationMode - default value is VALUE_INTERPOLATION_BILINEAR
     */
    public void setInterpolationMode(Object interpolationMode) {
        this.interpolationMode = interpolationMode;
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        if (image == null)
            return;
        int x = EFFECTIVE_PADDING;
        int y = EFFECTIVE_PADDING;
        if (!isAdaptive) {
            g.drawImage(image, x, y, this);
        }
        else {
            Dimension viewSize = scrollPane.getViewport().getSize();
            viewSize.width -= 2*EFFECTIVE_PADDING;
            viewSize.height -= 2*EFFECTIVE_PADDING;
            float image_ratio = (float) image.getWidth() / image.getHeight();
            float panel_ratio = (float) viewSize.width / viewSize.height;
            int newWidth, newHeight;
            if (image_ratio > panel_ratio) {
                newWidth = viewSize.width;
                newHeight = Math.round(newWidth / image_ratio);
            }
            else {
                newHeight = viewSize.height;
                newWidth = Math.round(newHeight * image_ratio);
            }
            Graphics2D graphics2D = (Graphics2D) g.create();
            graphics2D.setRenderingHint(
                    RenderingHints.KEY_INTERPOLATION,
                    interpolationMode
            );
            graphics2D.drawImage(image, x, y, newWidth, newHeight, null);
            graphics2D.dispose();
        }
    }

    private int lastX = 0;
    private int lastY = 0;

    @Override
    public void mousePressed(MouseEvent e) {
        if (isDraggingEnabled && e.getModifiersEx() == InputEvent.BUTTON1_DOWN_MASK) {
            lastX = e.getX();
            lastY = e.getY();
            setCursor(Cursor.getPredefinedCursor(Cursor.MOVE_CURSOR));
        }
    }

    @Override
    public void mouseDragged(MouseEvent e) {
        if (e.getModifiersEx() != InputEvent.BUTTON1_DOWN_MASK || !isDraggingEnabled) {
            return;
        }

        Point scroll = scrollPane.getViewport().getViewPosition();
        scroll.x += ( lastX - e.getX() );
        scroll.y += ( lastY - e.getY() );
        lastX = e.getX() + (lastX - e.getX());	// lastX = lastX
        lastY = e.getY() + (lastY - e.getY());	// lastY = lastY
        scrollPane.getHorizontalScrollBar().setValue(scroll.x);
        scrollPane.getVerticalScrollBar().setValue(scroll.y);
        scrollPane.repaint();
    }

    @Override
    public void mouseClicked(MouseEvent e) {

    }

    @Override
    public void mouseReleased(MouseEvent e) {
        if (isDraggingEnabled) {
            setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        }
    }

    @Override
    public void mouseEntered(MouseEvent e) {
        if (isDraggingEnabled)
            setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
    }

    @Override
    public void mouseExited(MouseEvent e) {
        setCursor(Cursor.getPredefinedCursor(Cursor.DEFAULT_CURSOR));
    }

    @Override
    public void mouseMoved(MouseEvent e) {

    }
}
