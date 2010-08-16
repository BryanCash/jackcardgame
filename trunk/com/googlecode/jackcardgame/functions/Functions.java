/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.googlecode.jackcardgame.functions;

import javax.swing.JOptionPane;

/**
 *
 * @author ssoldatos
 */
public class Functions {

  public static int confirm(String title, String message) {
    return JOptionPane.showConfirmDialog(null, message, title, JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE);
  }

  public static void waitMs(int ms) {
    long t0, t1;
    t0 = System.currentTimeMillis();
    do {
      t1 = System.currentTimeMillis();
    } while ((t1 - t0) < (ms));
  }

  public static void Error(String title, String message) {
    JOptionPane.showMessageDialog(null, message,title,JOptionPane.ERROR_MESSAGE);
  }

  private Functions() {
  }
}
