package com.example.springsecurity.service;

import com.example.springsecurity.domain.AppUser;
import com.example.springsecurity.domain.Roles;
import com.example.springsecurity.repo.RolesRepo;
import com.example.springsecurity.repo.UserRepo;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

@Service
@Transactional
@Slf4j
public class AppUserServiceImpl implements AppuserService, UserDetailsService {
    private final UserRepo userRepo;
    private final RolesRepo rolesRepo;
    private final PasswordEncoder passwordEncoder;

    @Autowired
    public AppUserServiceImpl(UserRepo userRepo, RolesRepo rolesRepo, PasswordEncoder passwordEncoder) {
        this.userRepo = userRepo;
        this.rolesRepo = rolesRepo;
        this.passwordEncoder = passwordEncoder;
    }
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        AppUser appUser= userRepo.findByUsername(username);
        if(appUser==null){
            log.error("User not found in the database");
            throw new UsernameNotFoundException("User not found in the database");
        }
        else{
            log.info("User found in the database :{}",username);
        }
        Collection<SimpleGrantedAuthority> authorities=new ArrayList<>();
        appUser.getRoles().forEach(roles -> {
            authorities.add(new SimpleGrantedAuthority(roles.getName()));
        });
        return new org.springframework.security.core.userdetails.User(appUser.getUsername(),appUser.getPassword(),authorities);
    }

    @Override
    public AppUser saveAppUser(AppUser appUser) {
        log.info("Saving new user to the database");
        appUser.setPassword(passwordEncoder.encode(appUser.getPassword()));
        return userRepo.save(appUser);
    }

    @Override
    public Roles saveRoles(Roles roles) {
        log.info("Saving new role {}to the database",roles.getName());
        return rolesRepo.save(roles);
    }

    @Override
    public void addRolesToUser(String username, String roleName) {
         log.info("Adding role {}to user {}",roleName,username);
          AppUser appUser=userRepo.findByUsername(username);
          Roles roles=rolesRepo.findByName(roleName);
          appUser.getRoles().add(roles);

    }

    @Override
    public AppUser getAppUser(String username) {
        log.info("Fetching user {}",username);
        return userRepo.findByUsername(username);
    }

    @Override
    public List<AppUser> getAppUsers() {
        log.info("Fetching all users");
        return userRepo.findAll();
    }


}
