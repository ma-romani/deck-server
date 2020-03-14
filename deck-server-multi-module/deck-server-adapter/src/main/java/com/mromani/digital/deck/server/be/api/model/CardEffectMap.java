package com.mromani.digital.deck.server.be.api.model;

import java.util.HashMap;
import java.util.Map;

import javax.enterprise.context.ApplicationScoped;


public class CardEffectMap {

	private Map<String, String> effectMap = new HashMap<String, String>();
	
	public CardEffectMap() {
		effectMap.put("treacheryCard", "treacheryEffect");
	}
	
	public String getEffect(String card) {
		return effectMap.get(card);
	}
	
}
