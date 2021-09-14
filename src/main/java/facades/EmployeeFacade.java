package facades;

import dtos.EmployeeDTO;
import entities.Employee;

import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.TypedQuery;
import utils.EMF_Creator;

/**
 *
 * Rename Class to a relevant name Add add relevant facade methods
 */
public class EmployeeFacade
{

    private static EmployeeFacade instance;
    private static EntityManagerFactory emf;
    
    //Private Constructor to ensure Singleton
    private EmployeeFacade() {}
    
    
    /**
     * 
     * @param _emf
     * @return an instance of this facade class.
     */
    public static EmployeeFacade getEmployeeFacadeInstance(EntityManagerFactory _emf) {
        if (instance == null) {
            emf = _emf;
            instance = new EmployeeFacade();
        }
        return instance;
    }

    private EntityManager getEntityManager() {
        return emf.createEntityManager();
    }
    
    public EmployeeDTO create(EmployeeDTO rm){
        Employee rme = new Employee(rm.getId(), rm.getFirstName(), rm.getLastName(), rm.getSalary());
        EntityManager em = emf.createEntityManager();
        try {
            em.getTransaction().begin();
                em.persist(rme);
            em.getTransaction().commit();
        } finally {
            em.close();
        }
        return new EmployeeDTO(rme);
    }
    public EmployeeDTO getById(long id){
        EntityManager em = emf.createEntityManager();
        return new EmployeeDTO(em.find(Employee.class, id));
    }
    
    //TODO Remove/Change this before use
    public long getEmployeeCount(){
        EntityManager em = emf.createEntityManager();
        try{
            long employeeCount = (long) em.createQuery("SELECT COUNT(e) FROM Employee e").getSingleResult();
            return employeeCount;
        }finally{  
            em.close();
        }
    }
    
    public List<EmployeeDTO> getAll(){
        EntityManager em = emf.createEntityManager();
        TypedQuery<Employee> query = em.createQuery("SELECT e FROM Employee e", Employee.class);
        List<Employee> rms = query.getResultList();
        return EmployeeDTO.getDtos(rms);
    }
    
    public static void main(String[] args) {
        emf = EMF_Creator.createEntityManagerFactory();
        EmployeeFacade fe = getEmployeeFacadeInstance(emf);
        fe.getAll().forEach(dto->System.out.println(dto));
    }

}
