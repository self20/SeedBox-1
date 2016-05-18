package seedbox.test;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.UnsupportedEncodingException;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;
import java.net.URLEncoder;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;

import javax.activation.MimetypesFileTypeMap;

import com.abercap.mediainfo.api.MediaInfo;
import com.google.api.client.http.FileContent;
import com.google.api.services.drive.Drive;



import com.google.api.services.drive.model.FileList;
import com.google.service.DriveService;
import com.google.service.FilesUploader;
import com.line.service.MsgSender;

import seedbox.files.FileMang;
import seedbox.files.MimeType;



public class test {

	/*
	public static void main(String[] args){

		File fileToBeUploaded=new File("D:\\Movie\\The.Martian.2015.1080p.BluRay.x264.DTS-WiKi.Sample.mkv");

		System.out.println(MimeType.getMimeType(fileToBeUploaded));

	}
	*/
	
	/*
	public static void main(String args[]) throws IOException{
		
		
		Drive driveService=DriveService.getDriveService();
		
		String pageToken = null;
		do {
		    FileList result = driveService.files().list().execute();
		    for(File file: result.getFiles()) {
		        System.out.println(file.getName()+"\t"+file.getMimeType());
		    }
		    pageToken = result.getNextPageToken();
		} while (pageToken != null);
	}
	*/
	
	/*
	public static void main(String args[]) throws IOException{
		
		
		Drive driveService=DriveService.getDriveService();

		File f=new File("D:\\FileA.txt");
		
		FilesUploader fa=new FilesUploader();
		fa.uploadFile(driveService, f, null);
		
	}
	*/
	/*
	public static void main(String args[]) throws IOException{
		
		FileMang fmg=new FileMang();
		fmg.listFile();
		
	}
	*/
	/*
	public static void main(String[] args){
		
		String url = "https://csie.io/lineme";
		String charset = "UTF-8";  // Or in Java 7 and later, use the constant: java.nio.charset.StandardCharsets.UTF_8.name()
		String param1 = "gctcxu-5338774";
		String param2 = "Hello from the other side";
		// ...

		String query = null;
		try {
			query = String.format("token=%s&msg=%s", URLEncoder.encode(param1, charset), URLEncoder.encode(param2, charset));
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		}
		
		
		URLConnection connection;
		try {
			connection = new URL(url + "?" + query).openConnection();
			connection.setRequestProperty("Accept-Charset", charset);
			InputStream response = connection.getInputStream();
		} catch (IOException e) {
			e.printStackTrace();
		}

	}
	 */
	
	/*
	public static void main(String[] args) throws InterruptedException{
		
		for(int i=0;i<100;i++){
			Thread.sleep(100);
			System.out.print("\b\b\b\b\b\b\b\b\b\b\b\b\b\b\b\b\b\b\b\b\b\b\b\b\b\b\b\b\b\b");
			System.out.print("Upload percent: "+i+"%");
		}
		
		
	}
	*/
	/*
	public static void main(String[] args){
		
		MsgSender.send("Hello World from the other side 2016/4/10 -4");
		
	}
	*/
	
	/*
	public static void main(String[] args){
		
		System.out.println("cirkeln.2015.720p.bluray.dts.x264-sbr.mkv".indexOf("0"));
		
		
	}
	*/
}
