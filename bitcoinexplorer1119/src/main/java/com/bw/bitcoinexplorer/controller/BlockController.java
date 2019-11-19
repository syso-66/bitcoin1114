package com.bw.bitcoinexplorer.controller;

import com.alibaba.fastjson.JSONObject;
import com.bw.bitcoinexplorer.Dto.PageDTO;
import com.bw.bitcoinexplorer.po.Block;
import com.bw.bitcoinexplorer.service.BlockService;
import com.bw.bitcoinexplorer.service.TransactionDetaiService;
import com.bw.bitcoinexplorer.service.TransactionService;
import com.github.pagehelper.Page;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/block")
public class BlockController {
    @Autowired
    private BlockService blockService;

    @Autowired
    private TransactionService transactionService;

    @Autowired
    private TransactionDetaiService transactionDetailService;

    @GetMapping("/getRecent")
    public List<JSONObject> getRecent(){

        List<Block> blocks = blockService.getRecent();
        List<JSONObject> blockJsons = blocks.stream().map(block -> {
            JSONObject blockJson = new JSONObject();
            blockJson.put("time", block.getTime());
            blockJson.put("height", block.getHeight());
            blockJson.put("miner", block.getMiner());
            blockJson.put("blockhash", block.getBlockhash());
            blockJson.put("size", block.getSizeondisk());
            return blockJson;
        }).collect(Collectors.toList());
        return blockJsons;
    }

    @GetMapping("/getWithPage")
    public PageDTO<JSONObject> getWithPage(@RequestParam(required = false, defaultValue = "1") Integer page){
        Page<Block> blocks = blockService.getWithPage(page);
        List<JSONObject> blockJsons = blocks.stream().map(block -> {
            JSONObject blockJson = new JSONObject();
            blockJson.put("time", block.getTime());
            blockJson.put("miner", block.getMiner());
            blockJson.put("height", block.getHeight());
            blockJson.put("blockhash", block.getBlockhash());
            blockJson.put("size", block.getSizeondisk());
            return blockJson;
        }).collect(Collectors.toList());

        PageDTO<JSONObject> pageDTO = new PageDTO<>();
        pageDTO.setList(blockJsons);
        pageDTO.setTotal(blocks.getTotal());
        pageDTO.setPageSize(blocks.getPageSize());
        pageDTO.setCurrentPage(blocks.getPageNum());

        return  pageDTO;
    }

    @GetMapping("/getInfoByHash")
    public JSONObject getInfoByHash(@RequestParam String blockhash){
        JSONObject blockInfoJson = new JSONObject();
        Block block = blockService.getByBlockhash(blockhash);
        blockInfoJson.put("blockhash",block.getBlockhash());
        blockInfoJson.put("confirmations",null);
        blockInfoJson.put("time",block.getTime());
        blockInfoJson.put("txSize",block.getTxsize());
        blockInfoJson.put("difficulty",block.getDifficulty());
        blockInfoJson.put("height",block.getHeight());
        blockInfoJson.put("miner",block.getMiner());
        blockInfoJson.put("merkleroot",block.getMerkleRoot());
        blockInfoJson.put("weight",block.getWeight());
        blockInfoJson.put("sizeOnDisk",block.getSizeondisk());
        blockInfoJson.put("version",block.getVersion());
        blockInfoJson.put("bits",block.getBits());
        blockInfoJson.put("nonce",block.getNonce());
        blockInfoJson.put("feeReward",block.getFeeReward());
        blockInfoJson.put("txVol",block.getTransactionVolume());
        blockInfoJson.put("blockReward",block.getBlockReward());

        return blockInfoJson;
    }

    @GetMapping("/getInfoByHeight")
    public JSONObject getInfoByHeight(@RequestParam Integer height){
        return null;
    }
}
