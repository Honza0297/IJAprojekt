package sample;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;

import javafx.fxml.Initializable;
import javafx.scene.image.ImageView;
import javafx.scene.image.Image;
import javafx.scene.layout.*;

import java.net.URL;
import java.util.ResourceBundle;

public class Controller implements Initializable
{

    @FXML
    private ImageView obr1;
    @FXML
    private ImageView black;

    @FXML
    private ImageView white;

    @FXML
    private GridPane grid;
/*
    @FXML
    public void initialize()
    {
        Image image = new Image("WhitePawn.png");
        ivnn.setImage(image);
    }
    */
    @FXML
    public void handleButtonClick(ActionEvent actionEvent)
    {
        //Image image = new Image("WhitePawn.png");

    }

    @Override
    public void initialize(URL location, ResourceBundle resources)
    {
        System.out.println("blabla");
        white.setImage(new Image("WhitePawn.png"));
        black.setImage(new Image("WhitePawn.png"));
        BackgroundImage bi = new BackgroundImage(new Image("whiteField.png"), BackgroundRepeat.REPEAT, BackgroundRepeat.REPEAT, BackgroundPosition.DEFAULT, BackgroundSize.DEFAULT);
        grid.setBackground(new Background(bi));
        obr1.setImage(new Image("WhitePawn.png"));
        GridPane.setColumnIndex(obr1, 5);

    }
}
