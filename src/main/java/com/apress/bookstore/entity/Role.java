package com.apress.bookstore.entity;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table(name = "role")
public class Role implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID", nullable = false)
    @Getter
    private Long id;

    @Basic(optional = false)
    @Column(name = "ROLE_NAME", nullable = false, length = 60)
    @Setter
    @Getter
    private String roleName;

}
