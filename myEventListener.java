package Listeners;

import Utils.Rift;
import java.util.HashMap;
import java.util.List;
import net.dv8tion.jda.core.entities.Channel;
import net.dv8tion.jda.core.entities.Guild;
import net.dv8tion.jda.core.events.message.guild.GuildMessageReceivedEvent;
import net.dv8tion.jda.core.managers.AudioManager;

public class myEventListener extends net.dv8tion.jda.core.hooks.ListenerAdapter
{
  public myEventListener() {}
  
  public void onGuildMessageReceived(GuildMessageReceivedEvent e)
  {
    if (e.getMessage().getContent().split("\\s+")[0].equals("*start"))
    {
      try
      {
        if (e.getGuild().getVoiceChannelsByName(e.getMessage().getContent().split("\\s+")[2], true).get(0) != null)
        {
          if (!Utils.Rifts.rifts.containsKey(e.getMessage().getContent().split("\\s+")[1]))
          {
            Rift rift = new Rift((Channel)e.getGuild().getVoiceChannelsByName(e.getMessage().getContent().split("\\s+")[2], true).get(0));
            Utils.Rifts.rifts.put(e.getMessage().getContent().split("\\s+")[1], rift);
            
            e.getGuild().getAudioManager().openAudioConnection((net.dv8tion.jda.core.entities.VoiceChannel)e.getGuild().getVoiceChannelsByName(e.getMessage().getContent().split("\\s+")[2], true).get(0));
            e.getGuild().getAudioManager().setReceivingHandler(new Handlers.myAudioRecieveHandler(e.getMessage().getContent().split("\\s+")[1], (Channel)e.getGuild().getVoiceChannelsByName(e.getMessage().getContent().split("\\s+")[2], true).get(0)));
            e.getGuild().getAudioManager().setSendingHandler(new Handlers.myAudioSendHandler(e.getMessage().getContent().split("\\s+")[1], (Channel)e.getGuild().getVoiceChannelsByName(e.getMessage().getContent().split("\\s+")[2], true).get(0)));
          }
          else
          {
            e.getChannel().sendMessage("Rift in use").queue();
          }
          
        }
        else {
          e.getChannel().sendMessage("Channel not found").queue();
        }
      }
      catch (ArrayIndexOutOfBoundsException e1)
      {
        e.getChannel().sendMessage("You must enter a rift name and Channel name").queue();
      }
    }
    
    if (e.getMessage().getContent().split("\\s+")[0].equals("*join"))
    {
      ((Rift)Utils.Rifts.rifts.get(e.getMessage().getContent().split("\\s+")[1])).setChannel2((Channel)e.getGuild().getVoiceChannelsByName(e.getMessage().getContent().split("\\s+")[2], true).get(0));
      
      e.getGuild().getAudioManager().openAudioConnection((net.dv8tion.jda.core.entities.VoiceChannel)e.getGuild().getVoiceChannelsByName(e.getMessage().getContent().split("\\s+")[2], true).get(0));
      e.getGuild().getAudioManager().setReceivingHandler(new Handlers.myAudioRecieveHandler(e.getMessage().getContent().split("\\s+")[1], (Channel)e.getGuild().getVoiceChannelsByName(e.getMessage().getContent().split("\\s+")[2], true).get(0)));
      e.getGuild().getAudioManager().setSendingHandler(new Handlers.myAudioSendHandler(e.getMessage().getContent().split("\\s+")[1], (Channel)e.getGuild().getVoiceChannelsByName(e.getMessage().getContent().split("\\s+")[2], true).get(0)));
    }
    
    if (e.getMessage().getContent().split("\\s+")[0].equals("*close"))
    {
      try
      {
        ((Rift)Utils.Rifts.rifts.get(e.getMessage().getContent().split("\\s+")[1])).getChannel1().getGuild().getAudioManager().closeAudioConnection();
        try
        {
          ((Rift)Utils.Rifts.rifts.get(e.getMessage().getContent().split("\\s+")[1])).getChannel2().getGuild().getAudioManager().closeAudioConnection();
          Utils.Rifts.rifts.remove(e.getMessage().getContent().split("\\s+")[1]);
        }
        catch (NullPointerException localNullPointerException1) {}
        

        return;
      }
      catch (ArrayIndexOutOfBoundsException e1)
      {
        e.getChannel().sendMessage("Please specify which rift you want to close").queue();
      }
      catch (NullPointerException e1)
      {
        e.getChannel().sendMessage("No such rift with that name").queue();
      }
    }
  }
}