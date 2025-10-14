package com.zied.functions;

import com.zied.models.Request;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.apache.spark.api.java.function.MapGroupsFunction;
import scala.Tuple2;

import java.util.Iterator;

public class CountMapGroupsFunction implements MapGroupsFunction<String, Tuple2<String, Request>, Tuple2<String, Integer>> {
    private static final long serialVersionUID = 3063137958539664959L;
    private static final Logger LOGGER = LogManager.getLogger(CountMapGroupsFunction.class);

    @Override
    public Tuple2<String, Integer> call(String s, Iterator<Tuple2<String, Request>> iterator) throws Exception {
        LOGGER.info("Counting groups");
        int counter = 0;
        while(iterator.hasNext()){
            iterator.next();
            counter ++;
        }
        return new Tuple2<>(s, counter);
    }
}
