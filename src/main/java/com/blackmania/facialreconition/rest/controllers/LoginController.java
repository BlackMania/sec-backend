package com.blackmania.facialreconition.rest.controllers;


import com.blackmania.facialreconition.data.repository.UserRepository;
import com.blackmania.facialreconition.data.tabellen.User;
import com.blackmania.facialreconition.rest.exceptions.FileStorageException;
import com.blackmania.facialreconition.rest.fileHandlers.FilePropperties;
import com.blackmania.facialreconition.rest.fileHandlers.FileStorageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;

@RestController
@RequestMapping("/auth")
public class LoginController {

    @Autowired
    private FileStorageService fileStorageService;

    @Autowired
    private FilePropperties propperties;

    @Autowired
    private UserRepository userRepository;

    private static final String PATH = "/tmp";
    private Path path;

    @PostMapping(value = "/register")
    public ResponseEntity<?> register(@RequestParam("file") MultipartFile file, @RequestParam("name") String name) throws FileStorageException {
        String filename = fileStorageService.storeFile(file);

        User user = new User(name, filename);

        userRepository.save(user);

        return new ResponseEntity<>(HttpStatus.OK);
    }

    @PostMapping(value = "/test")
    public ResponseEntity<?> test(@RequestParam("file") MultipartFile file) throws FileStorageException, IOException {
        this.path = Paths.get(PATH).toAbsolutePath().normalize();

        try {
            Files.createDirectories(this.path);
        } catch (Exception ex){
            throw new FileStorageException("Could not create the directory where the uploaded files will be stored.", ex);
        }

        String filename = StringUtils.cleanPath(file.getOriginalFilename());

        Path target = this.path.resolve(filename);
        Files.copy(file.getInputStream(), target, StandardCopyOption.REPLACE_EXISTING);

        Iterable<User> users = userRepository.findAll();

        return new ResponseEntity<>(HttpStatus.OK);

    }

    @GetMapping(value = "pythonTest")
    public String TEST() throws IOException, InterruptedException {
        String command = "python src\\main\\resources\\test.py";
        Process p = Runtime.getRuntime().exec(command);
        p.waitFor();

        try (BufferedReader br = new BufferedReader(new InputStreamReader(p.getInputStream()))) {
            String line;
            line = br.readLine();
            return line;
        }
    }
}
