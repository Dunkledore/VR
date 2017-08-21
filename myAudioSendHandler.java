package Handlers;

import Utils.Rift;
import net.dv8tion.jda.core.entities.Channel;

public class myAudioSendHandler implements net.dv8tion.jda.core.audio.AudioSendHandler
{
  private String riftName;
  private Channel channel;
  
  public myAudioSendHandler(String riftName, Channel channel)
  {
    this.riftName = riftName;
    this.channel = channel;
  }
  


  public boolean canProvide()
  {
    return true;
  }
  

  public byte[] provide20MsAudio()
  {
    try
    {
      if (channel.getId().equals(((Rift)Utils.Rifts.rifts.get(riftName)).getChannel1().getId()))
      {
        return ((Rift)Utils.Rifts.rifts.get(riftName)).getServer2Audio();
      }
      if (channel.getId().equals(((Rift)Utils.Rifts.rifts.get(riftName)).getChannel2().getId()))
      {
        return ((Rift)Utils.Rifts.rifts.get(riftName)).getServer1Audio();
      }
    }
    catch (NullPointerException localNullPointerException) {}
    


    return null;
  }
}