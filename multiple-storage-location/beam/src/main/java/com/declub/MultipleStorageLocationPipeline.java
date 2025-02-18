package com.declub;

import org.apache.beam.sdk.Pipeline;
import org.apache.beam.sdk.options.PipelineOptions;
import org.apache.beam.sdk.options.PipelineOptionsFactory;

public class MultipleStorageLocationPipeline {
  public static void main(String[] args) {
    final PipelineOptions options = PipelineOptionsFactory.fromArgs(args).withValidation().create();
    final Pipeline p = Pipeline.create(options);

    // TODO implementation...
    p.run().waitUntilFinish();
  }
}
