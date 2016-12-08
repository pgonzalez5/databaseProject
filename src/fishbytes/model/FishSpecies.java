package fishbytes.model;

import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

/**
 * Created by Anthony on 12/3/2016.
 */
public class FishSpecies {

    private IntegerProperty fid;
    private IntegerProperty num;
    private StringProperty name;

    public FishSpecies(){
        this.fid = new SimpleIntegerProperty(0);
        this.num = new SimpleIntegerProperty(0);
        this.name = new SimpleStringProperty("");
    }

    public FishSpecies(String name, int fid, int num){
        this.fid = new SimpleIntegerProperty(fid);
        this.num = new SimpleIntegerProperty(num);
        this.name = new SimpleStringProperty(name);
    }

    public IntegerProperty numOfSubs(){return num;}
    public StringProperty getNameProperty(){return name;}

    public String getName(){return name.get();}

    public void setName(String s){ name.set(s);}
    public void setID(int x){ fid.set(x);}
    public int getID(){return fid.get();}
}
