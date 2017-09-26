package api.mapper;

import api.entity.Customer;

import java.util.List;

public interface TestMapper {
  Customer getUser(Customer customer);

  List<Customer> getUserList(Customer customer);
}
