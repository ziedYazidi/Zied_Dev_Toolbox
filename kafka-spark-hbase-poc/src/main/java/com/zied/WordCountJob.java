//package com.zied;
//
//import com.zied.functions.CountMapGroupsFunction;
//import com.zied.functions.RequestsGroupingFunction;
//import com.zied.mappers.RowToRequestMapper;
//import com.zied.models.Request;
//import org.apache.logging.log4j.LogManager;
//import org.apache.logging.log4j.Logger;
//import org.apache.spark.sql.Dataset;
//import org.apache.spark.sql.Encoders;
//import org.apache.spark.sql.Row;
//import org.apache.spark.sql.SparkSession;
//import org.apache.spark.sql.streaming.StreamingQueryException;
//import scala.Tuple2;
//
//import java.util.concurrent.TimeoutException;
//
//public class WordCountJob {
//    private static final Logger LOGGER = LogManager.getLogger(WordCountJob.class);
//
//    public static void main(String[] args) {
//
//        LOGGER.info("Started Word Count Job");
//        try {
//            SparkSession spark = SparkSession
//                    .builder()
//                    .master("local[2]")
//                    .appName("JavaStructuredNetworkWordCount")
//                    .getOrCreate();
//
//            Dataset<Row> data = spark
//                    .readStream()
//                    .format("kafka")
//                    .option("kafka.bootstrap.servers", "kafka:9092")
//                    .option("subscribe", "requests_topic")
//                    .option("startingOffsets", "earliest")
//                    .option("failOnDataLoss", "false")
//                    .load()
//                    .selectExpr("CAST(key AS STRING)", "CAST(value AS STRING)");
//
//            Dataset<Tuple2<String, Integer>> requests = data
//                    .map(new RowToRequestMapper(), Encoders.tuple(Encoders.STRING(), Encoders.kryo(Request.class)))
//                    .groupByKey(new RequestsGroupingFunction(), Encoders.STRING())
//                    .mapGroups(new CountMapGroupsFunction(), Encoders.tuple(Encoders.STRING(), Encoders.INT()));
//
//            requests.writeStream()
//                    .format("console")
//                    .option("checkpointLocation", "/tmp/BigDataScaffolderCheckpoints")
//                    .start()
//                    .awaitTermination();
//        } catch (StreamingQueryException | TimeoutException e) {
//            e.printStackTrace();
//        }
//
//    }
//}
//
