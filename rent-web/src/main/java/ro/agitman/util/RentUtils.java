package ro.agitman.util;

import ro.agitman.dto.DotariEnum;
import ro.agitman.model.Advert;

import java.util.EnumSet;

/**
 * Created by AlexandruG on 5/11/2015.
 */
public class RentUtils {

    public static EnumSet<DotariEnum> loadDotari(Advert advert) {
        EnumSet<DotariEnum> result = EnumSet.noneOf(DotariEnum.class);
        long dot = advert.getDotari();
        if (dot != 0) {
            for (DotariEnum d : DotariEnum.values()) {
                if ((dot & d.getVal()) != 0) {
                    result.add(d);
                }
            }
        }
        return result;
    }

    public static long criptDotari(EnumSet<DotariEnum> dotari) {
        long result = 1 << 2;
        for (DotariEnum d : DotariEnum.values()) {
            result = (result << 1) | (dotari.contains(d) ? 1 : 0);
        }
        return result;
    }
}
