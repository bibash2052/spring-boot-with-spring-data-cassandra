package com.cassandra.demo.demo.repository;

import com.cassandra.demo.demo.entity.Student;
import org.springframework.data.cassandra.repository.AllowFiltering;
import org.springframework.data.cassandra.repository.CassandraRepository;

import java.util.List;
import java.util.UUID;

public interface StudentRepository extends CassandraRepository<Student, UUID> {

    @AllowFiltering
    List<Student> findAllByStatus(boolean status);

    List<Student> findAllByAddressLike(String address);
}
