package com.blackmania.facialreconition.rest.controllers;


import com.blackmania.facialreconition.data.repository.UserRepository;
import com.blackmania.facialreconition.data.tabellen.User;
import com.blackmania.facialreconition.rest.dto.RegisterDTO;
import com.blackmania.facialreconition.rest.exceptions.FileStorageException;
import com.blackmania.facialreconition.rest.fileHandlers.FileStorageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

@RestController
@RequestMapping("/auth")
public class LoginController {

    @Autowired
    private FileStorageService fileStorageService;

    @Autowired
    private UserRepository userRepository;

    @PostMapping(value = "/register")
    public ResponseEntity<?> register(@RequestParam("file") MultipartFile file, @RequestParam("name") String name) throws FileStorageException {
        String filename = fileStorageService.storeFile(file);

        User user = new User(name, filename);

        userRepository.save(user);

        return new ResponseEntity<>(HttpStatus.OK);
    }

}
