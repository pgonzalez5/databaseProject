package fishbytes.model;

import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

/**
 * Created by Anthony on 12/3/2016.
 */
public class FishSub {

    private IntegerProperty fsubid;
    private StringProperty name;
    private IntegerProperty fspecid;
    private IntegerProperty catches;
    private IntegerProperty avgWeight;
    private IntegerProperty avgLength;

    public FishSub(){
        this.fsubid = new SimpleIntegerProperty(0);
        this.name = new SimpleStringProperty("");
        this.fspecid = new SimpleIntegerProperty(0);
        this.catches = new SimpleIntegerProperty(0);
        this.avgLength = new SimpleIntegerProperty(0);
        this.avgWeight = new SimpleIntegerProperty(0);
    }

    public FishSub(String name, int fsubid, int fspecid, int c, int avgW, int avgL){
        this.fsubid = new SimpleIntegerProperty(fsubid);
        this.name = new SimpleStringProperty(name);
        this.fspecid = new SimpleIntegerProperty(fspecid);
        this.catches = new SimpleIntegerProperty(c);
        this.avgLength = new SimpleIntegerProperty(avgL);
        this.avgWeight = new SimpleIntegerProperty(avgW);
    }

    public StringProperty getName(){ return name;}
    public IntegerProperty getFsubID(){return fsubid;}
    public IntegerProperty getCatches(){return catches;}
    public IntegerProperty getAvgWeight(){return avgWeight;}
    public IntegerProperty getAvgLength(){return avgLength;}


    public void setName(String newName){
        name.set(newName);
    }
    public String getFishSubName(){ return name.get();}
    public void setID(int x){ fsubid.set(x);}
    public int getID(){return fsubid.get();}
    public void setFspecID(int x){ fspecid.set(x);}
    public int getFspecID(){ return fspecid.get();}
}
