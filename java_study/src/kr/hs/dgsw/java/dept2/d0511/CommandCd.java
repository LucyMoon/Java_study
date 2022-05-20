package kr.hs.dgsw.java.dept2.d0511;

import java.io.File;

public class CommandCd extends AbstractCommand {

   public CommandCd(File currentDirectory, String commandLine) {
      super(currentDirectory, commandLine);

   }

   @Override
   public File executeCommand() {
      File dir = currentDirectory;
      
      String path = commandLine.substring(3);
      
      if(new File(dir, path).exists()) {
    	  if(path.equals("..")) {
        	  for(int i = dir.getAbsolutePath().length()-1; i > 0; i--) {
        		  if(dir.getAbsolutePath().substring(i-1, i).equals("\\")) {
        			  return new File(dir.getAbsolutePath().substring(0, i));
        		  }
        	  }
        	  System.out.println("cannot find the path");
        	  return currentDirectory;
          }else {
        	  return new File(dir + "\\"+path);
          }

      }
      else {
    	  System.out.println("cannot find the path");
    	  return currentDirectory;
      }
   }

}