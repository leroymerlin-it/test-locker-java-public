package it.leroymerlin.locker.domain;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Size {

    private final List<Integer> dim;

    public Size(Integer length, Integer width, Integer height) {
        dim = new ArrayList<>();
        dim.add(length);
        dim.add(width);
        dim.add(height);
        Collections.sort(dim);
    }

    public static Size of(Integer length, Integer width, Integer height) {
        return new Size(length, width, height);
    }

    public List<Integer> getDim() {
        return new ArrayList<>(dim);
    }

    public boolean couldContain(Size requestedSize) {
        for (int i = 0; i < 3; ++i) {
            if (dim.get(i) < requestedSize.dim.get(i))
                return false;
        }
        return true;
    }

}
