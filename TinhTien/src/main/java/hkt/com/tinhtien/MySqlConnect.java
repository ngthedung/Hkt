/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package hkt.com.tinhtien;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 *
 * @author MRG
 */
public class MySqlConnect {
    final static String HOST = "localhost";
    final static String DATABASE = "bank";
    final static String USER = "root";
    final static String PASS = "";
    
    public static Connection connectMySql() throws SQLException, ClassNotFoundException{
        return getConnectMySql(HOST, DATABASE, USER, PASS);
    }
    
    public static Connection getConnectMySql(String HOST,String DATABASE,String USER,String PASS) throws SQLException, ClassNotFoundException{
        String urlConnect = "jdbc:mysql://" + HOST + ":3307/" + DATABASE;
        Connection connect = DriverManager.getConnection(urlConnect, USER, PASS);
        return connect;
    }
}
