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

    private Image blackPawn = new Image("BlackPawn.png");
    private Image blackRook = new Image("BlackRook.png");
    private Image blackBishop = new Image("BlackBishop.png");
    private Image blackKnight = new Image("BlackKnight.png");
    private Image blackKing = new Image("BlackKing.png");
    private Image blackQueen = new Image("BlackQueen.png");


    private Image whitePawn = new Image("WhitePawn.png");
    private Image whiteRook = new Image("WhiteRook.png");
    private Image whiteBishop = new Image("WhiteBishop.png");
    private Image whiteKnight = new Image("WhiteKnight.png");
    private Image whiteKing = new Image("WhiteKing.png");
    private Image whiteQueen = new Image("WhiteQueen.png");


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
        Command cmd = hra.nextMove();

        if(cmd != null)
        {
            if(cmd.getTo() == null)
            {
                ;
                //todo  handle error
            }
            move(convert(cmd, false));
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
        for(int row = 0; row < 8; row++)
        {
            for(int col = 0; col < 8; col++)
            {
                ImageView im = new ImageView();
                grid.add(im,col, row);
                //GridPane.setRowIndex(im, i);
                //GridPane.setColumnIndex(im, j);
                im.setFitHeight(80);
                im.setFitWidth(80);

                switch(row){
                    case 0:
                        switch(col)
                        {
                            case 0:
                            case 7:
                                im.setImage(whiteRook);
                                break;
                            case 1:
                            case 6:
                                im.setImage(whiteKnight);
                                break;
                            case 2:
                            case 5:
                                im.setImage(whiteBishop);
                                break;
                            case 4:
                                im.setImage(whiteQueen);
                                break;
                            case 3:
                                im.setImage(whiteKing);
                                break;
                            default:
                                break;
                        }
                        break;
                    case 1:
                        im.setImage(whitePawn);
                        break;
                    case 2:
                    case 3:
                    case 4:
                    case 5:
                        break;
                    case 6:
                        im.setImage(blackPawn);
                        break;
                    case 7:
                        switch(col)
                        {
                            case 0:
                            case 7:
                                im.setImage(blackRook);
                                break;
                            case 1:
                            case 6:
                                im.setImage(blackKnight);
                                break;
                            case 2:
                            case 5:
                                im.setImage(blackBishop);
                                break;
                            case 4:
                                im.setImage(blackQueen);
                                break;
                            case 3:
                                im.setImage(blackKing);
                                break;
                            default:
                                break;
                        }
                        break;
                    default:
                        break;
                }
            }
        }
        setBasicPositions();
    }
}