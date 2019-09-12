#!/bin/bash

spark-submit --class "com.soapsnake.spark.stream.StreamKafka" --master local[4] /home/ubuntu/github/bigdata/target/spark-learning-1.0-SNAPSHOT.jar