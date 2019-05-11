package sample;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.FileChooser;
import javafx.stage.Stage;

import java.io.File;
//todo denny zabit autoplay vlakna pri ukonceni appky
//todo berry po zabiti krale a snaze hrat, zustanou figurky oznacene
//todo denny nacteni notace s +, #, x, atd.. + notace ktera konci tahem bileho
//todo nejaky problem s undo a redo
//todo sachovnice ma mit popisky radku a sloupku
//todo berry & denny zmena figurky - pesec na konci sachovnice (rosada pokud zbyde cas - berry)
//todo denny opravit export bez user tahu
//todo zakazat tlacitka ve spravnych chvilich
//todo misto konzoloveho vypisu objevit javovske messageboxy
//todo berry? vycentrovat figurky
//todo berry? zvyrazneni kliknutych policek
//todo dvakrat kliknuto na to same = zrusit odznaceni (ted hazi hlasku)

public class Main extends Application {

    public static void main(String [] args) {
        launch(args);
    }
    @Override
    public void start(Stage primaryStage) throws Exception {

        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(getClass().getResource("FirstView.fxml"));
        FirstViewController firstViewController = new FirstViewController();
        loader.setController(firstViewController);
        Parent parent = loader.load();
        Scene scene = new Scene(parent);
        primaryStage.setScene(scene);
        primaryStage.show();
    }



}