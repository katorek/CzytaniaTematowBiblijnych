package engine.reader;

import engine.classes.Czytanie;
import engine.classes.Ksiega;
import engine.classes.Temat;
import engine.exception.BrakException;

import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Scanner;

/**
 * Created by Wojciech Jaronski on 25.07.2017.
 */
public class CzytanieReader {

    private static final String filePath = "/czytaniaTematamiPodstawowe.txt";

    private List<String> podstawoweCzytania;
    private HashMap<String, Temat> tematHashMap;
    private static List<Ksiega> ksiegas;
    private List<String> tematyList;

    public CzytanieReader() {
        try {
            Scanner sc = new Scanner(new InputStreamReader(this.getClass().getResourceAsStream("/czytaniaTematamiPodstawowe.txt"), StandardCharsets.UTF_8));
            podstawoweCzytania = new ArrayList<>();
            while(sc.hasNextLine()) podstawoweCzytania.add(sc.nextLine());
//            podstawoweCzytania = Files.readAllLines(Paths.get(this.getClass().getResource(filePath).toURI()));
            ksiegas = new Ksiega().LoadKsiegi();
            tematHashMap = new HashMap<>();
            tematyList = new ArrayList<>(330);

            String temat = "";
            for (String linia : podstawoweCzytania)
                if (linia.contains(";")) dodajDoTematu(temat, linia);
                else temat = dodajTemat(linia);
//            tematHashMap.keySet().forEach(e -> {
//                tematHashMap
//            });
            tematHashMap.forEach((s,t) -> t.sortujCzytania());

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private String dodajTemat(String linia) throws ArrayIndexOutOfBoundsException {
        String[] arr = linia.split(" ");
        int liczby = 5;
        while (!"0123456789".contains(arr[liczby++].charAt(0) + "")) ;
        String out = "";
        for (int i = 4; i < liczby - 1; ++i) {
            out += arr[i] + " ";
        }
        out = out.substring(0, out.length() - 1);
        tematyList.add(out);
        return out;
    }

    private void dodajDoTematu(String temat, String linia) throws BrakException {
        String[] arr = linia.split(";");
        for (int i = 0; i < arr.length - 1; i++) {
            dodajDoTematu(temat, new Czytanie(arr[i]));
        }
    }

    private void dodajDoTematu(String temat, Czytanie czytanie) {
        if (tematHashMap.containsKey(temat)) {
            Temat t = tematHashMap.get(temat);
            t.dodajCzytania(czytanie);
            tematHashMap.put(temat, t);
        } else {
            tematHashMap.put(temat, new Temat(czytanie));
        }
//        Temat t = tematHashMap.get(temat);
    }


    private HashMap<String, Temat> przerob() {
        return null;
    }

    /**
     * Gets ksiegas.
     *
     * @return Value of ksiegas.
     */
    public static List<Ksiega> getKsiegas() {
        return ksiegas;
    }

    /**
     * Gets tematHashMap.
     *
     * @return Value of tematHashMap.
     */
    public HashMap<String, Temat> getTematHashMap() {
        return tematHashMap;
    }

    /**
     * Gets tematyList.
     *
     * @return Value of tematyList.
     */
    public List<String> getTematyList() {
        return tematyList;
    }
}
