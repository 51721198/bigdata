package com.soapsnake.spark.stream

import org.apache.spark.SparkConf
import org.apache.spark.streaming.{Seconds, StreamingContext}

object StreamKafkaWord {

  def main(args: Array[String]): Unit = {

        if (args.length < 4) {
          System.err.println("Usage: KafkaWordCount <zkQuorum><group> <topics> <numThreads>")
          System.exit(1)
        }
        val Array(zkQuorom, group, topics, numThreads) = args
        val sparkConf = new SparkConf().setAppName("kafkaWordCount")
        val ssc = new StreamingContext(sparkConf, Seconds(2))
        ssc.checkpoint("checkpoint")

        val topicMap = topics.split(",").map((_, numThreads.toInt)).toMap
//        val lines = KafkaUtils.createDirectStream(ssc, zkQuorom, group, topicMap).map(_._2)
//        val words = lines.flatMap()
//        val wordCounts = words.map()

  }

}
