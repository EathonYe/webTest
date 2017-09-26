package api.mapper;

import api.entity.Admin;

import java.util.List;

public interface LoginMapper {
  List<Admin> selectByName(Admin admin);

  int updateAdmin(Admin admin);
}
