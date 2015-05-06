package ro.agitman.dto;

/**
 * Created by edi on 4/17/2015.
 */
public enum DotariEnum {

    //-- curatenie
    FAIANTA(1 << 28,"Faianta", 1),
    GRESIE(1 << 27, "Gresie", 1),
    PARCHET(1 << 26, "Parchet", 1),
    RENOVAT(1 << 25, "Renovat recent", 1),

    // -- confort termic
    AC(1 << 24, "A.C.", 2),
    APOMETRE(1 << 23, "Apometre", 2),
    CTRGAZ(1 << 22, "Centrala Gaz", 2),
    CTTERM(1 << 21, "Centrala Termica", 2),
    REABILITATTERMIC(1 << 20, "Reabil. Term.", 2),
    TERMOPAN(1 << 19, "Termopane", 2),

    //  -- bucatarie
    ARAGAZ(1 << 18, "Aragaz", 3),
    CUPTOR(1 << 17, "Cuptor", 3),
    CUPTORMU(1 << 16, "Cuptor microunde", 3),
    FRIGIDER(1 << 15, "Frigider", 3),
    MASRUFE(1 << 14, "Masina spalat rufe", 3),
    MASUSCATOR(1 << 13, "Uscator rufe", 3),
    MASVASE(1 << 12, "Masina spalat vase", 3),

    //  -- living
    CTV(1 << 11, "Cablu TV", 4),
    CNT(1 << 10, "Cablu Internet", 4),
    TV(1 << 9, "Televizor", 4),

    //   -- baie
    DUS(1 << 8, "Dus", 2),
    JACUZZI(1 << 7, "Jacuzzi", 2),

    //    -- exterior
    BALCON(1 << 6, "Balcon", 5),
    LIFT(1 << 5, "Lift", 5),
    PARCARE(1 << 4, "Parcare", 5),
    UM(1 << 3, "Usa Metal", 5),

    //    -- altele
    ALARMA(1 << 2, "Alarma", 6),
    ANIMALE(1 << 1, "Permis animale", 6),
    FUMATORI(1, "Permis fumatori", 6);

    private long val;
    private String hName;
    private int cat;

    private DotariEnum(long value, String name, int cat) {
        this.val = value;
        this.hName = name;
        this.cat = cat;
    }

    public long getVal() {
        return val;
    }

    public String gethName() {
        return hName;
    }

    public int getCat() {
        return cat;
    }
}
