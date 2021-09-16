/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package smallproject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

/**
 *
 * @author iamso
 */
public class JDBCSmallProject {
    public static void main(String[] args)throws IOException {
        
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        
        
        WebseriesJDBC webseriesJDBC = new WebseriesJDBC();
        
        System.out.println("Enter Series name : ");
        String seriesName = reader.readLine();
        System.out.println("Season : ");
        int season =  Integer.parseInt(reader.readLine());
        
        System.out.println("Release Year : ");
        int year =  Integer.parseInt(reader.readLine());
        
        System.out.println("Complete? (true/false) : ");
        boolean isCompleted =  Boolean.parseBoolean(reader.readLine());
        
        
        webseriesJDBC.insertData(seriesName, season, year, isCompleted);
        
    }
    
}


class WebseriesJDBC{
    Connection connection;
    
    public WebseriesJDBC(){ // established connection in constructor
        try{
            connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/jdbc_testing","root","root");
        }catch(SQLException e){
            System.out.println("Connection not established");
            System.exit(0);
        }catch(Exception e){
            System.out.println("Something is wrong");
            System.exit(0);
        }
    }
    
    public void finalize(){
        System.out.println("finalize invoked");
    }
    
    
    public void insertData(String seriesName, int season, int releaseYear, boolean isCompleted){
        //  insert into database

        
        try{
             String query = "INSERT INTO webseries VALUES(?,?,?,?)";
             PreparedStatement pstatement = connection.prepareStatement(query);
             
             pstatement.setString(1, seriesName);
             pstatement.setInt(2, season);
             pstatement.setInt(3, releaseYear);
             pstatement.setBoolean(4, isCompleted);
             
             
             int affactedRows = pstatement.executeUpdate();
             
             if(affactedRows >0){
                 System.out.println("Inserted successfully !!!");
             }else{
                 System.out.println("Not inserted !!!");
             }
        }catch(SQLException e){
            System.out.println("Error: something is wrong in query(may be) ");
        }
        
       
    }
    
    public void showData(){
        // show all the data from database
    }
    
    
}