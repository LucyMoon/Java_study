package kr.hs.dgsw.java.dept2.d0511;

import java.io.File;
import java.text.SimpleDateFormat;
import java.util.Date;

public class CommandLs extends AbstractCommand {

	public CommandLs(File currentDirectory, String commandLine) {
		super(currentDirectory, commandLine);
		File dir = currentDirectory;
		String[] list = dir.list();
		File[] listsFiles = dir.listFiles();
		for (File file : listsFiles) {
			if (file != null) {
				String date = formateDate(file.lastModified());
				String pdir = "";
				if (file.isDirectory()) {
					pdir = "<dir>";
				}
				long Fsize = file.length();
				int countsize = 0;
				String Size = "B";
				while (Fsize >= 1024) {
					Fsize /= 1024;
					countsize++;
				}
				switch (countsize) {
				case 1:
					Size = "KB";
				case 2:
					Size = "MB";
				case 3:
					Size = "GB";
				case 4:
					Size = "TB";
				default : 
					Size = "B";
				}
				System.out.printf("%s %5s %4d%2s %s\n", date, pdir, Fsize, Size, file.getName());
			}
		}
	}

	public Date makeDate(long unixTime) {
		return new Date(unixTime);
	}

	public String formateDate(long l) {
		SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-mm-dd hh:dd:ss");
		return dateFormat.format(l);

	}

	@Override
	public File executeCommand() {
		return this.currentDirectory;
	}

}