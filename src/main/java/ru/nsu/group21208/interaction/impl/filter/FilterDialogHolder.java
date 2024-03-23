package ru.nsu.group21208.interaction.impl.filter;

import ru.nsu.group21208.filter.Filter;
import ru.nsu.group21208.filter.FilterEditor;
import ru.nsu.group21208.filter.FilterParams;
import ru.nsu.group21208.filter.ImageTransformation;

import javax.swing.*;
import java.awt.*;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;
import java.util.function.Consumer;

public class FilterDialogHolder<T extends FilterParams> implements WindowListener {

    private JDialog dialog;

    private final Consumer<ImageTransformation> transformationConsumer;

    public FilterDialogHolder(JComponent parent, Filter<T> filter, Consumer<ImageTransformation> transformationConsumer) {
        this.transformationConsumer = transformationConsumer;
        this.dialog = new JDialog();
        dialog.getContentPane().setLayout(new BoxLayout(dialog.getContentPane(), BoxLayout.Y_AXIS));
        dialog.setTitle("Filter settings");

        FilterEditor<T> editor = filter.createFilterEditor();

        JButton acceptButton = new JButton("apply");
        acceptButton.addActionListener((e) -> {
            synchronized (FilterDialogHolder.this) {
                transformationConsumer.accept(filter.apply(editor.build()));
                JDialog tempDialog = dialog;
                dialog = null;
                tempDialog.dispatchEvent(new WindowEvent(tempDialog, WindowEvent.WINDOW_CLOSING));
            }
        });

        dialog.getContentPane().setLayout(new BoxLayout(dialog.getContentPane(), BoxLayout.Y_AXIS));
        dialog.getContentPane().add(editor.parameterEditor());
        dialog.getContentPane().add(acceptButton);

        dialog.pack();
        Dimension dimension = dialog.getSize();
        dialog.setMinimumSize(dimension);
        Point p = parent.getLocationOnScreen();
        dialog.setVisible(true);
        dialog.setLocation(p);
        dialog.setAlwaysOnTop(true);
        dialog.addWindowListener(this);
        dialog.setVisible(true);
    }

    @Override
    public void windowOpened(WindowEvent e) {}

    @Override
    public synchronized void windowClosing(WindowEvent e) {
        if (dialog != null)
            transformationConsumer.accept(null);
    }

    @Override
    public synchronized void windowClosed(WindowEvent e) {
        if (dialog != null)
            transformationConsumer.accept(null);
    }

    @Override
    public void windowIconified(WindowEvent e) {}

    @Override
    public void windowDeiconified(WindowEvent e) {}

    @Override
    public void windowActivated(WindowEvent e) {}

    @Override
    public void windowDeactivated(WindowEvent e) {}

}
