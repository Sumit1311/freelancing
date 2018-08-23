package com.emotion.framework.tests;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.testng.annotations.Test;

import com.emotion.core.Log;
import com.emotion.util.Database;

public class DBTest {
  @Test
  public void test() throws SQLException 
  {
	  Log l = new Log();
	  Database db = new Database();
	  db.connect();
	  ResultSet rs = db.executeStatement("select * from pni_user");
	  while (rs.next()) {
			System.out.println(rs.getString("user_name"));
		}
  }
}
