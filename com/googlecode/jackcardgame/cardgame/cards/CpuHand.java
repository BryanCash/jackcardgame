/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.googlecode.jackcardgame.cardgame.cards;

import java.util.Iterator;

/**
 *
 * @author lordovol
 */
public class CpuHand extends AbstractPack {

  public void addCard(Card card) {
    if (cards.size() < 6) {
      cards.add(card);
    } else {
      throw new RuntimeException("Player can have no more than 6 cards");
    }
  }

  public Card getByRank(int rank) {
    for (Iterator<Card> it = cards.iterator(); it.hasNext();) {
      Card card = it.next();
      if (card.rank == rank) {
        return card;
      }

    }
    return null;
  }
}
