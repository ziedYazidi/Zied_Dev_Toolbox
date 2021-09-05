package mappers;

import models.Request;
import org.apache.spark.api.java.function.MapFunction;
import org.apache.spark.sql.Row;

import static constants.Constants.REQUEST_SEPARATOR;

public class RowToRequestMapper implements MapFunction<Row, Request> {
    private static final long serialVersionUID = 3520689095783306088L;

    @Override
    public Request call(Row row) throws Exception {
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
        return request;
    }
}
