package com.zuka.account.service;

import java.util.List;
import java.util.Objects;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import com.zuka.account.dto.AccountDTO;
import com.zuka.account.exception.AccountException;
import com.zuka.account.exception.ProblemType;
import com.zuka.account.mapper.AccountMapper;
import com.zuka.account.model.Account;
import com.zuka.account.repository.AccountRepository;
import com.zuka.account.repository.AccountSpec;

@Service
public class AccountServiceImpl implements AccountService {

	private AccountRepository accountRepository;
	private AccountMapper mapper;
	private MessageSource messageSource;

	@Autowired
	public AccountServiceImpl(AccountRepository accountRepository, AccountMapper mapper,
			MessageSource messageSource) {
		this.accountRepository = accountRepository;
		this.mapper = mapper;
		this.messageSource = messageSource;
	}

	private void BusinessRulesSave(AccountDTO accountDTO) throws AccountException {
		NotSaveCPFDuplicate(accountDTO);
	}

	private void NotSaveCPFDuplicate(AccountDTO accountDTO) throws AccountException {
		ProblemType problemType = ProblemType.CPF_ALREADY_EXISTS;
		String messageDetails = messageSource.getMessage(problemType.getMessageSource(),
				new Object[] { accountDTO.getCpf() }, LocaleContextHolder.getLocale());
		if (Objects.nonNull(accountRepository.findBycpf(accountDTO.getCpf()))) {
			throw new AccountException(HttpStatus.BAD_REQUEST.value(), problemType.getUri(), problemType.getTitle(),
					messageDetails);
		};
	}

	@Override
	public AccountDTO save(AccountDTO accountDTO) throws AccountException {
		BusinessRulesSave(accountDTO);

		Account account = accountRepository.save(mapper.toAccount(accountDTO));
		return mapper.toAccountDTO(account);
	}

	@Override
	public Page<AccountDTO> listAll(Pageable pageable, AccountDTO filter) throws AccountException {
		return accountRepository.findAll(AccountSpec.searchDesc(filter), pageable).map(mapper::toAccountDTO);
	}

	@Override
	public void delete(AccountDTO accountDTO) throws AccountException {
		accountRepository.delete(mapper.toAccount(accountDTO));
	}

	@Override
	public void deleteList(List<Long> ids) throws AccountException {
		ids.forEach(obj -> delete(findById(obj)));
	}

	@Override
	public AccountDTO findById(Long id) throws AccountException {
		ProblemType problemType = ProblemType.USER_NOT_EXISTS;
		String messageDetails = messageSource.getMessage(problemType.getMessageSource(), new Object[] {""}, LocaleContextHolder.getLocale());
		Optional<Account> obj = accountRepository.findById(id);
		AccountDTO accountDTO = mapper
				.toAccountDTO(obj.orElseThrow(() -> new AccountException(HttpStatus.BAD_REQUEST.value(),
						problemType.getTitle(), problemType.getUri(), messageDetails)));
		return accountDTO;
	}

}
