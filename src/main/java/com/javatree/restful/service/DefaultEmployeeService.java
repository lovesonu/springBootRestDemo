package com.javatree.restful.service;

import com.javatree.restful.model.Employee;
import com.javatree.restful.repository.EmployeeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.Serializable;
import java.util.List;

@Service
public class DefaultEmployeeService implements EmployeeService {

    @Autowired
    private EmployeeRepository employeeRepository;

    @Override
    public Employee save(Employee entity) {
        return employeeRepository.save(entity);
    }

    @Override
    public Employee getById(Serializable id) {
//        return employeeRepository.findOne((id));
        return null;

    }

    @Override
    public List<Employee> getAll() {
        return employeeRepository.findAll();
    }

    @Override
    public void delete(Serializable id) {
        employeeRepository.delete((Employee) id);
    }

    /*@Override
    public void delete(Serializable id) {
        employeeRepository.delete((Long) id);
    }*/
}
