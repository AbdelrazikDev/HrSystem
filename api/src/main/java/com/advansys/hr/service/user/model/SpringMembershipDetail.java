package com.advansys.hr.service.user.model;


import java.util.Date;

import org.springframework.security.core.GrantedAuthority;

@SuppressWarnings("serial")
public class SpringMembershipDetail implements GrantedAuthority {

    private Long id;
    private Date expire;

    public SpringMembershipDetail(Long id) {
        this.id = id;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }


	@Override
	public String getAuthority() {
		// TODO Auto-generated method stub
		return null;
	}

}
