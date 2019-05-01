package sample;

import java.net.URL;
import java.util.ResourceBundle;

import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.ListView;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.*;
import project.GameFactory;
import project.ImpossibleMoveException;
import project.common.Command;
import project.common.Game;
import project.game.Board;


public class SecondViewController implements Initializable {

    private Game game;
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
    @FXML
    private ListView movesListView;

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



    public Node getNodeByRowColumnIndex (final int row, final int column, GridPane gridPane)
    {
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
        DoNextMove();
    }

    private void DoNextMove()
    {
        try
        {
            Command cmd = game.nextMove();
            if (cmd == null)
            {
                System.out.println("dalsi tah neni");
                return; //todo handle no nextMove
            }
            moveGUI(cmd);
           // moveGUI(convert(cmd, false));
        }
        catch (ImpossibleMoveException ime)
        {
            System.out.println("neemozny tah");
            //todo handle it
        }
    }

    private Command convert(Command cmd, boolean undo) {
        //todo berry - moveGUI command predela na screen commmand - souradnice
        return null;
    }
    private void setBasicPositions()
    {
        for(int row = 0; row < 8; row++)
        {
            for(int col = 0; col < 8; col++)
            {
                ImageView im = new ImageView();
                grid.add(im,col, row);
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
    }

    @FXML
    public void UndoMoveButtonClicked(ActionEvent e)
    {
        DoUndoMove();
    }

    @FXML
    public void MoveFromListViewSelectedHandle(ActionEvent e)
    {
        int indexToGo = 2 * movesListView.getSelectionModel().getSelectedIndex();
        System.out.println("vybrano: " + Integer.toString(indexToGo)); //smazat

        if(game.getActualMoveIndex() < indexToGo)
        {
            while(game.getActualMoveIndex() < indexToGo)
                DoNextMove();
        }
        else
        {
            while(game.getActualMoveIndex() > indexToGo)
                DoUndoMove();
        }
    }

    private void DoUndoMove()
    {
        //todo berry
    }

    public void moveGUI(Command cmd)
    {
        ImageView nodeTo = (ImageView) getNodeByRowColumnIndex(cmd.getTo().getRow()-1, cmd.getTo().getCol()-1, grid);
        ImageView nodeFrom = (ImageView) getNodeByRowColumnIndex(cmd.getFrom().getRow()-1, cmd.getFrom().getCol()-1, grid);
        nodeTo.setImage(nodeFrom.getImage());
        nodeFrom.setImage(null);

        //TODO BERRY

        SetActualMoveOnListView();
    }
    @Override
    public void initialize(URL location, ResourceBundle resources)
    {
        board = new Board(8);
        game = GameFactory.createChessGame(board);
        //manager = new ChessManager(grid, game, board);
        BackgroundImage bi = new BackgroundImage(new Image("whiteField.png"), BackgroundRepeat.REPEAT, BackgroundRepeat.REPEAT, BackgroundPosition.DEFAULT, BackgroundSize.DEFAULT);
        grid.setBackground(new Background(bi));

        setBasicPositions();

        movesListView.setItems(game.getNotation());
        movesListView.getSelectionModel().select(0);
    }

    private void SetActualMoveOnListView()
    {
        movesListView.getSelectionModel().select(game.getActualMoveIndex()/2);
    }
}