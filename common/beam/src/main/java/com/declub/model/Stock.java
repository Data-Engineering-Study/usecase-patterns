package com.declub.model;

import com.google.auto.value.AutoValue;
import org.apache.beam.sdk.schemas.AutoValueSchema;
import org.apache.beam.sdk.schemas.annotations.DefaultSchema;

@AutoValue
@DefaultSchema(AutoValueSchema.class)
public abstract class Stock {

  public abstract String getSymbol();

  public abstract Double getPrice();

  public abstract Long getTimestamp();

  public static Stock of(String symbol, Double price, Long timestamp) {
    return new AutoValue_Stock.Builder()
        .setSymbol(symbol)
        .setPrice(price)
        .setTimestamp(timestamp)
        .build();
  }

  @AutoValue.Builder
  abstract static class Builder {

    abstract Builder setSymbol(String symbol);

    abstract Builder setPrice(Double price);

    abstract Builder setTimestamp(Long timestamp);

    abstract Stock build();
  }
}
