package engine.classes;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;

/**
 * Created by Wojciech Jaronski on 30.06.2017.
 */
public class Temat {

    private HashMap<Rodzaj, List<Czytanie>> czytaniaRodzajami;

    public Temat(Czytanie czytanie) {
        czytaniaRodzajami = new HashMap<>();
        dodajCzytania(czytanie);
    }

    public void dodajCzytania(Rodzaj rodzaj, List<Czytanie> czytania) {
        if (czytaniaRodzajami.containsKey(rodzaj)) dodajCzytaniaDoMapy(rodzaj, czytania);
        else czytaniaRodzajami.put(rodzaj, czytania);
    }

    private void dodajCzytaniaDoMapy(Rodzaj rodzaj, List<Czytanie> czytania) {
        List<Czytanie> czytanieSet = czytaniaRodzajami.get(rodzaj);
        czytanieSet.addAll(czytania);
        czytaniaRodzajami.put(rodzaj, czytanieSet);
    }

    public List<Czytanie> getCzytania(Rodzaj rodzaj) {
        if (!czytaniaRodzajami.containsKey(rodzaj)) return new ArrayList<Czytanie>();
        return czytaniaRodzajami.get(rodzaj);
    }

    public void dodajCzytania(Czytanie czytanie) {
        List<Czytanie> czytanies = new ArrayList<>();
        czytanies.add(czytanie);
        dodajCzytania(Rodzaj.getRodzaj(czytanie.getKsiega()), czytanies);
    }

    @Override
    public String toString() {
        String out = "";
        for (Rodzaj rodzaj : Rodzaj.values()) {
            if(!czytaniaRodzajami.containsKey(rodzaj)) continue;
            out += rodzaj + ": ";
            for (Czytanie czytanie : czytaniaRodzajami.get(rodzaj)) {
                out += czytanie + " | ";
            }
            out += "\n";
        }
        return out;
    }

    public String toString2() {
        String out = "";
        for (Rodzaj rodzaj : Rodzaj.values()) {
            if(!czytaniaRodzajami.containsKey(rodzaj)) continue;
            out += rodzaj.getName()+"\n";
            for(Czytanie czytanie: czytaniaRodzajami.get(rodzaj)){
                out += czytanie.toString() + " | ";
            }
            out = out.substring(0,out.length()-3) + "\n\n";
        }
        return out;
    }

    public void sortujCzytania() {
        for (Rodzaj rodzaj : Rodzaj.values()) {
            if(!czytaniaRodzajami.containsKey(rodzaj)) continue;
            Collections.sort(czytaniaRodzajami.get(rodzaj));
//            Set<Czytanie> czytanieSet = czytaniaRodzajami.get(rodzaj);
//            Collections.sort(czytanieSet);
        }
    }
}
