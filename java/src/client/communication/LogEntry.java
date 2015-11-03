package client.communication;

import shared.definitions.CatanColor;
import client.model.MessageLine;

/**
 * Message (or entry) displayed in the LogComponent
 */
public class LogEntry
{
	
	/**
	 * Color used when displaying the message
	 */
	private CatanColor color;
	
	/**
	 * Message text
	 */
	private String message;
	
	public LogEntry(CatanColor color, String message)
	{
		this.color = color;
		this.message = message;
	}
	
	public LogEntry(MessageLine line) {
		// TODO??//
	}

	public CatanColor getColor()
	{
		return color;
	}
	
	public void setColor(CatanColor color)
	{
		this.color = color;
	}
	
	public String getMessage()
	{
		return message;
	}
	
	public void setMessage(String message)
	{
		this.message = message;
	}
	
}

