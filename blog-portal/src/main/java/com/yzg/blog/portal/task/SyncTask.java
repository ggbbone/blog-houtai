package com.yzg.blog.portal.task;

import cn.hutool.core.collection.CollectionUtil;
import com.yzg.blog.portal.dao.ArticleDao;
import com.yzg.blog.portal.utils.RedisKeysUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

/**
 * @author yangzg
 */
@Component
@EnableScheduling
@Slf4j
public class SyncTask {

    @Autowired
    StringRedisTemplate redisTemplate;
    @Resource
    ArticleDao articleDao;

    /**
     * 同步文章浏览次数
     */
    @Scheduled(cron = "0 0/30 * * * *")
    void ArticleViewCountSync() {
        log.info("开始同步文章浏览次数");
        long start = System.currentTimeMillis();
        Set<String> members = redisTemplate.opsForSet().members(RedisKeysUtil.getSyncArticleViewCount());
        redisTemplate.delete(RedisKeysUtil.getSyncArticleViewCount());
        if (CollectionUtil.isEmpty(members)){
            log.info("没有需要同步浏览次数的文章");
            return;
        }
        log.info("需要同步浏览次数的文章数量：{}", members.size());
        List<Integer> articleIds = members.stream().map(Integer::parseInt).collect(Collectors.toList());
        articleIds.forEach(id->{
            String s = redisTemplate.opsForValue().get(RedisKeysUtil.getArticleViewCountKey(id));
            if (s != null) {
                int viewCount = Integer.parseInt(s);
                int result = articleDao.updateViewCountById(id, viewCount);
                if (result > 0) {
                    redisTemplate.delete(RedisKeysUtil.getArticleViewCountKey(id));
                }
            }
        });

        log.info("结束同步文章浏览次数,用时：{}", System.currentTimeMillis() - start);
    }

}
