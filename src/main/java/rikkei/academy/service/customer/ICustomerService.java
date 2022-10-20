package rikkei.academy.service.customer;

import rikkei.academy.model.Customer;
import rikkei.academy.service.IGenericService;

public interface ICustomerService extends IGenericService<Customer> {
    boolean insertWithProcedure(Customer customer);
}
