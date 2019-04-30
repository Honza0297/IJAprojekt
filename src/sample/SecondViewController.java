package sample;

import java.net.URL;
import java.util.ResourceBundle;

import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.*;
import project.GameFactory;
import project.ImpossibleMoveException;
import project.common.Command;
import project.common.Game;
import project.game.Board;


public class SecondViewController implements Initializable {

    private Game hra;
    private Board board;
    //private ChessManager manager;
    @FXML
    private Button nextButton;
    @FXML
    private Button undoButton;
    @FXML
    private Button StartButton;
    @FXML
    private Button StopButton;
    @FXML
    private GridPane grid;

    private Image whitePawn = new Image("whitePawn.png");



    public Node getNodeByRowColumnIndex (final int row, final int column, GridPane gridPane) {
        Node result = null;
        ObservableList<Node> childrens = gridPane.getChildren();
        for (Node node : childrens) {
            Integer myrow = GridPane.getRowIndex(node);
            if(myrow == null)
                myrow = 0;
            Integer mycol = GridPane.getColumnIndex(node);
            if(mycol== null)
                mycol = 0;
            System.out.printf("%d %d\n", myrow, mycol);
            if(mycol == column && myrow == row)
            {
                result = node;
                break;
            }
        }
        return result;
    }

    @FXML
    public void StartGame(ActionEvent e)
    {

    }

    @FXML
    public void StopGame(ActionEvent e)
    {

    }

    @FXML
    public void NextMoveButtonCLicked(ActionEvent e)
    {
        try
        {
            Command cmd = hra.nextMove();

            if (cmd == null)
            {
                return; //todo handle no nextMove
            }
            move(convert(cmd, false));
        }
        catch (ImpossibleMoveException ime)
        {
            //todo handle it
        }
    }

    private Command convert(Command cmd, boolean undo) {
        //todo berry - move command predela na screen commmand - souradnice
        return null;
    }
    private void setBasicPositions()
    {
        //todo berry!
    }
    @FXML
    public void UndoMoveButtonClicked(ActionEvent e)
    {
    }

    @FXML
    public void poschanged(ActionEvent e)
    {
       /*ImageView temp = (ImageView) getNodeByRowColumnIndex(0,0,grid);*/
       // manager.move(new Coordinates(0,0), new Coordinates(1,1));
        //anager.move(1,1);


    }

    @FXML
    public void handleaction(ActionEvent e)
    {
        //manager.move(new Coordinates(1,1), new Coordinates(0,0));
       // manager.move(new Coordinates(0,0), new Coordinates(1,1));
    }

    public void move(Command cmd)
    {
        //TODO BERRY
    }
    @Override
    public void initialize(URL location, ResourceBundle resources)
    {

        board = new Board(8);
        hra = GameFactory.createChessGame(board);
        //manager = new ChessManager(grid, hra, board);
        BackgroundImage bi = new BackgroundImage(new Image("whiteField.png"), BackgroundRepeat.REPEAT, BackgroundRepeat.REPEAT, BackgroundPosition.DEFAULT, BackgroundSize.DEFAULT);
        grid.setBackground(new Background(bi));
        for(int i = 0; i < 8; i++)
        {
            for(int j = 0; j < 8; j++)
            {
                ImageView im = new ImageView();
                grid.add(im,i,j);
                //GridPane.setRowIndex(im, i);
                //GridPane.setColumnIndex(im, j);
                im.setFitHeight(80);
                im.setFitWidth(80);
                im.setImage(whitePawn);
            }
        }
        setBasicPositions();
    }
}