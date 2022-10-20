package rikkei.academy.repository.customer;

import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;
import rikkei.academy.model.Customer;

import javax.persistence.*;
import java.util.List;

@Component
@Transactional
public class CustomerRepository implements ICustomerRepository {

    @PersistenceContext
    private EntityManager em;


    @Override
    public List<Customer> findAll() {
        TypedQuery<Customer> query = em.createQuery("select c from Customer c", Customer.class);
        return query.getResultList();
    }

    @Override
    public Customer findById(Long id) {
        TypedQuery<Customer> query = em.createQuery("select c from Customer c where c.id = :id", Customer.class);
        query.setParameter("id", id);
        try {
            return query.getSingleResult();
        } catch (NoResultException e) {
            return null;
        }
    }

    @Override
    public void save(Customer customer) {
        if (customer.getId() == null) {
            em.persist(customer);
        } else {
            em.merge(customer);
        }
    }

    @Override
    public void remove(Long id) {
        Customer customer = findById(id);
        if (customer != null) {
            em.remove(customer);
        }
    }

    @Override
    public boolean insertWithProcedure(Customer customer) {
        String sql = "CALL Insert_Customer(:firstName, :lastName)";
        Query query = em.createNativeQuery(sql);
        query.setParameter("firstName", customer.getFirstName());
        query.setParameter("lastName", customer.getLastName());
        return query.executeUpdate() == 0;
    }

}
