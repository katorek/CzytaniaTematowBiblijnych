package engine.classes;

import engine.exception.BrakCzytaniaException;
import engine.exception.BrakException;
import engine.exception.BrakKsiegiException;
import engine.reader.CzytanieReader;

import java.util.List;

/**
 * Created by Wojciech Jaronski on 25.07.2017.
 */
public class Czytanie implements Comparable{

    private Ksiega ksiega;
    private String numer;

    public Czytanie(String s) throws BrakException {
        //todo
        int width = 2;
        while (!znajdz(width, s) && width < s.length()){
            ++width;
        }
        if(width >=s.length()) throw new BrakCzytaniaException(s);
        int startpos = 0;
        while(s.charAt(startpos)==' ') startpos+=1;
        String subStr = s.substring(startpos,startpos+width-1);
        for (Ksiega ksiega1 : CzytanieReader.getKsiegas()) {
            if (ksiega1.getSkrot().equals(subStr)) {
                ksiega = ksiega1;
//                System.out.println("Ksiega: " + ksiega);
                break;
            }
        }
        if(ksiega == null) throw new BrakKsiegiException(subStr);
        numer = s.substring(startpos+width);
//        System.out.println("numer = " + numer);
//        ksiega = CzytanieReader.getKsiegas().
    }

    private boolean znajdz(int width, String s) {
        int startpos = 0;
        while(s.charAt(startpos)==' ') startpos+=1;
        String subStr = s.substring(startpos,startpos+width-1);
        for (List<String> strings : Rodzaj.getRodzajeZCzytaniami()) {
//            if(strings.contains(subStr) jestNaLiscie(strings,subStr)) return true;
            if (strings.contains(subStr))return true;
        }
        return false;
    }

    private boolean jestNaLiscie(List<String> strings, String subStr) {
        for (String string : strings) {
            if(string.equals(subStr))return true;
        }
        return false;
    }

    /**
     * Gets ksiega.
     *
     * @return Value of ksiega.
     */
    public Ksiega getKsiega() {
        return ksiega;
    }

    /**
     * Sets new ksiega.
     *
     * @param ksiega New value of ksiega.
     */
    public void setKsiega(Ksiega ksiega) {
        this.ksiega = ksiega;
    }

    /**
     * Gets numer.
     *
     * @return Value of numer.
     */
    public String getNumer() {
        return numer;
    }

    /**
     * Sets new numer.
     *
     * @param numer New value of numer.
     */
    public void setNumer(String numer) {
        this.numer = numer;
    }

    @Override
    public String toString() {
        return ksiega.getSkrot() +" "+ numer;
    }

    @Override
    public int compareTo(Object o) {
        return Integer.compare(ksiega.getId(), ((Czytanie)o).getKsiega().getId() );
    }
}
