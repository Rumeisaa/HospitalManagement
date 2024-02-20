package com.project.contactmessage.mapper;

import com.project.contactmessage.dto.ContactMessageRequest;
import com.project.contactmessage.dto.ContactMessageResponse;
import com.project.contactmessage.entity.ContactMessage;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;

@Component //ben bu siniftan  bir obje istedigimde her seferinde ayni nesne donecek
public class ContactMessageMapper {


    //!!! REQUEST --> POJO donusumu

    public ContactMessage requestToContactMessage(ContactMessageRequest contactMessageRequest){

        return ContactMessage.builder()
                .name(contactMessageRequest.getName())
                .email(contactMessageRequest.getEmail())
                .subject(contactMessageRequest.getSubject())
                .message(contactMessageRequest.getMessage())
                .dateTime(LocalDateTime.now())
                .build();

    }



    // !!! POJO --> RESPONSE donusumu


    public ContactMessageResponse contactMessageToResponse(ContactMessage contactMessage){

        return ContactMessageResponse.builder()
                .name(contactMessage.getName())
                .email(contactMessage.getEmail())
                .subject(contactMessage.getSubject())
                .message(contactMessage.getMessage())
                .dateTime(contactMessage.getDateTime())
                .build(); //bu bilgilerle nesnemi olustur demis oldum

    }




}
