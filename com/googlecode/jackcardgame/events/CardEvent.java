/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.googlecode.jackcardgame.events;

import com.googlecode.jackcardgame.cardgame.cards.Card;
import java.awt.Container;
import java.util.EventObject;
import javax.swing.JPanel;

/**
 *
 * @author ssoldatos
 */
public class CardEvent extends EventObject {

  private static final long serialVersionUID = 34536467474567L;
  public int type;
  public Card card;
  public Container targetPanel;
  public Container sourcePanel;
  
  public CardEvent(Object source,Card card, int type) {
    super(source);
    this.type = type;
    this.card = card;
  }
   
}
