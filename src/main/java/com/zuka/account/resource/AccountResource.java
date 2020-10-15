package com.zuka.account.resource;

import java.io.Serializable;
import java.net.URI;
import java.util.List;
import java.util.Objects;
import java.util.UUID;

import javax.validation.Valid;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.zuka.account.dto.AccountDTO;
import com.zuka.account.exception.AccountException;
import com.zuka.account.exception.Problem;
import com.zuka.account.service.AccountService;

@RestController()
@RequestMapping(value = "/api/account")
public class AccountResource implements Serializable {
    private static final long serialVersionUID = 1L;

    private AccountService accountService;

    @Autowired
    private AccountResource (AccountService accountService){
        this.accountService = accountService;
    }

    @GetMapping
    public ResponseEntity<?> listAll(Pageable pageable, AccountDTO filter) {
        Page<AccountDTO> listAllAccountDTO = accountService.listAll(pageable, filter);
        return ResponseEntity.ok().body(listAllAccountDTO);
    }

    @PostMapping
    public ResponseEntity<?> save(@RequestBody @Valid AccountDTO accountDTO) {
        AccountDTO accountReturnDTO = accountService.save(accountDTO);
        URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(accountReturnDTO.getId()).toUri();
        return ResponseEntity.created(uri).body(accountReturnDTO);

    }

    @DeleteMapping(value = "/{id}")
    public ResponseEntity<?> delete(@PathVariable UUID id) {
        AccountDTO accountDTO = accountService.findById(id);
        accountService.delete(accountDTO);
        return ResponseEntity.ok().build();
    }

    @RequestMapping(value = "/deleteList/{ids}", method=RequestMethod.DELETE)
    public ResponseEntity<?> deleteList(@PathVariable List<UUID> ids) {
        accountService.deleteList(ids);
        return ResponseEntity.ok().build();
    }

    @PutMapping
    public ResponseEntity<?> update(@RequestBody AccountDTO accountDTO) {
        AccountDTO accountSaveDTO = accountService.findById(accountDTO.getId());
        if(Objects.nonNull(accountSaveDTO.getId())) {
            BeanUtils.copyProperties(accountDTO, accountSaveDTO, "id");
            accountService.save(accountSaveDTO);
        }
        return ResponseEntity.ok().build();
    }

    @ExceptionHandler({ AccountException.class })
    public ResponseEntity<Object> AccountException(AccountException ex) {
        Problem problem = createProblemBuild(ex.getStatus(), ex.getDetails(), ex.getType(), ex.getTitle())
                .build();
        return ResponseEntity.badRequest().body(problem);
    }

    private Problem.ProblemBuilder createProblemBuild(Integer status, String detail, String type, String title){
        return Problem.builder()
                .status(status)
                .details(detail)
                .type(type)
                .title(title);
    }
}
