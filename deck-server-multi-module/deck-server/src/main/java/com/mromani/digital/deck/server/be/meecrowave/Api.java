package com.mromani.digital.deck.server.be.meecrowave;

import javax.enterprise.context.Dependent;
import javax.ws.rs.ApplicationPath;


@Dependent
@ApplicationPath("api")
public class Api extends javax.ws.rs.core.Application {

  public Api() {
  }

}
