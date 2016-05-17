package seedbox.files;

import java.io.File;

import javax.activation.MimetypesFileTypeMap;

public class MimeType {

	public static String getMimeType(File f){
		
		MimetypesFileTypeMap map = new MimetypesFileTypeMap(MimeType.class.getResourceAsStream("/mime.types"));
		
		return map.getContentType(f);

	}
	
}
