package com.mromani.digital.deck.server.be.api.model;

import javax.json.bind.annotation.JsonbTransient;

import org.eclipse.microprofile.openapi.annotations.media.Schema;

@Schema(name = "Card", description = "")
public class CardViewModel {

  @Schema(required = true, example = "black lotus")
  private String carta;
  private String effetto;

  public CardViewModel() {
  }

  public String getCarta() {
    return carta;
  }
  
  public String getEffetto() {
	  return effetto;
  }

  public void setCarta(final String _name) {
    this.carta = _name;
  }
  
  @JsonbTransient
  public void setEffetto(final String _effect) {
	    this.effetto = _effect;
  }

  @Override
  public String toString() {
    return new StringBuilder().append(getClass().getSimpleName())
                              .append("[")
                              .append("name=")
                              .append(getCarta())
                              .append("]")
                              .toString();
  }

@Override
public int hashCode() {
	final int prime = 31;
	int result = 1;
	result = prime * result + ((carta == null) ? 0 : carta.hashCode());
	return result;
}

@Override
public boolean equals(Object obj) {
	if (this == obj)
		return true;
	if (obj == null)
		return false;
	if (getClass() != obj.getClass())
		return false;
	CardViewModel other = (CardViewModel) obj;
	if (carta == null) {
		if (other.carta != null)
			return false;
	} else if (!carta.equals(other.carta))
		return false;
	return true;
}

}
