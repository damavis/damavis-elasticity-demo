package com.damavis.elasticity.pipeline

import com.damavis.elasticity.stage.PipelineStage
import com.damavis.elasticity.repository.Repository

class Pipeline(repository: Repository,
               computeMeanPrice: PipelineStage,
               createLogFeatures: PipelineStage,
               fitMultiplicativeDemandModel: PipelineStage) {

  def run(): Unit = {
    val raw = repository.getData
    val rawWithMeanPrice = createLogFeatures.transform(raw)
    val rawWithLogFeatures = computeMeanPrice.transform(rawWithMeanPrice)
    val coefficients = fitMultiplicativeDemandModel.transform(rawWithLogFeatures)

    repository.writeElasticity(coefficients)
  }

}
