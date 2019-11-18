package com.bw.bitcoinexplorer.service.impl;


import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.bw.bitcoinexplorer.client.BitcoinRest;

import com.bw.bitcoinexplorer.dao.TransactionMapper;
import com.bw.bitcoinexplorer.po.Transaction;
import com.bw.bitcoinexplorer.service.TransactionService;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class TransactionServiceImpl implements TransactionService {

    @Autowired
    private BitcoinRest bitcoinRest;

    @Autowired
    private TransactionMapper transactionMapper;

    @Autowired
    private TransactionDetailServiceImpl transactionDetailService;


    @Override
    public void syncTransaction(String txid, Integer blockId, Long time) {
        JSONObject transactionJson = bitcoinRest.getTransaction(txid);
        Transaction transaction = new Transaction();
        //交易信息
        transaction.setBlockId(blockId);
        transaction.setStatus((byte)0);
        transaction.setTime(time);
        transaction.setSizeondisk(transactionJson.getInteger("size"));
        transaction.setTxid(transactionJson.getString("txid"));
        transaction.setWeight(transactionJson.getInteger("weight"));
        transaction.setTxhash(transactionJson.getString("hash"));

        transactionMapper.insert(transaction);

        Integer transactionId = transaction.getTransactionId();
        List<JSONObject> jsonObjects = transactionJson.getJSONArray("vout").toJavaList(JSONObject.class);

        for (JSONObject vout :jsonObjects){
            transactionDetailService.syncTxDetailVout(vout, transactionId);
        }

        List<JSONObject> vin = transactionJson.getJSONArray("vin").toJavaList(JSONObject.class);
        for (JSONObject vins:vin) {

            transactionDetailService.syncTxDetailVin(vins, transactionId);

        }


    }

    @Override
    public List<Transaction> getByBlockId(Integer blockId) {
        List<Transaction> transactions = transactionMapper.selectByBlockId(blockId);
        return transactions;
    }

    @Override
    public Page<Transaction> getByBlockIdwithpage(Integer blockId, Integer page) {
        PageHelper.startPage(page,10);
        transactionMapper.getByBlockIdwithpage(blockId);
        Page<Transaction> transactions = transactionMapper.selectByBlockIdWithPage(blockId);

        return transactions;
    }



    @Override
    public Transaction getByTxid(String txid) {
        Transaction transaction = transactionMapper.selectBytxid(txid);
        return transaction;
    }




}
