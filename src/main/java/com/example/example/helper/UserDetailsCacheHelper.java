package com.example.example.helper;

import org.springframework.cache.CacheManager;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.cache.SpringCacheBasedUserCache;
import org.springframework.stereotype.Component;

/**
 * @author fucheng on 2023/12/7
 */
@Component
public class UserDetailsCacheHelper {
    // 定义缓存对象
    private static SpringCacheBasedUserCache tokenCache;

    // 构造函数
    public UserDetailsCacheHelper(CacheManager cacheManager) {
        UserDetailsCacheHelper.tokenCache = new SpringCacheBasedUserCache(cacheManager.getCache("token"));
    }

    // 将用户信息从缓存中移除
    public static void removeUserCache(String username) {
        tokenCache.removeUserFromCache(username);
    }

    // 将用户信息放入缓存
    public static void putUserCache(UserDetails principal) {
        tokenCache.putUserInCache(principal);
    }

    // 从缓存中获取token
    public static UserDetails getUserCache(String username) {
        return tokenCache.getUserFromCache(username);
    }
}
