package pl.ostrowski;

import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;
import pl.ostrowski.enums.PacketType;
import pl.ostrowski.packet.event.PacketEventData;
import pl.ostrowski.packet.lap.PacketLapData;
import pl.ostrowski.packet.motion.PacketMotionData;
import pl.ostrowski.packet.session.PacketSessionData;

public class PacketStorage {

  private final PropertyChangeSupport support;
  private PacketSessionData packetSessionData;
  private PacketMotionData packetMotionData;
  private PacketLapData packetLapData;
  private PacketEventData packetEventData;

  public PacketStorage() {
    support = new PropertyChangeSupport(this);
  }

  public void setPacketSessionData(PacketSessionData packetSessionData) {
    support.firePropertyChange(
        PacketType.SESSION.name(), this.packetSessionData, packetSessionData);
    this.packetSessionData = packetSessionData;
  }

  public void setPacketMotionData(PacketMotionData packetMotionData) {
    support.firePropertyChange(PacketType.MOTION.name(), this.packetMotionData, packetMotionData);
    this.packetMotionData = packetMotionData;
  }

  public void setPacketLapData(PacketLapData packetLapData) {
    support.firePropertyChange(PacketType.LAP.name(), this.packetLapData, packetLapData);
    this.packetLapData = packetLapData;
  }

  public void setPacketEventData(PacketEventData packetEventData) {
    support.firePropertyChange(PacketType.EVENT.name(), this.packetEventData, packetEventData);
    this.packetEventData = packetEventData;
  }

  public void addPropertyChangeListener(PropertyChangeListener pcl) {
    support.addPropertyChangeListener(pcl);
  }

  public void removePropertyChangeListener(PropertyChangeListener pcl) {
    support.removePropertyChangeListener(pcl);
  }
}
