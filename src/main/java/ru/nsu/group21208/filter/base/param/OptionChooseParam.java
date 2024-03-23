package ru.nsu.group21208.filter.base.param;

import org.jetbrains.annotations.NotNull;
import ru.nsu.group21208.filter.base.AbstractParam;
import ru.nsu.group21208.filter.base.editor.OptionChooseEditor;

import javax.swing.*;
import java.util.ArrayList;
import java.util.List;

public class OptionChooseParam extends AbstractParam<Object> {

    private final OptionChooseEditor editor;

    public OptionChooseParam(String name, @NotNull Object value, List<Object> values, List<String> names) {
        super(name, value);
        List<OptionChooseEditor.Option> options = new ArrayList<>();
        int selectedNum = -1;
        for (int i = 0; i < values.size(); i++) {
            int finalI = i;
            options.add(new OptionChooseEditor.Option(names.get(i)) {
                @Override
                public void select() {
                    setValue(values.get(finalI));
                }
            });
            if (values.get(finalI) == value) {
                selectedNum = i;
            }
        }
        if (selectedNum == -1) {
            throw new IllegalArgumentException("Can't find value in available options");
        }
        editor = new OptionChooseEditor(options.toArray(new OptionChooseEditor.Option[0]));
        editor.setSelected(selectedNum);
    }

    @Override
    public JComponent editorComponent() {
        return editor;
    }
}
