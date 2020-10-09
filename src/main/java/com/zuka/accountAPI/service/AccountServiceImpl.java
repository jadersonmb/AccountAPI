package com.zuka.accountAPI.service;

import com.zuka.account.mapper.AccountMapper;
import com.zuka.accountAPI.dto.AccountDTO;
import com.zuka.accountAPI.model.Account;
import com.zuka.accountAPI.repository.AccountRepository;
import com.zuka.accountAPI.repository.AccountSpec;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class AccountServiceImpl implements AccountService {

    private AccountRepository accountRepository;
    private AccountMapper mapper;

    @Autowired
    public AccountServiceImpl(AccountRepository accountRepository, AccountMapper mapper) {
        this.accountRepository = accountRepository;
        this.mapper = mapper;
    }

    @Override
    public AccountDTO save(AccountDTO accountDTO) {
        Account account = accountRepository.save(mapper.toAccount(accountDTO));
        return mapper.toAccountDTO(account);
    }

    @Override
    public Page<AccountDTO> listAll(Pageable pageable, AccountDTO filter) {
        return accountRepository.findAll(AccountSpec.searchDesc(filter), pageable)
                .map(mapper::toAccountDTO);
    }

    @Override
    public void delete(AccountDTO accountDTO) {
        accountRepository.delete(mapper.toAccount(accountDTO));
    }

    @Override
    public AccountDTO findById(Long id) {
        Optional<Account> obj = accountRepository.findById(id);
        AccountDTO accountDTO = mapper
                .toAccountDTO(obj.orElseThrow(() -> new RuntimeException("Erro Account don't exist")));
        return accountDTO;
    }

    @Override
    public void deleteList(List<Long> ids) {
        ids.forEach(obj -> delete(new AccountDTO(obj)));
    }

}
