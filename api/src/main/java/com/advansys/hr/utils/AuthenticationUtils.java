package com.advansys.hr.utils;

import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;

import com.advansys.hr.service.user.model.SpringUserDetail;
import com.advansys.hr.service.user.model.UserDetail;


public final class AuthenticationUtils {

    public static String getUsername() {
        Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();

        String username = principal.toString();

        if (principal instanceof UserDetails) {
            username = ((UserDetails) principal).getUsername();
        }

        return username;
    }

    public static UserDetail toUserDetail(SpringUserDetail springUserDetail) {
        return (springUserDetail == null) ? null : new UserDetail(
                springUserDetail.getUsername(),
                springUserDetail.getFirstName(),
                springUserDetail.getLastName());
    }

}
