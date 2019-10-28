package org.astw.util;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.Arrays;
import java.util.StringTokenizer;

public class LatexMerge {

	public static void main(String ar[]){
        
		String folderName = "";
		
		if(ar.length != 0)
			folderName = ar[0];
			
        File f = new File("./"+folderName);
        File[] fList = f.listFiles();
        //System.out.println("this directory: "+f.getAbsolutePath());
        //System.out.println(fList.length+" files");

        Arrays.sort(fList);
        StringBuilder allContent = new StringBuilder("");

        for(int ii = 0; ii < fList.length; ii++){

        	File currentFile = fList[ii];
        	String fileName = currentFile.getName();
        	
        	if(fileName.charAt(0) != '.'){//ignore hidden files
        		
        		//System.out.println("fileName: "+fileName);
        		try {
        			String currContent = LatexMerge.getTheWholeFileContent(currentFile).replace("_", "\\_");
					allContent.append(currContent+"\n");
				} catch (IOException e) {
					e.printStackTrace();
				}
        	}
        }
        
        System.out.println(allContent);
		
	}

	private static String getTheWholeFileContent(File f) throws IOException{
		
		BufferedReader reader = new BufferedReader(new FileReader(f));
		StringBuilder allContent = new StringBuilder("");
		String newContent = reader.readLine();
		allContent.append(newContent+"\n");
		while(newContent != null){
			allContent.append(newContent+"\n");
			newContent = reader.readLine();
		}
		
		reader.close();
		
		return allContent.toString();
	}

    private static String getExtension(File f){
        
        StringTokenizer strtok = new StringTokenizer(f.getName(), ".");
        
        for(int ii = 0; ii < strtok.countTokens(); ii++)
            if(strtok.hasMoreTokens())
                strtok.nextToken();
        
        if(strtok.hasMoreTokens())
            return strtok.nextToken();
        else
            return "";
        
    }
    
}
