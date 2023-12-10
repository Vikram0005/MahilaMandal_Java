package com.mahilamandal.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Table(name = "tbl_member")
public class MemberEntity {
    @Id
    private int id;
    private String name;
    private String mobileNo;
    private String emailId;
    private String password;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "addedBy",referencedColumnName = "id")
    private UserRegistrationEntity addedBy;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "groupId",referencedColumnName = "id")
    private GroupEntity group;

    @Embedded
    private Address address;

    @CreationTimestamp
    private LocalDateTime createdDateTime;

    @UpdateTimestamp
    private LocalDateTime updatedDateTime;
}
