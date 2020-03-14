package com.mromani.digital.deck.server.be.api.model;

import com.mromani.digital.deck.server.be.model.Card;


public class CardViewModelMapper {
	private CardEffectMap effects = new CardEffectMap();
	public CardViewModelMapper() {}
	
	public CardViewModel map(Card _card) {
		var card = new CardViewModel();
		card.setCarta(_card.getName());
		card.setEffetto(effects.getEffect(_card.getName()));
		return card;
	}
}
