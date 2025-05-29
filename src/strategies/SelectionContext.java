package strategies;

import java.util.List;

public class SelectionContext<T> {
    private Selectable<T> selectionStrategy;

    public void setSelectionStrategy(Selectable<T> selectionStrategy)
    {this.selectionStrategy = selectionStrategy;}
    public T executeSelection(List<T> items) {
        if (selectionStrategy == null) throw new IllegalStateException("немає!");
        return selectionStrategy.getSelection(items);
    }
}
