package com.zuka.accountAPI.dto;

import com.zuka.accountAPI.enums.Sex;
import com.zuka.accountAPI.model.Adress;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;

import javax.persistence.*;
import java.time.LocalDateTime;

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
