package com.soapsnake.spark.stream

import org.apache.kafka.common.serialization.StringDeserializer
import org.apache.spark.SparkConf
import org.apache.spark.streaming.{Seconds, StreamingContext}
import org.apache.spark.streaming.kafka010._
import org.apache.spark.streaming.kafka010.LocationStrategies.PreferConsistent
import org.apache.spark.streaming.kafka010.ConsumerStrategies.Subscribe

object StreamKafka {

  def main(args: Array[String]): Unit = {

    val conf = new SparkConf().setAppName("kafkaSample")
    val ssc = new StreamingContext(conf, Seconds(5));

    val kafkaParams = Map[String, Object](
      "bootstrap.servers" -> "18.162.177.170:9092,18.162.128.89:9092",   //broker 地址
//      "key.deserializer" -> classOf[StringDeserializer],     //key序列化器
//      "value.deserializer" -> classOf[StringDeserializer],
      "group.id" -> "soapsnake_spark",     //consumer group???
      "auto.offset.reset" -> "latest",          //???????
      "enable.auto.commit" -> (false: java.lang.Boolean)
    )

    val topics = Array("input")   //topic
    val stream = KafkaUtils.createDirectStream[String, String] (
      ssc,
      PreferConsistent,
      Subscribe[String, String](topics, kafkaParams)
    )

    stream.map(record => (record.key, record.value)).print()

  }

}
