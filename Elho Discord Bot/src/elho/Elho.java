package elho;

import javax.security.auth.login.LoginException;

import net.dv8tion.jda.api.JDA;
import net.dv8tion.jda.api.JDABuilder;

public class Elho {
	
	public static JDA jda;
	public static String prefix = "?";
	
	public static void main(String[] args) throws LoginException {
		
		@SuppressWarnings("unused")
		JDA jda = JDABuilder.createDefault("YOUR TOKEN").build();
		jda.addEventListener(new Commands());
		
	}

}
