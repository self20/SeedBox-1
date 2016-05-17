package com.google.service;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;

import javax.activation.MimetypesFileTypeMap;

import com.google.api.client.googleapis.media.MediaHttpUploader;
import com.google.api.client.googleapis.media.MediaHttpUploaderProgressListener;
import com.google.api.client.http.FileContent;
import com.google.api.services.drive.Drive;
import com.google.api.services.drive.Drive.Files.Create;
import com.google.api.services.drive.model.File;
import com.line.service.MsgSender;

import seedbox.files.MimeType;
import seedbox.prop.PropReader;

public class FilesUploader {

	public File uploadFile(Drive service, java.io.File fileToBeUploaded, String parentId) {

  	  File file = new File();
  	  file.setName(fileToBeUploaded.getName());

  	  if(parentId!=null){
  		file.setParents(Arrays.asList(new String[]{parentId}));
  	  }
  	  
  	  //String mimeType=MimetypesFileTypeMap.getDefaultFileTypeMap().getContentType(fileToBeUploaded);
  	  FileContent fc=new FileContent(MimeType.getMimeType(fileToBeUploaded),fileToBeUploaded);

  	  
  	  try {
  		  Create insert = service.files().create(file,fc);
  		  MediaHttpUploader uploader=insert.getMediaHttpUploader();
  		  uploader.setDirectUploadEnabled(false);
  		  uploader.setChunkSize(MediaHttpUploader.MINIMUM_CHUNK_SIZE);
  		  uploader.setProgressListener(new FileUploadProgressListener(fc));
  		  file=insert.execute();

  	  }catch (IOException e) {
  		  e.printStackTrace();
  	  }

  	  //System.out.println("File ID: " + file.getId());
  	  
  	  return file;
	}

	
    private class FileUploadProgressListener implements MediaHttpUploaderProgressListener{

    	private String fileName=null;
    	private String type=null;
    	private long timeStampBeg;
    	private long timeStampEnd;
    	
    	
    	private FileUploadProgressListener(FileContent fc){
    		this.fileName=fc.getFile().getName();
    		this.type=fc.getType();
    	}
    	
    	
    	public void progressChanged(MediaHttpUploader uploader)throws IOException{

    		long progress=Math.round(uploader.getProgress()*100);
    		
    		switch(uploader.getUploadState()){
    		case INITIATION_STARTED:
    			timeStampBeg=System.currentTimeMillis();
    			System.out.println(PropReader.getProp("info.upload.begin"));
    		break;
    		case MEDIA_COMPLETE:
    			timeStampEnd=System.currentTimeMillis();
    			MsgSender.send(PropReader.getProp("info.upload.done")+"\n"+PropReader.getProp("info.upload.file")+":"+fileName+"\n"+"MimeType:"+type+"\n"+PropReader.getProp("info.upload.timeSpent")+":"+(timeStampEnd-timeStampBeg)/1000+"s");
    			System.out.print("\b\b\b\b\b\b\b\b\b\b\b\b\b\b\b");
    			System.out.print("-----"+progress+"%-----");
    		break;
    		case MEDIA_IN_PROGRESS:
    			
    			if(progress%10==0){
        			MsgSender.send(PropReader.getProp("info.upload.progress")+"\n"+PropReader.getProp("info.upload.file")+":"+fileName+"\n"+"MimeType:"+type);
    			}
    			
    			System.out.print("\b\b\b\b\b\b\b\b\b\b\b\b\b\b\b");
    			System.out.print("-----"+progress+"%-----");
    		break;
			case INITIATION_COMPLETE:
				break;
			case NOT_STARTED:
				break;
			default:
				break;
    		}
    	}
    	
    }
    
    
}
