package com.bw.bitcoinexplorer.service;

import com.bw.bitcoinexplorer.po.Block;
import com.github.pagehelper.Page;
import org.springframework.scheduling.annotation.Async;

import java.util.List;

public interface BlockService {

    String syncBlock(String blockhash);

    @Async
    void syncBlocks(String fromBlockhash);

    List<Block> getRecent();

    Page<Block> getWithPage(Integer page);

    Block getByBlockhash(String blockhash);
}
