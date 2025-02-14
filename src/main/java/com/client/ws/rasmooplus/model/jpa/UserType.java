package com.client.ws.rasmooplus.model.jpa;


import jakarta.persistence.*;
import lombok.*;
import org.springframework.security.core.GrantedAuthority;


@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
@Table(name = "user_type")
public class UserType implements GrantedAuthority {


    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(
            name = "user_type_id"
    )
    private  Long id;

    private  String name;

    private  String description;


    @Override
    public String getAuthority() {
        return name;
    }
}
