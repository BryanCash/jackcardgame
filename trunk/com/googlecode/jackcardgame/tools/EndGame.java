/*
 * To change this template, choose com.googlecode.jackcardgame.tools | Templates
 * and open the template in the editor.
 */

/*
 * EndGame.java
 *
 * Created on 11 Αυγ 2010, 7:09:42 μμ
 */
package com.googlecode.jackcardgame.tools;

import com.googlecode.jackcardgame.cardgame.Main;
import java.awt.Cursor;
import javax.swing.ImageIcon;

/**
 *
 * @author lordovol
 */
public class EndGame extends javax.swing.JDialog {

  private ImageIcon icon;
  public static final String WON = "won";
  public static final String LOST = "lost";
  public static final String DRAW = "draw";
  private String type;

  /** Creates new form EndGame */
  public EndGame(java.awt.Frame parent, boolean modal, String type) {
    super(parent, modal);
    this.type = type;
    icon = new ImageIcon(getClass().getResource("/com/googlecode/jackcardgame/icons/" + type + ".png"));
    initComponents();
    jLabel1.setCursor(new Cursor(Cursor.HAND_CURSOR));
    //setSize(100, 140);
    Main m = (Main) parent;
    label_score.setText(m.label_playerPoints.getText() + "-"+m.label_cpuPoints.getText());
    setLocationRelativeTo(parent);
    setVisible(true);

  }

  /** This method is called from within the constructor to
   * initialize the form.
   * WARNING: Do NOT modify this code. The content of this method is
   * always regenerated by the Form Editor.
   */
  @SuppressWarnings("unchecked")
  // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
  private void initComponents() {

    jTiledPanel1 = new com.googlecode.jackcardgame.tools.JTiledPanel(Main.tsoxatile);
    jLabel1 = new javax.swing.JLabel();
    label_score = new javax.swing.JLabel();

    setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
    setResizable(false);
    setUndecorated(true);

    jTiledPanel1.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(255, 255, 255), 3));

    jLabel1.setBackground(new java.awt.Color(255, 255, 255));
    jLabel1.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
    jLabel1.setIcon(icon);
    jLabel1.setPreferredSize(new java.awt.Dimension(100, 100));
    jLabel1.addMouseListener(new java.awt.event.MouseAdapter() {
      public void mouseReleased(java.awt.event.MouseEvent evt) {
        jLabel1MouseReleased(evt);
      }
    });
    jTiledPanel1.add(jLabel1);

    getContentPane().add(jTiledPanel1, java.awt.BorderLayout.CENTER);

    label_score.setBackground(new java.awt.Color(255, 255, 255));
    label_score.setFont(label_score.getFont().deriveFont(label_score.getFont().getStyle() | java.awt.Font.BOLD, label_score.getFont().getSize()+5));
    label_score.setForeground(new java.awt.Color(0, 102, 51));
    label_score.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
    label_score.setOpaque(true);
    label_score.setPreferredSize(new java.awt.Dimension(100, 20));
    getContentPane().add(label_score, java.awt.BorderLayout.SOUTH);

    pack();
  }// </editor-fold>//GEN-END:initComponents

    private void jLabel1MouseReleased(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel1MouseReleased
      dispose();
    }//GEN-LAST:event_jLabel1MouseReleased

  // Variables declaration - do not modify//GEN-BEGIN:variables
  private javax.swing.JLabel jLabel1;
  private com.googlecode.jackcardgame.tools.JTiledPanel jTiledPanel1;
  private javax.swing.JLabel label_score;
  // End of variables declaration//GEN-END:variables
}
