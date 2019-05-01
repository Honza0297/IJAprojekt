package sample;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
//TODO DENNY Opravit, aby se do ineergamenotation nezapisovaly nemozne tahy (napr tah pesce do strany)
//todo denny otevirani vstupniho souboru
//todo automaticke prehravani
//todo berry & denny zmena figurky - pesec na konci sachovnice (rosada pokud zbyde cas - berry)
//todo denny opravit export bez user tahu
//todo denny? ukonceni hry
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