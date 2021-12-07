package com.damavis.elasticity.stage
import org.apache.spark.ml.feature.VectorAssembler
import org.apache.spark.sql.{DataFrame, SparkSession}
import org.apache.spark.ml.regression.LinearRegression

class FitMultiplicativeDemandModel()(implicit spark: SparkSession) extends PipelineStage {

  import spark.implicits._

  override def transform(data: DataFrame): DataFrame = {
    val assembler = new VectorAssembler()
      .setInputCols(Array("LogMeanPrice"))
      .setOutputCol("features")

    val assembled = assembler.transform(data)

    val lr = new LinearRegression()
      .setLabelCol("Log_Sales_U")
      .setFeaturesCol("features")

    val lrModel = lr.fit(assembled)

    Seq(lrModel.coefficients(0)).toDF("elasticity")
  }
}
