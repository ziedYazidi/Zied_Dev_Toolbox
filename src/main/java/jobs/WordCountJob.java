package jobs;

import functions.RequestsGroupingFunction;
import mappers.RowToRequestMapper;
import models.Request;
import org.apache.spark.sql.Dataset;
import org.apache.spark.sql.Encoders;
import org.apache.spark.sql.Row;
import org.apache.spark.sql.SparkSession;
import org.apache.spark.sql.streaming.StreamingQueryException;

import java.util.concurrent.TimeoutException;

public class WordCountJob {
    public static void main(String[] args) {
        try {
            SparkSession spark = SparkSession
                    .builder()
                    .appName("JavaStructuredNetworkWordCount")
                    .getOrCreate();

            Dataset<Row> data = spark
                    .readStream()
                    .format("kafka")
                    .option("kafka.bootstrap.servers", "host1:port1,host2:port2")
                    .option("subscribe", "topic1")
                    .load()
                    .selectExpr("CAST(key AS STRING)", "CAST(value AS STRING)");

            /*Dataset<Request> requests = data
                    .map(new RowToRequestMapper(), Encoders.kryo(Request.class))
                    .groupByKey(new RequestsGroupingFunction(), Encoders.STRING())
                    .mapGroups()*/
            data.writeStream()
                    .outputMode("complete")
                    .format("console")
                    .start()
                    .awaitTermination();
        } catch (StreamingQueryException | TimeoutException e) {
            e.printStackTrace();
        }

    }
}
