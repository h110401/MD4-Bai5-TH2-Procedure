package rikkei.academy.repository.customer;

import rikkei.academy.model.Customer;
import rikkei.academy.repository.IGeneralRepository;

public interface ICustomerRepository extends IGeneralRepository<Customer> {
    boolean insertWithProcedure(Customer customer);
}
