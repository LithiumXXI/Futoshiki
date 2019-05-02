/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package futoshikitry2;

import javafx.geometry.Pos;
import javafx.scene.control.Label;
import javafx.scene.layout.Background;

/**
 *
 * @author Pau
 */
public class ConstraintSmaller extends FutoshikiConstraints {

    public ConstraintSmaller(int x, int y, boolean row, Futoshiki puzzle) {
        super(x, y, row, puzzle);
    }

    @Override
    public boolean comparison() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public String getContent() {
        if(row){
            return("<");
        }
        else{
            return("V");
        }
    }

    @Override
    public Label setStackPane() {
        Label l;
       if(row){ 
          l = new Label("<");
          l.setPrefSize(40,40);
           
       }
       else{
           l = new Label("V");
           l.setPrefSize(40,40);
       }
       l.setAlignment(Pos.CENTER);
       l.setStyle( "-fx-font-weight: bold; -fx-text-fill: #EACBD2;" );
       return l;
    }
    
}
