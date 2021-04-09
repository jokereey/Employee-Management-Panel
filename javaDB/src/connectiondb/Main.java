package connectiondb;

import GUI.LoginGUI;

public class Main
{    
    public static void main(String[] args) {

        Connect connectDB = new Connect();
        try 
        {
            LoginGUI logowanie = new LoginGUI();
        } 
        catch (Exception e) 
        {
            e.printStackTrace();
        }
    }

    

}
