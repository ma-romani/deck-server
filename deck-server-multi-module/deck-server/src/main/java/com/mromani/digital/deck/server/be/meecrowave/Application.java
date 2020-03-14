package com.mromani.digital.deck.server.be.meecrowave;

import org.apache.meecrowave.Meecrowave;

public class Application {

  public Application() {
  }

  public static void main(final String[] _args) {
    configureLog4j2();

    try (Meecrowave meecrowave = new Meecrowave(newMeecrowaveBuilder())) {
      meecrowave.bake()
                .await();
    }
  }

  private static Meecrowave.Builder newMeecrowaveBuilder() {
    Meecrowave.Builder builder = new Meecrowave.Builder();
    builder.setHttpPort(9998);
    builder.setHttpsPort(9999);
    builder.setTomcatScanning(true);
    builder.setJsonbPrettify(true);
    builder.setJsonpPrettify(true);
    builder.setHttp2(true);
    builder.setJaxrsAutoActivateBeanValidation(true);
    builder.setJaxwsSupportIfAvailable(false);
    builder.setLoggingGlobalSetup(true);
    builder.setUseLog4j2JulLogManager(true);
    builder.setWatcherBouncing(10);
    return builder;
  }

  private static void configureLog4j2() {
    System.setProperty("java.util.logging.manager", "org.apache.logging.log4j.jul.LogManager");
    System.setProperty("log4j2.contextSelector", "org.apache.logging.log4j.core.async.AsyncLoggerContextSelector");
  }

}
