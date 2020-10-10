package com.zuka.account.dto;

import com.zuka.account.enums.Sex;
import com.zuka.account.model.Adress;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class AccountDTO {

    private Long id;
    private String name;
    private String cpf;
    private Sex sex;
    private String cellPhone;
    private Adress adress;

    public AccountDTO(Long id){
        this.id = id;
    }
}
