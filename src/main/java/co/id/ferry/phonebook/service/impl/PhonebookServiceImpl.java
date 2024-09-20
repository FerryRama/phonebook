package co.id.ferry.phonebook.service.impl;

import co.id.ferry.phonebook.config.Config;
import co.id.ferry.phonebook.model.dto.CreateDTO;
import co.id.ferry.phonebook.model.dto.DeleteDTO;
import co.id.ferry.phonebook.model.dto.EditDTO;
import co.id.ferry.phonebook.model.entity.ContactEntity;
import co.id.ferry.phonebook.repository.phonebookRepository;
import co.id.ferry.phonebook.service.DatabaseService;
import co.id.ferry.phonebook.service.PhonebookService;
import co.id.ferry.phonebook.utils.ValidationUtils;
import org.json.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class PhonebookServiceImpl implements PhonebookService {
    Logger logger = LoggerFactory.getLogger(PhonebookServiceImpl.class);
    @Autowired
    private ValidationUtils validationUtils;
    @Autowired
    private phonebookRepository phonebookRepository;
    @Autowired
    private DatabaseService databaseService;
    @Override
    public CreateDTO.CreateDTORS createNewContant(CreateDTO.CreateDTORQ request) throws Exception {
        String email = request.getEmail();
        CreateDTO.CreateDTORS response = new CreateDTO.CreateDTORS();
        boolean validation = false;
        boolean resultEmail = validationUtils.emailValidation(email);
        String responseMessage = "", responseCode = "";
        if (!resultEmail) responseCode = "RC01";
        else validation = true;
        if (validation){
            logger.info("Request message valid");
            ContactEntity saveContact = databaseService.fillDataContact(request);
            phonebookRepository.save(saveContact);
            responseCode = "RC00";
            responseMessage = Config.getInformationRC("RC00");
        }else{
            logger.info("Request message invalid");
           responseMessage = Config.getInformationRC(responseCode);
        }
        response.setFirstName(request.getFirstName());
        response.setLastName(request.getLastName());
        response.setEmail(request.getEmail());
        response.setPhoneNum(request.getPhoneNum());
        response.setWorkPhone(request.getWorkPhone());
        response.setAddress(request.getAddress());
        response.setResponseCode(responseCode);
        response.setResponseMessage(responseMessage);
        return response;
    }

    @Override
    public JSONObject inquiryContact(String id) {
        String responseCode;
        JSONObject response = new JSONObject();
        ContactEntity data = phonebookRepository.getById(id);
        try{
            responseCode = "RC00";
            String idContact = String.valueOf(data.getId());
            String fullName = data.getFullName();
            String phoneNum = data.getPhoneNum();
            String workPhone = data.getWorkPhone();
            String address = data.getAddress();
            String email = data.getEmail();
            response.put("id", idContact);
            response.put("fullName", fullName);
            response.put("phoneNum", phoneNum);
            response.put("workPhone", workPhone);
            response.put("address", address);
            response.put("email", email);
            response.put("errorCode", responseCode);
            response.put("errorMessage", Config.getInformationRC(responseCode));
        }catch (Exception e){
            responseCode = "RC05";
            response.put("errorCode", responseCode);
            response.put("errorMessage", Config.getInformationRC(responseCode));
        }
        return response;
    }

    @Override
    public EditDTO.EditDTORS editContact(EditDTO.EditDTORQ request) throws Exception {
        EditDTO.EditDTORS response = new EditDTO.EditDTORS();
        String responseMessage = "", responseCode = "";
        ContactEntity data = phonebookRepository.getById(request.getId());
        boolean validation = false;
        boolean resultEmail = validationUtils.emailValidation(request.getEmail());
        if (!resultEmail) responseCode = "RC01";
        else validation = true;
        if(validation) {
            logger.info("Request message valid");
            try {
                ContactEntity saveContact = databaseService.fillDataEditContact(request);
                phonebookRepository.save(saveContact);
                responseCode = "RC00";
                responseMessage = Config.getInformationRC(responseCode);
            } catch (Exception e) {
                logger.info("Request message invalid");
                responseCode = "RC05";
                responseMessage = Config.getInformationRC(responseCode);
            }
        }else {
            logger.info("Request message invalid");
            responseMessage = Config.getInformationRC(responseCode);
        }
        response.setFirstName(request.getFirstName());
        response.setLastName(request.getLastName());
        response.setEmail(request.getEmail());
        response.setPhoneNum(request.getPhoneNum());
        response.setWorkPhone(request.getWorkPhone());
        response.setAddress(request.getAddress());
        response.setResponseCode(responseCode);
        response.setResponseMessage(responseMessage);
        return response;
    }

    @Override
    public DeleteDTO.DeleteDTORS deleteContact(DeleteDTO.DeleteDTORQ request) throws Exception {
        DeleteDTO.DeleteDTORS response = new DeleteDTO.DeleteDTORS();
        String responseMessage = "", responseCode = "";
        int id = Integer.parseInt(request.getId());
        List<ContactEntity> findContact = phonebookRepository.findAllContact(id);
        if(findContact.size() < 1){
            logger.info("Request message invalid");
            responseCode = "RC05";
            responseMessage = Config.getInformationRC(responseCode);
        }else{
            phonebookRepository.deleteById(request.getId());
            responseCode = "RC00";
            responseMessage = Config.getInformationRC(responseCode);
        }
        response.setFirstName(request.getFirstName());
        response.setLastName(request.getLastName());
        response.setEmail(request.getEmail());
        response.setPhoneNum(request.getPhoneNum());
        response.setWorkPhone(request.getWorkPhone());
        response.setAddress(request.getAddress());
        response.setResponseCode(responseCode);
        response.setResponseMessage(responseMessage);
        return response;
    }

    @Override
    public List<ContactEntity> getAllContact() {
        List<ContactEntity> contact = new ArrayList<ContactEntity>();
        phonebookRepository.findAll().forEach(contact1 -> contact.add(contact1));
        return contact;
    }
}
