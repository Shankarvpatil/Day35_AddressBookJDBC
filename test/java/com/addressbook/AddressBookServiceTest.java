package com.addressbook.jdbc;

import com.addressbook.jdbc.AddressbookService;
import com.addressbook.jdbc.Person;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.time.LocalDate;
import java.util.List;

public class AddressBookServiceTest {
    @Before
    public void init() {
        System.out.println("Welcome to AddressBook Service Program");
    }
    @Test
    public void givenAddressBookDB_WhenRetrivedShouldMatchPersonCount() {
        AddressbookService addressBookService = new AddressbookService();
        List<Person> addressBookData = addressBookService.readAddressBookData(AddressbookService.IOService.DB_IO);
        Assert.assertEquals(2, addressBookData.size());
    }
    
   
    @Test
    public void givenContactNumber_WhenUpdated_ShouldSyncWithDb() {
        AddressbookService addressBookService = new AddressbookService();
     addressBookService.updateContactNumber("shankar","11111111");
        boolean result = addressBookService.checkAddressBookInSyncWithDB("shankar");
        Assert.assertTrue(result);
    }

    @Test
    public void givenDateRangeWhenRetrieved_ShouldMatchEntryCount() {
        AddressbookService addressBookService = new AddressbookService();
        LocalDate startDate = LocalDate.of(2017, 01, 01);
        LocalDate endDate = LocalDate.now();
        List<Person> addressBookDataList =
                addressBookService.readAddressBookForDateRange(AddressbookService
                        .IOService.DB_IO, startDate, endDate);
        Assert.assertEquals(2, addressBookDataList.size());
    }  
    }  
