package fishbytes.utility;


import com.sun.xml.internal.fastinfoset.util.StringArray;
import fishbytes.model.*;
import javafx.beans.property.ObjectProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.io.IOException;
import java.sql.*;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.List;
import java.util.Vector;

/**
 * Created by Anthony on 11/10/2016.
 */


public class  Connect {
    public static Connection con;
    private static Statement statement;
    private static CallableStatement funcCall;
    public Connect() {

        try {
            DriverManager.registerDriver(new oracle.jdbc.driver.OracleDriver());
            con = DriverManager.getConnection(
                    "jdbc:oracle:thin:@delphi.cs.csub.edu:1521:dbs01", "cs3420", "c3m4p2s");
            System.out.println("connection established!");
        } catch (SQLException e) {
            e.printStackTrace();
        }

    }

    public boolean testAdminUser(String username, String password)
    {

        if(con == null)
        {
            System.out.println("Error not connected to database");
            return false;
        }
        String dusername = "'" + username + "'";
        String dpassword = "'" + password + "'";
        System.out.println(username + " and " + password);

        String q = "SELECT adminname as username FROM LPER_PGON_Admin WHERE adminname = "
                + dusername + " AND adminpassword = " + dpassword;
        System.out.println(q);

        try {
            ResultSet num = query(q, true);
            if(!num.next())
                return false;
            //ResultSetMetaData rnum = num.getMetaData();
            num.last();
            String count = num.getString("username");
            System.out.println("Count from query: " + count);
            return count.equals(username);
        } catch(SQLException e){
            e.printStackTrace();
            return false;
        }
    }

    public ObservableList<User> getUsers()
    {

        if(con == null)
        {
            System.out.println("Error not connected to database");
            return null;
        }
        ObservableList<User> result = null;
        try {
            ResultSet values = query("SELECT * FROM LPER_PGON_VIEW_USER_COUNT", true);
                    //query("SELECT * FROM Lper_Pgon_User", true);
            //if(!values.next())
              //  return null;
            result = FXCollections.observableArrayList();
            while(values.next()){
                int userid = values.getInt("USERID");
                String username = values.getString("USERNAME");
                String fname = values.getString("FNAME");
                String lname = values.getString("LNAME");
                String email = values.getString("EMAIL");
                LocalDate date = values.getDate("RDATE").toLocalDate();
                String city = values.getString("CITY");
                String pw = values.getString("PASSWORD");
                int commentCount = values.getInt("COMMENTS");
                int catchCount = values.getInt("CATCHES");
               // System.out.println(username);
                result.add(new User(fname,lname,username,userid,email,pw,date,city,commentCount,catchCount));
            }
            System.out.println("finished getting users");
            //values.close();
            return result;
        } catch(SQLException e){
            e.printStackTrace();
            return result;
        }
    }

    public ResultSet query(String sql_statment, boolean readonly)
    {
        try {

            if(!readonly)
                statement = con.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_UPDATABLE);
            else
                statement = con.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_UPDATABLE);
            System.out.println("sql test1");
            ResultSet g = statement.executeQuery(sql_statment);
            return g;
        } catch (SQLException e)
        {
            e.printStackTrace();
            return null;
        } finally{
           /* try {
                statement.close();
            } catch(SQLException g){
                g.printStackTrace();
            }*/
        }
    }

    public ObservableList<Location> getLocations()
    {

        try {
            ObservableList<Location> result = FXCollections.observableArrayList();
            //statement = con.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_UPDATABLE);

            ResultSet values = query("SELECT *\n" +
                    "FROM LPer_PGon_Location D full outer join (\n" +
                    "select L.LocationID, count(postid) as CATCHES\n" +
                    "FROM LPer_PGon_Location L full outer join  Lper_PGon_Catch C on L.locationid=C.locationid\n" +
                    "GROUP BY L.LocationID) G on D.locationid = G.locationid ", true);
            while(values.next()) {
                int locationID = values.getInt("LOCATIONID");
                int zip = values.getInt("ZIP");
                int catchCount = values.getInt("Catches");
                String name = values.getString("Name");
                String state = values.getString("state");
                String address = values.getString("Address");
                String city = values.getString("City");
                result.add(new Location(locationID, name,address,city,state,zip,catchCount));
            }
            return result;

        } catch (SQLException e)
        {
            e.printStackTrace();
            return null;
        }

    }

    public ObservableList<Bait> getBait()
    {
        try{
            ObservableList<Bait> result = FXCollections.observableArrayList();
            //statement = con.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_UPDATABLE);

            ResultSet values = query("select * from LPER_PGON_VIEW_BAIT_DETAIL", true);
            while(values.next()) {
                int baitid = values.getInt("BAITID");
                int type = values.getInt("BTYPE");
                int catchCount = values.getInt("CATCHES");
                String name = values.getString("BNAME");
                String Btype = "Real";
                if(type == 1)
                    Btype = "Artificial";
                result.add(new Bait(name, Btype, baitid, catchCount));
            }
            return result;
        } catch(Exception e){
            e.printStackTrace();
            return null;
        }
    }

    public ObservableList<FishSub> getFishSub(){
        try{
            ObservableList<FishSub> result = FXCollections.observableArrayList();

            //statement = con.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_UPDATABLE);
            ResultSet values = query("select * from lper_pgon_view_fsubspec_detail", true);
            while(values.next()) {
                int fsubid = values.getInt("FSUBID");
                int fspecid = values.getInt("FSPECID");
                int catchCount = values.getInt("CATCHES");
                int avgL = values.getInt("AVGLENGTH");
                int avgW  = values.getInt("AVGWEIGHT");
                String name = values.getString("FSUBNAME");

                result.add(new FishSub(name, fsubid, fspecid, catchCount, avgW, avgL));
            }

            return result;
        } catch(Exception e){
            e.printStackTrace();
            return null;
        }
    }
    public ObservableList<FishSpecies> getFishSpecies(){
        try{
            ObservableList<FishSpecies> result = FXCollections.observableArrayList();

            //statement = con.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_UPDATABLE);
            ResultSet values = query("select * from lper_pgon_view_fspec_detail", true);
            while(values.next()) {
                int fid = values.getInt("FSPECID");
                String name = values.getString("FSNAME");
                int num = values.getInt("NUM_OF");
                //System.out.println(num);
                result.add(new FishSpecies(name, fid, num));
            }
            //statement.close();
            return result;
        }catch(Exception e){
            e.printStackTrace();
            return null;
        }
    }


    public boolean newLocation(Location e, int i) {
        try{
            int newID;
            if(i == -1) {
                String getId = "Select LPER_GON_lckseq.nextval from dual";
                ResultSet r = query(getId, true);
                r.next();
                newID = r.getInt("NEXTVAL");
                System.out.println("NEW LOCATION");
            }
            else {
                newID = i;
                System.out.println("UPDATING LOCATION");
            }
            String sql = "INSERT INTO LPER_PGON_LOCATION VALUES(?,?,?,?,?,?)";
            PreparedStatement c = con.prepareStatement(sql);
            c.setInt(1,newID);
            c.setString(2,e.getName());
            c.setString(3,e.getAddress());
            c.setString(4,e.getCity());
            c.setString(5,e.getState());
            c.setInt(6,e.getZip());
            c.execute();
            return true;
        }catch(SQLException s){
            s.printStackTrace();
            return false;
        }
    }

    public boolean deleteLocation(Location e){
        int u = e.getID();
        String sql = "DELETE FROM LPER_PGON_LOCATION WHERE LOCATIONID =" + u;
        ResultSet r = query(sql, true);
        return true;
    }

    public boolean updateLocation(Location e){
        deleteLocation(e);
        newLocation(e,e.getID());
        return false;
    }

    public boolean deleteUser(User e){
            int u = e.getID();
            String sql = "DELETE FROM LPER_PGON_USER WHERE USERID =" + u;
            ResultSet r = query(sql, true);
            return true;
    }

    public boolean insertUser(User e, int i){
        try {
            int newID;
            if(i == -1) {
                String getId = "Select LPER_GON_lckseq.nextval from dual";
                ResultSet r = query(getId, true);
                r.next();
                newID = r.getInt("NEXTVAL");
                System.out.println("NEW USER");
            }
            else {
                newID = i;
                System.out.println("UPDATING USER");
            }

            String username = e.getUsername();
            String city = e.getCity();
            String firstname = e.getFirstName();
            String lastname = e.getLastName();
            String pass = e.getPassWorrd();
            String email = e.getEmail();
            System.out.println(username + " " + city + " " + firstname+
                        " "+lastname+ " "+ pass+ " " + email);
            String sql = "INSERT INTO LPER_PGON_USER VALUES(?,?,?,?,?,?,?,?)";
            PreparedStatement c = con.prepareStatement(sql);
            c.setInt(1,newID);
            c.setString(2,e.getUsername());
            c.setString(3,e.getLastName());
            c.setString(4,e.getFirstName());
            c.setString(5,e.getEmail());
            c.setString(6,e.getPassWorrd());
            c.setDate(7,java.sql.Date.valueOf(e.getRegDate()));
            c.setString(8,e.getCity());
            c.execute();
            return true;
        } catch (SQLException s){
            s.printStackTrace();
            return false;
        }
    }

    public boolean insertBait(Bait e, int i){
        try{
            int newID;
            if(i == -1) {
                String getId = "Select LPER_GON_lckseq.nextval from dual";
                ResultSet r = query(getId, true);
                r.next();
                newID = r.getInt("NEXTVAL");
                System.out.println("BAIT LOCATION");
            }
            else {
                newID = i;
                System.out.println("BAIT LOCATION");
            }
            e.setID(newID);
            String sql = "INSERT INTO LPER_PGON_BAIT VALUES(?,?,?)";
            PreparedStatement c = con.prepareStatement(sql);
            c.setInt(1,newID);
            c.setString(2,e.getName());
            if(e.getType().equals("Artificial"))
                c.setInt(3,1);
            else
                c.setInt(3,0);
            c.execute();
            return true;
        }catch(SQLException s){
            s.printStackTrace();
            return false;
        }
    }

    public void deleteBait(Bait e){
        int u = e.getID();
        String sql = "DELETE FROM LPER_PGON_BAIT WHERE BAITID =" + u;
        ResultSet r = query(sql, true);
    }

    public boolean updateBait(Bait e){
        deleteBait(e);
        insertBait(e,e.getID());
        return true;
    }

    public boolean updateUser(User e){
            deleteUser(e);
            insertUser(e,e.getID());
            return true;
    }

    public void deleteFishSub(FishSub e){
        int u = e.getID();
        String sql = "DELETE FROM LPER_PGON_FSUBSPEC WHERE FSUBID =" + u;
        ResultSet r = query(sql, true);
    }

    public boolean insertSubFish(FishSub e, int i) {
        try {
            int newID;
            if (i == -1) {
                String getId = "Select LPER_GON_lckseq.nextval from dual";
                ResultSet r = query(getId, true);
                r.next();
                newID = r.getInt("NEXTVAL");
                System.out.println("BAIT LOCATION");
            } else {
                newID = i;
                System.out.println("BAIT LOCATION");
            }
            e.setID(newID);
            String sql = "INSERT INTO LPER_PGON_FSUBSPEC VALUES(?,?,?)";
            PreparedStatement c = con.prepareStatement(sql);
            c.setInt(1, newID);
            c.setString(2, e.getFishSubName());
            c.setInt(3, e.getFspecID());
            c.execute();
            return true;
        } catch (SQLException s) {
            s.printStackTrace();
            return false;
        }
    }

    public boolean updateFishSub(FishSub e){
        deleteFishSub(e);
        insertSubFish(e,e.getID());
        return true;
    }

    public boolean insertFishSpec(FishSpecies e) {
        try {
            int newID;

            String getId = "Select LPER_GON_lckseq.nextval from dual";
            ResultSet r = query(getId, true);
            r.next();
            newID = r.getInt("NEXTVAL");
            System.out.println("New SPECIES");


            e.setID(newID);
            String sql = "INSERT INTO LPER_PGON_FSPEC VALUES(?,?)";
            PreparedStatement c = con.prepareStatement(sql);
            c.setInt(1, newID);
            c.setString(2, e.getName());
            c.execute();
            return true;
        } catch (SQLException s) {
            s.printStackTrace();
            return false;
        }
    }

    public void disconnect()
    {
        if(con != null)
            try {
                con.close();
                System.out.println("closed connection!");
            } catch (SQLException e) {
                e.printStackTrace();
            }
    }
}
