package com.mromani.digital.deck.server.be.api;

import static java.util.logging.Level.INFO;

import java.util.Set;
import java.util.logging.Logger;

import javax.annotation.PostConstruct;
import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;

import org.eclipse.microprofile.config.inject.ConfigProperty;

@ApplicationScoped
public class ApiProperties {

  private final Logger _log = Logger.getLogger(getClass().getName());

  private Integer      availability_max_age   = 3600;
  private Integer     access_control_max_age = 3600;
  private Set<String> corsWhitelist          = Set.of("localhost");
  private Integer      stale_while_revalidate = 3600;


  public ApiProperties() {
  }

  public int getAvailabilityMaxAge() {
    return availability_max_age;
  }

  public int getAccessControlMaxAge() {
    return access_control_max_age;
  }

  public Set<String> getCorsWhiteList() {
    return corsWhitelist;
  }

  public int getStaleWhileRevalidate() {
    return stale_while_revalidate;
  }
  
  
  @Inject
  public void setAvailabilityMaxAge(@ConfigProperty(name = "availability_max_age", defaultValue = "3600") Integer _maxAge) {
    availability_max_age = _maxAge;
  }

  @Inject
  public void setAccessControlMaxAge(@ConfigProperty(name = "access_control_max_age", defaultValue = "3600") Integer _maxAge) {
    access_control_max_age = _maxAge;
  }

  @Inject
  public void setCorsWhiteList(@ConfigProperty(name = "cors_whitelist", defaultValue = "localhost") Set<String> _whiteList) {
    corsWhitelist = _whiteList;
  }

  @Inject
  public void setStaleWhileRevalidate(@ConfigProperty(name = "stale_while_revalidate", defaultValue = "3600") Integer _stale) {
    stale_while_revalidate = _stale;
  }
  

  @PostConstruct
  public void log() {
    _log.log(INFO, "upc availability max age : {0}", availability_max_age);
    _log.log(INFO, "access control max age : {0}", access_control_max_age);
    _log.log(INFO, "cors whitelist : {0}", corsWhitelist);
    _log.log(INFO, "stale-while-revalidate header : {0}", stale_while_revalidate);
  }

}
