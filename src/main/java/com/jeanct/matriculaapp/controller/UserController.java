package com.jeanct.matriculaapp.controller;

import com.jeanct.matriculaapp.dto.StudentDTO;
import com.jeanct.matriculaapp.dto.UserDTO;
import com.jeanct.matriculaapp.model.Student;
import com.jeanct.matriculaapp.model.User;
import com.jeanct.matriculaapp.service.IUserService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping(value = "/users")
public class UserController {

    @Autowired
    private IUserService userService;

    @Autowired
    @Qualifier("userMapper")
    private ModelMapper mapper;

    /*@Autowired
    private PasswordEncoder passwordEncoder;*/

    @GetMapping
    public ResponseEntity<List<UserDTO>> readAll() throws Exception {
        ModelMapper modelMapper = new ModelMapper();

        List<UserDTO> list = userService.readAll()
                .stream()
                .map(user -> mapper.map(user, UserDTO.class))
                .collect(Collectors.toList());

        return new ResponseEntity<>(list, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<UserDTO> readById(@PathVariable("id") Integer id) throws Exception {
        User obj = userService.readById(id);
        UserDTO objTO =mapper.map(obj, UserDTO.class);
        /*if( obj.getIdUser() == null){
            throw new ModelNotFoundException("ID NOT FOUND " + id);
        }*/
        return new ResponseEntity<>(objTO, HttpStatus.OK);

    }

    @PostMapping
    public ResponseEntity<UserDTO> createUser(@Valid @RequestBody UserDTO userDTO) throws Exception {

        //setear el passwrod encryptado
        /*User userModel = mapper.map(userDTO, User.class);
        String password = passwordEncoder.encode(userModel.getPassword());
        userModel.setPassword(password);
        User obj = userService.save(userModel);

        return new ResponseEntity<>(mapper.map(obj, UserDTO.class), HttpStatus.CREATED);*/

        User userModel = mapper.map(userDTO, User.class);
        UserDTO userSaved = mapper.map(userService.save(userModel), UserDTO.class);
        return new ResponseEntity<>(userSaved, HttpStatus.CREATED);
    }

    @PutMapping
    public ResponseEntity<UserDTO> updateUser(@Valid @RequestBody UserDTO userDTO) throws Exception {
        User obj = userService.update(mapper.map(userDTO, User.class));
        return new ResponseEntity<>(mapper.map(obj, UserDTO.class), HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteUser(@PathVariable("id")Integer id) throws Exception {
        User obj =userService.readById(id);
        if(obj != null){
            userService.delete(id);
        }
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
