package co.id.ferry.phonebook.service.impl;

import co.id.ferry.phonebook.model.dto.CreateDTO;
import co.id.ferry.phonebook.model.dto.EditDTO;
import co.id.ferry.phonebook.model.entity.ContactEntity;
import co.id.ferry.phonebook.service.DatabaseService;
import org.springframework.stereotype.Service;

@Service
public class DatabaseServiceImpl implements DatabaseService {

    @Override
    public ContactEntity fillDataContact(CreateDTO.CreateDTORQ request) throws Exception {
        ContactEntity fieldData = new ContactEntity();
        fieldData.setFirstName(request.getFirstName());
        fieldData.setLastName(request.getLastName());
        fieldData.setFullName(request.getFirstName() + ' ' +request.getLastName());
        fieldData.setEmail(request.getEmail());
        fieldData.setPhoneNum(request.getPhoneNum());
        fieldData.setWorkPhone(request.getWorkPhone());
        fieldData.setAddress(request.getAddress());
        return fieldData;
    }

    @Override
    public ContactEntity fillDataEditContact(EditDTO.EditDTORQ request) throws Exception {
        ContactEntity fieldData = new ContactEntity();
        Long id = Long.valueOf(request.getId());
        fieldData.setId(id);
        fieldData.setFirstName(request.getFirstName());
        fieldData.setLastName(request.getLastName());
        fieldData.setFullName(request.getFirstName() + ' ' +request.getLastName());
        fieldData.setEmail(request.getEmail());
        fieldData.setPhoneNum(request.getPhoneNum());
        fieldData.setWorkPhone(request.getWorkPhone());
        fieldData.setAddress(request.getAddress());
        return fieldData;
    }
}
