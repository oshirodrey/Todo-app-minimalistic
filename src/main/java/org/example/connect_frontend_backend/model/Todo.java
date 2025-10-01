package org.example.connect_frontend_backend.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.example.connect_frontend_backend.model.appuser.AppUser;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Todo {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;
    private String title;
    private String description;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JsonIgnore
    @JoinColumn(name = "app_user_id", nullable = false)
    private org.example.connect_frontend_backend.model.appuser.AppUser appUser;


    public Todo(String title, String description, AppUser appUser) {
        this.title = title;
        this.description = description;
        this.appUser = appUser;
    }
}
