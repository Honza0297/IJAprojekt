package sample;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;

import javafx.fxml.Initializable;
import javafx.scene.image.ImageView;
import javafx.scene.image.Image;
import javafx.scene.layout.*;

import java.awt.*;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
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

    @FXML
    private Button pokusbutton;
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
        GridPane.setColumnIndex(obr1, 7);
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



        List<ImageView> figures = new ArrayList<>();

        for(int i = 0; i < 8; i++)
        {
            ImageView image = new ImageView();
            image.setImage(new Image("WhitePawn.png"));
            image.setPreserveRatio(true);
            image.setFitHeight(100);
            grid.add(image, i,6);
            figures.add(image);
        }


    }
}
