package com.mromani.digital.deck.server.be.api;

import static java.util.logging.Level.INFO;
import static javax.ws.rs.HttpMethod.GET;
import static javax.ws.rs.HttpMethod.OPTIONS;
import static javax.ws.rs.core.HttpHeaders.ACCEPT;
import static javax.ws.rs.core.HttpHeaders.AUTHORIZATION;
import static javax.ws.rs.core.HttpHeaders.CONTENT_TYPE;
import static javax.ws.rs.core.HttpHeaders.VARY;
import static javax.ws.rs.core.MediaType.APPLICATION_JSON;
import static org.eclipse.microprofile.metrics.MetricUnits.MINUTES;

import java.util.ArrayList;
import java.util.StringJoiner;
import java.util.logging.Logger;
import java.util.stream.Collectors;

import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.ws.rs.Consumes;
import javax.ws.rs.DefaultValue;
import javax.ws.rs.GET;
import javax.ws.rs.HeaderParam;
import javax.ws.rs.OPTIONS;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;

import javax.ws.rs.core.Response;

import org.assertj.core.util.Arrays;
import org.eclipse.microprofile.openapi.annotations.media.Content;
import org.eclipse.microprofile.openapi.annotations.media.Schema;
import org.eclipse.microprofile.openapi.annotations.parameters.RequestBody;
import org.eclipse.microprofile.openapi.annotations.tags.Tag;


import com.mromani.digital.deck.server.be.api.model.CardViewModel;
import com.mromani.digital.deck.server.be.api.model.CardViewModelMapper;
import com.mromani.digital.deck.server.be.model.Card;
import com.mromani.digital.deck.server.be.service.DeckService;

@RequestScoped
@Tag(name = "deck-server", description = "deck-server endpoints")
@Path("deck")
@Consumes(APPLICATION_JSON + "; charset=UTF-8")
@Produces(APPLICATION_JSON)
public class DeckResourceImpl implements DeckResource {

	private final Logger _log = Logger.getLogger(getClass().getName());

	private DeckService service;

	private CardViewModelMapper mapper = new CardViewModelMapper();

	public DeckResourceImpl() {
	}

	@Inject
	public DeckResourceImpl(DeckService _service) {
		service = _service;
	}
	
	

	@Override
	@GET
	@Path("{deckname}/draw")
	public Response draw(@PathParam("deckname") String deckname, @QueryParam("draws") @DefaultValue("1") Integer draws) {
		
		var cards = new ArrayList<CardViewModel>();
		service.draw(deckname, draws).forEach(c -> {
			cards.add(mapper.map(c));
		});
		if (cards.isEmpty()){
			return Response.ok().entity("Il mazzo " + deckname + " è vuoto").build();
		}
 		return Response.ok().entity(cards).build();
	}

	@Override
	@GET
	@Path("{deckname}/putback/{cardname}")
	public Response putBack(@PathParam("deckname")String deckname,
			@PathParam("cardname") String _card) {
		service.addTop(deckname, new Card(_card));
		return Response.ok().entity("Carta " + _card + " rimessa in cima al mazzo " + deckname).build();
	}
	
	@Override
	@GET
	@Path("{deckname}/use/{cardname}")
	public Response use(@PathParam("deckname")String deckname,
			@PathParam("cardname") String _card) {
		service.use(deckname, new Card(_card));
		return Response.ok().entity("Carta " + _card + " messa nel mazzo degli scarti " + deckname).build();
	}

	@Override
	@GET
	@Path("{deckname}/shuffle")
	public Response shuffle(@PathParam("deckname")String deckname) {
		service.shuffle(deckname);
		return Response.ok().entity("Mazzo " + deckname + " mescolato").build();
	}

	@Override
	@GET
	@Path("{deckname}/reset")
	public Response reset(@PathParam("deckname")String deckname) {
		service.reset(deckname);
		return Response.ok().entity("Mazzo " +deckname + " resettato").build();
	}

	@Override
	@GET
	@Path("{deckname}/peek")
	public Response peek(@PathParam("deckname") String deckname,
			@QueryParam("peeks") @DefaultValue("1") Integer peeks) {
		var cards = new ArrayList<CardViewModel>();
		service.peek(deckname, peeks).forEach(c -> {
			cards.add(mapper.map(c));
		});
		if (cards.isEmpty()){
			return Response.ok().entity("Il mazzo " + deckname + " è vuoto").build();
		}
 		return Response.ok().entity(cards).build();
	}

	@Override
	@GET
	@Path("{deckname}/resetpile")
	public Response resetPile(@PathParam("deckname")String deckname) {
		service.resetPile(deckname);
		return Response.ok().entity("Pila degli scarti rimescolata nel mazzo " + deckname).build();
	}
	
	@Override
	@GET
	@Path("{deckname}/override")
	public Response overrideDeck(@PathParam("deckname") String deckname,
			@QueryParam("cards") @DefaultValue("") String _cards) {
		var cards = new ArrayList<Card>();
		Arrays.asList(_cards.split(",")).forEach(c -> {
			var c_split = c.toString().split("x");
			if (c_split.length > 1) {
				int num = Integer.valueOf(c_split[0]);
				var name = c_split[1];
				for (int i=0; i<num; i++) {
					cards.add(new Card(name));
				}
			}else {
				cards.add(new Card(c.toString()));
			}
			
		});
		service.setDeck(deckname, cards);
		return Response.ok().entity("Mazzo " + deckname + " sovrascritto").build();
	}
	
	@Override
	@GET
	@Path("discardpile/{deckname}/override")
	public Response overrideDiscardPile(@PathParam("deckname") String deckname,
			@QueryParam("cards") @DefaultValue("") String _cards) {
		var cards = new ArrayList<Card>();
		Arrays.asList(_cards.split(",")).forEach(c -> {
			var c_split = c.toString().split("x");
			if (c_split.length > 1) {
				int num = Integer.valueOf(c_split[0]);
				var name = c_split[1];
				for (int i=0; i<num; i++) {
					cards.add(new Card(name));
				}
			}else {
				cards.add(new Card(c.toString()));
			}
		});
		service.setDiscard(deckname, cards);
		return Response.ok().entity("Pila degli scarti " + deckname + " sovrascritta").build();
	}
	
	
	
	
}
