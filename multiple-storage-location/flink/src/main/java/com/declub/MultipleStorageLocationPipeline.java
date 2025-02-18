package com.declub;

import org.apache.flink.streaming.api.environment.StreamExecutionEnvironment;

public class MultipleStorageLocationPipeline {
  public static void main(String[] args) throws Exception {
    final StreamExecutionEnvironment env = StreamExecutionEnvironment.getExecutionEnvironment();

    // TODO implementation...

    env.execute("multiple storage location");
  }
}
