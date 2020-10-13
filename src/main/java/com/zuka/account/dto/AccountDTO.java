package com.zuka.account.dto;

import java.io.Serializable;
import java.util.UUID;

import javax.validation.constraints.NotNull;

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
    @NotNull
    private String name;
    @NotNull
    private String cpf;
    private Sex sex;
    @NotNull
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
