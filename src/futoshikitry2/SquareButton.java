/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package futoshikitry2;

import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import javafx.scene.control.Button;

/**
 *
 * @author Pau
 */
public class SquareButton extends Button {
    Square s;
    private StringProperty  number = new SimpleStringProperty();
    Boolean editable;

    public SquareButton(Square s) {
        this.s = s; 
        this.number.setValue(s.getValueString());
        setPrefSize(40,40);
        editable = s.getEditable();
        if(!editable){
            setDisable(false);
            this.setId ("Hint");
           
            
        }
        else{
            this.setId("Empty");
           
         
        }
    }

    public String getNumber() {
        return number.get();
        
    }
    
    public StringProperty numberProperty(){
        return number;
    }
            
    public void setNumber(String number) {
        this.number.setValue(number);
                if(s.getValueString().equals("0")){
                    setText(" ");
                }        
                else{
                    setText(s.getValueString());
                }
    }
    

    public Boolean getEditable() {
        return s.getEditable();
    }


    public Square getS() {
        return s;
    }
    
    public void setTextNumber() {
        if(s.getValueString().equals("0")){
                    setText(" ");
                }        
                else{
                    setText(s.getValueString());
                }
    }

  
    
   
    
    
    
}
