 package model.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import db.DB;
import db.DbException;
import db.DbIntegrityException;
import model.dao.DepartmentDao;
import model.entities.Department;

public class DepartmentDaoJDBC implements DepartmentDao {

	private Connection connection;
	
	public DepartmentDaoJDBC(Connection connection) {
		this.connection = connection;
	}

	@Override
	public void insert(Department department) {
		
		PreparedStatement preparedStatement = null;
		
		try {
			preparedStatement = connection.prepareStatement(
					
					"INSERT INTO department " +
					"(Name) " + "VALUES (?)",
					
					Statement.RETURN_GENERATED_KEYS);
			
			preparedStatement.setString(1, department.getName());
			
			int rowsAffected = preparedStatement.executeUpdate();

			if (rowsAffected > 0) {
				ResultSet resultSet = preparedStatement.getGeneratedKeys();
				
				if (resultSet.next())
					department.setId(resultSet.getInt(1));
				
				DB.closeResultSet(resultSet);
			}else
				throw new DbException("Unexpected error! No rows affected!");
			
		} catch (SQLException e) {
			throw new DbException(e.getMessage());
		} finally {
			DB.closeStatement(preparedStatement);
		}
	}

	@Override
	public void update(Department department) {
		
		PreparedStatement preparedStatement = null;

		try {
			preparedStatement = connection.prepareStatement(

					"UPDATE department " + 
					"SET Name = ? " +
					"WHERE Id = ? ",

					Statement.RETURN_GENERATED_KEYS
			);
			
			preparedStatement.setString(1, department.getName());
			preparedStatement.setInt(2, department.getId());

			preparedStatement.executeUpdate();

		} catch (SQLException e) {
			throw new DbException(e.getMessage());
		} finally {
			DB.closeStatement(preparedStatement);
		}
	}

	@Override
	public void deleteById(Integer id) {
		
		PreparedStatement preparedStatement = null;
		
		try {
			
			preparedStatement = connection.prepareStatement(
					"DELETE FROM department " +
					"WHERE " +
					"Id = ?"
			);
			
			preparedStatement.setInt(1, id);
			preparedStatement.executeUpdate();
			
		} catch (SQLException e) {
			throw new DbIntegrityException(e.getMessage());
		} finally {
			DB.closeStatement(preparedStatement);
		}
	}

	@Override
	public Department findById(Integer id) {
		
		PreparedStatement preparedStatement = null;
		ResultSet resultSet = null;
		
		try {

			preparedStatement = connection.prepareStatement(
					"SELECT * FROM department " + 
					"WHERE department.Id = ?");

			preparedStatement.setInt(1, id);

			resultSet = preparedStatement.executeQuery();

			if (resultSet.next())
				return instantiateDepartment(resultSet);

			return null;

		} catch (SQLException e) {
			throw new DbException(e.getMessage());
		} finally {
			DB.closeStatement(preparedStatement);
			DB.closeResultSet(resultSet);
		}
	}

	@Override
	public List<Department> findAll() {
		
		PreparedStatement preparedStatement = null;
		ResultSet resultSet = null;
		
		try {
			
			preparedStatement = connection.prepareStatement(
					"SELECT * " + 
					"FROM department " + 
					"ORDER BY Name"
			);
			
			List<Department> departments = new ArrayList<>();
			
			resultSet = preparedStatement.executeQuery();
			
			while (resultSet.next())
				departments.add(instantiateDepartment(resultSet));
			
			return departments;
			
		} catch (SQLException e) {
			throw new DbException(e.getMessage());
		} finally {
			DB.closeStatement(preparedStatement);
			DB.closeResultSet(resultSet);
		}
	}
	
	private Department instantiateDepartment(ResultSet resultSet) throws SQLException {
		
		Department department = new Department();

		department.setId(resultSet.getInt("Id"));
		department.setName(resultSet.getString("Name"));
		
		return department;
	}

}
