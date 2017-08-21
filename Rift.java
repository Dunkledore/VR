package Utils;

import net.dv8tion.jda.core.entities.Channel;

public class Rift {
  private byte[] server1Audio;
  private byte[] server2Audio;
  private Channel channel1;
  private Channel channel2;
  
  public byte[] getServer1Audio() { return server1Audio; } public void setServer1Audio(byte[] server1Audio) { this.server1Audio = server1Audio; }
  public byte[] getServer2Audio() { return server2Audio; } public void setServer2Audio(byte[] server2Audio) { this.server2Audio = server2Audio; }
  
  public Channel getChannel1() { return channel1; } public void setChannel1(Channel channel1) { this.channel1 = channel1; }
  public Channel getChannel2() { return channel2; } public void setChannel2(Channel channel2) { this.channel2 = channel2; }
  

  public Rift(Channel channel1)
  {
    this.channel1 = channel1;
  }
  
  public void Join(Channel channel2)
  {
    this.channel2 = channel2;
  }
}