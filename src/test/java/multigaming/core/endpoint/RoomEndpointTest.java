package multigaming.core.endpoint;

import io.grpc.ManagedChannel;
import io.grpc.stub.StreamObserver;
import io.micronaut.context.annotation.Bean;
import io.micronaut.context.annotation.Factory;
import io.micronaut.grpc.annotation.GrpcChannel;
import io.micronaut.grpc.server.GrpcServerChannel;
import io.micronaut.test.annotation.MicronautTest;
import multigaming.core.RoomReply;
import multigaming.core.RoomRequest;
import multigaming.core.RoomServiceGrpc;
import org.junit.jupiter.api.Test;
import org.mockito.ArgumentCaptor;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;


import javax.inject.Inject;
import java.util.ArrayList;

@MicronautTest
public class RoomEndpointTest {
    @Inject
    RoomServiceGrpc.RoomServiceBlockingStub blockingStub;

    @Test
    void testJoin(){
        assertEquals(true, true);

        final RoomRequest roomRequest = RoomRequest.newBuilder()
                .setUid("1233321")
                .setToken("31231231231231")
                .setRoomid("12")
                .build();

        assertEquals(
                "OK",
                blockingStub.join(roomRequest)
                        .getMessage()
        );
    }
}

@Factory
class Clients {
    @Bean
    RoomServiceGrpc.RoomServiceBlockingStub blockingStub(
            @GrpcChannel(GrpcServerChannel.NAME) ManagedChannel channel) {
        return RoomServiceGrpc.newBlockingStub(
                channel
        );
    }

}
