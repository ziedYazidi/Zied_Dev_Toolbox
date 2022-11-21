package com.zied.functions;

import com.zied.models.Request;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.apache.spark.api.java.function.MapFunction;
import scala.Tuple2;

public class RequestsGroupingFunction implements MapFunction<Tuple2<String, Request>, String> {
    private static final long serialVersionUID = 5183574971535824145L;
    private static Logger LOGGER = LogManager.getLogger(RequestsGroupingFunction.class);

    @Override
    public String call(Tuple2<String, Request> stringRequestTuple2){
        LOGGER.info("Grouping requests");
        return stringRequestTuple2._2.getRequestGroup();
    }
}
