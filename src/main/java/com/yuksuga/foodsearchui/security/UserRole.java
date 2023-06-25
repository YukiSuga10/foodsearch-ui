package com.yuksuga.foodsearchui.security;

import java.util.List;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.AuthorityUtils;

public enum UserRole {

    //管理者権限
    ADMIN(){
        @Override
        public List<GrantedAuthority> getGrantedAuthority() {
            return AuthorityUtils.createAuthorityList("ROLE_ADMIN", "ROLE_USER");
        }
    },

    //ユーザ権限
    USER() {
        @Override
        public List<GrantedAuthority> getGrantedAuthority() {
            return AuthorityUtils.createAuthorityList("ROLE_USER");
        }
    };

    public abstract List<GrantedAuthority> getGrantedAuthority();
}
