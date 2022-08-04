package pl.ostrowski;

import static pl.ostrowski.Main.mongoDatabase;

import com.mongodb.client.MongoCollection;
import com.mongodb.client.model.Filters;
import com.mongodb.client.model.ReplaceOptions;
import enums.PacketType;
import org.bson.Document;
import pl.ostrowski.packet.Packet;
import pl.ostrowski.packet.PacketFactory;

public class PacketMongoRepository implements PacketRepository {

  private static final String ID_FIELD_NAME = "_id";

  @Override
  public void savePacket(Packet packet, String clientId) {
    String packetType = packet.getPacketType().toString();
    dto.Packet packetDTO = PacketFactory.toDto(packet);
    MongoCollection<Document> mongoCollection = mongoDatabase.getCollection(packetType);
    Document document = new Document(clientId, packetDTO);
//    if(packetType.equals(PacketType.CAR_TELEMETRY.toString())){
      mongoCollection.replaceOne(
        Filters.eq(ID_FIELD_NAME, clientId), document, new ReplaceOptions().upsert(true));
//    }
  }
}
