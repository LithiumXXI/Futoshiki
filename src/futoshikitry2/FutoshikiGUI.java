/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package futoshikitry2;

import javafx.application.Application;
import javafx.beans.binding.StringBinding;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.HPos;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.ColumnConstraints;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.RowConstraints;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.TilePane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

/**
 *
 * @author Pau
 */
public class FutoshikiGUI extends Application {
    
    BorderPane gameScreen = new BorderPane();
    GridPane gridFutoshiki = new GridPane();
    Futoshiki puzzle = new Futoshiki(8);
    int gridsize = puzzle.getSize();
    StringBinding contentObserve;
    
    
    //Sets up the gridPane
   
    private void gridFutoshikiSetUp() {
        int numRows = 9;
        int numCols = 9;
        gridFutoshiki.setGridLinesVisible(true);
        
        for (int i = 0; i < numCols; i++) {
            ColumnConstraints colConst = new ColumnConstraints();
            colConst.setPercentWidth(100.0 / numCols);
            gridFutoshiki.getColumnConstraints().add(colConst);
        }
        for (int i = 0; i < numRows; i++) {
            RowConstraints rowConst = new RowConstraints();
            rowConst.setPercentHeight(100.0 / numRows);
            gridFutoshiki.getRowConstraints().add(rowConst);
        }
        
    }
    
    private Futoshiki difficultyGrid(String d, Futoshiki f){
        switch(d){
            case "E":
                f.fillPuzzle(gridsize/2, gridsize/4, gridsize/4);
                break;
            case "M":
                f.fillPuzzle(gridsize/3, gridsize/8, gridsize/8);
                break;
            case "H":
                f.fillPuzzle(gridsize/4, gridsize/2, 2);
                
        }
           return f;
    }
    
    public void displayGridString(){
        for(int row = 0; row<gridsize;row++){
            for(int col=0; col<gridsize; col++){
            System.out.print(puzzle.getSquares()[row][col].getValueString());
            if(col<4){
            if(puzzle.getRowConstraints()[row][col]!= null){
            System.out.print(puzzle.getRowConstraints()[row][col].getContent());
            }
            else{
                System.out.print("E");
                }
            }
            }
                System.out.println("");
                if(row<4){
                for(int col= 0; col<gridsize;col++){
                if(puzzle.getColumnConstraints()[col][row]!= null){
                
                System.out.print(puzzle.getColumnConstraints()[col][row].getContent());
                
                }
                else{
                    System.out.print("E");
                    
                }
                if(col<4){
                   System.out.print(" ");
                }
                
            }}
            System.out.println("");
    }
    }
            
    //Adds the content to the GridPane
    public GridPane displayGridGUI() {
        int rowGrid = 0;
      
        //for every row in the grid
        for (int row = 0; row < gridsize; row++) {
            int colGrid = 0;
            
            //for every column in that row creates a stackPane
            for (int col = 0; col < gridsize; col++) {
                //numbers
                SquareButton t = new SquareButton(puzzle.getSquares()[row][col]);
                t.setTextNumber(); 
                gridFutoshiki.setHalignment(t, HPos.CENTER);
                gridFutoshiki.add(t, colGrid, rowGrid);
                colGrid++;
                int x = row;
                int y = col;
                if (t.getEditable() == true){
                t.setOnAction(new EventHandler<ActionEvent>() {
                 @Override
                    public void handle(ActionEvent event) {
                      squareClicked(x, y);
                      if(puzzle.checkDuplicateSquare(x, y)){
                        t.setTextNumber();
                        t.setId("Wrong");
                      }
                      else{                          
                          t.setTextNumber();
                          t.setId("Empty");          
                      }
                    }
                  });
                }
                if (col<gridsize-1) {

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
                                                System.out.println(colGrid+"ErowC"+rowGrid);
                        gridFutoshiki.setHalignment(s, HPos.CENTER);
                        colGrid++;

                    }
                }

              
            }
            colGrid = 0;
            rowGrid++;
            
                for (int col = 0; col < gridsize ; col++) {
                    if (row<gridsize-1) {
                    if (puzzle.getColumnConstraints()[col][row] != null) {

                        Label s = new Label(puzzle.getColumnConstraints()[col][row].getContent());
                        s.setStyle("-fx-text-fill: #F7F7F7");
                        gridFutoshiki.add(s, colGrid, rowGrid);
                        
                                                                        System.out.println(colGrid+"colC"+rowGrid);

                        gridFutoshiki.setHalignment(s, HPos.CENTER);
                    } else {
                        StackPane s = new StackPane();
                        s.setPrefSize(40, 40);
                        s.getChildren().add(new Label(" "));

                        gridFutoshiki.add(s, colGrid, rowGrid);
                                                                        System.out.println(colGrid+"ColCE"+rowGrid);

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
    //Returns the grid with constraints and numbers(empty or not
   
     //Adds 1 to the square
    public void squareClicked(int row, int col){
        if(puzzle.getSquares()[row][col].getValue()<gridsize){
            int value = (puzzle.getSquares()[row][col].getValue()+1);
            puzzle.getSquares()[row][col].setValue(value);
            
        }
        else{
            puzzle.getSquares()[row][col].setValue(0);
            System.out.print(puzzle.getSquares()[row][col].getValue());

        }
    }
    private Scene back(Stage primaryStage){
          StackPane root = new StackPane();
          VBox mainM = mainMenu(primaryStage);
          root.getChildren().add(mainM);
         Scene scene = new Scene(root, 600, 500);
                        scene.getStylesheets().add(getClass().getResource("game.css").toExternalForm());
          return scene;              
    }
    
    
private VBox mainMenu(Stage primaryStage){
     VBox mainMenu = new VBox();
        mainMenu.setAlignment(Pos.CENTER);
        mainMenu.setSpacing(50);
        mainMenu.setStyle("-fx-background-color: #684A60");
        
        
        HBox difficulty = new HBox();
        difficulty.setAlignment(Pos.CENTER);
        Button difficultL = new Button("Difficulty: ");
        ComboBox difficultCB = new ComboBox();
        difficultCB.getItems().addAll(
                "Easy",
                "Medium",
                "Hard"
        );
        difficultCB.setPromptText("Please choose one");
                
        difficulty.getChildren().addAll(difficultL, difficultCB);
        
        ///Move this to a method main screen 
        //New Game Button 
        Button newButton = new Button();
        newButton.setText("New Game");
        newButton.setOnAction(new EventHandler<ActionEvent>() {
            
            @Override
            public void handle(ActionEvent event) {
                System.out.println("New game!");
                displayGridString();
                GridPane gui = displayGridGUI();
                gui.setMaxSize(300,300);
                gameScreen.setStyle("-fx-background-color: #684A60;");
                gameScreen.setCenter(gui);
                VBox optionsGame = new VBox();
                Button mainMenu = new Button("Main Menu");
                mainMenu.setOnAction(new EventHandler<ActionEvent>() {
                    @Override
                    public void handle(ActionEvent event) {
                     primaryStage.setScene( back(primaryStage));
                    }
                });
                        
                Button hint = new Button("Hint");
                Button giveUp = new Button("Give up");
                optionsGame.setAlignment(Pos.CENTER);
                
                optionsGame.getChildren().addAll(mainMenu, hint, giveUp);
                gameScreen.setRight(optionsGame);
                
               
                //TextBox Title
                Label futoshikiGameTitle = new Label("Futoshiki Game");
                futoshikiGameTitle.setId("Title");                
                gameScreen.setTop(futoshikiGameTitle);
                gameScreen.setAlignment(futoshikiGameTitle, Pos.CENTER);
                Scene scene2 = new Scene(gameScreen, 700,700);
                scene2.getStylesheets().add(getClass().getResource("game.css").toExternalForm());

                primaryStage.setScene(scene2);
            }
        });
        
        //Quit game button 
        Button quitButton = new Button();
        quitButton.setText("Quit Game");
        quitButton.setOnAction(new EventHandler<ActionEvent>() {
            
            
            @Override
            public void handle(ActionEvent event) {
                System.out.println("Bye");
                System.exit(0);
            }
        });
         
        //TextBox Title
        Label futoshikiGame = new Label("Futoshiki Game");
        futoshikiGame.setId("Title");
     
        
        
        
        mainMenu.getChildren().addAll(futoshikiGame, newButton, difficulty, quitButton);
    return mainMenu;
}    

    @Override
    public void start(Stage primaryStage) {
      //setting game screen
      //make sure you change size options etc later on 
       puzzle.fillPuzzle(10,10,10);
        //new puzzle 
        //set random

        //Main menu Screen
        VBox mainMenu = mainMenu(primaryStage);
        StackPane root = new StackPane(); 
        root.getChildren().add(mainMenu);
        
        //
        
        Scene scene = new Scene(root, 600, 500);
        scene.getStylesheets().add(getClass().getResource("game.css").toExternalForm());
        
        primaryStage.setTitle("Futoshiki Game");
       
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    /**
     * @param args the command line arguments
     */


}