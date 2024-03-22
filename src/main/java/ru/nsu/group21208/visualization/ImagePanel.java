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
    private final Color BACKGROUND = Color.WHITE;
    private final int EMPTY_PADDING = 4;
    private final int EFFECTIVE_PADDING = EMPTY_PADDING + 1;

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
            repaint();
            scrollPane.updateUI();
        }
        else {

        }
    }

    public void setAdaptive(boolean adaptive) {
        isAdaptive = adaptive;
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        if (image == null)
            return;
        if (!isAdaptive) {
            int x = EFFECTIVE_PADDING;
            int y = EFFECTIVE_PADDING;
            g.drawImage(image, x, y, this);
        }
        else {

        }
    }

    /**
     * Cool image-dragging feature
    */

    private int lastX = 0;
    private int lastY = 0;

    @Override
    public void mousePressed(MouseEvent e) {
        lastX = e.getX();
        lastY = e.getY();
    }


    @Override
    public void mouseDragged(MouseEvent e) {
        if (e.getModifiersEx() != InputEvent.BUTTON3_DOWN_MASK) {
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

    }

    @Override
    public void mouseEntered(MouseEvent e) {

    }

    @Override
    public void mouseExited(MouseEvent e) {

    }

    @Override
    public void mouseMoved(MouseEvent e) {

    }
}
