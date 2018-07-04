package com.javatree.restful.controller;

import com.javatree.restful.model.Employee;
import com.javatree.restful.service.EmployeeService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.math.BigInteger;
import java.util.Arrays;
import java.util.List;


@RestController
@RequestMapping("/employee")
public class EmployeeController {
//    private final Logger logger = Logger.getLogger(this.getClass());
private final static Logger logger = LoggerFactory.getLogger(EmployeeController.class);

//private final static Logger logger = (Logger) LoggerFactory.getLogger(EmployeeController.class);

    @Autowired
    EmployeeService empService;

    @RequestMapping(method = RequestMethod.POST)
    public ResponseEntity<Employee> addEmployee(@RequestBody Employee employee) {
//        employee = employee.save(employee);
        empService.save(employee);
        System.out.println("Add employee method :---->"+employee+ "employee.getId() >>>>>"+ employee.getId());
//        logger.debug("Added:: " + employee);
        logger.info("Added:: " + employee);
        return new ResponseEntity<Employee>(employee, HttpStatus.CREATED);
    }


    @RequestMapping(method = RequestMethod.PUT)
    public ResponseEntity<Void> updateEmployee(@RequestBody Employee employee) {
        Employee existingEmp = empService.getById(employee.getId());
        if (existingEmp == null) {
//            logger.debug("Employee with id " + employee.getId() + " does not exists");
            logger.info("Employee with id " + employee.getId() + " does not exists");

            return new ResponseEntity<Void>(HttpStatus.NOT_FOUND);
        } else {
            empService.save(employee);
            return new ResponseEntity<Void>(HttpStatus.OK);
        }
    }


    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    public ResponseEntity<Employee> getEmployee(@PathVariable("id") BigInteger id) {
        Employee employee = empService.getById(id);
        if (employee == null) {
//            logger.debug("Employee with id " + id + " does not exists");
            logger.info("Employee with id " + id + " does not exists");
            return new ResponseEntity<Employee>(HttpStatus.NOT_FOUND);
        }
//        logger.debug("Found Employee:: " + employee);
        logger.info("Found Employee:: " + employee);
        return new ResponseEntity<Employee>(employee, HttpStatus.OK);
    }


    @RequestMapping(method = RequestMethod.GET)
    public ResponseEntity<List<Employee>> getAllEmployees() {
        List<Employee> employees = empService.getAll();
        if (employees.isEmpty()) {
//            logger.debug("Employees does not exists");
            logger.info("Employees does not exists");
            return new ResponseEntity<List<Employee>>(HttpStatus.NO_CONTENT);
        }
//        logger.debug("Found " + employees.size() + " Employees");
//        logger.debug(Arrays.toString(employees.toArray()));
        logger.info("Found " + employees.size() + " Employees");
        logger.info(Arrays.toString(employees.toArray()));
        return new ResponseEntity<List<Employee>>(employees, HttpStatus.OK);
    }


    @RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
    public ResponseEntity<Void> deleteEmployee(@PathVariable("id") BigInteger id) {
        Employee employee = empService.getById(id);
        if (employee == null) {
//            logger.debug("Employee with id " + id + " does not exists");
            logger.info("Employee with id " + id + " does not exists");
            return new ResponseEntity<Void>(HttpStatus.NOT_FOUND);
        } else {
            empService.delete(id);
//            logger.debug("Employee with id " + id + " deleted");
            logger.info("Employee with id " + id + " deleted");
            return new ResponseEntity<Void>(HttpStatus.GONE);
        }
    }
}
