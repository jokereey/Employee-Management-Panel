package connectiondb;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.Scanner;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.Date;

public class Connect {
    
    final public static String login="root";
    private static String password="";

    public static String getPassword() {
        return password;
    }
    
    

    public static ArrayList<String> get(String username, String password) throws Exception {
        try {
            Connection con = getConnection(username, password);
            PreparedStatement statement = con.prepareStatement("SELECT * FROM pracownicy ORDER BY im_i_nazw ");

            ResultSet result = statement.executeQuery();

            ArrayList<String> array = new ArrayList<String>();
            while (result.next()) {
                System.out.print("Pracownik:");
                System.out.println(result.getString("im_i_nazw"));
                System.out.print("Data urodzenia:");
                System.out.println(result.getString("d_ur"));
                System.out.println(" ");

                array.add(result.getString("im_i_nazw"));
            }
            System.out.println("All records have been selected!");
            return array;

        } catch (Exception e) {
            System.out.println(e);
        }
        return null;

    }

    public static void display(String username, String password, JList lista) throws Exception {
        try {
            DefaultListModel mod = new DefaultListModel();
            lista.setModel(mod);
            //mod.removeAllElements();
            Connection con = getConnection(username, password);
            PreparedStatement statement = con.prepareStatement("SELECT * FROM pracownicy ORDER BY im_i_nazw");

            ResultSet result = statement.executeQuery();

            ArrayList<String> array = new ArrayList<String>();
            while (result.next())
            {

                String zmienna = result.getString("im_i_nazw");
                array.add(zmienna);
            }

            for (int i = 0; i < array.size(); i++) 
                mod.addElement(array.get(i));
            
        } catch (Exception e) {
            System.out.println(e);
        }

    }

    public static void countEmployees(String username, String password, JList lista) throws Exception {
        try {
            DefaultListModel mod = new DefaultListModel();
            lista.setModel(mod);
            mod.removeAllElements();
            Connection con = getConnection(username, password);
            PreparedStatement statement = con.prepareStatement("SELECT COUNT(*) FROM pracownicy");
            int a = 0;
            ResultSet result = statement.executeQuery();
            while (result.next()) {
                a = result.getInt(1);
            }

            ArrayList<String> array = new ArrayList<String>();

            mod.addElement(a);
        } catch (Exception e) {

        }

        System.out.println("koniec");
    }

    public static Connection getConnection(String username, String password) throws Exception {
        try {

            String driver = "com.mysql.cj.jdbc.Driver";
            String url = "jdbc:mysql://localhost:3306/firma2?useUnicode=true&useJDBCCompliantTimezoneShift=true&useLegacyDatetimeCode=false&serverTimezone=Europe/Moscow";

            Class.forName(driver);

            Connection conn = DriverManager.getConnection(url, username, password);
            System.out.println("Connected");
            Connect.password=password;
            return conn;
        } catch (Exception e) {
            System.out.println(e);
        }

        return null;
    }

    public static void post(String username, String password,int ID,String imie_i_nazw,String data,int numerWydz,String staz) throws Exception {
       String pensja="0";
        try {
            Connection con = getConnection(username, password);
            PreparedStatement posted = con.prepareStatement("INSERT INTO pracownicy (id_prac, im_i_nazw,d_ur,numer_wydz,Staż,Pensja) VALUES ('" + ID + "', '" + imie_i_nazw + "','"+ data+"','"+numerWydz +"','"+staz+"','"+pensja+"')");
            posted.executeUpdate();
        } catch (Exception e) {
            System.out.println(e);
        } finally {
            System.out.println("Insert Completed.");
        }
    }
   public static  String deleteEmployee(String username, String password,String ID) throws Exception {
       String imie="";
        try {
            
            Connection con = getConnection(username, password);
            PreparedStatement statement = con.prepareStatement("delete from pracownicy where id_prac = "+ID);
            PreparedStatement statement2 = con.prepareStatement("SELECT * FROM pracownicy where id_prac = "+ID);
            ResultSet result2 = statement2.executeQuery();
            int result = statement.executeUpdate();

           
            
             while(result2.next())   
            imie=result2.getString("im_i_nazw");
 
        } catch (Exception e) {
            System.out.println(e);
        }
        System.out.println(imie);
        return imie;
    }
   public static  String displaySalary(String username, String password,String ID) throws Exception {
       String imie="";
       int pensja=0;
        try {
            
            Connection con = getConnection(username, password);
            PreparedStatement statement2 = con.prepareStatement("SELECT * FROM pracownicy where id_prac = "+ID);
            ResultSet result2 = statement2.executeQuery();
            

             while(result2.next())   
            pensja=result2.getInt("pensja");

        } 
        catch (Exception e)
        {
            System.out.println(e);
        }
        System.out.println(imie);
        imie=String.valueOf(pensja);
        System.out.println(imie);
        return imie;
    }
   public static  void changeSalary(String username, String password,String ID,String nowaPensja) throws Exception {
       
       int pensja=0;
        try {
            
            Connection con = getConnection(username, password);
            PreparedStatement statement2 = con.prepareStatement("UPDATE pracownicy SET Pensja = '"+nowaPensja+"' WHERE `pracownicy`.`id_prac` = "+ID);
            int result2 = statement2.executeUpdate();
  
        } 
        catch (Exception e)
        {
            System.out.println(e);
        }
       
        
    }

}
