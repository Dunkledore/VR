package Handlers;

import Utils.Rift;
import Utils.Rifts;
import java.util.HashMap;
import net.dv8tion.jda.core.audio.CombinedAudio;
import net.dv8tion.jda.core.entities.Channel;

public class myAudioRecieveHandler implements net.dv8tion.jda.core.audio.AudioReceiveHandler
{
  private String riftName;
  private Channel channel;
  
  public myAudioRecieveHandler(String riftName, Channel channel)
  {
    this.riftName = riftName;
    this.channel = channel;
  }
  


  public boolean canReceiveCombined()
  {
    return true;
  }
  


  public boolean canReceiveUser()
  {
    return false;
  }
  

  public void handleCombinedAudio(CombinedAudio combinedAudio)
  {
    try
    {
      if (channel.getId().equals(((Rift)Rifts.rifts.get(riftName)).getChannel1().getId()))
      {
        ((Rift)Rifts.rifts.get(riftName)).setServer1Audio(combinedAudio.getAudioData(0.5D));
      }
      if (channel.getId().equals(((Rift)Rifts.rifts.get(riftName)).getChannel2().getId())) {}
      
      ((Rift)Rifts.rifts.get(riftName)).setServer2Audio(combinedAudio.getAudioData(0.5D));
    }
    catch (NullPointerException localNullPointerException) {}
  }
  
  public void handleUserAudio(net.dv8tion.jda.core.audio.UserAudio userAudio) {}
}