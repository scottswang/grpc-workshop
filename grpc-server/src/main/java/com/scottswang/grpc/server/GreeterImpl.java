package com.scottswang.grpc.server;

import io.grpc.examples.helloworld.GreeterGrpc;
import io.grpc.examples.helloworld.HelloReply;
import io.grpc.examples.helloworld.HelloRequest;
import io.grpc.stub.StreamObserver;
import net.devh.springboot.autoconfigure.grpc.server.GrpcService;


@GrpcService(GreeterGrpc.class)
public class GreeterImpl extends GreeterGrpc.GreeterImplBase {

    public void sayHello(HelloRequest req, StreamObserver<HelloReply> responseObserver) {
        System.out.println("service:"+req.getName());
        HelloReply reply = HelloReply.newBuilder().setMessage(("Hello: " + req.getName())).build();
        responseObserver.onNext(reply);
        responseObserver.onCompleted();
    }
}
