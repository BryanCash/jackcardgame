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
public abstract class AbstractPack {

  protected ArrayList<Card> cards = new ArrayList<Card>();
  public int kseres;

  public void addCard(Card card){
    cards.add(card);
  }

  public void removeAllCards() {
    cards.clear();
  }

  public void removeCard(Card card) {
    cards.remove(card);
  }

  public int getSize(){
    return cards.size();
  }

  public ArrayList<Card> getAllCards() {
    return cards;
  }

  public Card getCard(int i){
    return cards.get(i);
  }

  public void addCards(ArrayList<Card> coll) {
    cards.addAll(coll);
  }

  protected AbstractPack() {
  }
}
