package seedbox.coman;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;

import com.google.api.services.drive.Drive;
import com.google.service.DriveService;
import com.google.service.FilesUploader;

import seedbox.files.FileMang;
import seedbox.prop.Location;

public class command {

	public static FileMang fmg=null;
	
	public static void main(String[] args){

		
		
		argTrim(args);
		
		if((fmg=getFileMang())==null){
			fmg=FileMang.getInstance();
		}

		switch(args[0]){

			case "-l":
				doList();
			break;
			case "-opendir":
				String dirId=args[1];
				doOpenDir(Integer.parseInt(dirId));
			break;
			case "-upload":
				String fileIdofList=args[1];
				
				if(fileIdofList.matches("[0-9]*")){
					doUploadByNo(Integer.parseInt(fileIdofList));
				}else{
					doUploadByFilename(fileIdofList);
				}

			break;
			case "-refresh":
				doInit();
			break;
			case "-search":
				String keyword=args[1];
				doSearch(keyword);
			break;

		}

		storeFileMang(fmg);

	}
	
	
	public static void argTrim(String[] args){
		
		for(String arg:args){
			arg=arg.trim();
		}

	}
	
	
	public static void doInit(){
		
		fmg=FileMang.getInstance();
		
	}
	
	
	public static void doList(){
		
		fmg.listFile();

	}
	
	
	public static void doOpenDir(int i){

		fmg.openDir(i);

	}
	
	
	public static void doUploadByNo(int fileId){

		Drive service = null;
		
		try {
			service = DriveService.getDriveService();
		} catch (IOException e) {
			e.printStackTrace();
		};

		
		File f=fmg.getFile(fileId);
		
		FilesUploader fa=new FilesUploader();
		fa.uploadFile(service, f, null);
		
	}
	
	
	public static void doUploadByFilename(String fileName){
		
		Drive service = null;
		
		try {
			service = DriveService.getDriveService();
		} catch (IOException e) {
			e.printStackTrace();
		};

		
		File f=fmg.getFile(fileName);
		
		FilesUploader fa=new FilesUploader();
		fa.uploadFile(service, f, null);
		
	}
	
	
	public static void storeFileMang(Serializable serialObject){
		FileOutputStream fos=null;
		ObjectOutputStream oos=null;

		try {
			
			
			fos=new FileOutputStream(Location.PATH+"FileMangObject");
			oos=new ObjectOutputStream(fos);
			oos.writeObject(serialObject);
			
			oos.close();
			fos.close();
			
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	
	public static FileMang getFileMang(){
		
		FileInputStream fis=null;
		ObjectInputStream ois=null;
		
		FileMang fm=null;
		
		try {
			fis=new FileInputStream(Location.PATH+"FileMangObject");
			ois=new ObjectInputStream(fis);
			fm=(FileMang)ois.readObject();
			
			ois.close();
			fis.close();
			
		} catch (Exception e) {
			e.printStackTrace();
		}

		
		return fm;
	}
	
	
	public static void doSearch(String keyword){
		
		fmg.search(keyword);
		
	}
	
}
