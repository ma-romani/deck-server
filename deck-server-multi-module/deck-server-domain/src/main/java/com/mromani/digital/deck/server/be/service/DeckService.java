package com.mromani.digital.deck.server.be.service;

import java.util.List;
import java.util.Map;

import com.mromani.digital.deck.server.be.model.Card;

public interface DeckService {
	
	public List<Card> draw(String deckname, int draws);
	public List<Card> peek(String deckname, int peeks);
	public void shuffle(String deckname);
	void setDeck(String deckname, List<Card> _cards);
	void addTop(String deckname, Card card);
	void resetAll();
	void reset(String deckname);
	void resetPile(String deckname);
	void setDiscard(String deckname, List<Card> _cards);
	void use(String deckname, Card card);
	
}
