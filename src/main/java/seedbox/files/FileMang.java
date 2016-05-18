package seedbox.files;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Properties;
import java.util.Set;
import java.util.logging.Logger;

import org.fusesource.jansi.AnsiConsole;

import seedbox.prop.PropReader;

import static org.fusesource.jansi.Ansi.*;
import static org.fusesource.jansi.Ansi.Color.*;

public class FileMang implements Serializable{


	private static final long serialVersionUID = 8130380429043506178L;
	private List<File> fileList=null;
	private List<File> dirList=null;
	
	public static final String FILTER=".mkv";
	
	private Properties prop=new Properties();
	
	private Logger logger=null;
	
	private FileMang(){
		
		//logger=Logger.getLogger(this.getClass().getName());

		File dir=new File(PropReader.getProp("system.files.path"));
		
		File[] files=dir.listFiles();
		
		dirList=new ArrayList<File>();
		fileList=new ArrayList<File>();

		for(File f:files){
			if(f.isDirectory()){
				dirList.add(f);
				for(File f2:f.listFiles()){
					if(f2.isFile() && f2.getName().contains(FILTER))
						fileList.add(f2);
				}
				
				
			}else if(f.isFile()){
				fileList.add(f);
			}else{
				logger.info(f+" is not neightor a file nor a directory!");
			}
		}

		
		sort(dirList);
		sort(fileList);
	}
	
	
	public void sort(List<File> list){
		
		Collections.sort(list, new Comparator<File>(){
			@Override
			public int compare(File a, File b){
				return a.getName().compareTo(b.getName());
			}
		});

	}
	
	
	public static FileMang getInstance(){
		
		return new FileMang();
		
		/*
		dirList=new ArrayList<File>();
		fileList=new ArrayList<File>();
		*/
	}
	
	
	public void listFile(){
		
		System.out.println("----------------Directory-------------------------");
		for(int i=0;i<dirList.size();i++){
			System.out.println(i+". "+dirList.get(i).getName());
		}
		
		
		if(fileList.size()==0){
			System.out.println("----------------There is no file------------------");
		}else{
			System.out.println("----------------Files-----------------------------");
			for(int i=0;i<fileList.size();i++){
				System.out.println(i+". "+fileList.get(i).getName());
			}
		}

		
	}
	
	
	public File getFile(int i){
		
		return fileList.get(i);
		
	}
	

	public List<File> openDir(int i){
		
		List<File> tmpList=new ArrayList<File>();
		
		File dir=dirList.get(i);
		if(dir.isDirectory()){
			
			File[] files=dir.listFiles();
			int nextFileIndex=fileList.size();
			for(File f:files){
				fileList.add(f);
				tmpList.add(f);
			}

			System.out.println("----------------New Files-------------------------");
			for(int j=nextFileIndex;j<fileList.size();j++){
				System.out.println(j+". "+fileList.get(j).getName());
			}

		}

		return tmpList;
	}
	
	
	public void search(String keyword){
		
		Map<Integer,File> map=new HashMap<Integer,File>();

		
		for(int i=0;i<fileList.size();i++){
			if(fileList.get(i).getName().toLowerCase().indexOf(keyword.toLowerCase())!=-1){
				map.put(i, fileList.get(i));
			}
		}
		
		
		AnsiConsole.systemUninstall();

		System.out.println("----------------Search Result---------------------");
		Set<Integer> set=map.keySet();
		
		for(Integer i:set){
			System.out.println(ansi().fg(YELLOW).a(i+". "+map.get(i).getName()).reset());
		}

	}

}
