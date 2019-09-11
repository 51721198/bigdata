package com.soapsnake.spark.stream

import org.apache.spark.SparkConf
import org.apache.spark.streaming._

object StreamSample {

  def main(args: Array[String]): Unit = {

    val conf = new SparkConf().setMaster("local[2]").setAppName("NetworkWordCount")
    val ssc = new StreamingContext(conf, Seconds(5));

      //lines 将会是一个DStreams
    val lines = ssc.socketTextStream("localhost", 9999)  //Define the input sources by creating input DStreams.

    val words = lines.flatMap(_.split(" "))   //根据空格对输入的文子进行split,flatMap真的很精绥啊

    val pairs = words.map(word => (word, 1))   //map操作
    val wordCounts = pairs.reduceByKey(_+_)   //reduce操作

    wordCounts.print()

    ssc.start()
    ssc.awaitTermination()

  }

}
