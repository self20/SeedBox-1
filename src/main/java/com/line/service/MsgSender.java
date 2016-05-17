package com.line.service;

import java.io.IOException;
import java.io.InputStream;
import java.io.UnsupportedEncodingException;
import java.net.URL;
import java.net.URLConnection;
import java.net.URLEncoder;

import seedbox.prop.PropReader;

public class MsgSender {


	private MsgSender(){}
	
	
	public static void send(String msg){
		
		
		String charset = PropReader.getProp("line.msger.charset");; 
		String url = PropReader.getProp("line.msger.url");
		String token = PropReader.getProp("line.msger.token");;
		
		String param2 = msg;

		String query = null;
		try {
			query = String.format("token=%s&msg=%s", URLEncoder.encode(token, charset), URLEncoder.encode(param2, charset));
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		}
		
		
		URLConnection connection;
		try {
			connection = new URL(url + "?" + query).openConnection();
			connection.setRequestProperty("Accept-Charset", charset);
			@SuppressWarnings("unused")
			InputStream response = connection.getInputStream();
		} catch (IOException e) {
			e.printStackTrace();
		}

	}

}
