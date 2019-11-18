package com.bw.bitcoinexplorer.service;



import com.bw.bitcoinexplorer.po.Transaction;
import com.github.pagehelper.Page;

import java.util.List;

public interface TransactionService {
    void syncTransaction(String txid, Integer blockId, Long time);

    List<Transaction> getByBlockId(Integer blockId);

    Page<Transaction> getByBlockIdwithpage(Integer blockId, Integer page);

    Transaction getByTxid(String txid);


}
