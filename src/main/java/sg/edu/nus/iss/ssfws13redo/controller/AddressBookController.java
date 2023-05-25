package sg.edu.nus.iss.ssfws13redo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import jakarta.validation.Valid;
import sg.edu.nus.iss.ssfws13redo.model.Contact;
import sg.edu.nus.iss.ssfws13redo.service.Contacts;
// import sg.edu.nus.iss.ssfws13redo.utility.Utility;

@Controller
@RequestMapping
public class AddressBookController {


@Autowired
Contacts service;

// @Autowired
// Utility utility;

@Value("${data.dir}")
private String dataDir;

    //load landing page
@GetMapping
public String landingPage(Model model) {   
    
    // new Contact to create a new contact form each time page is loaded
    model.addAttribute("contact", new Contact());
    return "home";

}
//BindingResult returns error message from validation in AddressBook page
//Contact contact to store new Contact from line 36
@PostMapping("/contact")
public String displayContact(@Valid Contact filledContact, BindingResult result, Model model) throws Exception {
    
    if(result.hasErrors()){

        return "home";
    }
    
    service.save(filledContact, model, dataDir);
    //return successful message as required by workshop task
    model.addAttribute("msg", "SAVED [Status code: " + HttpStatus.CREATED + "]" );
    return "contact";
}

@GetMapping("/list")
public String displayContactId(Model model) {
    service.displayContactId(model, dataDir);
    return "contacts";

}

@GetMapping("/contact/{contactId}")
    public String getContactById(Model model, @PathVariable String contactId) {
        //empty form object
       Contact contact =  new Contact();
       
       contact = service.getContactById(contactId, dataDir);
        if (contact == null) {
            model.addAttribute("errorMessage", "Contact not found");
            return "error";
        }
        model.addAttribute("contact", contact);
        return "showContact";
    }

}

