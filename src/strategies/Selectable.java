package strategies;

import java.util.List;

public interface Selectable<T>{
    T getSelection(List<T> t);
}
