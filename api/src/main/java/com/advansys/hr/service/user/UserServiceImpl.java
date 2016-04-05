package com.advansys.hr.service.user;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.advansys.hr.constant.Constants;
import com.advansys.hr.persistence.dao.user.UserDAO;
import com.advansys.hr.persistence.exceptions.ChangePasswordException;
import com.advansys.hr.persistence.exceptions.CreateUserException;
import com.advansys.hr.persistence.exceptions.DeleteUserException;
import com.advansys.hr.persistence.exceptions.UpdateUserException;

@Service(Constants.SERVICE_USER)
public class UserServiceImpl implements UserService {

    @Autowired
    private UserDAO userDAO;

    // === UserDetailsService implementation ===

    @Override
    public UserDetails loadUserByUsername(String email)
            throws UsernameNotFoundException, DataAccessException {
        return userDAO.loadUser(email);
    }

    // === UserDetailsManager implementation ===

    @Override
    public void changePassword(String email, String password) throws ChangePasswordException {
        userDAO.changePassword(email, password);
    }

    @Override
    public boolean userExists(String email) {
        return userDAO.userExists(email);
    }

    @Override
    public void createUser(UserDetails user) throws CreateUserException {
        userDAO.createUser(user);
    }

    @Override
    public void updateUser(UserDetails user) throws UpdateUserException {
        userDAO.updateUser(user);
    }

    @Override
    public void deleteUser(String email) throws DeleteUserException {
        userDAO.deleteUser(email);
    }

}
