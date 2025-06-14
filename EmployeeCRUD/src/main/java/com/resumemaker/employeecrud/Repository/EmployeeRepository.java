package com.resumemaker.employeecrud.Repository;

import com.resumemaker.employeecrud.Entity.Employee;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface EmployeeRepository  extends JpaRepository<Employee,Long>{

}
