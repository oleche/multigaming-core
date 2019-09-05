package multigaming.core.endpoint;

import io.grpc.stub.StreamObserver;
import multigaming.core.*;
import multigaming.core.service.RoomService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.inject.Singleton;

import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;

@Singleton
public class RoomEndpoint extends RoomServiceGrpc.RoomServiceImplBase {
    private static final Logger logger = LoggerFactory.getLogger(RoomEndpoint.class);

    private final RoomService roomService;

    public RoomEndpoint(RoomService roomService){
        this.roomService = roomService;
    }

    @Override
    public void join(RoomRequest request,
                       StreamObserver<RoomReply> responseObserver) {
        responseObserver.onNext(roomService.processMemberJoin(request));
        responseObserver.onCompleted();
    }

    @Override
    public void send(RoomMemberRequest request,
                     StreamObserver<RoomMemberReply> responseObserver) {
        responseObserver.onNext(roomService.processMemberUpdate(request));
        responseObserver.onCompleted();
    }

}
