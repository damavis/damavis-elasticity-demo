package com.damavis.elasticity.stage
import org.apache.spark.sql.DataFrame
import org.apache.spark.sql.functions.{log, col}

class CreateLogFeatures extends PipelineStage {
  override def transform(data: DataFrame): DataFrame = {
    data
      .withColumn("LogMeanPrice", log(col("MeanPrice")))
      .withColumn("Log_Sales_U", log(col("Sales_U")))
  }
}
