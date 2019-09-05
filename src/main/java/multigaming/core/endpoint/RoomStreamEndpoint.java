package multigaming.core.endpoint;

import io.grpc.stub.StreamObserver;
import multigaming.core.*;
import multigaming.core.service.RoomService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.inject.Singleton;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;

import static multigaming.core.RoomStreamServiceGrpc.RoomStreamServiceImplBase;

@Singleton
public class RoomStreamEndpoint extends RoomStreamServiceImplBase {
    private static final Logger logger = LoggerFactory.getLogger(RoomStreamEndpoint.class);

    private final RoomService roomService;

    private static Set<StreamObserver<RoomExchangeReply>> observers = ConcurrentHashMap.newKeySet();

    public RoomStreamEndpoint(RoomService roomService){
        this.roomService = roomService;
    }

    @Override
    public StreamObserver<RoomExchangeRequest> exchange(StreamObserver<RoomExchangeReply> responseObserver){
        observers.add(responseObserver);

        return new StreamObserver<RoomExchangeRequest>(){
            @Override
            public void onNext(RoomExchangeRequest request) {
                RoomExchangeReply reply = RoomExchangeReply.newBuilder().setMessage("OK").build();
                logger.info("REQUEST: {} - {}",request.getCode(),request.getRoomid());

                for (StreamObserver<RoomExchangeReply> observer : observers) {
                    if ("10".equals(request.getCode())){
                        reply = RoomExchangeReply.newBuilder().setMessage("OK").setCode("END").build();
                        observer.onNext(reply);
                        if (observer.equals(responseObserver)){
                            observer.onCompleted();
                            observers.remove(responseObserver);
                        }
                    }else{
                        observer.onNext(reply);
                    }

                }
            }

            @Override
            public void onError(Throwable t) {
                logger.error("Encountered error in recordRoute", t);
                responseObserver.onError(t);
            }

            @Override
            public void onCompleted() {
                logger.info("completing conn");
                observers.remove(responseObserver);
            }
        };
    }
}
