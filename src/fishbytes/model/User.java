package fishbytes.model;

import javafx.beans.property.*;

import java.time.LocalDate;
import java.util.Date;
import java.util.Locale;

/**
 * Created by Anthony on 11/11/2016.
 */
public class User {

    private StringProperty firstname;
    private StringProperty userName;
    private StringProperty lastname;
    private IntegerProperty userId;
    private IntegerProperty rank;
    private StringProperty email;
    private StringProperty password;
    private ObjectProperty<LocalDate> regDate;
    private StringProperty city;
    private IntegerProperty commentCount;
    private IntegerProperty catchCount;

    public User(){
        this.firstname = new SimpleStringProperty("");
        this.lastname = new SimpleStringProperty("");
        this.userName = new SimpleStringProperty("");
        //  this.rank = new SimpleIntegerProperty(rank);
        this.userId = new SimpleIntegerProperty(1234);
        this.password = new SimpleStringProperty("");
        this.email = new SimpleStringProperty("");
        this.regDate = new SimpleObjectProperty<LocalDate>();
        this.city = new SimpleStringProperty("");
        this.commentCount = new SimpleIntegerProperty(0);
        this.catchCount = new SimpleIntegerProperty(0);
    }

    public User(String fname, String Lname, String Uname, int id, String email,
                String pw, LocalDate date, String city, int commentCount, int catchCount) {
        this.firstname = new SimpleStringProperty(fname);
        this.lastname = new SimpleStringProperty(Lname);
        this.userName = new SimpleStringProperty(Uname);
      //  this.rank = new SimpleIntegerProperty(rank);
        this.userId = new SimpleIntegerProperty(id);
        this.password = new SimpleStringProperty(pw);
        this.email = new SimpleStringProperty(email);
        this.regDate = new SimpleObjectProperty<LocalDate>(date);
        this.city = new SimpleStringProperty(city);
        this.commentCount = new SimpleIntegerProperty(commentCount);
        this.catchCount = new SimpleIntegerProperty(catchCount);
    }

    public StringProperty getUserNameProperty(){
        return userName;
    }

    public StringProperty getFirstNameProperty(){
        return firstname;
    }
    public StringProperty getLastNameProperty(){
        return lastname;
    }
    public ObjectProperty<LocalDate> getRegDateProperty(){
        return regDate;
    }
    public IntegerProperty getCommentCountProperty(){
        return commentCount;
    }
    public IntegerProperty getCatchCountProperty(){
        return catchCount;
    }

    public String getUsername()
    {
        return userName.get();
    }
    public String getEmail(){
        return email.get();
    }
    public String getFirstName(){
        return firstname.get();
    }
    public String getLastName(){
        return lastname.get();
    }
    public String getCity(){ return city.get();}
    public String getPassWorrd(){return password.get();}
    public LocalDate getRegDate(){return regDate.get();}
    public int getID(){return userId.get();}

    public void setFirstname(String s){firstname.set(s);}
    public void setLastname(String s){ lastname.set(s);}
    public void setUserName(String s){ userName.set(s);}
    public void setCity(String s){ city.set(s);}
    public void setEmail(String s){ email.set(s);}
    public void setPassword(String s){password.set(s);}
    public void setDate(LocalDate d){ regDate.setValue(d);}
}
