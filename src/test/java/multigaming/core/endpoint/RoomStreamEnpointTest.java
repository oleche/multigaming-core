package multigaming.core.endpoint;

import io.grpc.ManagedChannel;
import io.grpc.stub.StreamObserver;
import io.micronaut.context.annotation.Bean;
import io.micronaut.context.annotation.Factory;
import io.micronaut.grpc.annotation.GrpcChannel;
import io.micronaut.grpc.server.GrpcServerChannel;
import io.micronaut.test.annotation.MicronautTest;
import multigaming.core.RoomExchangeReply;
import multigaming.core.RoomExchangeRequest;
import multigaming.core.RoomStreamServiceGrpc;
import org.junit.jupiter.api.Test;
import org.mockito.ArgumentCaptor;

import javax.inject.Inject;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

@MicronautTest
public class RoomStreamEnpointTest {
    @Inject
    RoomStreamServiceGrpc.RoomStreamServiceStub stub;


    @Test
    void testExchange(){
        int timesOnNext = 0;

        @SuppressWarnings("unchecked")
        StreamObserver<RoomExchangeReply> responseObserver =
                (StreamObserver<RoomExchangeReply>) mock(StreamObserver.class);

        StreamObserver<RoomExchangeRequest> requestObserver = stub.exchange(responseObserver);
        verify(responseObserver, never()).onNext(any(RoomExchangeReply.class));

        //requestObserver.onNext();
//        ArgumentCaptor<RoomExchangeReply> roomExchangeCaptor;
//        roomExchangeCaptor = ArgumentCaptor.forClass(RoomExchangeReply.class);
//        verify(responseObserver, timeout(100).times(++timesOnNext)).onNext(roomExchangeCaptor.capture());
//        RoomExchangeReply result = roomExchangeCaptor.getAllValues().get(timesOnNext - 1);
//        assertEquals(RoomExchangeReply.newBuilder().build(), result);

//        requestObserver.onCompleted();
//        verify(responseObserver, timeout(100)).onCompleted();
    }
}

@Factory
class StreamClients {
    @Bean
    RoomStreamServiceGrpc.RoomStreamServiceStub stub(
            @GrpcChannel(GrpcServerChannel.NAME) ManagedChannel channel) {
        return RoomStreamServiceGrpc.newStub(
                channel
        );
    }

}