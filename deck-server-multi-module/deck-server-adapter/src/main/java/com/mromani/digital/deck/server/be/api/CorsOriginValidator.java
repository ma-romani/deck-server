package com.mromani.digital.deck.server.be.api;

import static java.util.logging.Level.WARNING;

import java.net.URI;
import java.util.Set;
import java.util.logging.Logger;

import javax.enterprise.context.ApplicationScoped;

@ApplicationScoped
public class CorsOriginValidator {

  private final Logger _log = Logger.getLogger(getClass().getName());

  public CorsOriginValidator() {
  }

  public boolean validate(final String _origin, final Set<String> _whiteList) {
    boolean res = false;
    try {
      final var lowerCaseOriginUriHostname = URI.create(_origin)
                                                .getHost()
                                                .toLowerCase();
      res = _whiteList.stream()
                      .map(whiteHost -> whiteHost.toLowerCase())
                      .anyMatch(whiteHost -> {
                        return whiteHost.startsWith("*.") ? lowerCaseOriginUriHostname.endsWith(whiteHost.substring(2))
                                                          : lowerCaseOriginUriHostname.equals(whiteHost);
                      });
    } catch (final IllegalArgumentException | NullPointerException _e) {
      _log.log(WARNING, "origin hostname {0} not valid", new Object[] { _origin });
    }
    return res;
  }

}
