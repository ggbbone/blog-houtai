package com.yzg.blog.portal.component;

import com.yzg.blog.portal.dao.BmsArticleInfoDao;
import com.yzg.blog.portal.service.BmsArticleService;
import com.yzg.blog.portal.utils.RedisKeysUtils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.util.ArrayList;

/**
 * Created by yzg on 2019/12/31
 *
 * 定时任务
 */
@Component
@Slf4j
public class ScheduledTasks {
    @Autowired
    StringRedisTemplate redisTemplate;
    @Autowired
    BmsArticleInfoDao articleInfoDao;
    @Autowired
    BmsArticleService articleService;

    /**
     * 从redis同步文章浏览次数到数据库
     */
    @Scheduled(fixedDelay = 2 * 60 * 60 * 1000)
    void changeArticle() {
        log.info("开始同步文章浏览次数");
        String temp = "TEMP_ARTICLE_VIEW_COUNT";
        //复制待同步队列
        redisTemplate.opsForSet().unionAndStore(RedisKeysUtils.getSyncArticleViewCount(), new ArrayList<>(), temp);
        //移除待同步队列
        redisTemplate.delete(RedisKeysUtils.getSyncArticleViewCount());
        //遍历待同步队列
        do {
            String pop = redisTemplate.opsForSet().pop(temp);
            if (pop != null) {
                int articleId = Integer.parseInt(pop);
                //从redis获取浏览次数
                String viewCount = redisTemplate.opsForValue().get(RedisKeysUtils.getArticleViewCountKey(articleId));
                //清零
                redisTemplate.delete(RedisKeysUtils.getArticleViewCountKey(articleId));
                if (viewCount != null) {
                    int count = Integer.parseInt(viewCount);
                    try {
                        articleInfoDao.addViewCount(articleId, count);
                    } catch (Exception ignored) {//如果持久化到数据库出现异常，回滚数据到redis
                        articleService.addArticleViewCount(articleId, count);
                    }
                }
            }else {
                break;
            }
        } while (true);

        redisTemplate.delete(temp);
    }
}
