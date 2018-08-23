package com.emotion.util;

import java.io.File;
import java.io.IOException;

public class UplaodUtils {
	
	public static void dragAndDrop() throws IOException
	{
		Runtime.getRuntime().exec(System.getProperty("user.dir") 
				+ File.separator+ "src/main/resources" + File.separator + "1.exe");
	}

}
