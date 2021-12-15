package main.pipeline

import com.damavis.elasticity.Arguments
import com.damavis.elasticity.stage.{ComputeMeanPrice, CreateLogFeatures, FitMultiplicativeDemandModel, PipelineStage}
import com.damavis.elasticity.pipeline.Pipeline
import com.damavis.elasticity.repository.resource.{CSVResourceImpl, FileResource}
import com.damavis.elasticity.repository.{Repository, RepositoryImpl}
import com.damavis.spark.{SparkApp, SparkConf}
import com.typesafe.config.{Config, ConfigFactory}

class PipelineInstance(arguments: Arguments) extends SparkApp with SparkConf {

  override val name: String = "demo-price-elasticity"

  val config: Config = ConfigFactory.load(arguments.environment)

  def run(): Unit = {
    // Resources
    val resource: FileResource = new CSVResourceImpl()

    // Repository
    val repository: Repository = new RepositoryImpl(config, resource)

    // Interactors
    val computeMeanPrice: PipelineStage = new ComputeMeanPrice()
    val createLogFeatures: PipelineStage = new CreateLogFeatures()
    val fitMultiplicativeDemandModel: PipelineStage = new FitMultiplicativeDemandModel()

    // Pipeline
    val pipeline = new Pipeline(repository, createLogFeatures, computeMeanPrice, fitMultiplicativeDemandModel)

    pipeline.run()
  }
}
