package com.yuksuga.foodsearchui.security;

import org.springframework.security.core.userdetails.User;
import java.util.Collection;
import org.springframework.security.core.GrantedAuthority;

public class LoginUser extends User {

    private Long userId;

    private boolean adminFlag;

    private String firstName;

    private String lastName;

    public LoginUser(Long userId,
                     String username,
                     String password,
                     boolean adminFlag,
                     Collection<? extends GrantedAuthority> authorities,
                     String firstName,
                     String lastName) {
        super(username, password, authorities);
        this.userId = userId;
        this.adminFlag = adminFlag;
        this.firstName = firstName;
        this.lastName = lastName;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public boolean isAdminFlag() {
        return adminFlag;
    }

    public void setAdminFlag(boolean adminFlag) {
        this.adminFlag = adminFlag;
    }


    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }
}
