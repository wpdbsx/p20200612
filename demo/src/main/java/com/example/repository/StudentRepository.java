package com.example.repository;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import com.example.entity.Student;

//엔티티 , ID타입(Long)
public interface StudentRepository extends CrudRepository<Student,Long> {
	
	
	List<Student> findByKorGreaterThan(int kor );
	@Query(value= "SELECT * FROM STUDENT WHERE ST_KOR >=:kor",nativeQuery= true)
	List<Student> selectStudentQuery(@Param("kor") int kor );
	
	//SELECT* FROM STUDENT WHERE
	Student findById(String id);
}
