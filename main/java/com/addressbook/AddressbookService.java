package com.addressbook.jdbc;

import java.time.LocalDate;
import java.util.*;
import com.addressbook.jdbc.Person;

public class AddressbookService {
	
    public enum IOService {DB_IO, REST_IO}
    private List<Person> personList;
    private AddressBookDBService addressBookDBService;

    //constructor
    public AddressbookService() {
        addressBookDBService = AddressBookDBService.getInstance();
    }
    
    public AddressbookService(List<Person> personList) {
        this();
        this.personList = personList;
    }
    
    // purpose:checking address book of data equal 
     
    public List<Person> readAddressBookData(IOService ioService) {
        if (ioService.equals(IOService.DB_IO))
            this.personList = addressBookDBService.readData();
        return personList;
    }
    
    public void updateContactNumber(String firstName, String phoneNo) {
        int result = addressBookDBService.updateContactNumber(firstName, phoneNo);
        if (result == 0) return;
        Person person = this.getAddressBookData(firstName);
        if (person != null)
            person.phoneNo = phoneNo;


    }

    public Person getAddressBookData(String firstName) {
        return this.personList.stream()
                .filter(addressBookDataItem -> addressBookDataItem.getFirstName().equals(firstName))
                .findFirst()
                .orElse(null);
    }

    public boolean checkAddressBookInSyncWithDB(String firstName) {
        List<Person> personList = addressBookDBService.getaddressBookData(firstName);
        return personList.get(0).equals(getAddressBookData(firstName));
    }

    public List<Person> readAddressBookForDateRange(IOService ioService, LocalDate startDate, LocalDate endDate) {
        if (ioService.equals(IOService.DB_IO))
            return addressBookDBService.getAddressBookForDateRange(startDate, endDate);
        return null;
    }
}
