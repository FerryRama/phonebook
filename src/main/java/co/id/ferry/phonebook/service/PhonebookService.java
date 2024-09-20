package co.id.ferry.phonebook.service;

import co.id.ferry.phonebook.model.dto.CreateDTO;
import co.id.ferry.phonebook.model.dto.DeleteDTO;
import co.id.ferry.phonebook.model.dto.EditDTO;
import co.id.ferry.phonebook.model.entity.ContactEntity;
import org.json.JSONObject;

import java.util.List;

public interface PhonebookService {
    CreateDTO.CreateDTORS createNewContant(CreateDTO.CreateDTORQ request) throws Exception;

    public JSONObject inquiryContact(String id);

    EditDTO.EditDTORS editContact(EditDTO.EditDTORQ request) throws Exception;

    DeleteDTO.DeleteDTORS deleteContact(DeleteDTO.DeleteDTORQ request) throws Exception;

    List<ContactEntity> getAllContact();

}
