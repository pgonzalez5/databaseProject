package fishbytes.model;

import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

/**
 * Created by Anthony on 11/17/2016.
 */
public class Bait {
    private StringProperty name;
    private StringProperty type;
    private IntegerProperty id;
    private IntegerProperty catches;

    public Bait()
    {
        this.name = new SimpleStringProperty("");
        this.type = new SimpleStringProperty("");
        this.id = new SimpleIntegerProperty(0);
        this.catches = new SimpleIntegerProperty(0);
    }

    public Bait(String name, String type, int id, int c){
        this.name = new SimpleStringProperty(name);
        this.type = new SimpleStringProperty(type);
        this.id = new SimpleIntegerProperty(id);
        this.catches = new SimpleIntegerProperty(c);
    }

    public StringProperty getNameProperty(){ return name;}
    public StringProperty getTypeProerty(){ return type;}
    public IntegerProperty getIDProerty(){return id;}
    public IntegerProperty getCatchesProperty() {return catches;}

    public String getName(){return name.get();}
    public String getType(){return type.get();}
    public int getID(){return id.get();}
    public int getCatches(){return catches.get();}

    public void setName(String s){ name.set(s);}
    public void setType(String s){type.set(s);}
    public void setID(int x){id.set(x);}
}
