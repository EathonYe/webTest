package impl;

import bean.Customer;

import java.sql.ResultSet;

public interface Mapper {
  Customer mapRow(ResultSet res);
}
