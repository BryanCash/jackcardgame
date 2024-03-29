/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

/*
 * Main.java
 *
 * Created on 9 Αυγ 2010, 11:13:41 πμ
 */
package com.googlecode.jackcardgame.cardgame;

import com.googlecode.jackcardgame.cardgame.cards.Card;
import com.googlecode.jackcardgame.cardgame.cards.CpuHand;
import com.googlecode.jackcardgame.cardgame.cards.CpuWonCards;
import com.googlecode.jackcardgame.cardgame.cards.Deck;
import com.googlecode.jackcardgame.cardgame.cards.PlayedCards;
import com.googlecode.jackcardgame.cardgame.cards.PlayerHand;
import com.googlecode.jackcardgame.cardgame.cards.PlayerWonCards;
import com.googlecode.jackcardgame.events.CardEvent;
import com.googlecode.jackcardgame.events.CardEventHandler;
import com.googlecode.jackcardgame.events.CardEventsClass;
import java.awt.Color;
import java.awt.Dimension;
import java.sql.SQLException;
import java.util.Enumeration;
import javax.swing.AbstractButton;
import javax.swing.Icon;
import javax.swing.border.Border;
import com.googlecode.jackcardgame.tools.Functions;
import java.awt.Font;
import java.awt.Image;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.Random;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.SwingUtilities;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;
import com.googlecode.jackcardgame.tools.ChooseDeck;
import com.googlecode.jackcardgame.tools.Database;
import com.googlecode.jackcardgame.tools.EndGame;
import com.googlecode.jackcardgame.tools.SelectUser;
import com.googlecode.jackcardgame.tools.Statistics;
import com.googlecode.jackcardgame.tools.User;

/**
 *
 * @author ssoldatos
 */
public class Main extends javax.swing.JFrame {

  private static final long serialVersionUID = 2352345345L;
  public static final int EASIEST = 0;
  public static final int EASY = 1;
  public static final int DIFFICULT = 2;
  public static final int HARD = 3;
  public static final int NONE = -1;
  public static final int PLAYER = 0;
  public static final int CPU = 1;
  public static CardEventsClass evClass = new CardEventsClass();
  public static int prevGameTurn = NONE;
  public Deck deck;
  public static boolean isGame;
  public PlayerHand playerHand = new PlayerHand();
  public CpuHand cpuHand = new CpuHand();
  public PlayedCards playedCards = new PlayedCards();
  public PlayerWonCards playerWonCards = new PlayerWonCards();
  public CpuWonCards cpuWonCards = new CpuWonCards();
  private boolean playersTurn = false;
  public JLabel label_remainingCards = new JLabel();
  public int lastWinner;
  public User user;
  public static Image woodtile;
  public static Image tsoxatile;
  public static Image marbletile;
  public static String deckName = Deck.DEFAULT;
  private Icon play;
  private Icon notPlay;

  /** Creates new form Main */
  public Main() {
    woodtile = new ImageIcon(getClass().getResource("/com/googlecode/jackcardgame/icons/woodtile.jpg")).getImage();
    tsoxatile = new ImageIcon(getClass().getResource("/com/googlecode/jackcardgame/icons/tsoxatile.jpg")).getImage();
    marbletile = new ImageIcon(getClass().getResource("/com/googlecode/jackcardgame/icons/marbletile.jpg")).getImage();
    play = new ImageIcon(getClass().getResource("/com/googlecode/jackcardgame/icons/go.png"));
    notPlay = new ImageIcon(getClass().getResource("/com/googlecode/jackcardgame/icons/stop.jpg"));


    initComponents();
    setTitle("Jack v1.0 by Spyros 2010");
    Database.connectToDB();
    try {
      UIManager.setLookAndFeel(UIManager.getInstalledLookAndFeels()[0].getClassName());
    } catch (ClassNotFoundException ex) {
      Logger.getLogger(Main.class.getName()).log(Level.SEVERE, null, ex);
    } catch (InstantiationException ex) {
      Logger.getLogger(Main.class.getName()).log(Level.SEVERE, null, ex);
    } catch (IllegalAccessException ex) {
      Logger.getLogger(Main.class.getName()).log(Level.SEVERE, null, ex);
    } catch (UnsupportedLookAndFeelException ex) {
      Logger.getLogger(Main.class.getName()).log(Level.SEVERE, null, ex);
    }
    selectUser(true);
    label_remainingCards.setFont(label_remainingCards.getFont().deriveFont(Font.BOLD, label_remainingCards.getFont().getSize() + 5));
    label_remainingCards.setForeground(Color.WHITE);
    setPreferredSize(new Dimension(756, 587));
    setIconImage(new ImageIcon(getClass().getResource("/com/googlecode/jackcardgame/icons/start-32x32.png")).getImage());

    //setSize(new Dimension(720, 572));
    setExtendedState(MAXIMIZED_BOTH);
    setLocationRelativeTo(null);
    startApp();

  }

  /** This method is called from within the constructor to
   * initialize the form.
   * WARNING: Do NOT modify this code. The content of this method is
   * always regenerated by the Form Editor.
   */
  @SuppressWarnings("unchecked")
  // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
  private void initComponents() {

    bg_dif = new javax.swing.ButtonGroup();
    toolbar = new javax.swing.JToolBar();
    bt_newGame = new javax.swing.JButton();
    bt_stop = new javax.swing.JButton();
    jSeparator1 = new javax.swing.JToolBar.Separator();
    tb_easiest = new javax.swing.JToggleButton();
    tb_easy = new javax.swing.JToggleButton();
    tb_difficult = new javax.swing.JToggleButton();
    tb_hard = new javax.swing.JToggleButton();
    jSeparator3 = new javax.swing.JToolBar.Separator();
    tb_cheat = new javax.swing.JToggleButton();
    tb_genious = new javax.swing.JToggleButton();
    jSeparator2 = new javax.swing.JToolBar.Separator();
    bt_deck = new javax.swing.JButton();
    bt_user = new javax.swing.JButton();
    tb_report = new javax.swing.JButton();
    bt_exit = new javax.swing.JButton();
    mainPanel = new com.googlecode.jackcardgame.tools.JTiledPanel(tsoxatile);
    panel_cpuCards = new com.googlecode.jackcardgame.tools.JTiledPanel(tsoxatile);
    panel_playersCards = new com.googlecode.jackcardgame.tools.JTiledPanel(tsoxatile);
    panel_cpuWonCards = new com.googlecode.jackcardgame.tools.JTiledLayeredPanel(tsoxatile);
    panel_remainingCards = new com.googlecode.jackcardgame.tools.JTiledPanel(tsoxatile);
    panel_playersWonCards = new com.googlecode.jackcardgame.tools.JTiledLayeredPanel(tsoxatile);
    label_playerPoints = new javax.swing.JLabel();
    label_cpuPoints = new javax.swing.JLabel();
    panel_playedCards = new com.googlecode.jackcardgame.tools.JTiledLayeredPanel();
    label_turn = new javax.swing.JLabel();
    EAST = new com.googlecode.jackcardgame.tools.JTiledPanel(woodtile);
    label_name = new javax.swing.JLabel();
    label_games = new javax.swing.JLabel();
    label_won = new javax.swing.JLabel();
    label_draw = new javax.swing.JLabel();
    label_lost = new javax.swing.JLabel();
    label_bigwin = new javax.swing.JLabel();
    label_biglost = new javax.swing.JLabel();
    jSeparator4 = new javax.swing.JSeparator();
    label_points = new javax.swing.JLabel();

    setDefaultCloseOperation(javax.swing.WindowConstants.DO_NOTHING_ON_CLOSE);
    setTitle("Cards Game");
    addWindowListener(new java.awt.event.WindowAdapter() {
      public void windowClosing(java.awt.event.WindowEvent evt) {
        formWindowClosing(evt);
      }
    });

    toolbar.setBackground(new java.awt.Color(255, 255, 255));
    toolbar.setFloatable(false);
    toolbar.setRollover(true);
    toolbar.setBorderPainted(false);

    bt_newGame.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/googlecode/jackcardgame/icons/start-32x32.png"))); // NOI18N
    bt_newGame.setToolTipText("New Game");
    bt_newGame.setFocusable(false);
    bt_newGame.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
    bt_newGame.setOpaque(false);
    bt_newGame.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
    bt_newGame.addActionListener(new java.awt.event.ActionListener() {
      public void actionPerformed(java.awt.event.ActionEvent evt) {
        bt_newGameActionPerformed(evt);
      }
    });
    toolbar.add(bt_newGame);

    bt_stop.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/googlecode/jackcardgame/icons/stop.jpg"))); // NOI18N
    bt_stop.setToolTipText("Stop Game");
    bt_stop.setFocusable(false);
    bt_stop.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
    bt_stop.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
    bt_stop.addActionListener(new java.awt.event.ActionListener() {
      public void actionPerformed(java.awt.event.ActionEvent evt) {
        bt_stopActionPerformed(evt);
      }
    });
    toolbar.add(bt_stop);

    jSeparator1.setSeparatorSize(new java.awt.Dimension(20, 40));
    toolbar.add(jSeparator1);

    tb_easiest.setBackground(new java.awt.Color(255, 255, 255));
    bg_dif.add(tb_easiest);
    tb_easiest.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/googlecode/jackcardgame/icons/easiest_alpha.png"))); // NOI18N
    tb_easiest.setSelected(true);
    tb_easiest.setToolTipText("Easiest");
    tb_easiest.setFocusable(false);
    tb_easiest.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
    tb_easiest.setRolloverIcon(new javax.swing.ImageIcon(getClass().getResource("/com/googlecode/jackcardgame/icons/easiest.png"))); // NOI18N
    tb_easiest.setSelectedIcon(new javax.swing.ImageIcon(getClass().getResource("/com/googlecode/jackcardgame/icons/easiest.png"))); // NOI18N
    tb_easiest.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
    toolbar.add(tb_easiest);

    tb_easy.setBackground(new java.awt.Color(255, 255, 255));
    bg_dif.add(tb_easy);
    tb_easy.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/googlecode/jackcardgame/icons/easy_alpha.png"))); // NOI18N
    tb_easy.setToolTipText("Easy");
    tb_easy.setFocusable(false);
    tb_easy.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
    tb_easy.setRolloverIcon(new javax.swing.ImageIcon(getClass().getResource("/com/googlecode/jackcardgame/icons/easy.png"))); // NOI18N
    tb_easy.setSelectedIcon(new javax.swing.ImageIcon(getClass().getResource("/com/googlecode/jackcardgame/icons/easy.png"))); // NOI18N
    tb_easy.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
    toolbar.add(tb_easy);

    tb_difficult.setBackground(new java.awt.Color(255, 255, 255));
    bg_dif.add(tb_difficult);
    tb_difficult.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/googlecode/jackcardgame/icons/dif_alpha.png"))); // NOI18N
    tb_difficult.setToolTipText("Difficult");
    tb_difficult.setFocusable(false);
    tb_difficult.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
    tb_difficult.setRolloverIcon(new javax.swing.ImageIcon(getClass().getResource("/com/googlecode/jackcardgame/icons/dif.png"))); // NOI18N
    tb_difficult.setSelectedIcon(new javax.swing.ImageIcon(getClass().getResource("/com/googlecode/jackcardgame/icons/dif.png"))); // NOI18N
    tb_difficult.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
    toolbar.add(tb_difficult);

    tb_hard.setBackground(new java.awt.Color(255, 255, 255));
    bg_dif.add(tb_hard);
    tb_hard.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/googlecode/jackcardgame/icons/hard_alpha.png"))); // NOI18N
    tb_hard.setToolTipText("Hard");
    tb_hard.setFocusable(false);
    tb_hard.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
    tb_hard.setRolloverIcon(new javax.swing.ImageIcon(getClass().getResource("/com/googlecode/jackcardgame/icons/hard.png"))); // NOI18N
    tb_hard.setSelectedIcon(new javax.swing.ImageIcon(getClass().getResource("/com/googlecode/jackcardgame/icons/hard.png"))); // NOI18N
    tb_hard.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
    toolbar.add(tb_hard);

    jSeparator3.setSeparatorSize(new java.awt.Dimension(20, 40));
    toolbar.add(jSeparator3);

    tb_cheat.setBackground(new java.awt.Color(255, 255, 255));
    tb_cheat.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/googlecode/jackcardgame/icons/cheat_alpha.png"))); // NOI18N
    tb_cheat.setToolTipText("Cheat");
    tb_cheat.setFocusable(false);
    tb_cheat.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
    tb_cheat.setRolloverIcon(new javax.swing.ImageIcon(getClass().getResource("/com/googlecode/jackcardgame/icons/cheat.png"))); // NOI18N
    tb_cheat.setSelectedIcon(new javax.swing.ImageIcon(getClass().getResource("/com/googlecode/jackcardgame/icons/cheat.png"))); // NOI18N
    tb_cheat.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
    tb_cheat.addActionListener(new java.awt.event.ActionListener() {
      public void actionPerformed(java.awt.event.ActionEvent evt) {
        tb_cheatActionPerformed(evt);
      }
    });
    toolbar.add(tb_cheat);

    tb_genious.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/googlecode/jackcardgame/icons/idea_alpha.png"))); // NOI18N
    tb_genious.setToolTipText("Be a genious");
    tb_genious.setFocusable(false);
    tb_genious.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
    tb_genious.setRolloverIcon(new javax.swing.ImageIcon(getClass().getResource("/com/googlecode/jackcardgame/icons/idea.png"))); // NOI18N
    tb_genious.setSelectedIcon(new javax.swing.ImageIcon(getClass().getResource("/com/googlecode/jackcardgame/icons/idea.png"))); // NOI18N
    tb_genious.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
    toolbar.add(tb_genious);

    jSeparator2.setSeparatorSize(new java.awt.Dimension(20, 40));
    toolbar.add(jSeparator2);

    bt_deck.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/googlecode/jackcardgame/icons/deck.png"))); // NOI18N
    bt_deck.setToolTipText("Select Deck");
    bt_deck.setFocusable(false);
    bt_deck.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
    bt_deck.setOpaque(false);
    bt_deck.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
    bt_deck.addActionListener(new java.awt.event.ActionListener() {
      public void actionPerformed(java.awt.event.ActionEvent evt) {
        bt_deckActionPerformed(evt);
      }
    });
    toolbar.add(bt_deck);

    bt_user.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/googlecode/jackcardgame/icons/user.png"))); // NOI18N
    bt_user.setToolTipText("Change user");
    bt_user.setFocusable(false);
    bt_user.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
    bt_user.setOpaque(false);
    bt_user.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
    bt_user.addActionListener(new java.awt.event.ActionListener() {
      public void actionPerformed(java.awt.event.ActionEvent evt) {
        bt_userActionPerformed(evt);
      }
    });
    toolbar.add(bt_user);

    tb_report.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/googlecode/jackcardgame/icons/report.png"))); // NOI18N
    tb_report.setToolTipText("Statistics");
    tb_report.setFocusable(false);
    tb_report.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
    tb_report.setOpaque(false);
    tb_report.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
    tb_report.addActionListener(new java.awt.event.ActionListener() {
      public void actionPerformed(java.awt.event.ActionEvent evt) {
        tb_reportActionPerformed(evt);
      }
    });
    toolbar.add(tb_report);

    bt_exit.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/googlecode/jackcardgame/icons/exit.png"))); // NOI18N
    bt_exit.setToolTipText("Exit");
    bt_exit.setFocusable(false);
    bt_exit.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
    bt_exit.setOpaque(false);
    bt_exit.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
    bt_exit.addActionListener(new java.awt.event.ActionListener() {
      public void actionPerformed(java.awt.event.ActionEvent evt) {
        bt_exitActionPerformed(evt);
      }
    });
    toolbar.add(bt_exit);

    getContentPane().add(toolbar, java.awt.BorderLayout.PAGE_START);

    mainPanel.setBackground(new java.awt.Color(153, 255, 153));
    mainPanel.setPreferredSize(new java.awt.Dimension(773, 520));

    panel_cpuCards.setBackground(new java.awt.Color(255, 255, 255));
    panel_cpuCards.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(255, 255, 255), 5));
    panel_cpuCards.setLayout(new java.awt.FlowLayout(java.awt.FlowLayout.LEFT));

    panel_playersCards.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(255, 255, 255), 5));
    panel_playersCards.setPreferredSize(new java.awt.Dimension(504, 188));
    panel_playersCards.setLayout(new java.awt.FlowLayout(java.awt.FlowLayout.LEFT));

    panel_cpuWonCards.setBackground(new java.awt.Color(255, 255, 255));
    panel_cpuWonCards.setBorder(javax.swing.BorderFactory.createEmptyBorder(5, 5, 5, 5));
    panel_cpuWonCards.setPreferredSize(new java.awt.Dimension(254, 137));

    javax.swing.GroupLayout panel_cpuWonCardsLayout = new javax.swing.GroupLayout(panel_cpuWonCards);
    panel_cpuWonCards.setLayout(panel_cpuWonCardsLayout);
    panel_cpuWonCardsLayout.setHorizontalGroup(
      panel_cpuWonCardsLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
      .addGap(0, 222, Short.MAX_VALUE)
    );
    panel_cpuWonCardsLayout.setVerticalGroup(
      panel_cpuWonCardsLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
      .addGap(0, 107, Short.MAX_VALUE)
    );

    panel_remainingCards.setBackground(new java.awt.Color(204, 255, 204));
    panel_remainingCards.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(255, 255, 255), 3));
    panel_remainingCards.setLayout(new java.awt.FlowLayout(java.awt.FlowLayout.CENTER, 5, 30));

    panel_playersWonCards.setBackground(new java.awt.Color(255, 255, 255));
    panel_playersWonCards.setBorder(javax.swing.BorderFactory.createEmptyBorder(5, 5, 5, 5));

    label_playerPoints.setBackground(new java.awt.Color(255, 255, 255));
    label_playerPoints.setFont(label_playerPoints.getFont().deriveFont(label_playerPoints.getFont().getStyle() | java.awt.Font.BOLD, label_playerPoints.getFont().getSize()+5));
    label_playerPoints.setForeground(new java.awt.Color(255, 255, 255));
    label_playerPoints.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
    label_playerPoints.setText("0");

    label_cpuPoints.setBackground(new java.awt.Color(255, 255, 255));
    label_cpuPoints.setFont(label_cpuPoints.getFont().deriveFont(label_cpuPoints.getFont().getStyle() | java.awt.Font.BOLD, label_cpuPoints.getFont().getSize()+5));
    label_cpuPoints.setForeground(new java.awt.Color(255, 255, 255));
    label_cpuPoints.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
    label_cpuPoints.setText("0");

    label_turn.setBackground(new java.awt.Color(255, 255, 255));
    label_turn.setFont(label_turn.getFont().deriveFont(label_turn.getFont().getStyle() | java.awt.Font.BOLD, label_turn.getFont().getSize()+4));
    label_turn.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
    label_turn.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/googlecode/jackcardgame/icons/stop.jpg"))); // NOI18N
    label_turn.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0), 2));
    label_turn.setOpaque(true);

    javax.swing.GroupLayout mainPanelLayout = new javax.swing.GroupLayout(mainPanel);
    mainPanel.setLayout(mainPanelLayout);
    mainPanelLayout.setHorizontalGroup(
      mainPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
      .addGroup(mainPanelLayout.createSequentialGroup()
        .addContainerGap()
        .addGroup(mainPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
          .addGroup(mainPanelLayout.createSequentialGroup()
            .addComponent(panel_cpuCards, javax.swing.GroupLayout.PREFERRED_SIZE, 516, javax.swing.GroupLayout.PREFERRED_SIZE)
            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
            .addGroup(mainPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
              .addGroup(mainPanelLayout.createSequentialGroup()
                .addComponent(label_cpuPoints, javax.swing.GroupLayout.DEFAULT_SIZE, 230, Short.MAX_VALUE)
                .addGap(2, 2, 2))
              .addComponent(panel_cpuWonCards, javax.swing.GroupLayout.DEFAULT_SIZE, 232, Short.MAX_VALUE)))
          .addGroup(mainPanelLayout.createSequentialGroup()
            .addComponent(panel_remainingCards, javax.swing.GroupLayout.PREFERRED_SIZE, 184, javax.swing.GroupLayout.PREFERRED_SIZE)
            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
            .addGroup(mainPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
              .addComponent(label_turn, javax.swing.GroupLayout.PREFERRED_SIZE, 330, javax.swing.GroupLayout.PREFERRED_SIZE)
              .addComponent(panel_playedCards, javax.swing.GroupLayout.DEFAULT_SIZE, 564, Short.MAX_VALUE)))
          .addGroup(mainPanelLayout.createSequentialGroup()
            .addComponent(panel_playersCards, javax.swing.GroupLayout.PREFERRED_SIZE, 520, javax.swing.GroupLayout.PREFERRED_SIZE)
            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
            .addGroup(mainPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
              .addComponent(label_playerPoints, javax.swing.GroupLayout.DEFAULT_SIZE, 228, Short.MAX_VALUE)
              .addGroup(mainPanelLayout.createSequentialGroup()
                .addComponent(panel_playersWonCards, javax.swing.GroupLayout.DEFAULT_SIZE, 226, Short.MAX_VALUE)
                .addGap(2, 2, 2)))))
        .addContainerGap())
    );
    mainPanelLayout.setVerticalGroup(
      mainPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
      .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, mainPanelLayout.createSequentialGroup()
        .addContainerGap()
        .addGroup(mainPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
          .addGroup(mainPanelLayout.createSequentialGroup()
            .addComponent(panel_cpuWonCards, javax.swing.GroupLayout.PREFERRED_SIZE, 117, javax.swing.GroupLayout.PREFERRED_SIZE)
            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
            .addComponent(label_cpuPoints))
          .addComponent(panel_cpuCards, javax.swing.GroupLayout.PREFERRED_SIZE, 144, javax.swing.GroupLayout.PREFERRED_SIZE))
        .addGroup(mainPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
          .addGroup(mainPanelLayout.createSequentialGroup()
            .addGap(7, 7, 7)
            .addComponent(panel_remainingCards, javax.swing.GroupLayout.PREFERRED_SIZE, 172, javax.swing.GroupLayout.PREFERRED_SIZE))
          .addGroup(mainPanelLayout.createSequentialGroup()
            .addGap(8, 8, 8)
            .addComponent(panel_playedCards, javax.swing.GroupLayout.PREFERRED_SIZE, 123, javax.swing.GroupLayout.PREFERRED_SIZE)
            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
            .addComponent(label_turn)))
        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
        .addGroup(mainPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
          .addGroup(mainPanelLayout.createSequentialGroup()
            .addComponent(panel_playersWonCards, javax.swing.GroupLayout.PREFERRED_SIZE, 117, javax.swing.GroupLayout.PREFERRED_SIZE)
            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
            .addComponent(label_playerPoints))
          .addComponent(panel_playersCards, 0, 0, Short.MAX_VALUE))
        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
    );

    mainPanelLayout.linkSize(javax.swing.SwingConstants.VERTICAL, new java.awt.Component[] {panel_cpuCards, panel_playersCards});

    getContentPane().add(mainPanel, java.awt.BorderLayout.CENTER);

    EAST.setPreferredSize(new java.awt.Dimension(200, 10));

    label_name.setFont(label_name.getFont().deriveFont(label_name.getFont().getStyle() | java.awt.Font.BOLD, label_name.getFont().getSize()+5));
    label_name.setForeground(new java.awt.Color(255, 255, 255));
    label_name.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);

    label_games.setFont(label_games.getFont().deriveFont(label_games.getFont().getStyle() | java.awt.Font.BOLD, label_games.getFont().getSize()+5));
    label_games.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
    label_games.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/googlecode/jackcardgame/icons/start-32x32.png"))); // NOI18N
    label_games.setText("0");
    label_games.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);
    label_games.setIconTextGap(50);

    label_won.setFont(label_won.getFont().deriveFont(label_won.getFont().getStyle() | java.awt.Font.BOLD, label_won.getFont().getSize()+5));
    label_won.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
    label_won.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/googlecode/jackcardgame/icons/easy.png"))); // NOI18N
    label_won.setText("0");
    label_won.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);
    label_won.setIconTextGap(50);

    label_draw.setFont(label_draw.getFont().deriveFont(label_draw.getFont().getStyle() | java.awt.Font.BOLD, label_draw.getFont().getSize()+5));
    label_draw.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
    label_draw.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/googlecode/jackcardgame/icons/dif.png"))); // NOI18N
    label_draw.setText("0");
    label_draw.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);
    label_draw.setIconTextGap(50);

    label_lost.setFont(label_lost.getFont().deriveFont(label_lost.getFont().getStyle() | java.awt.Font.BOLD, label_lost.getFont().getSize()+5));
    label_lost.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
    label_lost.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/googlecode/jackcardgame/icons/hard.png"))); // NOI18N
    label_lost.setText("0");
    label_lost.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);
    label_lost.setIconTextGap(50);

    label_bigwin.setFont(label_bigwin.getFont().deriveFont(label_bigwin.getFont().getStyle() | java.awt.Font.BOLD, label_bigwin.getFont().getSize()+5));
    label_bigwin.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
    label_bigwin.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/googlecode/jackcardgame/icons/easiest.png"))); // NOI18N
    label_bigwin.setText("0");
    label_bigwin.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);
    label_bigwin.setIconTextGap(50);

    label_biglost.setFont(label_biglost.getFont().deriveFont(label_biglost.getFont().getStyle() | java.awt.Font.BOLD, label_biglost.getFont().getSize()+5));
    label_biglost.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
    label_biglost.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/googlecode/jackcardgame/icons/hard.png"))); // NOI18N
    label_biglost.setText("0");
    label_biglost.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);
    label_biglost.setIconTextGap(50);

    label_points.setFont(label_points.getFont().deriveFont(label_points.getFont().getStyle() | java.awt.Font.BOLD, label_points.getFont().getSize()+5));
    label_points.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
    label_points.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/googlecode/jackcardgame/icons/report.png"))); // NOI18N
    label_points.setText("0");
    label_points.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);
    label_points.setIconTextGap(50);

    javax.swing.GroupLayout EASTLayout = new javax.swing.GroupLayout(EAST);
    EAST.setLayout(EASTLayout);
    EASTLayout.setHorizontalGroup(
      EASTLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
      .addGroup(EASTLayout.createSequentialGroup()
        .addContainerGap()
        .addGroup(EASTLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
          .addComponent(label_name, javax.swing.GroupLayout.DEFAULT_SIZE, 180, Short.MAX_VALUE)
          .addComponent(label_biglost, javax.swing.GroupLayout.DEFAULT_SIZE, 180, Short.MAX_VALUE)
          .addComponent(label_bigwin, javax.swing.GroupLayout.DEFAULT_SIZE, 180, Short.MAX_VALUE)
          .addComponent(label_points, javax.swing.GroupLayout.DEFAULT_SIZE, 180, Short.MAX_VALUE)
          .addComponent(label_lost, javax.swing.GroupLayout.DEFAULT_SIZE, 180, Short.MAX_VALUE)
          .addComponent(label_draw, javax.swing.GroupLayout.DEFAULT_SIZE, 180, Short.MAX_VALUE)
          .addComponent(label_games, javax.swing.GroupLayout.DEFAULT_SIZE, 180, Short.MAX_VALUE)
          .addComponent(label_won, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, 180, Short.MAX_VALUE)
          .addComponent(jSeparator4, javax.swing.GroupLayout.DEFAULT_SIZE, 180, Short.MAX_VALUE))
        .addContainerGap())
    );
    EASTLayout.setVerticalGroup(
      EASTLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
      .addGroup(EASTLayout.createSequentialGroup()
        .addContainerGap()
        .addComponent(label_name, javax.swing.GroupLayout.PREFERRED_SIZE, 37, javax.swing.GroupLayout.PREFERRED_SIZE)
        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        .addComponent(label_games)
        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
        .addComponent(label_won)
        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
        .addComponent(label_draw)
        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
        .addComponent(label_lost)
        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
        .addComponent(label_points)
        .addGap(26, 26, 26)
        .addComponent(jSeparator4, javax.swing.GroupLayout.PREFERRED_SIZE, 14, javax.swing.GroupLayout.PREFERRED_SIZE)
        .addGap(18, 18, 18)
        .addComponent(label_bigwin)
        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
        .addComponent(label_biglost)
        .addGap(580, 580, 580))
    );

    getContentPane().add(EAST, java.awt.BorderLayout.EAST);

    java.awt.Dimension screenSize = java.awt.Toolkit.getDefaultToolkit().getScreenSize();
    setBounds((screenSize.width-990)/2, (screenSize.height-580)/2, 990, 580);
  }// </editor-fold>//GEN-END:initComponents

    private void bt_newGameActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_bt_newGameActionPerformed
      startNewGame();
    }//GEN-LAST:event_bt_newGameActionPerformed

    private void tb_cheatActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_tb_cheatActionPerformed
      cheat(tb_cheat.isSelected());
    }//GEN-LAST:event_tb_cheatActionPerformed

    private void bt_exitActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_bt_exitActionPerformed
      if (Functions.confirm("Exit", "Really Exit?") == JOptionPane.YES_OPTION) {
        exit();
      }
    }//GEN-LAST:event_bt_exitActionPerformed

    private void bt_deckActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_bt_deckActionPerformed
      String tmpDeck = deckName;
      new ChooseDeck(this, true);
      if (Deck.isValidDeck(deckName)) {
        setDeck(deckName);
      } else {
        setDeck(tmpDeck);
      }
    }//GEN-LAST:event_bt_deckActionPerformed

  private void setDeck(String deck) {
    Main.deckName = deck;
    if (panel_remainingCards.getComponentCount() > 0) {
      Card card = (Card) panel_remainingCards.getComponent(0);
      card.setShown(false);
    }
  }

    private void bt_stopActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_bt_stopActionPerformed
      if (isGame) {
        if (Functions.confirm("Quit", "Do you relly want to quit this game?") == JOptionPane.YES_OPTION) {
          clearAllData();
          setDiffEnabled(true);
        }
      }
    }//GEN-LAST:event_bt_stopActionPerformed

    private void bt_userActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_bt_userActionPerformed
      selectUser(false);
    }//GEN-LAST:event_bt_userActionPerformed

    private void tb_reportActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_tb_reportActionPerformed
      new Statistics(this, true);
    }//GEN-LAST:event_tb_reportActionPerformed

    private void formWindowClosing(java.awt.event.WindowEvent evt) {//GEN-FIRST:event_formWindowClosing
      CardEvent ce = new CardEvent(this, null, CardEventHandler.EXIT_APP);
      evClass.fireMyEvent(ce);
    }//GEN-LAST:event_formWindowClosing

  private void cheat(boolean cheat) {
    ArrayList<Card> cards = cpuHand.getAllCards();
    for (Iterator<Card> it = cards.iterator(); it.hasNext();) {
      Card card = it.next();
      card.setShown(cheat);
    }
    panel_cpuCards.validate();
    panel_cpuCards.repaint();
  }

  public void startNewGame() {
    if (isGame) {
      if (Functions.confirm("New Game", "Start new game?") != JOptionPane.YES_OPTION) {
        return;
      } else {
        NewGame ng = new NewGame();
        Thread t = new Thread(ng);
        t.start();
      }
    } else {
      NewGame ng = new NewGame();
      Thread t = new Thread(ng);
      t.start();
    }
    deck = new Deck();
    isGame = true;
    setDiffEnabled(false);
    deck.shuffle();
    deal(true);
  }

  public void updatePanels() {
    panel_playedCards.validate();
    panel_playedCards.repaint();
    panel_cpuCards.validate();
    panel_cpuCards.repaint();
    validateTree();
    repaint();
  }

  public void clearAllPanels() {
    panel_cpuCards.removeAll();
    panel_cpuWonCards.removeAll();
    panel_playedCards.removeAll();
    panel_playersCards.removeAll();
    panel_playersWonCards.removeAll();
    panel_remainingCards.removeAll();
    validateTree();
    repaint();
  }

  /**
   * @param args the command line arguments
   */
  public static void main(String args[]) {
    java.awt.EventQueue.invokeLater(new Runnable() {

      public void run() {
        new Main().setVisible(true);
      }
    });
  }

  /**
   * @return the playersTurn
   */
  public boolean isPlayersTurn() {
    return playersTurn;
  }

  /**
   * @param aPlayersTurn the playersTurn to set
   */
  public void setPlayersTurn(final boolean turn) {
    playersTurn = turn;
    SwingUtilities.invokeLater(new Runnable() {

      public void run() {
        if (turn) {
          label_turn.setIcon(play);
        } else {
          label_turn.setIcon(notPlay);
        }
      }
    });

  }

  public void cpuPlay() {
    int difficulty = getDifficulty();
    Card cardToPlay = getWinningCard();
    if (cardToPlay == null) {
      switch (difficulty) {
        case EASIEST:
          cardToPlay = getRandomCard();
          break;
        case EASY:
          cardToPlay = getAICard(1);
          break;
        case DIFFICULT:
          cardToPlay = getAICard(2);
          break;
        case HARD:
          cardToPlay = getAICard(3);
          break;
      }
    }
    cardToPlay.setShown(true);
    final Card card = cardToPlay;
    SwingUtilities.invokeLater(new Runnable() {

      public void run() {
        CardEvent event = new CardEvent(Main.this, card, CardEventHandler.PLAY_CARD);
        event.sourcePanel = panel_cpuCards;
        event.targetPanel = panel_playedCards;
        evClass.fireMyEvent(event);
      }
    });
  }

  private Card getAICard(int times) {
    if (times == 0) {
      return getRandomCard();
    }
    for (Iterator<Card> it = cpuHand.getAllCards().iterator(); it.hasNext();) {
      Card cpuCard = it.next();
      int passed = countPassed(cpuCard.rank, Card.CPU);
      if (passed >= times + 1) {
        return cpuCard;
      }
    }

    return getAICard(times - 1);

  }

  public int countPassed(int rank, int owner) {
    int times = 0;
    ArrayList<Card> down = playedCards.getAllCards();
    for (Iterator<Card> it = down.iterator(); it.hasNext();) {
      Card card = it.next();
      if (card.rank == rank) {
        times++;
      }
    }
    ArrayList<Card> hand;
    if (owner == Card.CPU) {
      hand = cpuHand.getAllCards();
    } else {
      hand = playerHand.getAllCards();
    }
    for (Iterator<Card> it = hand.iterator(); it.hasNext();) {
      Card card = it.next();
      if (card.rank == rank) {
        times++;
      }
    }
    ArrayList<Card> cpuWon = cpuWonCards.getAllCards();
    for (Iterator<Card> it = cpuWon.iterator(); it.hasNext();) {
      Card card = it.next();
      if (card.rank == rank) {
        times++;
      }
    }
    ArrayList<Card> playerWon = playerWonCards.getAllCards();
    for (Iterator<Card> it = playerWon.iterator(); it.hasNext();) {
      Card card = it.next();
      if (card.rank == rank) {
        times++;
      }
    }
    return times;
  }

  private int getDifficulty() {
    if (tb_easiest.isSelected()) {
      return EASIEST;
    } else if (tb_easy.isSelected()) {
      return EASY;
    } else if (tb_difficult.isSelected()) {
      return DIFFICULT;
    } else if (tb_hard.isSelected()) {
      return HARD;
    }
    return EASY;
  }

  private Card getRandomCard() {
    Card c;
    int cards = cpuHand.getSize();
    Random gen = new Random();
    int random = gen.nextInt(cards);
    c = cpuHand.getCard(random);
    if (c.rank == Card.JACK && playedCards.getSize() == 0 && cpuHand.getSize() > 1) {
      return getRandomCard();
    }
    return c;
  }

  private Card getWinningCard() {
    if (playedCards.getSize() == 0) {
      return null;
    }
    int rank = playedCards.getLastCard().rank;
    Card card = cpuHand.getByRank(rank);
    if (card != null) {
      return card;
    }
    card = cpuHand.getByRank(Card.JACK);
    if (card != null) {
      return card;
    }
    return null;
  }

  public void deal(boolean down) {
    Dealer d = new Dealer(down);
    Thread t = new Thread(d);
    t.start();
  }

  public void computePoints() {
    ScoreComputer c = new ScoreComputer();
    Thread t = new Thread(c);
    t.start();
  }

  public void endGame() {
    int playerPoints = Integer.parseInt(label_playerPoints.getText());
    int cpuPoints = Integer.parseInt(label_cpuPoints.getText());
    int winner;
    String result;
    if (playerPoints > cpuPoints) {
      winner = Database.PLAYER_WIN;
      result = EndGame.WON;
    } else if (cpuPoints > playerPoints) {
      winner = Database.CPU_WIN;
      result = EndGame.LOST;
    } else {
      winner = Database.DRAW;
      result = EndGame.DRAW;
    }
    String sql = "INSERT INTO games (user_id,difficulty,winner,userPoints,cpuPoints,date) " + "VALUES (" + user.user_id + "," + getDifficulty() + "," + winner + "," + playerPoints + "," + cpuPoints + ",'" + Functions.getToday() + "'" + ")";
    try {
      Database.stmt.executeUpdate(sql);
      updateUserStats();
      new EndGame(this, true, result);
      clearAllData();
    } catch (SQLException ex) {
      Logger.getLogger(Main.class.getName()).log(Level.SEVERE, null, ex);
    }
  }

  public void setDiffEnabled(boolean b) {
    Enumeration<AbstractButton> but = bg_dif.getElements();
    while (but.hasMoreElements()) {
      AbstractButton abstractButton = but.nextElement();
      abstractButton.setEnabled(b);
    }
    bt_deck.setEnabled(b);
    bt_user.setEnabled(b);
  }

  private void updateUserStats() {
    user.updateStats();
    label_name.setText(user.name);
    label_games.setText(String.valueOf(user.games));
    label_won.setText(String.valueOf(user.won) + " (" + getPerc(user.won, user.games) + "%)");
    label_draw.setText(String.valueOf(user.draw) + " (" + getPerc(user.draw, user.games) + "%)");
    label_lost.setText(String.valueOf(user.lost) + " (" + getPerc(user.lost, user.games) + "%)");
    label_points.setText(String.valueOf(user.pointsWon + "-" + user.pointsLost));
    label_bigwin.setText(String.valueOf(user.biggestWin));
    label_biglost.setText(String.valueOf(user.biggestLost));

  }

  private void startApp() {
    CardEvent c = new CardEvent(this, null, CardEventHandler.START_APP);
    evClass.fireMyEvent(c);
  }

  public void clearAllData() {
    clearAllPanels();
    Card c = new Card();
    c.main = this;
    isGame = false;
    setDiffEnabled(true);
    c.setBounds(CardEventHandler.DECK_LEFT_MARGIN, CardEventHandler.DECK_TOP_MARGIN, Card.CARD_WIDTH, Card.CARD_HEIGHT);
    panel_remainingCards.add(c);
    panel_remainingCards.add(label_remainingCards);
    label_remainingCards.setText(String.valueOf(Deck.TOTAL_CARDS));
    playerHand.removeAllCards();
    cpuHand.removeAllCards();
    playedCards.removeAllCards();
    playerWonCards.removeAllCards();
    cpuWonCards.removeAllCards();
    playerWonCards.kseres = 0;
    cpuWonCards.kseres = 0;
    label_cpuPoints.setText("0");
    label_playerPoints.setText("0");
  }

  private void selectUser(boolean allowEntry) {
    SelectUser su = new SelectUser(this, true,allowEntry);
    user = su.user;
    updateUserStats();
    setDeck(user.deckname);
    setDifficulty(user.difficulty);
  }

  private int getPerc(int a, int b) {
    return (int) (((double) a / b) * 100);
  }

  public void exit() {
    user.difficulty = getDifficulty();
    user.deckname = deckName;
    try {
      user.save();
    } catch (SQLException ex) {
      Logger.getLogger(Main.class.getName()).log(Level.SEVERE, null, ex);
    } finally {
      System.exit(0);
    }
  }

  private void setDifficulty(int difficulty) {
    if (difficulty == EASIEST) {
      tb_easiest.setSelected(true);
    } else if (difficulty == EASY) {
      tb_easy.setSelected(true);
    } else if (difficulty == DIFFICULT) {
      tb_difficult.setSelected(true);
    } else if (difficulty == HARD) {
      tb_hard.setSelected(true);
    }
  }

  class ScoreComputer implements Runnable {

    public void run() {
      int playerPoints = playerWonCards.kseres * 10;
      int cpuPoints = cpuWonCards.kseres * 10;
      ArrayList<Card> playerCards = playerWonCards.getAllCards();
      for (Iterator<Card> it = playerCards.iterator(); it.hasNext();) {
        Card card = it.next();
        playerPoints += card.getValue();
      }
      ArrayList<Card> cpuCards = cpuWonCards.getAllCards();
      for (Iterator<Card> it = cpuCards.iterator(); it.hasNext();) {
        Card card = it.next();
        cpuPoints += card.getValue();
      }
      if (playerWonCards.getSize() > cpuWonCards.getSize()) {
        playerPoints += 3;
      } else if (playerWonCards.getSize() < cpuWonCards.getSize()) {
        cpuPoints += 3;
      }
      label_playerPoints.setText(String.valueOf(playerPoints));
      label_cpuPoints.setText(String.valueOf(cpuPoints));
    }
  }

  class NewGame implements Runnable {

    public void run() {
      CardEvent event = new CardEvent(Main.this, null, CardEventHandler.NEW_GAME);
      evClass.fireMyEvent(event);
    }
  }

  public class Dealer implements Runnable {

    private final boolean dealDown;

    public Dealer(boolean dealDown) {
      this.dealDown = dealDown;
    }

    public void run() {
      if (deck.cardsLeft() == 0) {
        return;
      }
      for (int i = 0; i < 3; i++) {
        if (isPlayersTurn()) {
          dealPlayer(2);
          dealCpu(2);
        } else {
          dealCpu(2);
          dealPlayer(2);
        }

      }
      if (dealDown) {
        for (int i = 0; i < 4; i++) {
          Card card = deck.dealCard();
          card.enabled = false;
          card.setShown(true);
          card.main = Main.this;
          CardEvent event = new CardEvent(Main.this, card, CardEventHandler.DEAL_CARD_DOWN);
          event.targetPanel = panel_playedCards;
          evClass.fireMyEvent(event);
          Functions.waitMs(200);

        }
      }


      if (deck.cardsLeft() == 0) {
        panel_remainingCards.removeAll();
        panel_remainingCards.validate();
        panel_remainingCards.repaint();
      }

      if (!isPlayersTurn()) {
        CardEvent event = new CardEvent(Main.this, null, CardEventHandler.CPU_THINK);
        evClass.fireMyEvent(event);
      }
    }

    private void dealPlayer(int times) {
      for (int i = 0; i < times; i++) {
        Card card = deck.dealCard();
        card.enabled = true;
        card.owner = Card.PLAYER;
        card.main = Main.this;
        card.setShown(true);
        CardEvent event = new CardEvent(Main.this, card, CardEventHandler.DEAL_CARD);
        event.targetPanel = panel_playersCards;
        evClass.fireMyEvent(event);
        Functions.waitMs(200);
      }
    }

    private void dealCpu(int times) {
      for (int i = 0; i < times; i++) {
        Card card = deck.dealCard();
        card.enabled = false;
        card.setShown(tb_cheat.isSelected());
        card.owner = Card.CPU;
        card.main = Main.this;
        CardEvent event = new CardEvent(Main.this, card, CardEventHandler.DEAL_CARD);
        event.targetPanel = panel_cpuCards;
        evClass.fireMyEvent(event);
        Functions.waitMs(200);
      }
    }
  }
  // Variables declaration - do not modify//GEN-BEGIN:variables
  public com.googlecode.jackcardgame.tools.JTiledPanel EAST;
  public javax.swing.ButtonGroup bg_dif;
  public javax.swing.JButton bt_deck;
  public javax.swing.JButton bt_exit;
  public javax.swing.JButton bt_newGame;
  public javax.swing.JButton bt_stop;
  public javax.swing.JButton bt_user;
  public javax.swing.JToolBar.Separator jSeparator1;
  public javax.swing.JToolBar.Separator jSeparator2;
  public javax.swing.JToolBar.Separator jSeparator3;
  public javax.swing.JSeparator jSeparator4;
  public javax.swing.JLabel label_biglost;
  public javax.swing.JLabel label_bigwin;
  public javax.swing.JLabel label_cpuPoints;
  public javax.swing.JLabel label_draw;
  public javax.swing.JLabel label_games;
  public javax.swing.JLabel label_lost;
  public javax.swing.JLabel label_name;
  public javax.swing.JLabel label_playerPoints;
  public javax.swing.JLabel label_points;
  public javax.swing.JLabel label_turn;
  public javax.swing.JLabel label_won;
  public com.googlecode.jackcardgame.tools.JTiledPanel mainPanel;
  public com.googlecode.jackcardgame.tools.JTiledPanel panel_cpuCards;
  public com.googlecode.jackcardgame.tools.JTiledLayeredPanel panel_cpuWonCards;
  public com.googlecode.jackcardgame.tools.JTiledLayeredPanel panel_playedCards;
  public com.googlecode.jackcardgame.tools.JTiledPanel panel_playersCards;
  public com.googlecode.jackcardgame.tools.JTiledLayeredPanel panel_playersWonCards;
  public com.googlecode.jackcardgame.tools.JTiledPanel panel_remainingCards;
  public javax.swing.JToggleButton tb_cheat;
  public javax.swing.JToggleButton tb_difficult;
  public javax.swing.JToggleButton tb_easiest;
  public javax.swing.JToggleButton tb_easy;
  public javax.swing.JToggleButton tb_genious;
  public javax.swing.JToggleButton tb_hard;
  public javax.swing.JButton tb_report;
  public javax.swing.JToolBar toolbar;
  // End of variables declaration//GEN-END:variables
}
