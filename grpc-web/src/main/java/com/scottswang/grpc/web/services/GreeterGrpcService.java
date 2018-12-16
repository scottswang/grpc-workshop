package com.scottswang.grpc.web.services;

import com.google.common.util.concurrent.ListenableFuture;
import io.grpc.Channel;
import io.grpc.ManagedChannel;
import io.grpc.ManagedChannelBuilder;
import io.grpc.examples.helloworld.GreeterGrpc;
import io.grpc.examples.helloworld.HelloReply;
import io.grpc.examples.helloworld.HelloRequest;
import net.devh.springboot.autoconfigure.grpc.client.GrpcChannelFactory;
import net.devh.springboot.autoconfigure.grpc.client.GrpcClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class GreeterGrpcService {
    //也可以通过 GrpcChannelFactory中的 createChannel 得到 Channel
    @GrpcClient("device-grpc-server")
    private Channel serverChannel;

    public String greet(String name){
        GreeterGrpc.GreeterBlockingStub blockingStub = GreeterGrpc.newBlockingStub(serverChannel);
        HelloRequest request = HelloRequest.newBuilder().setName(name).setTel("15810706968").build();
        HelloReply response = blockingStub.sayHello(request);
        System.out.println(response.getMessage());
        return response.getMessage();
    }

    /*public String greet1(String name){
        GreeterGrpc.GreeterFutureStub futureStub = GreeterGrpc.newFutureStub(serverChannel);
        HelloRequest request = HelloRequest.newBuilder().setName(name).setTel("15810706968").build();
        ListenableFuture<HelloReply> response = futureStub.sayHello(request);
    }*/

}
