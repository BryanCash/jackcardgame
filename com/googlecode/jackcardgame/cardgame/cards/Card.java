/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

/*
 * CardFace.java
 *
 * Created on 9 Αυγ 2010, 1:01:49 μμ
 */
package com.googlecode.jackcardgame.cardgame.cards;

import com.googlecode.jackcardgame.cardgame.Main;
import com.googlecode.jackcardgame.events.CardEvent;
import com.googlecode.jackcardgame.events.CardEventHandler;
import java.awt.Dimension;
import java.awt.Image;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.net.URL;
import javax.swing.ImageIcon;
import javax.swing.SwingUtilities;

/**
 *
 * @author ssoldatos
 */
public class Card extends javax.swing.JPanel implements CardConstants {

  private static final long serialVersionUID = 23544534563456L;
  public static int CARD_WIDTH = 75;
  public static int CARD_HEIGHT = 107;
  /** The owners of the card  **/
  public static int DECK = -1;
  public static int PLAYER = 0;
  public static int CPU = 1;
  public int rank;
  public int suit;
  private boolean shown = false;
  public Main main;
  public boolean enabled;
  public int owner = DECK;
  private int value = 0;

  public Card() {
    this(Card.ACE, Card.HEARTS);
  }

  /** Creates new form CardFace */
  public Card(int rank, int suit) {
    this.rank = rank;
    this.suit = suit;
    value = computeValue();
    initComponents();
    icon.setIcon(getImage());
    setPreferredSize(new Dimension(CARD_WIDTH, CARD_HEIGHT));
    icon.addMouseListener(new MouseAdapter() {

      @Override
      public void mouseEntered(MouseEvent e) {
        if(owner == Card.PLAYER && main.tb_genious.isSelected()){
          int passed = main.countPassed(Card.this.rank,Card.PLAYER);
          System.out.println(passed);
          icon.setToolTipText(String.valueOf(passed));
        }
      }

      @Override
      public void mouseExited(MouseEvent e) {
            icon.setToolTipText("");
      }





      @Override
      public void mouseReleased(MouseEvent e) {
        // if (owner == -1 && main.playedCards.getSize() == 0 && main.cpuHand.getSize() == 0) {
        //   main.deal(false);
        //}
        if (enabled && main.isPlayersTurn()) {
          SwingUtilities.invokeLater(new Runnable() {

            public void run() {
              CardEvent event = new CardEvent(main, Card.this, CardEventHandler.PLAY_CARD);
              event.sourcePanel = main.panel_playersCards;
              event.targetPanel = main.panel_playedCards;
              Main.evClass.fireMyEvent(event);
              enabled = false;
            }
          });


        } else if (owner == DECK && !Main.isGame) {
          main.startNewGame();
        }
      }
    });
  }

  /**
   * @return the image
   */
  private ImageIcon getImage() {
    URL file;
    String path;
    if (isShown()) {
      path = Deck.DECK_PATH + "/" + Main.deckName + "/" + getSuitName() + "-" + rank + ".png";
    } else {
      path = Deck.DECK_PATH + "/" + Main.deckName + "/back.png";
    }
    Image fullscaled = new ImageIcon(path).getImage();
    return new ImageIcon(fullscaled.getScaledInstance(CARD_WIDTH, CARD_HEIGHT, Image.SCALE_SMOOTH));
  }

  public String getSuitName() {
    switch (suit) {
      case CLUBS:
        return "clubs";
      case SPADES:
        return "spades";
      case HEARTS:
        return "hearts";
      case DIAMONDS:
        return "diamonds";
    }
    return "";
  }

  public String getRankName() {
    String rankName = "";
    switch (rank) {
      case ACE:
        return "Ace";
      case DEUCE:
        return "Deuce";
      case THREE:
        return "Three";
      case FOUR:
        return "Four";
      case FIVE:
        return "Five";
      case SIX:
        return "Six";
      case SEVEN:
        return "Seven";
      case EIGHT:
        return "Eight";
      case NINE:
        return "Nine";
      case TEN:
        return "Ten";
      case JACK:
        return "Jack";
      case QUEEN:
        return "Queen";
      case KING:
        return "King";
    }
    return "";
  }

  /**
   * @return the shown
   */
  public boolean isShown() {
    return shown;
  }

  /**
   * @param shown the shown to set
   */
  public void setShown(boolean shown) {
    this.shown = shown;
    icon.setIcon(getImage());
  }

  @Override
  public String toString() {
    return getRankName() + " of " + getSuitName();
  }

  /** This method is called from within the constructor to
   * initialize the form.
   * WARNING: Do NOT modify this code. The content of this method is
   * always regenerated by the Form Editor.
   */
  @SuppressWarnings("unchecked")
  // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
  private void initComponents() {

    icon = new javax.swing.JLabel();

    setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
    setMaximumSize(new java.awt.Dimension(75, 107));
    setMinimumSize(new java.awt.Dimension(75, 107));

    javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
    this.setLayout(layout);
    layout.setHorizontalGroup(
      layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
      .addComponent(icon, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 75, javax.swing.GroupLayout.PREFERRED_SIZE)
    );
    layout.setVerticalGroup(
      layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
      .addComponent(icon, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 107, javax.swing.GroupLayout.PREFERRED_SIZE)
    );
  }// </editor-fold>//GEN-END:initComponents
  // Variables declaration - do not modify//GEN-BEGIN:variables
  protected javax.swing.JLabel icon;
  // End of variables declaration//GEN-END:variables

  /**
   * @return the value
   */
  public int getValue() {
    return value;
  }

  private int computeValue() {
    switch (rank) {
      case ACE:
        return 1;
      case JACK:
        return 1;
      case QUEEN:
        return 1;
      case KING:
        return 1;
      case DEUCE:
        if (suit == CLUBS) {
          return 1;
        }
        return 0;
      case TEN:
        if (suit == DIAMONDS) {
          return 2;
        }
        return 1;
    }
    return 0;
  }
}
