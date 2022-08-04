package pl.ostrowski;

import com.mongodb.MongoClientSettings;
import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoClients;
import com.mongodb.client.MongoDatabase;
import org.bson.Document;
import org.bson.codecs.configuration.CodecRegistry;
import org.bson.codecs.pojo.PojoCodecProvider;

public class Main {

  public static MongoClient mongoClient;
  public static MongoDatabase mongoDatabase;

  public static void main(String[] args) {
//    String connectionString = "mongodb://77.254.122.180:27017/?replicaSet=rs0";
//    CodecRegistry pojoCodecRegistry =
//        org.bson.codecs.configuration.CodecRegistries.fromRegistries(
//            MongoClientSettings.getDefaultCodecRegistry(),
//            org.bson.codecs.configuration.CodecRegistries.fromProviders(
//                PojoCodecProvider.builder().automatic(true).build()));
//
//    mongoClient = MongoClients.create(connectionString);
//    mongoDatabase = mongoClient.getDatabase("f1").withCodecRegistry(pojoCodecRegistry);
//    mongoDatabase.getCollection("CAR_TELEMETRY").createIndex(new Document("_id", 1));

    EchoServer echoServer = new EchoServer(new PacketKafkaRepository());
    echoServer.start();
  }
}
