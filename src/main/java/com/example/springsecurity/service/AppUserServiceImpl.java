package com.example.springsecurity.service;

import com.example.springsecurity.domain.AppUser;
import com.example.springsecurity.domain.Roles;
import com.example.springsecurity.repo.RolesRepo;
import com.example.springsecurity.repo.UserRepo;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

@Service
@Transactional
@Slf4j
public class AppUserServiceImpl implements AppuserService{
    private final UserRepo userRepo;
    private final RolesRepo rolesRepo;

    @Autowired
    public AppUserServiceImpl(UserRepo userRepo, RolesRepo rolesRepo) {
        this.userRepo = userRepo;
        this.rolesRepo = rolesRepo;
    }

    @Override
    public AppUser saveAppUser(AppUser appUser) {
        return userRepo.save(appUser);
    }

    @Override
    public Roles saveRoles(Roles roles) {
        return rolesRepo.save(roles);
    }

    @Override
    public void addRolesToUser(String username, String roleName) {
          AppUser appUser=userRepo.findByUsername(username);
          Roles roles=rolesRepo.findByName(roleName);
          appUser.getRoles().add(roles);

    }

    @Override
    public AppUser getAppUser(String username) {
        return userRepo.findByUsername(username);
    }

    @Override
    public List<AppUser> getAppUsers() {
        return userRepo.findAll();
    }
}
