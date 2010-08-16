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
public class PlayedCards extends AbstractPack {


  public Card getLastCard(){
    if(getSize()>0){
      return getCard(getSize()-1);
    }
    return null;
  }
}
