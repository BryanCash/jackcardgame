/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.googlecode.jackcardgame.events;

import com.googlecode.jackcardgame.cardgame.Main;
import com.googlecode.jackcardgame.cardgame.cards.AbstractPack;
import com.googlecode.jackcardgame.cardgame.cards.Card;
import com.googlecode.jackcardgame.cardgame.cards.Deck;
import com.googlecode.jackcardgame.cardgame.cards.PlayedCards;
import com.googlecode.jackcardgame.tools.Functions;
import java.awt.Container;
import com.googlecode.jackcardgame.tools.JTiledLayeredPanel;

/**
 *
 * @author ssoldatos
 */
public class CardEventHandler implements CardEventListener {

  public static final int START_APP = -1;
  public static final int NEW_GAME = 0;
  public static final int DEAL_CARD = 1;
  public static final int DEAL_CARD_DOWN = 2;
  public static final int CPU_GET_CARD = 3;
  public static final int PLAY_CARD = 4;
  public static final int CPU_THINK = 5;
  public static int TOP_MARGIN = 10;
  public static int KSERI_TOP_MARGIN = 2;
  public static int KSERI_LEFT_MARGIN = 20;
  public static int PLAYED_LEFT_MARGIN = 20;
  public static int TAKEN_LEFT_MARGIN = 10;
  public static int TAKEN_TOP_MARGIN = 2;
  public static int DECK_LEFT_MARGIN = 10;
  public static int DECK_TOP_MARGIN = 6;
  private Main main = null;
  private Container sourcePanel;
  private Container targetPanel;

  @Override
  public void myEventOccured(CardEvent evt) {
    if (evt.getSource() instanceof Main) {
      main = (Main) evt.getSource();
    }
    Card card = evt.card;
    switch (evt.type) {
      case START_APP:
        Card c = new Card();
        c.main = main;
        c.setBounds(DECK_LEFT_MARGIN, DECK_TOP_MARGIN, Card.CARD_WIDTH, Card.CARD_HEIGHT);
        main.panel_remainingCards.add(c);
        main.panel_remainingCards.add(main.label_remainingCards);
        main.label_remainingCards.setText(String.valueOf(Deck.TOTAL_CARDS));
        break;
      case NEW_GAME:
       main.clearAllData();
       if(Main.prevGameTurn == Main.PLAYER){
          main.setPlayersTurn(false);
          Main.prevGameTurn = Main.CPU;
       }else if(Main.prevGameTurn == Main.CPU){
          main.setPlayersTurn(true);
          Main.prevGameTurn = Main.PLAYER;
       }else{
         boolean turn = (int) (Math.random() * 10) % 2 == 0 ? true : false;
        main.setPlayersTurn(turn);
        Main.prevGameTurn = turn ? Main.PLAYER : Main.CPU;
       }
        
        break;
      case DEAL_CARD_DOWN:
        targetPanel = evt.targetPanel;
        main.playedCards.addCard(card);
        card.setBounds(main.playedCards.getSize() * (Card.CARD_WIDTH / 5), TOP_MARGIN, Card.CARD_WIDTH, Card.CARD_HEIGHT);
        targetPanel.add(card, new Integer(main.playedCards.getSize()));
        targetPanel.validate();
        targetPanel.repaint();
        break;
      case DEAL_CARD:
        targetPanel = evt.targetPanel;
        if (card.owner == Card.PLAYER) {
          main.playerHand.addCard(card);
        } else {
          main.cpuHand.addCard(card);
        }
        targetPanel.add(card);
        targetPanel.validate();
        targetPanel.repaint();
        break;
      case PLAY_CARD:
        Card lastCard = main.playedCards.getLastCard();
        main.setPlayersTurn(card.owner == Card.CPU);
        JTiledLayeredPanel layerPanel =  (JTiledLayeredPanel) evt.targetPanel;
        sourcePanel = evt.sourcePanel;

        AbstractPack handToRemoveFrom;
        AbstractPack wonPack;
        Container wonPanel;
        int lastWon;
        if (card.owner == Card.PLAYER) {
          handToRemoveFrom = main.playerHand;
          wonPack = main.playerWonCards;
          wonPanel = main.panel_playersWonCards;
          lastWon = Main.PLAYER;
        } else {
          handToRemoveFrom = main.cpuHand;
          wonPack = main.cpuWonCards;
          wonPanel = main.panel_cpuWonCards;
          lastWon = Main.CPU;
        }
        handToRemoveFrom.removeCard(card);
        sourcePanel.remove(card);
        sourcePanel.validate();
        sourcePanel.repaint();

        main.playedCards.addCard(card);
        card.setBounds(main.playedCards.getSize() * PLAYED_LEFT_MARGIN, TOP_MARGIN, Card.CARD_WIDTH, Card.CARD_HEIGHT);
        layerPanel.add(card, new Integer(main.playedCards.getSize()));
        layerPanel.validate();
        layerPanel.repaint();


        if (card.owner == Card.CPU) {
          com.googlecode.jackcardgame.tools.Functions.waitMs(1500);
        }
        if (lastCard != null) {
          if (takeCards(lastCard, card)) {
            if (kseri(main.playedCards, lastCard, card)) {
              if (jackKseri(lastCard, card)) {
                card.setShown(true);
                wonPack.kseres++;
              }
              wonPack.kseres++;
              card.setShown(true);
              card.setBounds(wonPack.kseres * KSERI_LEFT_MARGIN, KSERI_TOP_MARGIN, Card.CARD_WIDTH, Card.CARD_HEIGHT);
            } else {
              card.setShown(false);
              card.setBounds(TAKEN_LEFT_MARGIN, TAKEN_TOP_MARGIN, Card.CARD_WIDTH, Card.CARD_HEIGHT);
            }
            wonPanel.add(card);
            wonPack.addCards(main.playedCards.getAllCards());
            main.playedCards.removeAllCards();
            main.panel_playedCards.removeAll();
            main.computePoints();
            main.updatePanels();
            main.lastWinner = lastWon;
          } else {
            lastWon = Main.NONE;
          }
        }
        if (handEnd() || gameEnd()) {
          if (gameEnd()) {
            Main.isGame = false;
            main.setDiffEnabled(true);
            if (main.playedCards.getSize() > 0) {
              if (main.lastWinner == Main.CPU) {
                main.cpuWonCards.addCards(main.playedCards.getAllCards());
              } else {
                main.playerWonCards.addCards(main.playedCards.getAllCards());
              }
              main.playedCards.removeAllCards();
              main.panel_playedCards.removeAll();
            }
            main.computePoints();
            main.updatePanels();
            Functions.waitMs(1000);
            main.endGame();
          } else {
            main.deal(false);
          }
          break;
        }
        if (card.owner == Card.PLAYER) {
          CardEvent event = new CardEvent(main, null, CPU_THINK);
          Main.evClass.fireMyEvent(event);
        }
        
        break;
      case CPU_THINK:
        main.cpuPlay();
        main.setPlayersTurn(true);
        break;
    }
    if (main.deck != null) {
      main.label_remainingCards.setText(String.valueOf(main.deck.cardsLeft()));
    }
  }

  private boolean handEnd() {
    if (main.playerHand.getSize() == 0 && main.cpuHand.getSize() == 0) {
      return true;
    }
    return false;
  }

  private boolean gameEnd() {
    if (main.playerHand.getSize() == 0 && main.cpuHand.getSize() == 0 && main.deck.cardsLeft() == 0) {
      return true;
    }
    return false;
  }

  private boolean takeCards(Card lastCard, Card card) {
    if (lastCard.rank == card.rank || card.rank == Card.JACK) {
      return true;
    }
    return false;
  }

  private boolean kseri(PlayedCards playedCards, Card lastCard, Card card) {
    if (playedCards.getSize() == 2 && lastCard.rank == card.rank) {
      return true;
    }
    return false;
  }

  private boolean jackKseri(Card lastCard, Card card) {
    if (lastCard.rank == Card.JACK) {
      return true;
    }
    return false;
  }
}
