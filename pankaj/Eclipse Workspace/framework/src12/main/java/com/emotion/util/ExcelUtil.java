package com.emotion.util;

/**
 *	@author himanshu_upadhyay
 *	@version 1.0
 *	Last Updated: 12 NOV 2014
 */

import java.io.File;
import com.emotion.core.Log;
import jxl.*;

public class ExcelUtil
{
   	public static String[][] getTableArray(String xlFilePath, String sheetName, String tableName) 
   	{
        String[][] tabArray=null;
	    Workbook workbook = null;
		try {
			workbook = Workbook.getWorkbook(new File(xlFilePath));
		} catch (Exception e) {
			Log.error(e.toString());;
		} 
	    Sheet sheet = workbook.getSheet(sheetName); 
	    int startRow,startCol, endRow, endCol,ci,cj;
	    Cell tableStart=sheet.findCell(tableName);
	    startRow=tableStart.getRow();
	    startCol=tableStart.getColumn();
	    endRow= sheet.getRows();
	    endCol= sheet.getColumns();
	    tabArray=new String[endRow-startRow-1][endCol-startCol-1];
	    ci=0;
	
	    for (int i=startRow+1;i<endRow;i++,ci++){
	        cj=0;
	        for (int j=startCol+1;j<endCol;j++,cj++){
	            tabArray[ci][cj]=sheet.getCell(j,i).getContents();
	        }
	    }
        return(tabArray);
    }

}
