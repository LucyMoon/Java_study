package kr.hs.dgsw.java.dept2.d0511;

import java.io.File;

public class CommandCd extends AbstractCommand {

	public CommandCd(File currentDirectory, String commandLine) {
		super(currentDirectory, commandLine);
	}

	@Override
	public File executeCommand() {
		String addpath = commandLine.substring(3, commandLine.length());
		
		File path = new File(currentDirectory+addpath);
		
		System.out.println(path);
		
		if(addpath.equals("..")) {
			return new File("C:/");
		}
		else if(path.exists()) {
			return path;
		}
		
		System.out.println("cannot find path");
		return currentDirectory;
	}
}
