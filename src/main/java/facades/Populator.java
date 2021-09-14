/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package facades;

import dtos.EmployeeDTO;
import entities.Employee;
import javax.persistence.EntityManagerFactory;
import utils.EMF_Creator;

import java.math.BigDecimal;

/**
 *
 * @author tha
 */
public class Populator {
    public static void populate(){
        EntityManagerFactory emf = EMF_Creator.createEntityManagerFactory();
        EmployeeFacade fe = EmployeeFacade.getEmployeeFacadeInstance(emf);
        fe.create(new EmployeeDTO(new Employee("xa12tt", "Kurt", "Wonnegut", new BigDecimal(335567))));
        fe.create(new EmployeeDTO(new Employee("hyu654", "Hanne", "Olsen", new BigDecimal(435867))));
        fe.create(new EmployeeDTO(new Employee("uio876", "Jan", "Olsen", new BigDecimal(411567))));
    }
    
    public static void main(String[] args) {
        populate();
    }
}
