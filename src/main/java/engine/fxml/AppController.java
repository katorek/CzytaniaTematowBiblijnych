package engine.fxml;

import engine.classes.Czytanie;
import engine.classes.Rodzaj;
import engine.classes.Temat;
import engine.reader.CzytanieReader;
import javafx.application.Platform;
import javafx.collections.FXCollections;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ListView;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;

import java.net.URL;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.ResourceBundle;
import java.util.stream.Collectors;

import static engine.text.StringSimilarity.similarity;

/**
 * Created by Wojciech Jaronski on 26.07.2017.
 */
public class AppController implements Initializable {

    private HashMap<String, Temat> tematyHashMap;
    private List<String> tematyList;
    private List<String> podrecznaList;
    private HashMap<Rodzaj, List<Czytanie>> podreczneRodzajeCzytania;
//    private

    @FXML
    private TextArea czytaniaTextArea;

    @FXML
    private TextField tematTextField;

    @FXML
    private ListView<String> tematyListView, podrecznaListView;

    @FXML
    private Button addButton, delButton, clearButton, showButton;

    @FXML
    private void aktualizujListe() {
        if (tematTextField.getText().length() > 0) {
            String text = tematTextField.getText();
            List<String> temp =
                    tematyList
                            .stream()
                            .sorted((s1, s2) -> porownaj(s1, s2, text))
                            .collect(Collectors.toList());
            Collections.reverse(temp);
            tematyListView.setItems(FXCollections.observableList(temp));
        } else {
            tematyListView.setItems(FXCollections.observableList(tematyList));
        }
        Platform.runLater(() -> tematyListView.scrollTo(0));
    }

    @FXML
    private void show() {
        String out = "tematy: ";
        if (!podrecznaList.isEmpty()) {
            for (String s : podrecznaList) out += s + ",";
            out = out.substring(0, out.length() - 1) + "\n\n";

            //todo dodac wszystkie czytania z kazdego tematu do 1 z 4 list, posortowac i wyswietlic
//            List<List<Rodzaj>> rodzaje = new ArrayList<ArrayList<Czytanie>>(4);

            for (Rodzaj rodzaj : Rodzaj.values()) {
                List<Czytanie> temp = podreczneRodzajeCzytania.get(rodzaj);
                out += rodzaj.getName() + "\n";
                for (String s : podrecznaList) {
                    for (Czytanie czytanie : tematyHashMap.get(s).getCzytania(rodzaj)) {
//                        out += czytanie.toString() + " | ";
                        if (!temp.contains(czytanie)) temp.add(czytanie);
                    }
//                    out = out.substring(0, out.length() - 2);
                }
                Collections.sort(temp);
                podreczneRodzajeCzytania.put(rodzaj, temp);


                for (Czytanie czytanie : temp) {
                    out += czytanie.toString() + " | ";
                }
                out = out.substring(0, out.length() - 2) + "\n\n";
            }


//            out
//            out = out.substring(0, out.length() - 2);
        }else{
            out += "\n";
            for (Rodzaj rodzaj : Rodzaj.values()) {
                out += rodzaj.getName() + "\n\n";
            }
        }
        czytaniaTextArea.setText(out);
    }

    @FXML
    private void add() {
        String temat = tematyListView.getSelectionModel().getSelectedItem();

//        podreczneRodzajeCzytania.get()
        if (!podrecznaList.contains(temat))
            podrecznaList.add(temat);
        update();
    }

    @FXML
    private void del() {
        podrecznaList.remove(podrecznaListView.getSelectionModel().getSelectedItem());
        podreczneRodzajeCzytania.forEach((e, s) -> s.clear());
        update();
        show();
    }

    @FXML
    private void clear() {
        podrecznaList.clear();
        podreczneRodzajeCzytania.forEach((e, s) -> s.clear());
//        while (podrecznaList.size() > 0) podrecznaList.remove(0);
        update();
        show();
    }

    private void update() {
        Platform.runLater(() -> podrecznaListView.setItems(FXCollections.observableList(podrecznaList)));
    }

    private int porownaj(String s1, String s2, String text) {
        return Double.compare(similarity(s1, text), similarity(s2, text));
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        CzytanieReader reader = new CzytanieReader();
        tematyHashMap = reader.getTematHashMap();
        tematyList = reader.getTematyList();

        podreczneRodzajeCzytania = new HashMap<>(4);
        Arrays.stream(Rodzaj.values()).forEach(rodzaj -> podreczneRodzajeCzytania.put(rodzaj, new ArrayList<>()));

        podrecznaList = new LinkedList<>();
        podrecznaListView.setItems(FXCollections.observableList(podrecznaList));
        podrecznaListView.setOnMousePressed(e ->
                czytaniaTextArea.setText(text(podrecznaListView)));

        tematyListView.setItems(FXCollections.observableList(tematyList));
        tematyListView.setOnMousePressed(e ->
                czytaniaTextArea.setText(text(tematyListView)));


        czytaniaTextArea.setWrapText(true);

        tematTextField.textProperty().addListener((ov, oldValue, newValue) -> {
            tematTextField.setText(newValue.toUpperCase());
            aktualizujListe();
        });

    }

    private String text(ListView<String> list) {
        if(list==null || list.getItems().size()<1 || list.getSelectionModel().getSelectedItem() == null) return "";
        String temat = list.getSelectionModel().getSelectedItem();
        return temat + "\n\n" + tematyHashMap.get(temat).toString2();
    }

}
