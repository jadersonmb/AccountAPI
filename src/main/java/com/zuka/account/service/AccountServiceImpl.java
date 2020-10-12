package com.zuka.account.service;

import com.zuka.account.exception.AccountException;
import com.zuka.account.exception.ProblemType;
import com.zuka.account.mapper.AccountMapper;
import com.zuka.account.dto.AccountDTO;
import com.zuka.account.model.Account;
import com.zuka.account.repository.AccountRepository;
import com.zuka.account.repository.AccountSpec;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class AccountServiceImpl implements AccountService {

    private AccountRepository accountRepository;
    @Autowired
    private AccountMapper mapper;

    @Autowired
    public AccountServiceImpl(AccountRepository accountRepository) {
        this.accountRepository = accountRepository;
    }

    @Override
    public AccountDTO save(AccountDTO accountDTO) throws AccountException{
        Account account = accountRepository.save(mapper.toAccount(accountDTO));
        return mapper.toAccountDTO(account);
    }

    @Override
    public Page<AccountDTO> listAll(Pageable pageable, AccountDTO filter) throws AccountException{
        return accountRepository.findAll(AccountSpec.searchDesc(filter), pageable)
                .map(mapper::toAccountDTO);
    }

    @Override
    public void delete(AccountDTO accountDTO) throws AccountException{
        accountRepository.delete(mapper.toAccount(accountDTO));
    }

    @Override
    public AccountDTO findById(Long id) throws AccountException{
        ProblemType problemType = ProblemType.USER_NOT_FOUND;
        Optional<Account> obj = accountRepository.findById(id);
        AccountDTO accountDTO = mapper
                .toAccountDTO(obj.orElseThrow(() -> new AccountException("Erro Account don't exist", problemType)));
        return accountDTO;
    }

    @Override
    public void deleteList(List<Long> ids) throws AccountException {
        ids.forEach(obj -> delete(new AccountDTO(obj)));
    }

}
