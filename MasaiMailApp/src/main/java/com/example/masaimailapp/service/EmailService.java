package com.example.masaimailapp.service;

import com.example.masaimailapp.dto.SendEmailDTO;
import com.example.masaimailapp.entity.Email;
import com.example.masaimailapp.modelmapper.ModelMapperClass;
import com.example.masaimailapp.repository.EmailRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


@Service
public class EmailService {
    @Autowired
    EmailRepository emailRepository;

    @Autowired
    ModelMapperClass modelMapperClass;

    public boolean sendEmail(SendEmailDTO sendEmailDTO){
        Email email = new Email();
        email.setTo(sendEmailDTO.getTo());
        email.setFrom(sendEmailDTO.getFrom());
        email.setSubject(sendEmailDTO.getSubject());
        emailRepository.save(email);
        return true;
    }

    public boolean starAnEmail(Long id){
        Email email = emailRepository.getReferenceById(id);
        email.setStarred(true);
        emailRepository.save(email);
        return true;
    }

    public boolean deleteEmail(Long id){
        emailRepository.deleteById(id);
        return true;
    }
}
