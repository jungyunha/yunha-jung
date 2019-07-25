package com.revature.ulti;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

public class ConnFactory {
//Singleton Factory
	private static ConnFactory cf = new ConnFactory();

	private ConnFactory()  {
		super();
	}

	public static synchronized ConnFactory getInstance() {
		if (cf == null) {
			cf = new ConnFactory();
		}
		return cf;
	}

	public static Connection getConnection() {
		Connection conn = null;
		Properties prop = new Properties();

		try {
			InputStream stream = ConnFactory.class.getClassLoader().getResourceAsStream("database.properties");
			prop.load(stream);
			Class.forName(prop.getProperty("driver"));
			// Class.forName(prop.getProperty("url"));
			// Class.forName(prop.getProperty("user"));
			// Class.forName(prop.getProperty("password"));
			conn = DriverManager.getConnection(prop.getProperty("url"), prop.getProperty("user"),
					prop.getProperty("password"));
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return conn;
	}
}