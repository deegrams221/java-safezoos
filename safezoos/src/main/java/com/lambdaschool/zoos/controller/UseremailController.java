package com.lambdaschool.zoos.controller;

import com.lambdaschool.zoos.model.Useremail;
import com.lambdaschool.zoos.service.UseremailService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

@RestController
@RequestMapping("/useremails")
public class UseremailController
{
    private static final Logger logger = LoggerFactory.getLogger(UseremailController.class);

    @Autowired
    UseremailService useremailService;

    // GET
    // http://localhost:2019/useremails/useremails
    @PreAuthorize("hasAuthority('ROLE_ADMIN')")
    @GetMapping(value = "/useremails",
            produces = {"application/json"})
    public ResponseEntity<?> listAllUseremails(HttpServletRequest request)
    {
        logger.trace(request.getMethod()
                .toUpperCase() + " " + request.getRequestURI() + " accessed");

        List<Useremail> allUserEmails = useremailService.findAll();
        return new ResponseEntity<>(allUserEmails,
                HttpStatus.OK);
    }

    // GET
    // http://localhost:2019/useremails/useremail/{useremailId}
    @PreAuthorize("hasAuthority('ROLE_ADMIN')")
    @GetMapping(value = "/useremail/{useremailId}",
            produces = {"application/json"})
    public ResponseEntity<?> getUserEmailById(HttpServletRequest request,
                                              @PathVariable
                                                      Long useremailId)
    {
        logger.trace(request.getMethod()
                .toUpperCase() + " " + request.getRequestURI() + " accessed");

        Useremail ue = useremailService.findUseremailById(useremailId);
        return new ResponseEntity<>(ue,
                HttpStatus.OK);
    }

    // GET - cinnamon
    // http://localhost:2019/useremails/username/{userName}
    @GetMapping(value = "/username/{userName}",
            produces = {"application/json"})
    public ResponseEntity<?> findUseremailByUserName(HttpServletRequest request,
                                                     @PathVariable String userName)
    {
        logger.trace(request.getMethod()
                .toUpperCase() + " " + request.getRequestURI() + " accessed");

        List<Useremail> theUseremails = useremailService.findByUserName(userName,
                request.isUserInRole("ADMIN"));
        return new ResponseEntity<>(theUseremails,
                HttpStatus.OK);
    }

    // DELETE
    // http://localhost:2019/useremails/useremail/{useremailid}
    @DeleteMapping("/useremail/{useremailid}")
    public ResponseEntity<?> deleteUserEmailById(HttpServletRequest request,
                                                 @PathVariable
                                                         long useremailid)
    {
        logger.trace(request.getMethod()
                .toUpperCase() + " " + request.getRequestURI() + " accessed");

        useremailService.delete(useremailid,
                request.isUserInRole("ADMIN"));
        return new ResponseEntity<>(HttpStatus.OK);
    }

    // PUT - emails added by user
    // http://localhost:2019/useremails/useremail/{useremailid}/email/{emailaddress}
    @PutMapping("/useremail/{useremailid}/email/{emailaddress}")
    public ResponseEntity<?> updateUserEmail(HttpServletRequest request,
                                             @PathVariable
                                                     long useremailid,
                                             @PathVariable
                                                     String emailaddress)
    {
        useremailService.update(useremailid,
                emailaddress,
                request.isUserInRole("ADMIN"));
        return new ResponseEntity<>(HttpStatus.OK);
    }
}