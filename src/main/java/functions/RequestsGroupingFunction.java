package functions;

import models.Request;
import scala.Function1;

import java.util.function.Function;

public class RequestsGroupingFunction implements Function1<Request, String> {
    @Override
    public String apply(Request request) {
        return request.getRequestGroup();
    }
}
