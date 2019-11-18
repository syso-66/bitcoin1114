package com.bw.bitcoinexplorer.client;

import com.alibaba.fastjson.JSONObject;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;

@FeignClient(name = "bitcoinRest", url = "${bitcoin.rest.address}")
public interface BitcoinRest {
/*
返回有关块链处理的各种状态信息。只支持将JSON作为输出格式。

chain：(字符串)当前网络名称(主、测试、再测试)
blocks：(数字)服务器中处理的当前块数
标题：(数字)我们验证的当前头数
最佳块：(字符串)当前最佳块的散列
困难：(数字)当前的困难
中间时间：(数字)在块链上最近的块之前的11个块的中间时间。
验证进展：(数字)验证进度的估计[0.1]
链：(字符串)活动链中的总工作量，以十六进制为单位
剪枝：(布尔值)如果要修剪块
修剪高度：(数字)可用的最高块
软叉子：(阵列)正在进行中的软叉子状态
BIP 9_软叉子：(对象)BIP 9软叉子在进行中的状态
 */
    @GetMapping("/rest/chaininfo.json")
    JSONObject getChainInfo();

    //给定块哈希：向上返回块头的数量。如果块不存在或不在活动链中，则返回空。
    @GetMapping("/rest/headers/{count}/{blockhash}.json")
    List<JSONObject> getBlockHeaders(@PathVariable Integer count, @PathVariable String blockhash);
//给定块哈希：以二进制、十六进制编码的二进制或JSON格式返回块。如果块不存在，则用404进行响应。
//
//HTTP请求和响应都是完全在内存中处理的，从而使每个请求的最大内存使用量至少达到2.66MB(1MB最大块，加上十六进制编码)。
//
//使用/notxDetails/选项，JSON响应将只包含事务哈希，而不包含完整的事务详细信息。该选项只影响JSON响应。
    @GetMapping("/rest/block/notxdetails/{blockhash}.json")
    JSONObject getBlockNoTxDetails(@PathVariable String blockhash);

    @GetMapping("/rest/block/{blockhash}.json")
    JSONObject getBlockInfo(@PathVariable String blockhash);
//给定一个事务哈希：以二进制、十六进制编码的二进制或JSON格式返回事务。
    @GetMapping("/rest/tx/{txhash}.json")
    JSONObject getTransaction(@PathVariable String txhash);
//给定高度：在提供的高度处返回最佳块链中块的散列。
    @GetMapping("/rest/blockhashbyheight/{height}.json")
    JSONObject getBlockhashByHeight(@PathVariable Integer height);
//返回有关TX内存池的各种信息。只支持将JSON作为输出格式。
//
//LOAD：(布尔值)如果内存池已完全加载
//大小：(数字)TX内存池中的事务数
//字节：(数字)TX内存池的大小(以字节为单位)
//用法：(数字)TX内存池总使用量
//最大内存池：(数字)内存池的最大内存使用量(以字节为单位)
//备忘录费用：(数字)接受TX的最低发热率(每KB BTC)
    @GetMapping("/rest/mempool/info.json")
    JSONObject getMempoolInfo();

    @GetMapping("/rest/mempool/contents.json")
    JSONObject getMempoolContents();
//getutxo命令允许查询给定一组输出的UTXO集。输入和输出序列化见BIP 64：
    @GetMapping("/rest/getutxos/{txid}-{n}.json")
    JSONObject getUTXO(@PathVariable String txid, @PathVariable Integer n);
}
