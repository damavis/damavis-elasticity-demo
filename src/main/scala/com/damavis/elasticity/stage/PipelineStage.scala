package com.damavis.elasticity.stage

import org.apache.spark.sql.DataFrame

trait PipelineStage {
  def transform(data: DataFrame): DataFrame
}
