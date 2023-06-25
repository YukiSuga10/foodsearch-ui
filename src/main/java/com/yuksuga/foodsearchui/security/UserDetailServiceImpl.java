package com.yuksuga.foodsearchui.security;

import com.yuksuga.foodsearchui.dao.UserDao;
import com.yuksuga.foodsearchui.domain.User;
import java.util.List;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class UserDetailServiceImpl implements UserDetailsService {

    private final UserDao userDao;

    public UserDetailServiceImpl(UserDao userDao) {
        this.userDao = userDao;
    }

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        List<User> users = userDao.find(email).getUsers();
        if (users == null || users.isEmpty()) {
            throw new UsernameNotFoundException("User not Found");
        }

        User user = users.get(0);

        return new LoginUser(
                user.getUserId(),
                user.getEmail(),
                user.getPassword(),
                user.getAdminFlag(),
                this.determineRoles(user.getAdminFlag()),
                user.getFirstName(),
                user.getLastName());
    }

    private List<GrantedAuthority> determineRoles(boolean isAdmin) {
        return isAdmin ? UserRole.ADMIN.getGrantedAuthority() : UserRole.USER.getGrantedAuthority();
    }
}
