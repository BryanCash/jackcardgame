/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.googlecode.jackcardgame.cardgame.cards;

import java.util.ArrayList;

/**
 *
 * @author ssoldatos
 */
public class PlayerHand extends AbstractPack {

  public void addCard(Card card) {
    if (cards.size() < 6) {
      cards.add(card);
    } else {
      throw new RuntimeException("Player can have no more than 6 cards");
    }
  }

 
}
