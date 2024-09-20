package co.id.ferry.phonebook.service;

import co.id.ferry.phonebook.model.dto.CreateDTO;
import co.id.ferry.phonebook.model.dto.EditDTO;
import co.id.ferry.phonebook.model.entity.ContactEntity;

public interface DatabaseService {
    ContactEntity fillDataContact (CreateDTO.CreateDTORQ request) throws Exception;
    ContactEntity fillDataEditContact (EditDTO.EditDTORQ request) throws Exception;

}
