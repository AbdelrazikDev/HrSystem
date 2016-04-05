package com.advansys.hr.persistence.dao.base.query;

import java.util.List;


public interface QueryString {
    public String getStatement();

    public List<QueryParameter> getParameters();
}
