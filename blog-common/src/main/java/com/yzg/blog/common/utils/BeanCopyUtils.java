package com.yzg.blog.common.utils;

import org.springframework.cglib.beans.BeanCopier;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * @author yangzg
 *
 * 封装BeanCopier的copy方法，添加缓存机制
 */
public class BeanCopyUtils {
    /**
     * 创建过的BeanCopier实例放到缓存中，下次可以直接获取，提升性能
     */
    private static final Map<String, BeanCopier> BEAN_COPIERS = new ConcurrentHashMap<>();

    /**
     * 该方法没有自定义Converter,简单进行常规属性拷贝
     *
     * @param source  源对象
     * @param target 目标对象
     */
    public static void copy(Object source, Object target) {
        if (target == null || source == null) {
            source = null;
            target = null;
            return;
        }
        String key = genKey(source.getClass(), target.getClass());
        BeanCopier copier;
        if (!BEAN_COPIERS.containsKey(key)) {
            copier = BeanCopier.create(source.getClass(), target.getClass(), false);
            BEAN_COPIERS.put(key, copier);
        } else {
            copier = BEAN_COPIERS.get(key);
        }
        copier.copy(source, target, null);
    }


    private static String genKey(Class<?> srcClazz, Class<?> destClazz) {
        return srcClazz.getName() + destClazz.getName();
    }
}
