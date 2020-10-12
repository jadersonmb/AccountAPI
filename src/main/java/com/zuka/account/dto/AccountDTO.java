package com.zuka.account.dto;

import com.zuka.account.enums.Sex;
import com.zuka.account.model.Address;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class AccountDTO implements Serializable {

    /**
     *
     */
    private static final long serialVersionUID = 1L;

    private Long id;
    private String name;
    private String cpf;
    private Sex sex;
    private String cellPhone;
    private AddressDTO address;

    public AccountDTO(Long id){
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public Long getId() {
        return id;
    }
}
