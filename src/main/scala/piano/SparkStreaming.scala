package piano

import com.datastax.driver.core.ConsistencyLevel
import com.datastax.spark.connector.streaming._
import com.datastax.spark.connector.writer.WriteConf
import com.datastax.spark.connector.{ColumnName, SomeColumns}
import org.apache.spark.sql.SparkSession
import org.apache.spark.streaming.kafka010.{ConsumerStrategies, KafkaUtils, LocationStrategies}
import org.apache.spark.streaming.{Seconds, StreamingContext}
import org.apache.spark.{SparkConf, SparkContext}

object SparkStreaming extends App {

  val conf = new SparkConf(true).set("spark.cassandra.connection.host", CassandraHelper.host)
  val context = new SparkContext("local", "PianoStreamingJob", conf)
  val streamingContext = new StreamingContext(context, Seconds(1))
  val session = SparkSession.builder.config(conf).getOrCreate()

  import session.implicits._



  streamingContext.start()
  streamingContext.awaitTermination()

}
