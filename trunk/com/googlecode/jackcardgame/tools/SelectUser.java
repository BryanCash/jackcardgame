package com.googlecode.jackcardgame.tools;

/*
 * To change this template, choose com.googlecode.jackcardgame.tools | Templates
 * and open the template in the editor.
 */

/*
 * SelectUser.java
 *
 * Created on 10 Αυγ 2010, 2:34:27 μμ
 */
import com.googlecode.jackcardgame.cardgame.Main;
import com.googlecode.jackcardgame.tools.Database;
import com.googlecode.jackcardgame.tools.User;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.DefaultComboBoxModel;

/**
 *
 * @author ssoldatos
 */
public class SelectUser extends javax.swing.JDialog {

  DefaultComboBoxModel usersModel = new DefaultComboBoxModel(User.getUsers());
  public User user = null;
  private boolean allowEntry;

  /** Creates new form SelectUser */
  public SelectUser(java.awt.Frame parent, boolean modal, boolean allowEntry) {
    super(parent, modal);
    this.allowEntry = allowEntry;
    initComponents();
    setLocationRelativeTo(null);
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
    cb_users = new javax.swing.JComboBox();
    bt_ok = new javax.swing.JButton();

    setDefaultCloseOperation(javax.swing.WindowConstants.DO_NOTHING_ON_CLOSE);
    setTitle("Select User");
    setModal(true);
    setResizable(false);
    setUndecorated(true);

    jTiledPanel1.setBorder(new javax.swing.border.SoftBevelBorder(javax.swing.border.BevelBorder.RAISED, new java.awt.Color(102, 255, 51), null, new java.awt.Color(0, 0, 0), new java.awt.Color(0, 153, 51)));

    jLabel1.setFont(jLabel1.getFont().deriveFont(jLabel1.getFont().getStyle() | java.awt.Font.BOLD, jLabel1.getFont().getSize()+2));
    jLabel1.setForeground(new java.awt.Color(255, 255, 255));
    jLabel1.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
    jLabel1.setText("Select User");

    cb_users.setEditable(allowEntry);
    cb_users.setModel(usersModel);

    bt_ok.setText("OK");
    bt_ok.addActionListener(new java.awt.event.ActionListener() {
      public void actionPerformed(java.awt.event.ActionEvent evt) {
        bt_okActionPerformed(evt);
      }
    });

    javax.swing.GroupLayout jTiledPanel1Layout = new javax.swing.GroupLayout(jTiledPanel1);
    jTiledPanel1.setLayout(jTiledPanel1Layout);
    jTiledPanel1Layout.setHorizontalGroup(
      jTiledPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
      .addGroup(jTiledPanel1Layout.createSequentialGroup()
        .addContainerGap()
        .addGroup(jTiledPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
          .addGroup(jTiledPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
            .addComponent(cb_users, javax.swing.GroupLayout.Alignment.LEADING, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(jLabel1, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, 212, Short.MAX_VALUE))
          .addComponent(bt_ok))
        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
    );
    jTiledPanel1Layout.setVerticalGroup(
      jTiledPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
      .addGroup(jTiledPanel1Layout.createSequentialGroup()
        .addContainerGap()
        .addComponent(jLabel1)
        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
        .addComponent(cb_users, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        .addComponent(bt_ok)
        .addContainerGap())
    );

    javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
    getContentPane().setLayout(layout);
    layout.setHorizontalGroup(
      layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
      .addComponent(jTiledPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
    );
    layout.setVerticalGroup(
      layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
      .addComponent(jTiledPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
    );

    pack();
  }// </editor-fold>//GEN-END:initComponents

  private void bt_okActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_bt_okActionPerformed
    Object si = cb_users.getSelectedItem();
    if (si instanceof User) {
      user = (User) si;
      dispose();
    } else {
      if (si instanceof String) {
        String name = ((String) si).trim();
        if (!name.equals("")) {
          User u = new User(-1, name);
          try {
            u.save();
          } catch (SQLException ex) {
             if(ex.getMessage().equals("column name is not unique")){
             Functions.Error("Insert User", "This username already exists");
             }
            Logger.getLogger(SelectUser.class.getName()).log(Level.SEVERE, null, ex);
          }
          user = User.getUserByName(name);
          dispose();
        }
      }
    }
    
  }//GEN-LAST:event_bt_okActionPerformed

  /**
   * @param args the command line arguments
   */
  public static void main(String args[]) {
  }
  // Variables declaration - do not modify//GEN-BEGIN:variables
  private javax.swing.JButton bt_ok;
  private javax.swing.JComboBox cb_users;
  private javax.swing.JLabel jLabel1;
  private com.googlecode.jackcardgame.tools.JTiledPanel jTiledPanel1;
  // End of variables declaration//GEN-END:variables
}
