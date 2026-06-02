package com.pucetec.students.servise

import com.pucetec.students.dto.StudentRequest
import com.pucetec.students.dto.StudentResponse
import com.pucetec.students.entities.Student
import com.pucetec.students.repositories.StudentRepository
import org.slf4j.LoggerFactory
import org.springframework.stereotype.Service

@Service
class StudentService(
    private val repository: StudentRepository
) {

    private val logger = LoggerFactory.getLogger(javaClass)

    fun createStudent(request: StudentRequest): StudentResponse {

        logger.info("Creating student ${request.name}")

        val studentToSave = Student(
            name = request.name,
            email = request.email
        )

        val savedStudent = repository.save(studentToSave)

        logger.info("Saved student with id ${savedStudent.id}")

        return StudentResponse(
            id = savedStudent.id ?: 0L,
            name = savedStudent.name,
            email = savedStudent.email
        )
    }

    fun getAllStudents(): List<StudentResponse> {

        logger.info("Get all student list")

        val students: List<Student> = repository.findAll()

        return students.map { miEstudiante: Student ->

            StudentResponse(
                id = miEstudiante.id ?: 0L,
                name = miEstudiante.name,
                email = miEstudiante.email
            )
        }
    }
}