package pl.ostrowski;

import enums.PacketType;
import java.util.Properties;
import org.apache.kafka.clients.producer.KafkaProducer;
import org.apache.kafka.clients.producer.Producer;
import org.apache.kafka.clients.producer.ProducerRecord;
import pl.ostrowski.packet.Packet;
import pl.ostrowski.packet.PacketFactory;

public class PacketKafkaRepository implements PacketRepository {

  private static final String ID_FIELD_NAME = "_id";
  private final String topic;
  private final Properties props;
  Producer<String, dto.Packet> producer;

  public PacketKafkaRepository() {
    String username = "el33rgui";
    String password = "UZ15Zupjmdl-5l2IavSVedw6kul-fsTw";
//    String brokers =
//        "rocket-01.srvs.cloudkafka.com:9094,rocket-02.srvs.cloudkafka.com:9094,rocket-03.srvs.cloudkafka.com:9094";
        String brokers =
        "51.75.45.190:9092";
    this.topic = username + "-EVENT";

    String jaasTemplate =
        "org.apache.kafka.common.security.scram.ScramLoginModule required username=\"%s\" password=\"%s\";";
    String jaasCfg = String.format(jaasTemplate, username, password);

    String serializer = PacketDTOSerializer.class.getName();
    String deserializer = PacketDTOSerializer.class.getName();
    props = new Properties();
    props.put("bootstrap.servers", brokers);
    props.put("group.id", username + "-consumer");
    props.put("enable.auto.commit", "true");
    props.put("auto.commit.interval.ms", "1000");
    props.put("auto.offset.reset", "earliest");
    props.put("session.timeout.ms", "30000");
    props.put("key.deserializer", deserializer);
    props.put("value.deserializer", deserializer);
    props.put("key.serializer", serializer);
    props.put("value.serializer", serializer);
    props.put("default.replication.factor", 1);
//    props.put("security.protocol", "SASL_SSL");
//    props.put("sasl.mechanism", "SCRAM-SHA-256");
//    props.put("sasl.jaas.config", jaasCfg);

    producer = new KafkaProducer<>(props);
  }

  @Override
  public void savePacket(Packet packet, String clientId) {
    String packetType = packet.getPacketType().toString();
    if (packetType.equals(PacketType.CAR_TELEMETRY.toString())) {
      dto.Packet packetDTO = PacketFactory.toDto(packet);
      ProducerRecord<String, dto.Packet> producerRecord = new ProducerRecord<>(topic, packetDTO);
      producer.send(producerRecord);
      producer.flush();
    }
  }
}
