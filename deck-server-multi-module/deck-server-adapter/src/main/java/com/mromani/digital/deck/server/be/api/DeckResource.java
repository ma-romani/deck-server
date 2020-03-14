package com.mromani.digital.deck.server.be.api;

import javax.ws.rs.core.Response;

import com.mromani.digital.deck.server.be.api.model.CardViewModel;


public interface DeckResource {
	
	public Response draw(String deckname, Integer draws);
	public Response putBack(String deckname, String card);
	public Response shuffle(String deckname);
	public Response reset(String deckname);
	public Response peek(String deckname, Integer draws);
	public Response resetPile(String deckname);
	Response use(String deckname, String _card);
	Response overrideDeck(String deckname, String _cards);
	Response overrideDiscardPile(String deckname, String _cards);
}
