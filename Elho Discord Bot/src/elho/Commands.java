package elho;

import java.util.List;

import net.dv8tion.jda.api.EmbedBuilder;
import net.dv8tion.jda.api.entities.Message;
import net.dv8tion.jda.api.events.message.guild.GuildMessageReceivedEvent;
import net.dv8tion.jda.api.hooks.ListenerAdapter;

public class Commands extends ListenerAdapter{
	
	public void onGuildMessageReceived(GuildMessageReceivedEvent event) {
		
		String[] args = event.getMessage().getContentRaw().split("\\s+");
		
		if(args[0].equalsIgnoreCase(Elho.prefix)) {
			
			EmbedBuilder nocmd = new EmbedBuilder();
			nocmd.setTitle("Du musst einen Command hinter meinem Prefix benutzen, sonst weiß ich nicht was ich tun soll.");
			nocmd.setColor(0x4db86f);
			
			event.getChannel().sendTyping().queue();
			event.getChannel().sendMessage(nocmd.build()).queue();
			nocmd.clear();
			
		}
		
		if(args[0].equalsIgnoreCase(Elho.prefix + "clear")) {
			if(args.length < 2) {
				EmbedBuilder wrongUsage = new EmbedBuilder();
				wrongUsage.setTitle("❗  Error: Kein Wert hinter dem ?clear Befehl");
				wrongUsage.setDescription("Verwendung des Befehls: Du musst hinter dem ?clear Befehl eine Zahl zwischen 1 und 100 eingeben, damit ich weiss, wie viele Nachrichten ich löschen soll.");
				wrongUsage.setColor(0xff3923);
				
				event.getChannel().sendTyping().queue();
				event.getChannel().sendMessage(wrongUsage.build()).queue();
				wrongUsage.clear();
			}
			else {
				try {
					List<Message> messages = event.getChannel().getHistory().retrievePast(Integer.parseInt(args[1])).complete();
					event.getChannel().deleteMessages(messages).queue();
					
					EmbedBuilder success = new EmbedBuilder();
					success.setTitle("✅" + args[1] + "Nachrichten erfolgreich gelöscht!");
					success.setColor(0x4db86f);
					
					event.getChannel().sendTyping().queue();
					event.getChannel().sendMessage(success.build()).queue();
					success.clear();
				}
				catch (IllegalArgumentException e) {
					if(e.toString().startsWith("java.lang.IllegalArgumentException: Message retrieval")) {
						//too many message deletes requested
						EmbedBuilder messageError = new EmbedBuilder();
						messageError.setTitle("❗  Error: Zu viele oder wenige zu löschende Nachrichten");
						messageError.setDescription("Du kannst nach dem ?clear Befehl zwischen 1 und 100 Nachrichten löschen lassen.");
						messageError.setColor(0xff3923);
						
						event.getChannel().sendTyping().queue();
						event.getChannel().sendMessage(messageError.build()).queue();
						messageError.clear();
					}
					else {
						//messages are to old to delete (> 2 weeks)
						EmbedBuilder messageError = new EmbedBuilder();
						messageError.setTitle("❗  Error: Nachrichten älter als 2 Wochen");
						messageError.setDescription("Du kannst nach mit dem ?clear Befehl keine Nachrichten löschen, die älter als 2 Wochen sind.");
						messageError.setColor(0xff3923);
						
						event.getChannel().sendTyping().queue();
						event.getChannel().sendMessage(messageError.build()).queue();
						messageError.clear();
					}
				}
			}
		}
		
		if(args[0].equalsIgnoreCase(Elho.prefix + "info")) {
			
			EmbedBuilder info = new EmbedBuilder();
			info.setTitle("Elho Bot Information");
			info.setDescription("Ich bin Elho und ich wurde von Timo programmiert. Mein Prefix ist '?'.");
			info.addField("Creator:", "© 2020 codingbytimo", false);
			info.setColor(0x4db86f);
			
			event.getChannel().sendTyping().queue();
			event.getChannel().sendMessage(info.build()).queue();
			info.clear();
			
		}
		
		if(args[0].equalsIgnoreCase(Elho.prefix + "cmd")) {
			
			EmbedBuilder cmd = new EmbedBuilder();
			cmd.setTitle("Command List:");
			cmd.addField("1. '?info'", "Zeigt generelle Infos zu Elho", false);
			cmd.addField("2. '?cmd'", "Listet alle Befehle auf", false);
			cmd.addField("3. '?clear (number)", "Löscht eine beliebige Zahl an Nachrichten", false);
			cmd.setColor(0x5a5bc7);
			
			event.getChannel().sendTyping().queue();
			event.getChannel().sendMessage(cmd.build()).queue();		
			cmd.clear();
			
			
		}
		
		if(args[0].equalsIgnoreCase(Elho.prefix + "hi")) {
			event.getChannel().sendMessage("Hallo!").queue();
		}

}
}
