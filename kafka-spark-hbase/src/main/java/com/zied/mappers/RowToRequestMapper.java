package com.zied.mappers;

import com.zied.models.Request;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.apache.spark.api.java.function.MapFunction;
import org.apache.spark.sql.Row;
import scala.Tuple2;

import static com.zied.constants.Constants.REQUEST_SEPARATOR;

public class RowToRequestMapper implements MapFunction<Row, Tuple2<String, Request>> {
    private static final long serialVersionUID = 3520689095783306088L;
    private static final Logger LOGGER = LogManager.getLogger(RowToRequestMapper.class);

    @Override
    public Tuple2<String, Request> call(Row row) throws Exception {
        LOGGER.info("Mapping Row to Request");
        Request request = new Request();
        try {
            String value = row.get(1).toString();
            String[] values = value.split(REQUEST_SEPARATOR);
            request.setRequestId(values[0]);
            request.setRequestGroup(values[1]);
            request.setRequestContent(values[2]);
        } catch (Exception exception) {
            exception.printStackTrace();
        }
        return new Tuple2<>(request.getRequestId(), request);
    }
}
