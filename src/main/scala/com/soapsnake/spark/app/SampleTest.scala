package com.soapsnake.spark.app

import org.apache.spark.sql.SparkSession

object SampleTest {

  val SPARK_HOME = "file:///home/ubuntu/spark-2.4.4-bin-hadoop2.7/"

  def main(args: Array[String]): Unit = {
    val logFile = SPARK_HOME + "README.md" // Should be some file on your system
    val spark = SparkSession.builder.appName("Simple Application").getOrCreate()
    val logData = spark.read.textFile(logFile).cache()
    val numAs = logData.filter(line => line.contains("a")).count()
    val numBs = logData.filter(line => line.contains("b")).count()
    println(s"Lines with a: $numAs, Lines with b: $numBs")
    spark.stop()
  }

}
