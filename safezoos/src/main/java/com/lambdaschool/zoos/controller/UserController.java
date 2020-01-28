package com.lambdaschool.zoos.controller;

import com.lambdaschool.zoos.model.User;
import com.lambdaschool.zoos.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;
import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.List;

@RestController
@RequestMapping("/users")
public class UserController
{
    @Autowired
    private UserService userService;

    // GET
    // http://localhost:2019/users/users
    @PreAuthorize("hasAuthority('ROLE_ADMIN')")
    @GetMapping(value = "/users",
            produces = {"application/json"})
    public ResponseEntity<?> listAllUsers()
    {
        List<User> myUsers = userService.findAll();
        return new ResponseEntity<>(myUsers,
                HttpStatus.OK);
    }

    // GET
    // http://localhost:2019/users/user/{userId}
    @PreAuthorize("hasAuthority('ROLE_ADMIN')")
    @GetMapping(value = "/user/{userId}",
            produces = {"application/json"})
    public ResponseEntity<?> getUserById(
            @PathVariable
                    Long userId)
    {
        User u = userService.findUserById(userId);
        return new ResponseEntity<>(u,
                HttpStatus.OK);
    }

    // GET - cinnamon
    // http://localhost:2019/users/user/name/{userName}
    @PreAuthorize("hasAuthority('ROLE_ADMIN')")
    @GetMapping(value = "/user/name/{userName}",
            produces = {"application/json"})
    public ResponseEntity<?> getUserByName(
            @PathVariable
                    String userName)
    {
        User u = userService.findByName(userName);
        return new ResponseEntity<>(u,
                HttpStatus.OK);
    }

    // GET - da?sort=username
    // http://localhost:2019/users/user/name/like/{userName}
    @PreAuthorize("hasAuthority('ROLE_ADMIN')")
    @GetMapping(value = "/user/name/like/{userName}",
            produces = {"application/json"})
    public ResponseEntity<?> getUserLikeName(
            @PathVariable
                    String userName)
    {
        List<User> u = userService.findByNameContaining(userName);
        return new ResponseEntity<>(u,
                HttpStatus.OK);
    }

    // GET
    // http://localhost:2019/users/getusername
    @GetMapping(value = "/getusername",
            produces = {"application/json"})
    @ResponseBody
    public ResponseEntity<?> getCurrentUserName(Authentication authentication)
    {
        return new ResponseEntity<>(authentication.getPrincipal(),
                HttpStatus.OK);
    }

    // GET
    // http://localhost:2019/users/getuserinfo
    @GetMapping(value = "/getuserinfo",
            produces = {"application/json"})
    public ResponseEntity<?> getCurrentUserInfo(Authentication authentication)
    {
        User u = userService.findByName(authentication.getName());
        return new ResponseEntity<>(u,
                HttpStatus.OK);
    }

    // POST
    // http://localhost:2019/users/user
    @PreAuthorize("hasAuthority('ROLE_ADMIN')")
    @PostMapping(value = "/user",
            consumes = {"application/json"},
            produces = {"application/json"})
    public ResponseEntity<?> addNewUser(@Valid
                                        @RequestBody
                                                User newuser) throws URISyntaxException
    {
        newuser = userService.save(newuser);

        // set the location header for the newly created resource
        HttpHeaders responseHeaders = new HttpHeaders();
        URI newUserURI = ServletUriComponentsBuilder.fromCurrentRequest()
                .path("/{userid}")
                .buildAndExpand(newuser.getUserid())
                .toUri();
        responseHeaders.setLocation(newUserURI);

        return new ResponseEntity<>(null,
                responseHeaders,
                HttpStatus.CREATED);
    }

    // PUT
    // http://localhost:2019/users/user/{id}
    @PutMapping(value = "/user/{id}")
    public ResponseEntity<?> updateUser(HttpServletRequest request,
                                        @RequestBody
                                                User updateUser,
                                        @PathVariable
                                                long id)
    {
        userService.update(updateUser,
                id,
                request.isUserInRole("ADMIN"));
        return new ResponseEntity<>(HttpStatus.OK);
    }

    // DELETE
    // http://localhost:2019/users/user/{id}
    @PreAuthorize("hasAuthority('ROLE_ADMIN')")
    @DeleteMapping("/user/{id}")
    public ResponseEntity<?> deleteUserById(
            @PathVariable
                    long id)
    {
        userService.delete(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    // DELETE
    // http://localhost:2019/users/user/{userid}/role/{roleid}
    @PreAuthorize("hasAuthority('ROLE_ADMIN')")
    @DeleteMapping("/user/{userid}/role/{roleid}")
    public ResponseEntity<?> deleteUserRoleByIds(
            @PathVariable
                    long userid,
            @PathVariable
                    long roleid)
    {
        userService.deleteUserRole(userid,
                roleid);

        return new ResponseEntity<>(HttpStatus.OK);
    }

    // POST
    // http://localhost:2019/users/user/{userid}/role/{roleid}
    @PreAuthorize("hasAuthority('ROLE_ADMIN')")
    @PostMapping("/user/{userid}/role/{roleid}")
    public ResponseEntity<?> postUserRoleByIds(
            @PathVariable
                    long userid,
            @PathVariable
                    long roleid)
    {
        userService.addUserRole(userid,
                roleid);

        return new ResponseEntity<>(HttpStatus.CREATED);
    }
}