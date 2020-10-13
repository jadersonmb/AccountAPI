package com.zuka.account.service;

import java.util.List;
import java.util.UUID;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.zuka.account.dto.AccountDTO;
import com.zuka.account.exception.AccountException;

@Service
public interface AccountService {

	AccountDTO save(AccountDTO accountDTO) throws AccountException;
	Page<AccountDTO> listAll(Pageable pageable, AccountDTO filter) throws AccountException;
	void delete(AccountDTO accountDTO) throws AccountException;
	AccountDTO findById(UUID id) throws AccountException;
	void deleteList(List<UUID> ids) throws AccountException;
}
