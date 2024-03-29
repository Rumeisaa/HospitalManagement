package com.project.contactmessage.controller;

import com.project.contactmessage.dto.ContactMessageRequest;
import com.project.contactmessage.dto.ContactMessageResponse;
import com.project.contactmessage.entity.ContactMessage;
import com.project.contactmessage.service.ContactMessageService;
import com.project.payload.response.business.ResponseMessage;
import lombok.Data;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.persistence.Entity;
import javax.validation.Valid;
import java.util.List;


@RestController
@RequiredArgsConstructor
@RequestMapping("/contactMessages")
public class ContactMessageController {


    private final ContactMessageService contactMessageService;


    @PostMapping("save")  //http://localhost:8080/contactMessages/save + POST + JSON
    public ResponseMessage<ContactMessageResponse> save(@Valid @RequestBody ContactMessageRequest contactMessageRequest){

        return contactMessageService.save(contactMessageRequest);
    }


    @GetMapping("/getAll")
    public Page<ContactMessageResponse>getAll(   //http://localhost:8080/contactMessages/getAll + GET

            @RequestParam(value="page",defaultValue ="0") int page,
            @RequestParam(value="size",defaultValue = "10") int size,
            @RequestParam(value="sort",defaultValue = "dateTime") String sort,
            @RequestParam(value="type",defaultValue = "desc") String type
    ){

        return contactMessageService.getAll(page,size,sort,type);

    }


    @GetMapping("/searchByEmail")     //http://localhost8080/contactMessages/searchByEmail?email=aaa@bbb.com + GET
    public Page<ContactMessageResponse> searchByEmail(

            @RequestParam(value = "email") String email,
            @RequestParam(value = "page", defaultValue = "0") int page,
            @RequestParam(value = "size", defaultValue = "10") int size,
            @RequestParam(value = "sort", defaultValue = "dateTime") String sort,
            @RequestParam(value = "type" ,defaultValue = "desc") String type

    ){
        return contactMessageService.searchByEmail(email,page,size,sort,type);
    }



    @GetMapping("/searchBySubject")    //http://localhost8080/contactMessages/searchBySubject?subject=deneme + GET
    public Page<ContactMessageResponse> searchBySubject(

            @RequestParam(value = "subject") String subject,
            @RequestParam(value = "page",defaultValue = "0") int page,
            @RequestParam(value = "size",defaultValue = "10") int size,
            @RequestParam(value= "sort",defaultValue = "dateTime") String sort,
            @RequestParam(value = "type",defaultValue = "desc") String type

    ){

        return contactMessageService.searchBySubject(subject,page,sort,size,type);
    }


    //PathVariable ile yapilisi
    @DeleteMapping("/deleteById/{contactMessageId}")    // http://localhost8080/contactMessages/deleteById/2 + DELETE
    public ResponseEntity<String> deleteByIdPath (@PathVariable(value = "contactMessageId") Long contactMessageId){

        return ResponseEntity.ok(contactMessageService.deleteById(contactMessageId));
    }



    //RequestParam ile yapilisi
    @DeleteMapping("/deleteByIdParam")     //http://localhost8080/contactMessages/deleteByIdParam?contactMessageId=2 + DELETE
    public ResponseEntity<String> deleteByIdParam(@RequestParam(value = "contactMessageId") Long id){

        return ResponseEntity.ok(contactMessageService.deleteById(id));

    }


    @GetMapping("/searchBetweenDates")     //http://localhost8080/contactMessages/searchBetweenDates?beginDate=2024-09-13&endDate=2024-09-15 +GET
    public ResponseEntity<List<ContactMessage>> searchBetweenDates(

            @RequestParam(value = "beginDate") String beginDateString,
            @RequestParam(value="endDate") String endDateString

    ){
        List<ContactMessage> contactMessages=contactMessageService.searchBetweenDates(beginDateString,endDateString);
        return ResponseEntity.ok(contactMessages);

    }



    @GetMapping("/searchBetweenTimes")    //http://localhost:8080/contactMessages/searchBetweenTimes?startHour=09&startMinute=00&endHour=23&endMinute=30+ GET
    public ResponseEntity<List<ContactMessage>> searchBetweenTimes(
            @RequestParam(value = "startHour") String startHourString,
            @RequestParam(value = "startMinute") String startMinuteString,
            @RequestParam(value = "endHour") String endHourString,
            @RequestParam(value = "endMinute") String endMinuteString

    ){
       List<ContactMessage> contactMessages= contactMessageService.searchBetweenTimes(startHourString,startMinuteString,endHourString,endMinuteString);
        return ResponseEntity.ok(contactMessages);
    }

    //@RequestParam ile yapilisi
    @GetMapping("/getByIdParam")  //http://localhost:8080/contactMessages/getByIdParam?contactMessageId=2  +GET
    public ResponseEntity<ContactMessage> getById(
            @RequestParam(value = "contactMessageId") Long id
    ){
        return ResponseEntity.ok(contactMessageService.getContactMessageById(id));
    }


    //@PathVariable ile yapilisi

    @GetMapping("/getByIdPath/{id}")    //http://localhost:8080/contactMessages/getByIdPath/2  +GET
    public ResponseEntity<ContactMessage> getByIdPath(
            @PathVariable("id") Long id
    ){
        return ResponseEntity.ok(contactMessageService.getContactMessageById(id));
    }

}
