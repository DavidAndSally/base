package com.jd.pop.base.thread;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.CountDownLatch;

/**
 * 多线程实例
 *
 * @author qiw-a
 * @date 2019/4/28
 */
public class TestThread {

    public boolean refreshCacheData(RankPO rankPo) {

        // 关键词列表
        List<String> keywordList = rankPo.getKeywordList();
        // 类目列表
        List<CategoryTypePO> categoryTypePOList = rankPo.getCategoryList();
        int count = keywordList.size() * categoryTypePOList.size();
        // 类目和关键词笛卡尔积集合
        List<String> cartesianProduct = new ArrayList<>();

        //取得一个倒计时器,关键词与类目的笛卡尔积
        CountDownLatch countDownLatch = new CountDownLatch(count);

        for (String key : keywordList) {
            for (CategoryTypePO categoryTypePo : categoryTypePOList) {

                // 生成关键字和类目最细力度的缓存数据
//                String cacheKey = CommodityUtil.getJimDbKey(key, categoryTypePo.getCate1stCode(),
//                        categoryTypePo.getCate2ndCode(), categoryTypePo.getCate3rdCode());
//                cartesianProduct.add(cacheKey);

                // 线程池执行runnable接口对象
                DemoExecutorService.execute(() -> {
                    try {
                        // 通过关键词、类目调用RPC接口，返回数据
//                        List<CommodityJimDbPO> commodityJimDbPOList =
//                                commodityJimRpcService.getIntelligentCommodityList(key, categoryTypePo, RECALL);
                        // 存储缓存数据
//                        commodityJimDbDao.setList(cacheKey, commodityJimDbPOList);
//                        log.info("{}， 缓存添加成功。", cacheKey);
                    } catch (Exception e) {
//                        log.error("CommodityJimServiceImpl[refreshCacheData] callable exception : ", e);
                    } finally {
                        //线程结束时，将计时器减一
                        countDownLatch.countDown();
                    }
                });
            }
        }

        try {
            //等待所有的子线程结束
            countDownLatch.await();
        } catch (Exception e) {
//            throw new TagServerException(e);
        }

        // 生成榜单全部关键字、类目的汇总缓存数据
//        String key = CommodityUtil.getJimDbKey(String.valueOf(rankPo.getId()));
//        collectCacheData(cartesianProduct, key);

        return true;
    }

}
