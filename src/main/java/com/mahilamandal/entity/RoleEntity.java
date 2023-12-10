package com.mahilamandal.entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "tbl_role")
public class RoleEntity {
    @Id
//    @SequenceGenerator(
//            name = "role_sequence",
//            sequenceName = "role_sequence",
//            allocationSize = 1
//    )
//    @GeneratedValue(strategy = GenerationType.SEQUENCE,
//            generator ="role_sequence"
//    )
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private String roleName;

    @CreationTimestamp
    private LocalDateTime createdDateTime;

    @UpdateTimestamp
    private LocalDateTime updatedDateTime;
}
