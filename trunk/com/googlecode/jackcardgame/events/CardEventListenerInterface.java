/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.googlecode.jackcardgame.events;

/**
 *
 * @author ssoldatos
 */
public interface CardEventListenerInterface {

  public void addCustomEventListener(CardEventListener listener);

  public void removeMyEventListener(CardEventListener listener);

  void fireMyEvent(CardEvent evt);
}
