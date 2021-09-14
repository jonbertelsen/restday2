/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dtos;

import entities.Employee;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

public class EmployeeDTO
{
    private String id;
    BigDecimal salary;
    String firstName;
    String lastName;

    public static List<EmployeeDTO> getDtos(List<Employee> rms){
        List<EmployeeDTO> rmdtos = new ArrayList();
        rms.forEach(rm->rmdtos.add(new EmployeeDTO(rm)));
        return rmdtos;
    }

    public EmployeeDTO(Employee rm) {
        if(rm.getId() != null)
            this.id = rm.getId();
            this.firstName = rm.getFirstName();
            this.lastName = rm.getLastName();
            this.salary = rm.getSalary();
    }

    public String getId()
    {
        return id;
    }

    public void setId(String id)
    {
        this.id = id;
    }

    public BigDecimal getSalary()
    {
        return salary;
    }

    public void setSalary(BigDecimal salary)
    {
        this.salary = salary;
    }

    public String getFirstName()
    {
        return firstName;
    }

    public void setFirstName(String firstName)
    {
        this.firstName = firstName;
    }

    public String getLastName()
    {
        return lastName;
    }

    public void setLastName(String lastName)
    {
        this.lastName = lastName;
    }
}
