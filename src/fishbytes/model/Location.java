package fishbytes.model;

import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

/**
 * Created by Anthony on 11/17/2016.
 */
public class Location {
    private IntegerProperty locationId;
    private StringProperty name;
    private StringProperty address;
    private StringProperty city;
    private StringProperty state;
    private IntegerProperty zip;
    private IntegerProperty catchCount;

    public Location()
    {
        this.locationId = new SimpleIntegerProperty(0);
        this.name = new SimpleStringProperty("");
        this.address = new SimpleStringProperty("");
        this.city = new SimpleStringProperty("");
        this.zip = new SimpleIntegerProperty(1);
        this.state = new SimpleStringProperty("");
        this.catchCount = new SimpleIntegerProperty(0);
    }

    public Location(int locationId, String name, String address, String city, String state, int zip, int catchCount)
    {
        state = state.toUpperCase();
        this.locationId = new SimpleIntegerProperty(locationId);
        this.name = new SimpleStringProperty(name);
        this.address = new SimpleStringProperty(address);
        this.city = new SimpleStringProperty(city);
        this.zip = new SimpleIntegerProperty(zip);
        this.state = new SimpleStringProperty(state);
        this.catchCount = new SimpleIntegerProperty(catchCount);
    }

    public StringProperty getNameProperty(){
        return name;
    }

    public StringProperty getCityProperty(){
        return city;
    }

    public StringProperty getStateProperty(){
        return state;
    }

    public IntegerProperty getCatchCount(){
        return catchCount;
    }

    public String getName(){ return name.get();}
    public String getAddress(){ return address.get();}
    public String getCity(){return city.get();}
    public String getState(){return state.get();}
    public int getZip(){return zip.get();}

    public void setName(String s){ name.set(s);}
    public void setAddress(String s){address.set(s);}
    public void setCity(String s){city.set(s);}
    public void setState(String s){state.set(s);}
    public void setZip(int x){zip.set(x);}

    public int getID(){return locationId.get();}
}
