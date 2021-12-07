package com.damavis.elasticity.stage

import org.apache.spark.sql.DataFrame
import org.apache.spark.sql.functions.col

class ComputeMeanPrice extends PipelineStage {
  override def transform(data: DataFrame): DataFrame = {
    data.withColumn("MeanPrice", col("Sales_USD") / col("Sales_U"))
  }
}
