package co.id.ferry.phonebook.controller;

import co.id.ferry.phonebook.model.dto.CreateDTO;
import co.id.ferry.phonebook.model.dto.DeleteDTO;
import co.id.ferry.phonebook.model.dto.EditDTO;
import co.id.ferry.phonebook.model.entity.ContactEntity;
import co.id.ferry.phonebook.service.PhonebookService;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import org.json.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/restv1/phonebook")
public class Controller {
    Logger logger = LoggerFactory.getLogger(Controller.class);

    @Autowired
    PhonebookService phonebookService;
    @PostMapping(value = "/create", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody
    public ResponseEntity<?> createContact(@RequestBody CreateDTO.CreateDTORQ request) throws Exception {
        logger.info("Starting create new contact");
        CreateDTO.CreateDTORS resp = phonebookService.createNewContant(request);
        return new ResponseEntity<>(resp, HttpStatus.OK);
    }
    @GetMapping(value = "/inquiry/{id}")
    @ResponseBody
    public ResponseEntity<?> getContactByID(@PathVariable("id") String id){
        logger.info("Starting get contact by ID");
        JSONObject response = phonebookService.inquiryContact(id);
        logger.info("End get contact by ID");
        return new ResponseEntity<>(response.toMap(), HttpStatus.OK);
    }
    @PostMapping(value = "/edit", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody
    public ResponseEntity<?> editContact(@RequestBody EditDTO.EditDTORQ request) throws Exception{
        logger.info("Starting edit contact");
        EditDTO.EditDTORS resp = phonebookService.editContact(request);
        logger.info("End edit contact");
        return new ResponseEntity<>(resp, HttpStatus.OK);
    }

    @PostMapping(value = "/delete", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody
    public ResponseEntity<?> deleteContact(@RequestBody DeleteDTO.DeleteDTORQ request) throws Exception{
        logger.info("Starting edit contact");
        DeleteDTO.DeleteDTORS resp = phonebookService.deleteContact(request);
        logger.info("End edit contact");
        return new ResponseEntity<>(resp, HttpStatus.OK);
    }

    @GetMapping(value = "/getAll")
    @ResponseBody
    public List<ContactEntity> getAll(){
        logger.info("Starting get contact by ID");
        List<ContactEntity> response = phonebookService.getAllContact();

        logger.info("End get contact by ID");
        return response;
    }
}
