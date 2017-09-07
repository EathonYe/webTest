package impl;

import java.sql.ResultSet;
import java.util.ArrayList;

public interface Mapper<T> {
  ArrayList<T> mapRow(ResultSet res);
}
