package com.lambdaschool.zoos.controller;

import com.lambdaschool.zoos.service.ZooService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "/zoos")
public class ZooController
{
    @Autowired
    private ZooService zooService;

    // GET
    // localhost:2019/zoos/zoos
    @GetMapping(value = "/zoos", produces = {"application/json"})
    public ResponseEntity<?> listAllZoos()
    {
        return new ResponseEntity<>(zooService.findAll(), HttpStatus.OK);
    }

    // GET
    // localhost:2019/zoos/zoos/{id}
    @GetMapping(value = "/zoos/{id}", produces = {"application/json"})
    public ResponseEntity<?> findById(@PathVariable long id)
    {
        return new ResponseEntity<>(zooService.findZooById(id), HttpStatus.OK);
    }

    // GET
    // localhost:2019/zoos/zoos/{name}
    @GetMapping(value = "/{name}", produces = {"application/json"})
    public ResponseEntity<?> findByName(@PathVariable String name)
    {
        return new ResponseEntity<>(zooService.findZooByName(name), HttpStatus.OK);
    }
}