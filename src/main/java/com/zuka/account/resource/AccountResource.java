package com.zuka.account.resource;

import java.io.Serializable;
import java.net.URI;
import java.util.List;
import java.util.Objects;

import com.zuka.account.dto.AccountDTO;
import com.zuka.account.exception.AccountException;
import com.zuka.account.exception.Problem;
import com.zuka.account.exception.ProblemType;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.zuka.account.service.AccountService;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import javax.validation.Valid;

@RestController()
@RequestMapping(value = "/api/account")
public class AccountResource implements Serializable {
    private static final long serialVersionUID = 1L;

    private AccountService accountService;
    @Autowired
    private MessageSource messageSource;


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
    public ResponseEntity<?> delete(@PathVariable Long id) {
        AccountDTO accountDTO = accountService.findById(id);
        accountService.delete(accountDTO);
        return ResponseEntity.ok().build();
    }

    @RequestMapping(value = "/deleteList/{ids}", method=RequestMethod.DELETE)
    public ResponseEntity<?> deleteList(@PathVariable List<Long> ids) {
        accountService.deleteList(ids);
        return ResponseEntity.ok().build();
    }

    @PutMapping(value = "/{id}")
    public ResponseEntity<?> update(@PathVariable Long id, @RequestBody AccountDTO accountDTO) {
        AccountDTO accountSaveDTO = accountService.findById(id);
        if(Objects.nonNull(accountSaveDTO.getId())) {
            BeanUtils.copyProperties(accountDTO, accountSaveDTO, "id");
            accountService.save(accountSaveDTO);
        }
        return ResponseEntity.ok().build();
    }

    @ExceptionHandler({ AccountException.class })
    public ResponseEntity<Object> AccountException(AccountException ex, ProblemType problemType) {
        String messageUser = messageSource.getMessage(ex.getMessage(), null, LocaleContextHolder.getLocale());
        Problem problem = createProblemBuild(HttpStatus.BAD_REQUEST, messageUser, problemType)
                .build();
        return ResponseEntity.badRequest().body(problem);
    }

    private Problem.ProblemBuilder createProblemBuild (HttpStatus status, String detail, ProblemType problemType){
        return Problem.builder()
                .status(status.value())
                .title(problemType.getTitle())
                .type(problemType.getUri())
                .details(detail);
    }
}
