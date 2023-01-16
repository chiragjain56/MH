package com.example.masaimailapp.service;

import com.example.masaimailapp.dto.EmailDTO;
import com.example.masaimailapp.dto.LoginDTO;
import com.example.masaimailapp.dto.RegisterDTO;
import com.example.masaimailapp.entity.Email;
import com.example.masaimailapp.entity.User;
import com.example.masaimailapp.entity.UserPassword;
import com.example.masaimailapp.exception.IncorrectInputException;
import com.example.masaimailapp.exception.IncorrectMobileNumberFormat;
import com.example.masaimailapp.modelmapper.ModelMapperClass;
import com.example.masaimailapp.repository.UserPasswordRepository;
import com.example.masaimailapp.repository.UserRepository;
import com.fasterxml.jackson.databind.ser.impl.SimpleBeanPropertyFilter;
import com.fasterxml.jackson.databind.ser.impl.SimpleFilterProvider;
import org.modelmapper.TypeToken;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.converter.json.MappingJacksonValue;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Service
public class UserService {

    @Autowired
    UserRepository userRepository;

    @Autowired
    UserPasswordRepository userPasswordRepository;

    @Autowired
    ModelMapperClass modelMapperClass;

    public boolean registerUser(RegisterDTO registerDTO){
        if(registerDTO.getFirstName().matches(".*\\d+.*") || registerDTO.getFirstName().matches("[A-Za-z0-9 ]*"))
            throw new IncorrectInputException("First name should not have numbers or special characters");
        if(registerDTO.getLastName().matches(".*\\d+.*") || registerDTO.getLastName().matches("[A-Za-z0-9 ]*"))
            throw new IncorrectInputException("Last name should not have numbers or special characters");
        if(registerDTO.getMobileNumber()<10 || registerDTO.getMobileNumber()>10)
            throw new IncorrectMobileNumberFormat("Mobile number should be 10 digits");
        if(registerDTO.getDateOfBirth().compareTo(new Date()) > 0)
            throw new IncorrectInputException("Birth date should not be in future");

        SimpleBeanPropertyFilter filter= SimpleBeanPropertyFilter
                .filterOutAllExcept("email","firstName","lastName","mobileNumber","dateOfBirth");
        SimpleFilterProvider filterProvider = new SimpleFilterProvider()
                .addFilter("UserFilter",filter);
        MappingJacksonValue mappingJacksonValue = new MappingJacksonValue(registerDTO);
        mappingJacksonValue.setFilters(filterProvider);
        User user = modelMapperClass.modelMapper()
                .map(mappingJacksonValue.getValue(), new TypeToken<User>() {}.getType());
        User newUser = userRepository.save(user);
        return newUser != null;
    }

    public boolean loginUser(LoginDTO loginDTO){
        List<UserPassword> users = userPasswordRepository.findAll();
        for(UserPassword user : users){
            if((user.getEmail().equals(loginDTO.getEmail())) && (user.getPassword().equals(loginDTO.getPassword()))) return true;
        }
        return false;
    }

    public List<Email> getAllMails(EmailDTO emailDTO){
        List<User> users = userRepository.findAll();
        for(User user : users){
            if(user.getEmail().equals(emailDTO.getEmail())) {
                return user.getEmails();
            }
        }
        return null;
    }

    public List<Email> getAllStarredMails(EmailDTO emailDTO){
        List<User> users = userRepository.findAll();
        List<Email> emails = null;
        List<Email> starredEmails = new ArrayList<>();
        for(User user : users){
            if(user.getEmail().equals(emailDTO.getEmail())) {
                emails = user.getEmails();
            }
        }
        for(Email email : emails){
            if(email.isStarred()) starredEmails.add((email));
        }
        return starredEmails;
    }

    public boolean updateUser(RegisterDTO registerDTO){
        List<User> users = userRepository.findAll();
        User updatedUser = null;
        for(User user : users){
            if(user.getEmail().equals(registerDTO.getEmail())) {
                updatedUser = user;
            }
        }
        updatedUser.setFirstName(registerDTO.getFirstName());
        updatedUser.setLastName(registerDTO.getLastName());
        updatedUser.setMobileNumber(registerDTO.getMobileNumber());
        updatedUser.setDateOfBirth(registerDTO.getDateOfBirth());
        userRepository.save(updatedUser);
        return true;
    }
}
