package com.emotion.util;

import java.sql.DriverManager;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import com.emotion.core.Log;
import com.emotion.external.Configuration;


/**
 *	@author himanshu_upadhyay
 *	@version 1.0
 *	Last Updated: 17 NOV 2014
 */

public class Database 
{
	private String dbname;
	private String dbuser;
	private String dbmachine;
	private String dbpassword;
	private String dbport;
	private Connection conn=null;
	
	public Database() {
		dbname= Configuration.getProperty(Configuration.DB_NAME);
		dbuser= Configuration.getProperty(Configuration.DB_USERNAME);
		dbmachine= Configuration.getProperty(Configuration.DB_MACHINE);
		dbpassword= Configuration.getProperty(Configuration.DB_PASSWORD);
		dbport= Configuration.getProperty(Configuration.DB_PORT);
		if (dbport.isEmpty() || dbport==null)
		{
			this.dbport="1433";
		}
	}
	
	public Database(String dbmachine,String dbport,String dbname,String dbuser,String dbpassword)
	{
		this.dbname=dbname;
		this.dbmachine= dbmachine;
		this.dbpassword=dbpassword;
		if (dbport.isEmpty() || dbport==null)
		{
			this.dbport="1433";
		}else
		{
			this.dbport=dbport;
		}
		this.dbuser=dbuser;
	}
	
	public String getConnectionString()
	{
		return "jdbc:sqlserver://" + dbmachine + ":" + dbport + ";user=" + dbuser + 
						";password=" + dbpassword + ";database=" + dbname;
	}
	
	public void connect()
	{
		try {
			Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
			conn = 	DriverManager.getConnection(getConnectionString());
		}catch (Exception e) {
			Log.error(e.toString());
		}
	}
	
	public ResultSet executeStatement(String Sql)
	{
		try {
			Statement sta = conn.createStatement();
			return  sta.executeQuery(Sql);
		} catch (SQLException e) {
			Log.error(e.toString());
		}
		return null;
	}
	
}
