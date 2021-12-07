package com.damavis.elasticity.repository.resource
import org.apache.spark.sql.{DataFrame, SaveMode, SparkSession}

class CSVResourceImpl()(implicit spark: SparkSession) extends FileResource {

  override def read(path: String): DataFrame = {
    spark
      .read
      .option("header", "true")
      .csv(path)
  }

  override def write(path: String, data: DataFrame): Unit = {
    data
      .write
      .option("header", "true")
      .mode(SaveMode.Overwrite)
      .csv(path)
  }
}
