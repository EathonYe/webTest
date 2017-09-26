package api.mapper;

import bean.Customer;

import java.util.List;

public interface TestMapper {
  Customer getUser(Customer customer);

  List<Customer> getUserList(Customer customer);
}
