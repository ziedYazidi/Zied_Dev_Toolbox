package com.zied.client;

import com.zied.HelloRequest;
import com.zied.HelloResponse;
import com.zied.HelloServiceGrpc;
import io.grpc.ManagedChannel;
import io.grpc.ManagedChannelBuilder;

public class GrpcClient {
    public static void main(String[] args) {
        ManagedChannel channel = ManagedChannelBuilder
                .forAddress("localhost", 8000)
                .usePlaintext()
                .build();

        HelloServiceGrpc.HelloServiceBlockingStub stub = HelloServiceGrpc.newBlockingStub(channel);

        HelloRequest helloRequest = HelloRequest.newBuilder()
                .setFirstName("Zied")
                .setLastName("Yazidi")
                .build();

        HelloResponse helloResponse = stub.hello(helloRequest);
        System.out.println(helloResponse.getGreeting());
        channel.shutdown();
    }
}
