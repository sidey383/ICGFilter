package ru.nsu.group21208.filter.base.editor;

import ru.nsu.group21208.filter.base.param.IntegerParam;

import javax.swing.*;
import javax.swing.event.ChangeEvent;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;
import java.text.NumberFormat;

public class IntegerParamEditor extends JPanel {

    private final JTextField textField;

    private final JSlider slider;

    private final IntegerParam param;

    public IntegerParamEditor(IntegerParam param) {
        super();
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
        textField.setText(Integer.toString(param.getValue()));
        textField.addActionListener(this::updateField);
        slider = new JSlider(param.min(), param.max(), param.getValue());
        add(slider);
        slider.addChangeListener(this::updateSlider);
    }

    private void updateField(ActionEvent e) {
        textFieldUpdate();
    }

    private void textFieldUpdate() {
        try {
            int val = Integer.parseInt(textField.getText());
            if (val < param.min()) {
                textField.setText(Integer.toString(param.min()));
                wrongDataDialog();
                val = param.min();
            }
            if (val > param.max()) {
                textField.setText(Integer.toString(param.max()));
                wrongDataDialog();
                val = param.max();
            }
            slider.setValue(val);
        } catch (NumberFormatException ex) {
            textField.setText(Integer.toString(param.min()));
            wrongDataDialog();
            slider.setValue(param.min());
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
        dialog.add(new JLabel("Only integer values from " + param.min() + " to " + param.max() + " are available"), BorderLayout.CENTER);
        dialog.pack();
        dialog.setAlwaysOnTop(true);
        dialog.setVisible(true);
    }


    protected void setValue(int val) {
        param.setValue(val);
    }

}
