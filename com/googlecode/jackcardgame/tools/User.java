/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.googlecode.jackcardgame.tools;

import com.googlecode.jackcardgame.cardgame.Main;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Vector;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author ssoldatos
 */
public class User {

  public int user_id;
  public String name;
  public int difficulty = Main.EASIEST;
  public String deckname = "default";
  public int games = 0;
  public int won = 0;
  public int draw = 0;
  public int lost = 0;
  public int pointsWon = 0;
  public int pointsLost = 0;
  public String biggestWin = "";
  public String biggestLost = "";

  public User(int user_id, String name) {
    this.user_id = user_id;
    this.name = name;
  }

  public void save() throws SQLException {
    String sql = "";
    if (this.user_id == -1) {
      sql = "INSERT INTO users (name) VALUES ('" + name + "')";
    } else {
      sql = "UPDATE users SET difficulty = " + this.difficulty + " , deckname ='" + this.deckname + "' WHERE "
          + "user_id = " + this.user_id;
    }
    Database.stmt.executeUpdate(sql);
  }

  @Override
  public String toString() {
    return name;
  }

  public void updateStats() {
    try {
      ResultSet rs = Database.stmt.executeQuery("SELECT COUNT(*) FROM games WHERE user_id = " + this.user_id);
      if (rs.next()) {
        games = rs.getInt(1);
      }
      rs = Database.stmt.executeQuery("SELECT COUNT(*) FROM games WHERE winner =" + Database.PLAYER_WIN + " AND user_id = " + this.user_id);
      if (rs.next()) {
        won = rs.getInt(1);
      }
      rs = Database.stmt.executeQuery("SELECT COUNT(*) FROM games WHERE winner =" + Database.DRAW + " AND user_id = " + this.user_id);
      if (rs.next()) {
        draw = rs.getInt(1);
      }
      rs = Database.stmt.executeQuery("SELECT COUNT(*) FROM games WHERE winner =" + Database.CPU_WIN + " AND user_id = " + this.user_id);
      if (rs.next()) {
        lost = rs.getInt(1);
      }
      rs = Database.stmt.executeQuery("SELECT SUM(userPoints) FROM games WHERE user_id = " + this.user_id);
      if (rs.next()) {
        pointsWon = rs.getInt(1);
      }
      rs = Database.stmt.executeQuery("SELECT SUM(cpuPoints) FROM games WHERE user_id = " + this.user_id);
      if (rs.next()) {
        pointsLost = rs.getInt(1);
      }
      rs = Database.stmt.executeQuery("select MAX(userPoints-cpuPoints) AS maximum , userPoints, cpuPoints from GAMES where user_id =" + this.user_id + " GROUP BY game_id ORDER BY maximum DESC");
      if (rs.next()) {
        biggestWin = rs.getInt(1) + " (" + rs.getInt(2) + "-" + rs.getInt(3) + ")";
      }
      rs = Database.stmt.executeQuery("select MAX(cpuPoints-userPoints) AS maximum , userPoints, cpuPoints from GAMES where user_id =" + this.user_id + " GROUP BY game_id ORDER BY maximum DESC");
      if (rs.next()) {
        biggestLost = rs.getInt(1) + " (" + rs.getInt(2) + "-" + rs.getInt(3) + ")";
      }
    } catch (SQLException ex) {
      Logger.getLogger(User.class.getName()).log(Level.SEVERE, null, ex);
    }
  }

  public static Vector<User> getUsers() {
    Vector<User> users = new Vector<User>();
    try {
      ResultSet rs = Database.stmt.executeQuery("SELECT * FROM users");
      while (rs.next()) {
        int id = rs.getInt("user_id");
        String name = rs.getString("name");
        int difficulty = rs.getInt("difficulty");
        String deckname = rs.getString("deckname");
        User u = new User(id, name);
        u.difficulty = difficulty;
        u.deckname = deckname;
        users.add(u);
      }
      return users;
    } catch (SQLException ex) {
      return null;
    }
  }

  public static User getUserByName(String name) {
    try {
      ResultSet rs = Database.stmt.executeQuery("SELECT * FROM users WHERE name = '" + name + "'");
      while (rs.next()) {
        int id = rs.getInt("user_id");
        name = rs.getString("name");
        return new User(id, name);
      }
      return null;
    } catch (SQLException ex) {
      Logger.getLogger(User.class.getName()).log(Level.SEVERE, null, ex);
      return null;
    }
  }
}
