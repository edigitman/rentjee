package ro.agitman.dto;

/**
 * Created by edi on 4/17/2015.
 */
public enum DotariEnum {

    //-- curatenie
    FAIANTA((1 << 28)),
    GRESIE(1 << 27),
    PARCHET(1 << 26),
    RENOVAT(1 << 25),

    // -- confort termic
    AC(1 << 24),
    APOMETRE(1 << 23),
    CTRGAZ(1 << 22),
    CTTERM(1 << 21),
    REABILITATTERMIC(1 << 20),
    TERMOPAN(1 << 19),

    //  -- bucatarie
    ARAGAZ(1 << 18),
    CUPTOR(1 << 17),
    CUPTORMU(1 << 16),
    FRIGIDER(1 << 15),
    MASRUFE(1 << 14),
    MASUSCATOR(1 << 13),
    MASVASE(1 << 12),

    //  -- living
    CTV(1 << 11),
    CNT(1 << 10),
    TV(1 << 9),

    //   -- baie
    DUS(1 << 8),
    JACUZZI(1 << 7),

    //    -- exterior
    BALCON(1 << 6),
    LIFT(1 << 5),
    PARCARE(1 << 4),
    UM(1 << 3),

    //    -- altele
    ALARMA(1 << 2),
    ANIMALE(1 << 1),
    FUMATORI(1);

    private DotariEnum(long value) {
        this.val = value;
    }

    private long val;

    public long getVal() {
        return val;
    }

    public void setVal(long val) {
        this.val = val;
    }
}
