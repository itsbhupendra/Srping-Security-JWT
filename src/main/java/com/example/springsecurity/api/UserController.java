package com.example.springsecurity.api;

import com.example.springsecurity.domain.AppUser;
import com.example.springsecurity.domain.Roles;
import com.example.springsecurity.service.AppuserService;
import lombok.Data;
import lombok.RequiredArgsConstructor;
import org.springframework.http.RequestEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;

@RestController
@RequestMapping("/api")
@RequiredArgsConstructor
public class UserController {
    private final AppuserService appuserService;

    @GetMapping("/users")
    public ResponseEntity<List<AppUser>> getUsers(){
        return ResponseEntity.ok().body(appuserService.getAppUsers());
    }

    @PostMapping("/users/save")
        public ResponseEntity<AppUser> saveUser(@RequestBody AppUser appUser){
        URI uri =URI.create(ServletUriComponentsBuilder.fromCurrentContextPath().path("/api/users/save").toUriString());
          return ResponseEntity.created(uri).body(appuserService.saveAppUser(appUser));
        }

    @PostMapping("/role/save")
    public ResponseEntity<Roles> saveRole(@RequestBody Roles roles){
        URI uri =URI.create(ServletUriComponentsBuilder.fromCurrentContextPath().path("/api/users/save").toUriString());
        return ResponseEntity.created(uri).body(appuserService.saveRoles(roles));
    }
    @PostMapping("/role/addtouser")
    public ResponseEntity<?> addRoleToUser(@RequestBody RoleToUserForm roleToUserForm){
//        URI uri =URI.create(ServletUriComponentsBuilder.fromCurrentContextPath().path("/api/users/save").toUriString());
        appuserService.addRolesToUser(roleToUserForm.getUsername(),roleToUserForm.getRoleName());
//        return ResponseEntity.ok().body(appuserService.addRolesToUser(roleToUserForm.getUsername(),roleToUserForm.getRoleName()));
        return ResponseEntity.ok().build();
    }



}
@Data
class RoleToUserForm{
    private String username;
    private String roleName;
}
