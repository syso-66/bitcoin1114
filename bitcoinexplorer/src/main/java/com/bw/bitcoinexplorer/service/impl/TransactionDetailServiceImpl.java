package com.bw.bitcoinexplorer.service.impl;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.bw.bitcoinexplorer.client.BitcoinJsonRpcImpl;
import com.bw.bitcoinexplorer.client.BitcoinRest;
import com.bw.bitcoinexplorer.dao.TransactionDetailMapper;
import com.bw.bitcoinexplorer.enume.transactionDetailEnumType;
import com.bw.bitcoinexplorer.po.TransactionDetail;
import com.bw.bitcoinexplorer.service.TransactionDetaiService;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

public class TransactionDetailServiceImpl implements TransactionDetaiService {

    @Autowired
    private BitcoinRest bitcoinRest;

    @Autowired
    private TransactionDetailMapper transactionDetailMapper;

    @Autowired
    private BitcoinJsonRpcImpl bitcoinJsonRpc;

    @Override
    public void syncTxDetailVout(JSONObject vout, Integer transactionId) {
        TransactionDetail transactionDetail = new TransactionDetail();
        JSONObject scriptPubKey = vout.getJSONObject("scriptPubKey");
        JSONArray addresses = scriptPubKey.getJSONArray("addresses");
        if(addresses !=null){
            String address = (String) addresses.get(0);
            transactionDetail.setTransactionId(transactionId);
            transactionDetail.setAmount(vout.getDouble("value"));
            transactionDetail.setAddress(address);
            transactionDetail.setType((byte) transactionDetailEnumType.Receive.ordinal());

            transactionDetailMapper.insert(transactionDetail);

        }
    }

    @Override
    public void syncTxDetailVin(JSONObject vin, Integer transactionId) {

        TransactionDetail transactionDetail = new TransactionDetail();
        transactionDetail.setTransactionId(transactionId);
        transactionDetail.setType((byte)transactionDetailEnumType.Send.ordinal());
        String txidVin = vin.getString("txid");
        Integer n = vin.getInteger("vout");

        try {
            JSONObject transactionJson = bitcoinJsonRpc.getRawTransaction(txidVin);
            JSONArray vouts = transactionJson.getJSONArray("vout");
            JSONObject vout = vouts.getJSONObject(n);
            Double amount = vout.getDouble("value");
            transactionDetail.setAmount(-amount);
            JSONObject scriptPubKey = vout.getJSONObject("scriptPubKey");
            JSONArray addresses = scriptPubKey.getJSONArray("addresses");
            if (addresses != null){
                String address = addresses.getString(0);
                transactionDetail.setAddress(address);
                transactionDetailMapper.insert(transactionDetail);
            }
        } catch (Throwable throwable) {
            throwable.printStackTrace();
        }

    }

    @Override
    public List<TransactionDetail> getByTransactionId(Integer transactionId) {
        List<TransactionDetail> transactionDetails = transactionDetailMapper.selectByTransactionId(transactionId);
        return transactionDetails;

    }
}
