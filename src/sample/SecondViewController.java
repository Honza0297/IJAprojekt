package sample;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.ListView;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.*;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import project.GameFactory;
import project.ImpossibleMoveException;
import project.common.Command;
import project.common.Field;
import project.common.Figure;
import project.common.Game;
import project.game.Board;
import project.game.InnerMoveNotation;
import project.game.figures.*;

public class SecondViewController implements Initializable {

    private int userOn;
    private Game game;
    private Board board;

    //only for user's moves
    private Field from;
    private Image fromImage;
    private Field to;
    private Image toImage;

    @FXML
    private Button nextButton;
    @FXML
    private Button undoButton;
    @FXML
    private Button redoButton;
    @FXML
    private Button backButton;
    @FXML
    private Button StartButton;
    @FXML
    private Button StopButton;
    @FXML
    private Button jumpButton;
    @FXML
    private Button exportButton;

    @FXML
    private GridPane grid;
    @FXML
    private ListView movesListView;

    private Image blackPawn = new Image("file:lib/BlackPawn.png");
    private Image blackRook = new Image("file:lib/BlackRook.png");
    private Image blackBishop = new Image("file:lib/BlackBishop.png");
    private Image blackKnight = new Image("file:lib/BlackKnight.png");
    private Image blackKing = new Image("file:lib/BlackKing.png");
    private Image blackQueen = new Image("file:lib/BlackQueen.png");

    private Image whitePawn = new Image("file:lib/WhitePawn.png");

    private Image whiteRook = new Image("file:lib/WhiteRook.png");
    private Image whiteBishop = new Image("file:lib/WhiteBishop.png");
    private Image whiteKnight = new Image("file:lib/WhiteKnight.png");
    private Image whiteKing = new Image("file:lib/WhiteKing.png");
    private Image whiteQueen = new Image("file:lib/WhiteQueen.png");

    private Image transparent = new Image("file:lib/transparentImage.png");
    private Image transparentSelected = new Image("file:lib/transparentImageSelected.png");

    private Image blackPawnSelected = new Image("file:lib/BlackPawnSelected.png");
    private Image blackRookSelected = new Image("file:lib/BlackRookSelected.png");
    private Image blackBishopSelected = new Image("file:lib/BlackBishopSelected.png");
    private Image blackKnightSelected = new Image("file:lib/BlackKnightSelected.png");
    private Image blackKingSelected = new Image("file:lib/BlackKingSelected.png");
    private Image blackQueenSelected = new Image("file:lib/BlackQueenSelected.png");

    private Image whitePawnSelected = new Image("file:lib/WhitePawnSelected.png");
    private Image whiteRookSelected = new Image("file:lib/WhiteRookSelected.png");
    private Image whiteBishopSelected = new Image("file:lib/WhiteBishopSelected.png");
    private Image whiteKnightSelected = new Image("file:lib/WhiteKnightSelected.png");
    private Image whiteKingSelected = new Image("file:lib/WhiteKingSelected.png");
    private Image whiteQueenSelected = new Image("file:lib/WhiteQueenSelected.png");


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
            if(mycol == column && myrow == row)
            {
                result = node;
                break;
            }
        }
        return result;
    }

    @FXML
    public void ReadNotationClicked(ActionEvent e)
    {
        File file = showFileChooser(false);
        if(file != null)
            setGameFromNotation(file);
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
    public void NextMoveButtonClicked(ActionEvent e)
    {
        DoNextMove();
    }

    EventHandler<MouseEvent> imageClickedEventHandler = new EventHandler<MouseEvent>()
    {
        @Override
        public void handle(MouseEvent event)
        {
            ImageView clickedImage = (ImageView) event.getSource();
            Field clickedField = board.getField(GridPane.getColumnIndex(clickedImage)+1, GridPane.getRowIndex(clickedImage)+1);
            if(from == null) //zvolena teprve prvni souradnice, jeste se bude cekat na druhou
            {
                from = clickedField;
                fromImage = clickedImage.getImage();
                clickedImage.setImage(getImageSelectedByClass(clickedField.get()));
            }
            else
            {
                if(from == clickedField)
                {
                    System.err.println("from == clickedfield");
                    clickedImage.setImage(fromImage);
                    toImage = null;
                    fromImage = null;
                    to = null;
                    from = null;
                    return;
                }

                to = clickedField;
                TryUsersMove();
                toImage = null;
                fromImage = null;
                to = null;
                from = null;
            }
        }
    };


    private void TryUsersMove()
    {
        try
        {
            if(from.get() == null)
                throw new ImpossibleMoveException(game.getActualMoveIndex());

            InnerMoveNotation moveNotation = new InnerMoveNotation(from, to, from.get().getType());
            moveGUI(game.doUsersMove(moveNotation), false);
            userOn++;
        }
        catch (ImpossibleMoveException ime)
        {
            System.out.println("nemozny tah");
            ((ImageView)getNodeByRowColumnIndex(from.getRow()-1, from.getCol()-1, grid)).setImage(fromImage);
            //todo handle it
        }
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
            moveGUI(cmd, false);
        }
        catch (ImpossibleMoveException ime)
        {
            System.out.println("neemozny tah");
            //todo handle it
        }
    }

    private void setBasicPositions()
    {
        for(int row = 0; row < 8; row++)
        {
            for(int col = 0; col < 8; col++)
            {
                ImageView im = new ImageView();
                grid.add(im,col, row);
                im.setPreserveRatio(true);
                im.setFitHeight(100);
                im.setPickOnBounds(true);
                im.setImage(transparent);
                im.setOnMouseClicked(imageClickedEventHandler);

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
    public void BackMoveButtonClicked(ActionEvent e)
    {
        DoBackMove();
    }


    @FXML
    public void RedoMoveButtonClicked(ActionEvent e)
    {
        DoRedoMove();
    }

    @FXML
    public void ExportButtonClicked(ActionEvent e)
    {
        File file = showFileChooser(true);
        if(file == null)
            return;

        if(!game.getParser().SaveGameNotation(game.getGameNotation(), file.toString()))
        {
            System.err.println("Nebylo mozno vyexportovat notaci. AKA Nepovedl se zapis."); //todo handle
        }
    }

    /**
     * Zobrazi okno pro otevreni/ulozeni souboru s notaci
     * @param save pokud true, potom se otevre okno pro ulozeni, pokud false, otevre se okno pro vyber souboru
     * @return null pokud byla akce zrusena, jinak File
     */
    private File showFileChooser(boolean save)
    {
        Stage stage = new Stage();
        final FileChooser fileChooser = new FileChooser();
        if(save)
        {
            fileChooser.getExtensionFilters().add(new FileChooser.ExtensionFilter("TXT files (*.txt)", "*.txt"));
            return fileChooser.showSaveDialog(stage);
        }
        else
            return fileChooser.showOpenDialog(stage);
    }


    private void DoRedoMove()
    {
        if(game.canUndo())
        {
            Command cmd = game.redoMove();
            if(cmd != null)
            {
                System.err.println("Stack prazdny");
                moveGUI(cmd, false);
            }
        }
    }

    private void DoBackMove()
    {
        Command cmd = game.backMove();
        if(cmd == null)
            return; //todo vyhodit messagebox krok zpet neni
        moveGUI(cmd, true);
    }

    private void DoUndoMove()
    {
        System.err.printf("%d\n", userOn);
        if(game.canUndo())
        {
            if(userOn != 0)
            {
                Command cmd = game.undoMove();
                moveGUI(cmd, true);
                userOn--;
            }
            else
            {
                //todo handle it
                System.err.printf("Undo pres limit user tahu!"); //smazat
            }
        }
    }

    @FXML
    public void MoveFromListViewSelectedHandle(ActionEvent e)
    {
        int indexToGo = 2 * movesListView.getSelectionModel().getSelectedIndex();
        //System.out.println("vybrano: " + Integer.toString(indexToGo)); //smazat

        if(game.getActualMoveIndex() < indexToGo)
        {
            while(game.getActualMoveIndex() < indexToGo)
                DoNextMove();
        }
        else
        {
            while(game.getActualMoveIndex() > indexToGo)
            {
                DoBackMove();
            }
        }
    }


    public void moveGUI(Command cmd, boolean isUndo)///note test it!!!
    {
        if(isUndo)
        {
            //nodeFrom is got from cmd.getTo() and vice versa
            ImageView nodeFrom = (ImageView) getNodeByRowColumnIndex(cmd.getTo().getRow()-1, cmd.getTo().getCol()-1, grid);
            ImageView nodeTo = (ImageView) getNodeByRowColumnIndex(cmd.getFrom().getRow()-1, cmd.getFrom().getCol()-1, grid);
            nodeTo.setImage(getImageByClass(cmd.getFrom().get()));
            //nodeTo.setImage(nodeFrom.getImage());
            Figure wasKilled = cmd.getWasKilled();
                if(wasKilled != null)
                {
                    nodeFrom.setImage(getImageByClass(wasKilled));
                }
                else
                {
                    nodeFrom.setImage(transparent);
                }
        }
        else
        {
            ImageView nodeTo = (ImageView) getNodeByRowColumnIndex(cmd.getTo().getRow()-1, cmd.getTo().getCol()-1, grid);
            ImageView nodeFrom = (ImageView) getNodeByRowColumnIndex(cmd.getFrom().getRow()-1, cmd.getFrom().getCol()-1, grid);
            nodeTo.setImage(getImageByClass(cmd.getTo().get()));
            //nodeTo.setImage(nodeFrom.getImage());
            nodeFrom.setImage(transparent);
        }

        SetActualMoveOnListView();
    }

    private Image getImageSelectedByClass(Figure figure) {
        Image returnImage = null;
        if(figure == null)
            return returnImage;
        if(figure.getClass() == Pawn.class)
        {
            returnImage = figure.isWhite() ? whitePawnSelected : blackPawnSelected;
        }
        else if(figure.getClass() == Rook.class)
        {
            returnImage = figure.isWhite() ? whiteRookSelected : blackRookSelected;
        }
        else if(figure.getClass() == Knight.class)
        {
            returnImage = figure.isWhite() ? whiteKnightSelected : blackKnightSelected;
        }
        else if(figure.getClass() == Bishop.class)
        {
            returnImage = figure.isWhite() ? whiteBishopSelected : blackBishopSelected;
        }
        else if(figure.getClass() == Queen.class)
        {
            returnImage = figure.isWhite() ? whiteQueenSelected : blackQueenSelected;
        }
        else if(figure.getClass() == King.class)
        {
            returnImage = figure.isWhite() ? whiteKingSelected : blackKingSelected;
        }
        return returnImage;
    }
    private Image getImageByClass(Figure figure)
    {
        Image returnImage = null;
        if(figure == null)
            return returnImage;
        if(figure.getClass() == Pawn.class)
        {
            returnImage = figure.isWhite() ? whitePawn : blackPawn;
        }
        else if(figure.getClass() == Rook.class)
        {
            returnImage = figure.isWhite() ? whiteRook : blackRook;
        }
        else if(figure.getClass() == Knight.class)
        {
            returnImage = figure.isWhite() ? whiteKnight : blackKnight;
        }
        else if(figure.getClass() == Bishop.class)
        {
            returnImage = figure.isWhite() ? whiteBishop : blackBishop;
        }
        else if(figure.getClass() == Queen.class)
        {
            returnImage = figure.isWhite() ? whiteQueen : blackQueen;
        }
        else if(figure.getClass() == King.class)
        {
            returnImage = figure.isWhite() ? whiteKing : blackKing;
        }
        return returnImage;
    }

    /**
     * zahájí novou hru podle předané notace
     * @param notationFile
     */
    private void setGameFromNotation(File notationFile)
    {
        try
        {
            userOn = 0;
            board = new Board(8);
            if(notationFile != null)
                game = GameFactory.createChessGame(board,notationFile.toString());
            else
                game = GameFactory.createChessGame(board);

            setChessBoardGUI();

            movesListView.setItems(game.getNotation());
            movesListView.getSelectionModel().select(0);
        }
        catch (IOException e)
        {
            System.err.println("nepovedlo se cist"); //fixme
           /* nextButton.setDisable(true);
            undoButton.setDisable(true);
            backButton.setDisable(true);
            redoButton.setDisable(true);
            StopButton.setDisable(true);
            StartButton.setDisable(true);
            jumpButton.setDisable(true);
            exportButton.setDisable(true);*/
        }
    }

    private void setChessBoardGUI()
    {
        BackgroundImage bi = new BackgroundImage(new Image("file:lib/whiteField.png"), BackgroundRepeat.REPEAT, BackgroundRepeat.REPEAT, BackgroundPosition.DEFAULT, BackgroundSize.DEFAULT);
        grid.setBackground(new Background(bi));

        setBasicPositions();
    }

    @Override
    public void initialize(URL location, ResourceBundle resources)
    {
        setGameFromNotation(null);
    }

    private void SetActualMoveOnListView()
    {
        movesListView.getSelectionModel().select(game.getActualMoveIndex()/2);
    }
}