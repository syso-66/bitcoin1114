package com.bw.bitcoinexplorer.dao;

import com.bw.bitcoinexplorer.po.Block;
import com.github.pagehelper.Page;

import java.util.List;

public interface BlockMapper {
    int deleteByPrimaryKey(Integer blockId);

    int insert(Block record);

    int insertSelective(Block record);

    Block selectByPrimaryKey(Integer blockId);

    int updateByPrimaryKeySelective(Block record);

    int updateByPrimaryKey(Block record);

    List<Block> selectRecent();

    Page<Block> selectWithPage();

    Block selectByBlockhash(String blockhash);
}