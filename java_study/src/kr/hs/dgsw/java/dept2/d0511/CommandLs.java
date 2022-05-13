package kr.hs.dgsw.java.dept2.d0511;

import java.io.File;
import java.text.SimpleDateFormat;
import java.util.Date;

public class CommandLs extends AbstractCommand {

	public CommandLs(File currentDirectory, String commandLine) {
		super(currentDirectory, commandLine);
	}

	@Override
	public File executeCommand() {
		File dir = currentDirectory;
		
		String[] list = dir.list();
		long time = 0;
		for (String name : list) {
			time = new File(currentDirectory + name).lastModified();
			System.out.print(formatDate(convertToDate(time)) + " ");
			if(new File(currentDirectory + name).isDirectory()) {
				System.out.print("<DIR> ");
			} else {
				System.out.print("      ");
			}
			long size = new File(currentDirectory + name).length();
			int size2 = 0;
			while(size >= 1024) {
				size /= 1024;
				size2 ++;
			}
			String size3 = "";
			
			
			switch (size2) {
			case 0 :
				size3 = "byte";
				break;
			case 1 :
				size3 = "KB";
				break;
			case 2 :
				size3 = "MB";
				break;
			case 3 :
				size3 = "GB";
			}
			
			
			
			System.out.printf("%4d %4s ", size, size3);
			System.out.println(name);
		}
		
		return currentDirectory;
	}
	
	public Date convertToDate(long unixTime) {
		return new Date(unixTime);
	}
	
	public String formatDate(Date date) {
		SimpleDateFormat dateFormat = 
				new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		return dateFormat.format(date);
	}
	
}
