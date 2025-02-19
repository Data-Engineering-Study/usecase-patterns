package com.declub;

import com.declub.model.Stock;
import com.declub.source.StockSource;
import org.apache.beam.sdk.Pipeline;
import org.apache.beam.sdk.options.PipelineOptions;
import org.apache.beam.sdk.options.PipelineOptionsFactory;
import org.apache.beam.sdk.transforms.DoFn;
import org.apache.beam.sdk.transforms.PTransform;
import org.apache.beam.sdk.transforms.ParDo;
import org.apache.beam.sdk.values.PCollection;
import org.apache.beam.sdk.values.PDone;

public class MultipleStorageLocationPipeline {
  public static void main(String[] args) {
    final PipelineOptions options = PipelineOptionsFactory.fromArgs(args).withValidation().create();
    final Pipeline p = Pipeline.create(options);

    final PCollection<Stock> stocks = p.apply("Get Stocks", StockSource.of());

    stocks.apply(
        "Print Stocks",
        ParDo.of(
            new DoFn<Stock, Void>() {
              @ProcessElement
              public void process(@Element Stock stock) {
                System.out.println("Current stock = " + stock);
              }
            }));

    stocks.apply("Save To BigQuery", new SaveToBigQueryDummy());

    stocks.apply("Save To BigTable", new SaveToBigTableMock());

    p.run().waitUntilFinish();
  }

  /** dummy */
  private static class SaveToBigQueryDummy extends PTransform<PCollection<Stock>, PDone> {

    @Override
    public PDone expand(PCollection<Stock> input) {
      input.apply(
          ParDo.of(
              new DoFn<Stock, Void>() {
                @ProcessElement
                public void process() {}
              }));
      return PDone.in(input.getPipeline());
    }
  }

  /** dummy */
  private static class SaveToBigTableMock extends PTransform<PCollection<Stock>, PDone> {

    @Override
    public PDone expand(PCollection<Stock> input) {
      input.apply(
          ParDo.of(
              new DoFn<Stock, Void>() {
                @ProcessElement
                public void process() {}
              }));
      return PDone.in(input.getPipeline());
    }
  }
}
