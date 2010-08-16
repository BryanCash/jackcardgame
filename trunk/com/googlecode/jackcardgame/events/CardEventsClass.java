/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.googlecode.jackcardgame.events;

import javax.swing.event.EventListenerList;

/**
 *
 * @author ssoldatos
 */
public class CardEventsClass implements CardEventListenerInterface {

  protected EventListenerList listenerList = new EventListenerList();

  public CardEventsClass() {
    addCustomEventListener(new CardEventHandler());
  }



  @Override
  public void addCustomEventListener(CardEventListener listener) {
    listenerList.add(CardEventListener.class, listener);
  }

  @Override
  public void removeMyEventListener(CardEventListener listener) {
    listenerList.remove(CardEventListener.class, listener);
  }

  
  @Override
  public void fireMyEvent(CardEvent evt) {
    Object[] listeners = listenerList.getListenerList();
    for (int i = 0; i < listeners.length; i = i + 2) {
      if (listeners[i] == CardEventListener.class) {
        ((CardEventListener) listeners[i + 1]).myEventOccured(evt);
      }
    }
  }
}
