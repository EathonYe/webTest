package api.mapper;

import api.entity.Customer;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface CustomerMapper {

    int getTotal(Customer customer);

    List<Customer> selectCustomer(@Param("customer") Customer customer, @Param("offset") int offset);

    int updateCustomer(Customer customer);

    int insertCustomer(Customer customer);

    int deleteCustomer(int id);
}

