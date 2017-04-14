package bbdp.doctor.model;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import bbdp.db.model.DBConnection;

public class PatientSearchVerification {

	public static String searchVerification(DBConnection conn, String inputID){
		String result = "";
		String patient = "";
		try {
			ResultSet resultSet = conn.runSql("SELECT * FROM patient WHERE account LIKE '_____" + inputID + "'");
			//搜尋成功
			while (resultSet.next()) {
				//第一個物件
				if(patient.equals("")){
					patient = "{\"patientID\":\"" + resultSet.getString("patientID") + 
							"\",\"account\":\"" + resultSet.getString("account") + 
								"\",\"name\":\"" + resultSet.getString("name") + "\"}";
				}
				else{
					patient += ",{\"patientID\":\"" + resultSet.getString("patientID") + 
							"\",\"account\":\"" + resultSet.getString("account") + 
							"\",\"name\":\"" + resultSet.getString("name") + "\"}";
				}
				result = "[" + patient +"]";
				//System.out.println(resultSet.getInt("id")+"\t\t"+ 
				//resultSet.getString("name")+"\t\t"+resultSet.getString("passwd")); 
			}
			
			//搜尋失敗
			if(result.equals("")){	
				result = "fail";
			}
			
			return result;
		} catch (SQLException e) {
			result = "SQLException";
			return result;
		}
		
	}
}