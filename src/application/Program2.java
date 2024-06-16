package application;

import java.util.List;

import model.dao.DaoFactory;
import model.dao.DepartmentDao;
import model.entities.Department;

public class Program2 {

	public static void main(String[] args) {
		
		DepartmentDao departmentDao = DaoFactory.createDepartmentDao();
		
//		Department department = new Department(null, "music");
//		
//		System.out.println("Teste insert");
//		
//		departmentDao.insert(department);
//		
//		System.out.println("Inserido");
		
//		System.out.println("Teste update");
//		
//		Department departmentUpdate = new Department(2, "Electronics");
//		
//		departmentDao.update(departmentUpdate);
//		
//		System.out.println("Atualizado");
		
		System.out.println("\n Teste: Pegar todos os departamentos");
		
		List<Department> departments = departmentDao.findAll();
		
		for (Department d : departments)
			System.out.println(d);
		
		System.out.println("\n Teste: Departamento por id");
		
		Department departmentId = departmentDao.findById(1);
		
		System.out.println(departmentId);
		
		System.out.println("\n Teste: Deletar Departamento");
		
		departmentDao.deleteById(1);
		System.out.println("Deletado");
	}

}//class Program2
