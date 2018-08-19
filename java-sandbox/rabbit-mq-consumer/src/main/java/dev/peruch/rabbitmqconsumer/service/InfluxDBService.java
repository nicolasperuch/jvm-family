package dev.peruch.rabbitmqconsumer.service;

import org.influxdb.InfluxDB;
import org.influxdb.dto.Point;
import org.influxdb.dto.Query;
import org.influxdb.dto.QueryResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class InfluxDBService {

    @Autowired
    private InfluxDB influxDB;

    public void saveEvent(String order) {
        Point point = Point
                .measurement("orders")
                .tag("type", "request")
                .addField("order", order)
                .build();

        influxDB.setDatabase("events");
        influxDB.write(point);
    }

    public void queryEvent(){
        QueryResult result = influxDB.query(new Query("SELECT * FROM orders", "springboot"));
        System.out.println("result: " + result.getResults());
    }
}
