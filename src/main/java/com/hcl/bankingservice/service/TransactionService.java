package com.hcl.bankingservice.service;

import com.hcl.bankingservice.dao.service.AccountDaoService;
import com.hcl.bankingservice.dao.service.CreditCardDaoService;
import com.hcl.bankingservice.dao.service.DebitCardDaoService;
import com.hcl.bankingservice.dao.service.TransactionDaoService;
import com.hcl.bankingservice.dto.AccountDto;
import com.hcl.bankingservice.dto.CreditCardDto;
import com.hcl.bankingservice.dto.DebitCardDto;
import com.hcl.bankingservice.dto.TransactionDto;
import com.hcl.bankingservice.enums.TransactionType;
import com.hcl.bankingservice.exception.*;
import com.hcl.bankingservice.mapper.AccountMapper;
import com.hcl.bankingservice.mapper.CreditCardMapper;
import com.hcl.bankingservice.mapper.DebitCardMapper;
import com.hcl.bankingservice.mapper.TransactionMapper;
import com.hcl.bankingservice.model.Account;
import com.hcl.bankingservice.model.CreditCard;
import com.hcl.bankingservice.model.DebitCard;
import com.hcl.bankingservice.model.Transaction;
import com.hcl.bankingservice.model.interfaces.Card;
import com.hcl.bankingservice.repository.TransactionRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class TransactionService {
    private static final Logger LOGGER= LoggerFactory.getLogger(TransactionService.class.getName());


    private final TransactionDaoService transactionDaoService;

    private final CreditCardService creditCardService;

    private final DebitCardService debitCardService;

    private final CreditCardDaoService creditCardDaoService;

    private final DebitCardDaoService debitCardDaoService;

    private final AccountDaoService accountDaoService;

    public TransactionService(TransactionDaoService transactionDaoService, CreditCardService creditCardService, DebitCardService debitCardService, CreditCardDaoService creditCardDaoService, DebitCardDaoService debitCardDaoService, AccountDaoService accountDaoService) {
        this.transactionDaoService = transactionDaoService;
        this.creditCardService = creditCardService;
        this.debitCardService = debitCardService;
        this.creditCardDaoService = creditCardDaoService;
        this.debitCardDaoService = debitCardDaoService;
        this.accountDaoService = accountDaoService;
    }

    public List<TransactionDto> getAll() {
        LOGGER.debug("Fetching all Transactions ");
        List<Transaction> transactionList = transactionDaoService.getAll();

        return TransactionMapper.toDto(transactionList);
    }

    public TransactionDto getOneById(long id) {
        LOGGER.debug("Fetching Transaction with id:{}",id);
        Optional<Transaction> optionalTransaction = transactionDaoService.getOneById(id);
        if (optionalTransaction.isEmpty()) {
            LOGGER.error("Transaction Not Found with id:{}",id);
            throw new TransactionIdNotFoundException(id);
        }
        return TransactionMapper.toDto(optionalTransaction.get());
    }

    public TransactionDto create(TransactionDto transactionDto) {
        LOGGER.info("Initiating a Transaction");

        if (transactionDto.getCreditCardId() != 0) {

            if (!creditCardService.existsById(transactionDto.getCreditCardId())) {
                LOGGER.error("Credit card not found with id:{}",transactionDto.getCreditCardId());
                throw new NoCreditCardFoundException();
            }

            Optional<CreditCard> optionalCreditCard = creditCardDaoService.getOneById(transactionDto.getCreditCardId());
            CreditCard creditCard = optionalCreditCard.get();

            Optional<Account> optionalAccount = accountDaoService.getOneById(creditCard.getAccount().getAccountId());

            Transaction transaction = TransactionMapper.toEntity(transactionDto);

            transaction.setCreditCard(creditCard);
            transaction.setDebitCard(null);
            creditCard.getTransactions().add(transaction);

            // Update this code
            Account account = optionalAccount.get();
            if (transaction.getTransactionType().equals(TransactionType.DEPOSIT)) {
                account.setBalance(account.getBalance() + transaction.getTransactionAmount());
            }
            else if (transaction.getTransactionType().equals(TransactionType.WITHDRAW)) {
                if (transaction.getTransactionAmount() <= account.getBalance()) {
                    account.setBalance(account.getBalance() - transaction.getTransactionAmount());
                }
                else {
                    LOGGER.error("Balance not sufficient to make transaction");
                    throw new InsufficientBalanceException();
                }
            }

            Transaction savedTransaction = transactionDaoService.create(transaction);
            Account update = accountDaoService.update(account);

            LOGGER.info("Transaction with Credit card completed :{}",transactionDto); // Replace with Logger
            return TransactionMapper.toDto(savedTransaction);
        }
        else if (transactionDto.getDebitCardId() != 0) {

            if (!debitCardService.existsById(transactionDto.getDebitCardId())) {
                LOGGER.error("Debit card Not Found with id:{}", transactionDto.getCreditCardId());
                throw new NoDebitCardFoundException();
            }

            Optional<DebitCard> optionalDebitCard = debitCardDaoService.getOneById(transactionDto.getDebitCardId());
            DebitCard debitCard = optionalDebitCard.get();

            Optional<Account> optionalAccount = accountDaoService.getOneById(debitCard.getAccount().getAccountId());

            Transaction transaction = TransactionMapper.toEntity(transactionDto);

            transaction.setDebitCard(debitCard);
            transaction.setCreditCard(null);
            debitCard.getTransactions().add(transaction);

            // Update this code
            Account account = optionalAccount.get();
            if (transaction.getTransactionType().equals(TransactionType.DEPOSIT)) {
                account.setBalance(account.getBalance() + transaction.getTransactionAmount());
            }
            else if (transaction.getTransactionType().equals(TransactionType.WITHDRAW)) {
                if (transaction.getTransactionAmount() <= account.getBalance()) {
                    account.setBalance(account.getBalance() - transaction.getTransactionAmount());
                }
                else {
                    LOGGER.error("Balance not sufficient to make transaction");
                    throw new InsufficientBalanceException();
                }
            }

            Transaction savedTransaction = transactionDaoService.create(transaction);
            accountDaoService.update(account);

            LOGGER.info("Transaction with Debit card completed "); // Replace with Logger

            return TransactionMapper.toDto(savedTransaction);
        }
        else {
            LOGGER.error("Please select any one card");
            throw new NoCardUsedException();
        }
    }



    // Try to implement this logic By creating another Card class and implement it
//    private static Account getAccount(Card card, Transaction transaction) {
//        Account account = debitCard.getAccount();
//        if (transaction.getTransactionType().equals(TransactionType.DEPOSIT)) {
//            account.setBalance(account.getBalance() + transaction.getTransactionAmount());
//        }
//        else if (transaction.getTransactionType().equals(TransactionType.WITHDRAW)) {
//            if (transaction.getTransactionAmount() >= account.getBalance()) {
//                account.setBalance(account.getBalance() - transaction.getTransactionAmount());
//            }
//        }
//        return account;
//    }

    public boolean delete(long id) {
        return transactionDaoService.delete(id);
    }


    // Check exception here if that credit card id is not valid

}
