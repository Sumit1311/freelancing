package com.emotion.autoit;

/**
 *	@author himanshu_upadhyay
 *	@version 1.0
 *	Last Updated: 12 NOV 2014
 */

import com.emotion.core.Log;

public class AutoItUtils 
{
	private static AutoIt autoit = new AutoIt();
	
	public static void browseFile(String FilePath)
	{
		String Title = "File Upload";
		 Log.info("Executing AutoIT script for file browser");
		 autoit.autoItSetOption("WinTitleMatchMode", 2);
		 if (autoit.winWaitActive("File Upload", "", 20)>0)
		 {
			 autoit.winActivate(Title, "");
			 autoit.controlSend(Title, "", "Edit1", FilePath);
			 autoit.controlClick(Title, "", "Button1");
		 }else
		 {
			 Log.error("Failed to find file browser window.");
		 }
	}
	
	public static void dragAndDropFile()
	{
		String filePath= "D:\\links.txt";
		int Pid= autoit.run("explorer.exe /n,/e,/select,", filePath, 0);
		autoit.processWait(Integer.toString(Pid), 5);
		autoit.winWaitActive("New Volume (D:)", "", 10);
		int x = autoit.winGetPos("New Volume (D:)", "");
		int y = autoit.winGetPos("New Volume (D:)", "");
		int pos=40;
		autoit.mouseClick("Left", x+pos, y+pos, 1, 1);
	}
}
