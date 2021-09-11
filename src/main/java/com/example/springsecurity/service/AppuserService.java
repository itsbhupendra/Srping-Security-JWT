package com.example.springsecurity.service;

import com.example.springsecurity.domain.AppUser;
import com.example.springsecurity.domain.Roles;

import java.util.List;

public interface AppuserService {

    AppUser saveAppUser(AppUser appUser);
    Roles saveRoles(Roles roles);
    void addRolesToUser(String username,String roleName);
    AppUser getAppUser(String username);
    List<AppUser> getAppUsers();
}
