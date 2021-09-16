/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package firstjavaproject;

/**
 *
 * @author iamso
 */


import java.sql.*;

public class FirstJavaProject {

    public static void main(String[] args) {
        
        try{
            
            System.out.println("Driver loaded succssfully");
            Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/twitter","root","root");  
            System.out.println("Connection successfull");
            
            
            
            
            
            
            
            String title = "Javascript";
            String content = "Most loved language";
            int favorites = 10324;
            
            String query = "INSERT INTO tweets VALUES(?,?,?)";
            PreparedStatement ps = con.prepareStatement(query);
            
            
            ps.setString(1, title);
            ps.setString(2, content);
            ps.setInt(3, favorites);
            
            
            
            int affactedRows = ps.executeUpdate();
            
            System.out.println(affactedRows+" rows affacted");
            
            Statement stmt = con.createStatement();
            
            
            
            
            ResultSet rs = stmt.executeQuery("SELECT * FROM tweets;");
            
            while(rs.next())  {          
                System.out.println(rs.getString(1));
                System.out.println(rs.getString(2));
                System.out.println(rs.getInt(3));
            }
            
            
            con.close();
            
        }
        catch(Exception e){
            System.out.println("error : "+e);
        }    
    }
    
}

