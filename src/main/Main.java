import engine.fxml.AppController;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

/**
 * Created by Wojciech Jaronski on 06.07.2017.
 */
public class Main extends Application {
    public static void main(String[] args) {
        try {
//            List<Ksiega> ksiegaList = new Ksiega().LoadKsiegi();
//            ksiegaList.forEach(System.out::println);
//            ksiegaList.forEach(e -> System.out.print("\""+e.getSkrot()+"\","));
//            new CzytanieReader();
            launch(args);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public void start(Stage primaryStage) throws Exception {
//        Parent root = FXMLLoader.load(getClass().getResource("window.fxml"));

        FXMLLoader loader = new FXMLLoader();
        Parent root = FXMLLoader.load(getClass().getResource("window.fxml"));

        AppController appController = loader.getController();


        Scene scene = new Scene(root);

        primaryStage.setTitle("Tematy słownika teologii biblijnej");
        primaryStage.setScene(scene);
        primaryStage.show();
    }
}
