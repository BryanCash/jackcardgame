/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package com.googlecode.jackcardgame.events;

import java.util.EventListener;

/**
 *
 * @author ssoldatos
 */
public interface CardEventListener extends EventListener{

  public void myEventOccured(CardEvent evt);
}

