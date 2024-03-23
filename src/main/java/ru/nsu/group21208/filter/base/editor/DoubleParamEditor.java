package ru.nsu.group21208.filter.base.editor;

import ru.nsu.group21208.filter.base.param.DoubleParam;

import javax.swing.*;
import javax.swing.event.ChangeEvent;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;
import java.text.NumberFormat;

public class DoubleParamEditor extends JPanel {

    private final JTextField textField;

    private final JSlider slider;

    private final DoubleParam param;

    private final double defaultValue;

    public DoubleParamEditor(DoubleParam param) {
        super();
        this.defaultValue = param.getValue();
        this.param = param;
        setLayout(new FlowLayout(FlowLayout.CENTER));
        JLabel label = new JLabel(param.name());
        add(label);
        NumberFormat amountFormat = NumberFormat.getNumberInstance();
        amountFormat.setParseIntegerOnly(true);
        textField = new JFormattedTextField(amountFormat);
        textField.addFocusListener(new FocusListener() {
            @Override
            public void focusGained(FocusEvent e) {}
            @Override
            public void focusLost(FocusEvent e) {
                textFieldUpdate();
            }
        });
        textField.setMinimumSize(new Dimension(30, 20));
        textField.setSize( 30, 20);
        textField.setPreferredSize(new Dimension(40, 20));
        add(textField);
        textField.setText(Double.toString(param.getValue()));
        textField.addActionListener(this::updateField);
        slider = new JSlider((int) param.min(), (int) param.max(), param.getValue().intValue());
        add(slider);
        slider.addChangeListener(this::updateSlider);
    }

    private void updateField(ActionEvent e) {
        textFieldUpdate();
    }

    private void textFieldUpdate() {
        try {
            double val = Double.parseDouble(textField.getText());
            if (val < param.min()) {
                textField.setText(Double.toString(param.min()));
                wrongDataDialog();
            }
            if (val > param.max()) {
                textField.setText(Double.toString(param.max()));
                wrongDataDialog();
            }
            slider.setValue((int) defaultValue);
        } catch (NumberFormatException ex) {
            textField.setText(Double.toString(defaultValue));
            wrongDataDialog();
            slider.setValue(1);
        }
    }

    private void updateSlider(ChangeEvent e) {
        int val = slider.getValue();
        textField.setText(Integer.toString(val));
        setValue(val);
    }

    public void wrongDataDialog() {
        JDialog dialog = new JDialog();
        dialog.setTitle("Wrong data");
        dialog.setPreferredSize(new Dimension(400, 100));
        dialog.setMinimumSize(new Dimension(400, 100));
        dialog.add(new JLabel("Only double values from " + param.min() + " to " + param.max() + " are available"), BorderLayout.CENTER);
        dialog.pack();
        dialog.setAlwaysOnTop(true);
        dialog.setVisible(true);
    }


    protected void setValue(double val) {
        param.setValue(val);
    }

}
