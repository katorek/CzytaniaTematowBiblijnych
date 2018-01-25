package engine.classes;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Created by Wojciech Jaronski on 30.06.2017.
 */
public enum Rodzaj {
    HISTORYCZNA("Historyczne  "), PROROCKA("Prorockie  "), EWANGELIA("Ewangelie  "), POZAEWANGELICZNA("Pozaewangeliczne  ");
    private static List<String> ewan, poza, hist, pror;



    static {
        hist = new ArrayList<>(Arrays.asList("Rdz", "Wj", "Kpł", "Lb", "Pwt", "Joz", "Sdz", "Rt", "1 Sm", "2 Sm", "1 Krl",
                "2 Krl", "1 Krn", "2 Krn", "Ezd", "Ne", "Tb", "Jdt", "Est", "1 Mch", "2 Mch", "Hi", "Ps", "Prz", "Koh", "Pnp", "Mdr", "Syr"));
        pror = new ArrayList<>(Arrays.asList("Iz", "Jr", "Ba", "Ez", "Dn", "Oz", "Jl",
                "Am", "Ab", "Jon", "Mi", "Na", "Ha", "So", "Ag", "Za", "Ml", "Lm"));
        ewan = new ArrayList<>(Arrays.asList("Mt", "Mk", "Łk", "J"));
        poza = new ArrayList<>(Arrays.asList("Dz", "Rz", "1 Kor", "2 Kor", "Ga", "Ef", "Flp", "Kol", "1 Tes", "2 Tes",
                "1 Tm", "2 Tm", "Tt", "Flm", "Hbr", "Jk", "1 P", "2 P", "1 J", "2 J", "3 J", "Jud", "Ap"));
//        hist = {"Mt","Mk","Łk","J"};

    }

    private String name;

    public static List<List<String>> getRodzajeZCzytaniami() {
        return Arrays.asList(ewan, poza, hist, pror);
    }

    public static int getId(Rodzaj r, String s) {
        switch (r) {
            case HISTORYCZNA:
                return hist.indexOf(s);
            case PROROCKA:
                return pror.indexOf(s);
            case EWANGELIA:
                return ewan.indexOf(s);
            case POZAEWANGELICZNA:
                return poza.indexOf(s);
            default:
                return 0;
        }
    }

    public static Rodzaj getRodzaj(Ksiega ksiega) {
        if (ewan.contains(ksiega.getSkrot())) return EWANGELIA;
        if (poza.contains(ksiega.getSkrot())) return POZAEWANGELICZNA;
        if (pror.contains(ksiega.getSkrot())) return PROROCKA;
        if (hist.contains(ksiega.getSkrot())) return HISTORYCZNA;
        return null;
    }

    Rodzaj(String name){
        this.name = name;
    }

    public static int getId(Ksiega ksiega) {
        return getId(ksiega.getRodzaj(),ksiega.getSkrot());
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
