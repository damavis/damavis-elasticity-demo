package com.damavis.elasticity.repository
import com.damavis.elasticity.repository.resource.FileResource
import com.typesafe.config.Config
import org.apache.spark.sql.DataFrame

class RepositoryImpl(config: Config, csvResource: FileResource) extends Repository {

  override def getData: DataFrame = {
    val path = config.getString("origin")
    csvResource.read(path)
  }

  override def writeElasticity(data: DataFrame): Unit = {
    val path = config.getString("destination")
    csvResource.write(path, data)
  }
}
