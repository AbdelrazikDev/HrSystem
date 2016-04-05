package com.advansys.hr.persistence.dao.user;


import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import com.advansys.hr.persistence.exceptions.ChangePasswordException;
import com.advansys.hr.persistence.exceptions.CreateUserException;
import com.advansys.hr.persistence.exceptions.DeleteUserException;
import com.advansys.hr.persistence.exceptions.UpdateUserException;

public interface UserDAO {
    public abstract UserDetails loadUser(String email)
            throws UsernameNotFoundException;

    public abstract void changePassword(String username, String password) throws ChangePasswordException;

    public abstract boolean userExists(String username);

    public abstract void createUser(UserDetails user) throws CreateUserException;

    public abstract void updateUser(UserDetails user) throws UpdateUserException;

    public abstract void deleteUser(String username) throws DeleteUserException;
}
