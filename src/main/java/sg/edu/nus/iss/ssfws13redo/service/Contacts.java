package sg.edu.nus.iss.ssfws13redo.service;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import org.springframework.stereotype.Service;
import org.springframework.ui.Model;

import sg.edu.nus.iss.ssfws13redo.model.Contact;

@Service
public class Contacts {
    public void save(Contact contact, Model model, String dataDir) throws Exception {
        String fileName = contact.getId();

        FileWriter fw = new FileWriter(dataDir + "/" + fileName + ".txt");
        PrintWriter pw = new PrintWriter(fw);

        pw.println(contact.getName());
        pw.println(contact.getEmail());
        pw.println(contact.getPhoneNumber());
        pw.println(contact.getDateOfBirth());

        model.addAttribute("contact", new Contact(contact.getId(), contact.getName(), contact.getEmail(), contact.getPhoneNumber(), contact.getDateOfBirth()));
        
        pw.close();
    }  
//method for return a set containing names of files excluding directories
//:: is method referencing, using method of file class. eg. getName is an instance method
public Set<String> listFiles(String dataDir) {
    return Stream.of(new File(dataDir)).filter(File :: isFile).map(File :: getName).collect(Collectors.toSet());
    }    

public void displayContactId(Model model, String dataDir) {
//calls listFiles method above, to obtain a set of file names stored in dataFiles    
    Set<String> dataFiles = listFiles(dataDir);    
    Set<String> modifiedFiles = new HashSet<>();
//rename files without .txt
    for(String file : dataFiles) {
        String formattedFile = file.replace(".txt", "");
        modifiedFiles.add(formattedFile);

    }
    model.addAttribute("contactFiles", modifiedFiles.toArray(new String[dataFiles.size()]));

}    

public Contact getContactById(String contactId, String dataDir) {
    Contact ctc = new Contact();
    DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");

        java.nio.file.Path filePath = new File(dataDir + "/" + contactId+".txt").toPath();
        Charset charset = Charset.forName("UTF-8");
        List<String> stringList = new ArrayList<String>();
        try {
        stringList = Files.readAllLines(filePath, charset);
        ctc.setId(contactId);
        ctc.setName(stringList.get(0));
        ctc.setEmail(stringList.get(1));
        ctc.setPhoneNumber(stringList.get(2));
        LocalDate dob = LocalDate.parse(stringList.get(3), formatter);
        ctc.setDateOfBirth(dob);
    } catch (IOException e) {
        e.printStackTrace();
        return null; 
    }
        return ctc;
}

}

