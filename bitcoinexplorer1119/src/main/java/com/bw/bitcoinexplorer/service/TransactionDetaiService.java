package com.bw.bitcoinexplorer.service;

import com.alibaba.fastjson.JSONObject;
import com.bw.bitcoinexplorer.po.TransactionDetail;

import java.util.List;

public interface TransactionDetaiService {
    void syncTxDetailVout(JSONObject vout, Integer transactionId);

    void syncTxDetailVin(JSONObject vin, Integer transactionId);

    List<TransactionDetail> getByTransactionId(Integer transactionId);

    Integer getTotalByAddress(String address);

    Double getReceiveByAddres(String address);

    Double getSendByAddress(String address);
}
