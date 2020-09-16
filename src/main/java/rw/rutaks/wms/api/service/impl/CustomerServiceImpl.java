package rw.rutaks.wms.api.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import rw.rutaks.wms.api.model.Customer;
import rw.rutaks.wms.api.repo.CustomerRepository;
import rw.rutaks.wms.api.service.CustomerService;

@Service
public class CustomerServiceImpl implements CustomerService {
  @Autowired private CustomerRepository customerRepo;

  @Override
  public Customer createCustomer(Customer customer) {
    return customerRepo.save(customer);
  }
}
