package com.advansys.hr.api.controller.user;

import org.springframework.http.MediaType;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.web.bind.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.advansys.hr.service.user.model.SpringUserDetail;
import com.advansys.hr.service.user.model.UserDetail;
import com.advansys.hr.utils.AuthenticationUtils;

@RestController
@RequestMapping(value = "/user")
public class UserController {

    @PreAuthorize("isAuthenticated()")
    @RequestMapping(value = "/retrieve", method = RequestMethod.GET, produces = { MediaType.APPLICATION_JSON_VALUE })
    public UserDetail retrieveUser(@AuthenticationPrincipal SpringUserDetail user) {
        return AuthenticationUtils.toUserDetail(user);
    }
}
