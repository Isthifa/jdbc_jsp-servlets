package Dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import Model.Employee;

public class EmployeeDao {
	public static int registerEmployee(Employee employee) throws ClassNotFoundException
	{
		String insertintodb="INSERT into Employee"+"(id,first_name,last_name,username,password,address,contact) values "+"(?,?,?,?,?,?,?);";
		
		int result=0;
		Class.forName("com.mysql.jdbc.Driver");
		try {
			Connection con=DriverManager.getConnection("jdbc.mysql://localhost:3306/employee","root","root");
			PreparedStatement preparedStatement=con.prepareStatement(insertintodb);
			
			 preparedStatement.setInt(1, 1);
	            preparedStatement.setString(2, employee.getFirstName());
	            preparedStatement.setString(3, employee.getLastName());
	            preparedStatement.setString(4, employee.getUsername());
	            preparedStatement.setString(5, employee.getPassword());
	            preparedStatement.setString(6, employee.getAddress());
	            preparedStatement.setString(7, employee.getContact());

	            System.out.println(preparedStatement);
	            // Step 3: Execute the query or update query
	            result = preparedStatement.executeUpdate();

	        } catch (SQLException e) {
	            // process sql exception
	            printSQLException(e);
	        }
	        return result;
	    }

	    private static void printSQLException(SQLException ex) {
	        for (Throwable e: ex) {
	            if (e instanceof SQLException) {
	                e.printStackTrace(System.err);
	                System.err.println("SQLState: " + ((SQLException) e).getSQLState());
	                System.err.println("Error Code: " + ((SQLException) e).getErrorCode());
	                System.err.println("Message: " + e.getMessage());
	                Throwable t = ex.getCause();
	                while (t != null) {
	                    System.out.println("Cause: " + t);
	                    t = t.getCause();
	                }
	            }
	        }
	}
}
