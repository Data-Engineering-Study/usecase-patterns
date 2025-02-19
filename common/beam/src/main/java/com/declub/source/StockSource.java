package com.declub.source;

import com.declub.model.Stock;
import java.util.Random;
import net.datafaker.Faker;
import org.apache.beam.sdk.transforms.*;
import org.apache.beam.sdk.values.PBegin;
import org.apache.beam.sdk.values.PCollection;
import org.checkerframework.checker.nullness.qual.NonNull;
import org.joda.time.Duration;
import org.joda.time.Instant;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class StockSource extends PTransform<@NonNull PBegin, @NonNull PCollection<Stock>> {

  private static final Logger LOG = LoggerFactory.getLogger(StockSource.class);

  public static StockSource of() {
    return new StockSource();
  }

  @Override
  public @NonNull PCollection<Stock> expand(@NonNull PBegin input) {
    return input
        .apply(PeriodicImpulse.create().withInterval(Duration.millis(50)))
        .apply(ParDo.of(new StockSourceFn()))
        .apply(WithTimestamps.of(stock -> Instant.ofEpochMilli(stock.getTimestamp())));
  }

  private static class StockSourceFn extends DoFn<Instant, Stock> {
    private transient Faker faker;
    private transient Random random;

    @Setup
    public void setup() {
      LOG.info("[@Setup] {} setup called at {}", getClass().getSimpleName(), Instant.now());
      this.faker = new Faker();
      this.random = new Random();
    }

    @ProcessElement
    public void process(@Element Instant element, OutputReceiver<Stock> output) {
      final String symbol = this.faker.stock().nsdqSymbol();
      final double price = this.random.nextDouble(1_000);
      output.output(
          Stock.of(symbol, Double.parseDouble(String.format("%.2f", price)), element.getMillis()));
    }

    @Teardown
    public void teardown() {
      LOG.info("[@Teardown] {} teardown called at {}", getClass().getSimpleName(), Instant.now());
      if (this.faker != null) {
        this.faker = null;
      }

      if (this.random != null) {
        this.random = null;
      }
    }
  }
}
