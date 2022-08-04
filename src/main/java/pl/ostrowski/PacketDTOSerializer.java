package pl.ostrowski;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import dto.Packet;
import java.util.Map;
import org.apache.kafka.common.serialization.Serializer;

public class PacketDTOSerializer implements Serializer<dto.Packet> {
  @Override
  public void configure(Map<String, ?> map, boolean b) {}

  @Override
  public byte[] serialize(String s, Packet packet) {
    ObjectMapper objectMapper = new ObjectMapper();
    String k = null;
    try {
      k = objectMapper.writeValueAsString(packet);
    } catch (JsonProcessingException e) {
      e.printStackTrace();
    }
    return k.getBytes();
  }

  @Override
  public void close() {}
}
