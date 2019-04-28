package sample;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.control.Button;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;

import java.awt.*;

public class Main extends Application {

    @Override
    public void start(Stage primaryStage) throws Exception
    {
        /*GridPane gridPane = new GridPane();
        gridPane.setVgap(8);
        gridPane.setHgap(10);

        Button button1 = new Button("pokus");
        GridPane.setConstraints(button1, 0,0);

        Button button2 = new Button("pokus2");
        GridPane.setConstraints(button2, 1,0);

        Parent board = FXMLLoader.load(getClass().getResource("boardGUI.fxml"));
        GridPane.setConstraints(board, 1,1);

        gridPane.getChildren().addAll(button1, button2, board);


        primaryStage.setScene(new Scene(gridPane, 900, 600));
        primaryStage.show();
*/

        Parent root = FXMLLoader.load(getClass().getResource("boardGUI.fxml"));
        primaryStage.setTitle("Hello World");
        primaryStage.setScene(new Scene(root, 800, 800));
        primaryStage.show();

    }


    public static void main(String[] args) {
        launch(args);
    }
}
