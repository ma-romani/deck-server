package com.mromani.digital.deck.server.be.model;

import static java.util.Objects.requireNonNull;

import java.util.Objects;

public class Card {

  private String name;


  public Card(String _name) {
    name = requireNonNull(_name);
  }

  @Override
  public boolean equals(Object obj) {
    if (this == obj)
      return true;
    if (obj == null)
      return false;
    if (getClass() != obj.getClass())
      return false;
    Card other = (Card) obj;
    return Objects.equals(name, other.name);
  }

  public String getName() {
    return name;
  }


  @Override
  public int hashCode() {
    return Objects.hash(name);
  }

  

  @Override
  public String toString() {
    return new StringBuilder().append(getClass().getSimpleName())
                              .append("[")
                              .append("name=")
                              .append(name)
                              .append("]")
                              .toString();
  }

}
