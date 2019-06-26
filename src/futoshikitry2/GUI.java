/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package futoshikitry2;

import javafx.application.Application;
import javafx.beans.binding.StringBinding;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.HPos;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

/**
 *
 * @author Pau
 */
public class GUI extends Application {

    BorderPane gameScreen = new BorderPane();
    GridPane gridFutoshiki = new GridPane();
    Futoshiki puzzle;
    int gridsize = 5;
    String difficulty = "E";
    Scene mainMenu;
    Scene gameMenu = new Scene(gameScreen, 700, 700);

    ;
    
    
    
    
     private Futoshiki difficultyGrid(String d, Futoshiki f) {
        gridsize = puzzle.getSize();
        switch (d) {
            case "E":
                f.fillPuzzle(gridsize, gridsize, gridsize);
                break;
            case "M":
                f.fillPuzzle(gridsize / 3, gridsize / 8, gridsize / 8);
                break;
            case "H":
                f.fillPuzzle(gridsize / 4, gridsize / 2, 2);
                break;

        }
        return f;
    }

     public void giveUp() {
         
     }
       

     
             
             /**
     * Creates the GUI Grid for the game
     *
     * @return gridPane with the grid filled.
     */
    public GridPane displayGridGUI() {
        gridsize = puzzle.getSize();
        gridFutoshiki = new GridPane();
        int rowGrid = 0;

        //for every row in the grid
        for (int row = 0; row < gridsize; row++) {
            int colGrid = 0;

            //for every column in that row creates a stackPane
            for (int col = 0; col < gridsize; col++) {
                //numbers
                SquareButton t = new SquareButton(puzzle.getSquares()[row][col]);
                puzzle.getSquares()[row][col].setNumber();
                t.textProperty().bind(puzzle.getSquares()[row][col].getNumber());
                gridFutoshiki.setHalignment(t, HPos.CENTER);
                gridFutoshiki.add(t, colGrid, rowGrid);
                colGrid++;
                int x = row;
                int y = col;
                if (t.getEditable() == true) {
                    t.setOnAction(new EventHandler<ActionEvent>() {
                        @Override
                        public void handle(ActionEvent event) {
                            squareClicked(x, y);
                            if (puzzle.checkDuplicateSquare(x, y)) {
                               puzzle.getSquares()[x][y].setNumber();
                                t.setId("Wrong");
                            } else {
                               puzzle.getSquares()[x][y].setNumber();
                                t.setId("Empty");
                            }
                        }
                    });
                }
                if (col < gridsize - 1) {

                    if (puzzle.getRowConstraints()[row][col] != null) {

                        Label s = new Label(puzzle.getRowConstraints()[row][col].getContent());
                        s.setStyle("-fx-text-fill: #F7F7F7");

                        gridFutoshiki.add(s, colGrid, rowGrid);

                        gridFutoshiki.setHalignment(s, HPos.CENTER);
                        colGrid++;

                    } else {
                        StackPane s = new StackPane();
                        s.setPrefSize(40, 40);
                        s.getChildren().add(new Label(" "));

                        gridFutoshiki.add(s, colGrid, rowGrid);
                        
                        gridFutoshiki.setHalignment(s, HPos.CENTER);
                        colGrid++;

                    }
                }

            }
            colGrid = 0;
            rowGrid++;

            for (int col = 0; col < gridsize; col++) {
                if (row < gridsize - 1) {
                    if (puzzle.getColumnConstraints()[col][row] != null) {
                        Label s = new Label(puzzle.getColumnConstraints()[col][row].getContent());
                        s.setStyle("-fx-text-fill: #F7F7F7");
                        gridFutoshiki.add(s, colGrid, rowGrid);
                        gridFutoshiki.setHalignment(s, HPos.CENTER);
                    } else {
                        StackPane s = new StackPane();
                        s.setPrefSize(40, 40);
                        s.getChildren().add(new Label(" "));
                        gridFutoshiki.add(s, colGrid, rowGrid);
                        gridFutoshiki.setHalignment(s, HPos.CENTER);
                    }

                }
                colGrid++;
                colGrid++;
            }
            rowGrid++;

        }
        return gridFutoshiki;
    }

    /**
     * Adds one to the square button clicked
     *
     * @param row
     * @param col
     */
    public void squareClicked(int row, int col) {
        if (puzzle.getSquares()[row][col].getValue() < gridsize) {
            int value = (puzzle.getSquares()[row][col].getValue() + 1);
            puzzle.getSquares()[row][col].setValue(value);

        } else {
            puzzle.getSquares()[row][col].setValue(0);
            System.out.print(puzzle.getSquares()[row][col].getValue());

        }
    }

    private Button difficultyCB() {
    

        Button difficulty = new Button("Easy");
        difficulty.setOnAction((event) -> {
       System.out.println("h");
            String d = difficulty.getText();
            switch(d){
                case("Easy"):
                    difficulty.setText("Medium");
                    break;
                case("Medium"):
                    difficulty.setText("Hard");
                    break;
                 case("Hard"):
                     difficulty.setText("Easy");
                     break;
                        
            }
        });
        return difficulty;
    }

    private void gameMenu(Stage primaryStage) {

        puzzle = new Futoshiki(gridsize);
        difficultyGrid("E", puzzle);
        GridPane gui = displayGridGUI();
        gui.setMaxSize(500, 500);
        gui.setAlignment(Pos.CENTER);
        gameScreen.setStyle("-fx-background-color: #684A60;");
        gameScreen.setCenter(gui);
        VBox optionsGame = new VBox();
        Button mainMenuB = new Button("Main Menu");
        mainMenuB.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {

                primaryStage.setScene(null);
                primaryStage.setScene(mainMenu);
            }
        });

       
        Button giveUp = new Button("Give up");
        giveUp.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                              
                Solve s = new Solve(puzzle);
                s.solveFunc();
                s.updatePuzzle();
                    
                Alert al = new Alert(Alert.AlertType.WARNING);
                    al.setTitle("You lost.");
                    al.setContentText("Good luck next time");
                    al.show();
                    primaryStage.setScene(mainMenu);
            }
            
        });
       
        optionsGame.setAlignment(Pos.CENTER);
        optionsGame.getChildren().addAll(mainMenuB, giveUp, quitGame());
        gameScreen.setRight(optionsGame);

        //TextBox Title
        Label futoshikiGameTitle = new Label("Futoshiki Game");
        futoshikiGameTitle.setId("Title");
        gameScreen.setTop(futoshikiGameTitle);
        gameScreen.setAlignment(futoshikiGameTitle, Pos.CENTER);
        gameMenu.setRoot(gameScreen);

        gameMenu.getStylesheets().add(getClass().getResource("game.css").toExternalForm());

        primaryStage.setScene(gameMenu);
    }

    public Button quitGame() {
        Button quit = new Button("Quit Game");

        quit.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                System.exit(0);
            }
        });

        return quit;
    }

    public void MainMenu(Stage primaryStage) {
        VBox mainM = new VBox();
        mainM.setStyle("-fx-background-color: #684A60");
        Button difficultCB = difficultyCB();
        Button newButton = new Button("New Game");
        Button quitButton = quitGame();
        Label futoshikiGame = new Label("Futoshiki Game");
        TextField size = new TextField("5");
                
        
        mainM.setAlignment(Pos.CENTER);
        mainM.setSpacing(50);
      
        //Textfield Size
        size.setMaxSize(60, 10);
        size.textProperty().addListener(new ChangeListener<String>() {
            @Override
            
            //Allows only numbers to be entered
            public void changed(ObservableValue<? extends String> observable, String oldValue,
                    String newValue) {
                if (!newValue.matches("\\d*")) {
                    size.setText(newValue.replaceAll("[^\\d]", ""));
                }
            }
        });
        
        //New Game button
        newButton.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {   
                
                int sizeInput = Integer.parseInt( size.getText());
                difficulty = difficultCB.getText();
                System.out.println(difficulty);
                
                //Checks parameters are correct.
                if(sizeInput > 10 || sizeInput < 5 || size.getText().equals("")){
                    Alert alert = new Alert(Alert.AlertType.WARNING);
                    alert.setTitle("Parameter Out of bounds");
                    alert.setContentText("Please Make sure the size is between 5-10");
                    alert.showAndWait();
                   
                }  
                else{
                    gridsize = sizeInput;
                    //difficulty 
                }

                gameMenu(primaryStage);

            }
        });

       

        futoshikiGame.setId("Title");
        mainM.getChildren().addAll(futoshikiGame, newButton, difficultCB, size, quitButton);
        mainMenu = new Scene(mainM, 500, 500);
        mainMenu.getStylesheets().add(getClass().getResource("game.css").toExternalForm());
    }

    @Override
    public void start(Stage primaryStage) {

        StackPane root = new StackPane();
        MainMenu(primaryStage);

        primaryStage.setTitle("Futoshiki");
        primaryStage.setScene(mainMenu);
        primaryStage.show();
    }

    /**
     * @param args the command line arguments
     */
        public static void main(String[] args) {
        launch(args);
    }

}
