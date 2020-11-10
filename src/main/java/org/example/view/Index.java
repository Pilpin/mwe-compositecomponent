package org.example.view;

import javax.faces.view.ViewScoped;
import javax.inject.Named;
import java.io.Serializable;
import java.time.LocalDate;
import java.time.LocalDateTime;

@Named
@ViewScoped
public class Index implements Serializable {
     private LocalDate date = LocalDate.now();
     private LocalDate next = LocalDate.now();

     public LocalDateTime getNow() {
          return LocalDateTime.now();
     }

     public LocalDate getDate() {
          return date;
     }

     public void setDate(LocalDate date) {
          this.date = date;
     }

     public LocalDate getNext() {
          return next;
     }

     public void setNext(LocalDate next) {
          this.next = next;
     }
}
