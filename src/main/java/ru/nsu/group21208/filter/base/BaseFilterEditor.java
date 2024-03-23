package ru.nsu.group21208.filter.base;

import org.jetbrains.annotations.Nullable;
import ru.nsu.group21208.filter.FilterEditor;

import javax.swing.*;
import java.util.HashMap;
import java.util.Map;

public class BaseFilterEditor extends JPanel implements FilterEditor<BaseFilterParams> {

    private final AbstractParam<?>[] params;

    public BaseFilterEditor(AbstractParam<?>... params) {
        setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
        this.params = params;
        for (AbstractParam<?> param : params) {
            add(param.editorComponent());
        }
    }

    @Override
    public @Nullable JComponent parameterEditor() {
        return this;
    }

    @Override
    public BaseFilterParams build() {
        Map<String, Object> values = new HashMap<>();
        for (AbstractParam<?> p : params) {
            values.put(p.name(), p.getValue());
        }
        return new BaseFilterParams(values);
    }
}
