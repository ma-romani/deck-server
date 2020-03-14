package com.mromani.digital.deck.server.be.service;

import static java.util.logging.Level.INFO;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;
import java.util.logging.Logger;
import java.util.stream.Collectors;
import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;

import com.mromani.digital.deck.server.be.model.Card;

@ApplicationScoped
public class DeckServiceImpl implements DeckService{
	
	private final Logger _log = Logger.getLogger(getClass().getName());
	

	private Map<String, ArrayList<Card>> decks = new HashMap<String, ArrayList<Card>>();
	private Map<String, ArrayList<Card>> discards = new HashMap<String, ArrayList<Card>>();

	
	public DeckServiceImpl() {
		decks = new HashMap<String, ArrayList<Card>>();
		discards = new HashMap<String, ArrayList<Card>>();
		resetAll();
	}
	
	@Override
	public void resetAll() {
		reset("treachery");
		reset("spices");
		reset("traitors");
	}
	
	@Override
	public void reset(String deckname) {
		var cards = new ArrayList<Card>();
		_log.log(INFO, "resetting " + deckname);
		switch(deckname) {
		case "treachery":

			cards.add(new Card("treacheryCard_1"));
			cards.add(new Card("treacheryCard_2"));
			cards.add(new Card("treacheryCard_3"));
			cards.add(new Card("treacheryCard_4"));


			break;
		case "spices":

			break;
		case "traitors":

			break;
		}
		setDeck(deckname, cards);
		_log.log(INFO, decks.toString());
		setDiscard(deckname, cards);

		shuffle(deckname);
	}
	
	@Override
	public List<Card> draw(String deckname, int draws) {
		
		var drawn = new ArrayList<Card>(); 
		for(int i=0;i<draws;i++){ 
			if(decks.get(deckname).isEmpty()) {
				break;
			}
			drawn.add(pop(decks.get(deckname)));
		}
		return drawn;
		
	}
	
	@Override
	public List<Card> peek(String deckname, int peeks) {
		
		var peeked = new ArrayList<Card>();
		for(int i=0;i<peeks;i++){ 
			if(i > decks.get(deckname).size()-1) {
				break;
			}
			peeked.add(decks.get(deckname).get(i));
		}
		return peeked;
		
	}
	
	@Override
	public void setDeck(String deckname, List<Card> _cards) {
		if (decks.containsKey(deckname)){
			decks.replace(deckname, new ArrayList<Card>(_cards));
		}else {
			decks.put(deckname, new ArrayList<Card>(_cards));
		}
	}
	
	@Override
	public void setDiscard(String deckname, List<Card> _cards) {
		if (discards.containsKey(deckname)){
			discards.replace(deckname, new ArrayList<Card>(_cards));
		}else {
			discards.put(deckname, new ArrayList<Card>(_cards));
		}
	}
	
	@Override
	public void shuffle(String deckname) {
		_log.log(INFO, "shuffling " + deckname);
		_log.log(INFO, decks.get(deckname).toString());
		Collections.shuffle(decks.get(deckname));;
	}
	
	private Card pop(List<Card> deck) {
		return deck.remove(0);
	}
	
	@Override
	public void addTop(String deckname, Card card) {
		var cards = new ArrayList<Card>();
		cards.add(card);
		
		cards.addAll(decks.get(deckname));
		decks.replace(deckname, cards);
	}
	
	@Override
	public void resetPile(String deckname) {
		var discardpile = discards.get(deckname);
		setDiscard(deckname, new ArrayList<Card>());
		setDeck(deckname, discardpile);
		shuffle(deckname);
	}
	
	@Override
	public void use(String deckname, Card card) {
		discards.get(deckname).add(card);
	}
	


}
