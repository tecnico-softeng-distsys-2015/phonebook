package pt.tecnico.phonebook.service.dto;
	
public class ContactDto {
	  
    private String name;
    private int phoneNumber;
	      
    public ContactDto(String name, int phoneNumber) {  
	this.name = name;
	this.phoneNumber = phoneNumber;
    }
	      
    public final String getName() {  
	return this.name;
    }

    public final int getPhoneNumber() {
	return this.phoneNumber;
    }
}  
