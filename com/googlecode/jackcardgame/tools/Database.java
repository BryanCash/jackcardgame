/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.googlecode.jackcardgame.tools;

import java.io.File;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Iterator;
import java.util.Vector;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author ssoldatos
 */
public class Database {

  public static Connection conn;
  public static Statement stmt;
  public static int PLAYER_WIN = 0;
  public static int CPU_WIN = 1;
  public static int DRAW = 2;
  private static String database = "./hi.db";

  public static void connectToDB() {
    if ((new File(database).isFile())) {
      connect();
    } else {
      connect();
      create();
    }


  }

  private static void connect() {
    try {
      Class.forName("org.sqlite.JDBC");
      conn = DriverManager.getConnection("jdbc:sqlite:" + database);
      stmt = conn.createStatement();
    } catch (SQLException ex) {
      Logger.getLogger(Database.class.getName()).log(Level.SEVERE, null, ex);
    } catch (ClassNotFoundException ex) {
      Logger.getLogger(Functions.class.getName()).log(Level.SEVERE, null, ex);
    }
  }

  private static void create() {
    try {
      String sql = "CREATE TABLE users ("
          + "user_id INTEGER PRIMARY KEY  AUTOINCREMENT  NOT NULL  UNIQUE,"
          + "name VARCHAR(40) unique"
          + ")";
      stmt.executeUpdate(sql);
      sql = "CREATE TABLE games ("
          + "	game_id INTEGER PRIMARY KEY  AUTOINCREMENT  NOT NULL  UNIQUE,"
          + "	user_id NUMERIC,"
          + "	difficulty NUMERIC default 0,"
          + "	winner NUMERIC default 2,"
          + "	userPoints NUMERIC default 0,"
          + "	cpuPoints NUMERIC default 0,"
          + "	date DATE"
          + ")";
      stmt.executeUpdate(sql);
    } catch (SQLException ex) {
      Logger.getLogger(Database.class.getName()).log(Level.SEVERE, null, ex);
    }
  }

  
}
