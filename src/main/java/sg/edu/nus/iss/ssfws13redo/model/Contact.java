package sg.edu.nus.iss.ssfws13redo.model;

import java.time.LocalDate;
import java.time.Period;
import java.util.Random;

import org.springframework.format.annotation.DateTimeFormat;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Past;
import jakarta.validation.constraints.Size;

public class Contact {
  //enter validation  
   @NotNull(message="Field cannot be empty")
   @Size(min=3, max=64, message="Name should be between 3 to 15 characters")
   String name;
   
   @NotEmpty(message="Field cannot be empty")
   @Email(message="Invalid email")
   String email;

   @NotNull(message="Field cannot be empty")
   @Size(min=7, message="Input should have 7 numbers")
   String phoneNumber;

   @NotNull(message="Field cannot be empty")
   @Past(message="Age must be older than 10 years old")
   @DateTimeFormat(pattern = "dd/MM/yyyy")
   LocalDate dateOfBirth;
   
   String id;
   
   public Contact() {
      
   }

   public Contact(String id, String name, String email, String phoneNumber, LocalDate dateOfBirth) {
      this.id = generateId();
      this.name = name;
      this.email = email;
      this.phoneNumber = phoneNumber;
      this.dateOfBirth = dateOfBirth;
   }


public String generateId() {
      Random random = new Random();
      StringBuilder sb = new StringBuilder();
      
      while(sb.length() < 8) {
         sb.append(Integer.toHexString(random.nextInt()));
      }
      return sb.toString().substring(0, 8);
  }
   

   public String getName() {
      return name;
   }

   public void setName(String name) {
      this.name = name;
   }

   public String getEmail() {
      return email;
   }

   public void setEmail(String email) {
      this.email = email;
   }

   public String getPhoneNumber() {
      return phoneNumber;
   }

   public void setPhoneNumber(String phoneNumber) {
      this.phoneNumber = phoneNumber;
   } 

   public LocalDate getDateOfBirth() {
      return dateOfBirth;
   }

   public void setDateOfBirth(LocalDate dateOfBirth) {
    this.dateOfBirth = dateOfBirth;
  }

   public String getId() {
      return id;
   }

   public void setId(String id) {
      this.id = id;
   }

   @Override
   public String toString() {
      return "Contact [name=" + name + ", email=" + email + ", phoneNumber=" + phoneNumber + ", dateOfBirth="
            + dateOfBirth + "]";
   }


}  
   
   

