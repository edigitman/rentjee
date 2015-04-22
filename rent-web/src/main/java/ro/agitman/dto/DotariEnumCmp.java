package ro.agitman.dto;

import java.util.Comparator;

/**
 * Created by AlexandruG on 4/22/2015.
 */
public class DotariEnumCmp implements Comparator<DotariEnum> {
    @Override
    public int compare(DotariEnum o1, DotariEnum o2) {
        if (o1.ordinal() < o2.ordinal()) {
            return -1;
        } else {
            if (o1.ordinal() > o2.ordinal()) {
                return 1;
            } else {
                return 0;
            }
        }
    }
}
