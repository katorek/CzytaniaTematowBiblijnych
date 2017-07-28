package engine.classes;

import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

/**
 * Created by Wojciech Jaronski on 06.07.2017.
 */
public class Ksiega {

    private String skrot;
    private String pelnaNazwa;
    private Testament testament;
    private Rodzaj rodzaj;


    public List<Ksiega> LoadKsiegi() throws Exception {
//        System.out.println(Paths.get(getClass().getResource("/ksiegi.txt").toURI()));

        Scanner sc = new Scanner(new InputStreamReader(this.getClass().getResourceAsStream("/ksiegi.txt"), StandardCharsets.UTF_8));


        List<String> ksiegiStrings = new ArrayList<>();
        while(sc.hasNextLine()) ksiegiStrings.add(sc.nextLine());
        //Files.readAllLines(Paths.get().toURI()));
        List<Ksiega> ksiegas = new ArrayList<>(ksiegiStrings.size());

        ksiegiStrings.forEach(e -> ksiegas.add(new Ksiega(e)));
//        ksiegiStrings.forEach(System.out::println);

        return ksiegas;
    }

    public Ksiega(){}

    @Override
    public int hashCode() {
        return Rodzaj.getId(rodzaj,skrot);
//        return super.hashCode();
    }

    public Ksiega(String wiersz) {
        String[] strs = wiersz.split(";");
        this.skrot = strs[0];
        this.pelnaNazwa = strs[1];
        this.testament = Testament.get(strs[2]);
        this.rodzaj = Rodzaj.getRodzaj(this);
    }

    public String getSkrot() {
        return skrot;
    }

    public String getPelnaNazwa() {
        return pelnaNazwa;
    }

    public Testament getTestament() {
        return testament;
    }

    @Override
    public String toString() {
        return "Ksiega{" +
                "skrot='" + skrot + '\'' +
                ", pelnaNazwa='" + pelnaNazwa + '\'' +
                ", testament=" + testament +
                '}';
    }

    public int getId() {
        return Rodzaj.getId(this);
    }

    public Rodzaj getRodzaj() {
        return rodzaj;
    }

    private enum Testament {
        stary, nowy;

        static public Testament get(String str) {
            return (str.equals("NT")) ? nowy : stary;
        }
    }
}
