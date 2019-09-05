package multigaming.core.service;

import com.google.protobuf.Timestamp;
import multigaming.core.*;

import javax.inject.Singleton;
import java.util.Date;

@Singleton
public class RoomService {
    public RoomExchangeReply processRoomUpdate(RoomExchangeRequest request) {
        //process the request

        //Prepare the escenario
        Action action = Action.newBuilder().setName("TEST").setTimestamp(Timestamp.newBuilder().setSeconds(new Date().getTime()).build()).build();
        // No feature was found, return an unnamed feature.
        return RoomExchangeReply.newBuilder().addAction(action).build();
    }

    public RoomMemberReply processMemberUpdate(RoomMemberRequest request){
        //process the action of the member
        Action action = request.getAction();

        // No feature was found, return an unnamed feature.
        return RoomMemberReply.newBuilder().setMessage("OK").build();
    }

    public RoomReply processMemberJoin(RoomRequest request){
        //process the action of the member
        String uid = request.getUid();

        // No feature was found, return an unnamed feature.
        return RoomReply.newBuilder().setMessage("OK").build();
    }

}