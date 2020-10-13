package com.zuka.account.dto;

import java.io.Serializable;
import java.util.UUID;

import com.zuka.account.enums.Sex;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class AccountDTO implements Serializable {

    /**
     *
     */
    private static final long serialVersionUID = 1L;

    private UUID id;
    private String name;
    private String cpf;
    private Sex sex;
    private String cellPhone;
    private AddressDTO address;

    public AccountDTO(UUID id){
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public UUID getId() {
        return id;
    }
}
