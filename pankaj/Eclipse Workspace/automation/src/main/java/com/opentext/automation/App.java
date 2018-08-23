package com.opentext.automation;

import java.io.File;
import java.io.IOException;

import org.apache.commons.codec.binary.Base64;
import org.apache.commons.io.FileUtils;


public class App 
{
    public static void main( String[] args ) throws IOException
    {
    	File file  = new File("D:\\Work\\Load testing\\Load\\774665-1.gif");
    	//File file  = new File("D:\\XSS.jpg");
        System.out.println(new String(Base64.encodeBase64(FileUtils.readFileToByteArray(file))));
    }
}
