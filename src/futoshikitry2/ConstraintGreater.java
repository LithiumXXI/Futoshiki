/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package futoshikitry2;

import javafx.geometry.Pos;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.StackPane;

/**
 *
 * @author pd266
 */
public class ConstraintGreater extends FutoshikiConstraints {

    public ConstraintGreater(int x, int y, boolean row, Futoshiki puzzle) {
        super(x, y, row, puzzle);
    }

    /**
     *
     * @return Constraint
     */
    @Override
    public String getContent() {
        if (row) {
            return (">");
        } else {
            return ("^");
        }
    }

    /**
     * Checks that the numbers next to constraint are valid.
     *
     * @returns true or false
     */
    //Returns a label that will print out the constraint
    @Override
    public Label setStackPane() {
        Label l;
        if (!row) {
            l = new Label(">");
            l.setPrefSize(40, 40);

        } else {
            l = new Label("^");
            l.setPrefSize(40, 40);

        }
        l.setAlignment(Pos.CENTER);
        l.setStyle("-fx-font-weight: bold; -fx-text-fill: #EACBD2;");
        return l;

    }

    @Override
    public boolean compare(Square[][] squares) {
    
        if (row){
            return puzzle.getSquare(x, y) < puzzle.getSquare(x + 1, y);
        } else {
            return puzzle.getSquare(x, y) < puzzle.getSquare(x, y + 1);
        }
    }
}
