package com.zied.mappers;

import junit.framework.TestCase;
import org.apache.spark.sql.Row;
import org.apache.spark.sql.RowFactory;
import org.junit.Test;

public class RowToRequestMapperTest extends TestCase {

    @Test
    public void testShouldCallRowToRequestMapper() throws Exception {
        String key = "key";
        String value = "1,group1,content";
        Row row = RowFactory.create(key, value);
        RowToRequestMapper rowToRequestMapper = new RowToRequestMapper();
//        Tuple2<String, Request> stringRequestTuple2 = rowToRequestMapper.call(row);

//        assertEquals(stringRequestTuple2._1, value.split(",")[0]);
//        assertEquals(stringRequestTuple2._2.getRequestId(), value.split(",")[0]);
//        assertEquals(stringRequestTuple2._2.getRequestGroup(), value.split(",")[1]);
//        assertEquals(stringRequestTuple2._2.getRequestContent(), value.split(",")[2]);
    }
}