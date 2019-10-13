package com.lambdaschool.zoos.service;

import com.lambdaschool.zoos.model.Useremail;

import java.util.List;

public interface UseremailService
{
    List<Useremail> findAll();

    Useremail findUseremailById(long id);

    List<Useremail> findByUserName(String username,
                                   boolean isAdmin);

    void delete(long id,
                boolean isAdmin);

    Useremail update(long useremailid,
                     String emailaddress,
                     boolean isAdmin);

    // emails are added by user
}