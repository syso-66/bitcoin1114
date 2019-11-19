package com.bw.bitcoinexplorer.dao;

import com.bw.bitcoinexplorer.po.Transaction;
import com.github.pagehelper.Page;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface TransactionMapper {
    int deleteByPrimaryKey(Integer transactionId);

    int insert(Transaction record);

    int insertSelective(Transaction record);

    Transaction selectByPrimaryKey(Integer transactionId);

    int updateByPrimaryKeySelective(Transaction record);

    int updateByPrimaryKey(Transaction record);

    List<Transaction> selectByBlockId(Integer blockId);

    Page<Transaction> getByBlockIdwithpage(@Param("blockId") Integer blockId);

    Transaction selectBytxid(@Param("txid")String txid);

    Page<Transaction> selectByBlockIdWithPage(Integer blockId);

    Page<Transaction> selectTransactionByAddress(String address);
}