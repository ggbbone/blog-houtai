package com.yzg.blog.common.utils;

import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.interfaces.Claim;
import com.auth0.jwt.interfaces.DecodedJWT;
import com.yzg.blog.common.exception.UnauthorizedException;
import org.apache.commons.lang3.StringUtils;

import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

/**
 * @author yangzg
 */
public class TokenUtils {
    /** token秘钥，请勿泄露，请勿随便修改 backups:JKKLJOoasdlfj */
    public static final String KEY = "JKKLJOoasdlfj";
    /** token 过期时间: 10天 */
    public static final int CALENDAR_FIELD = Calendar.DATE;
    public static final int CALENDAR_INTERVAL = 7;

    /**
     * JWT生成Token.<br/>
     *
     * JWT构成: header, payload, signature
     *
     * @param userId 登录成功后用户user_id, 参数user_id不可传空
     */
    public static String createToken(Long userId) throws UnauthorizedException {
        Date iatDate = new Date();
        // expire time
        Calendar nowTime = Calendar.getInstance();
        nowTime.add(CALENDAR_FIELD, CALENDAR_INTERVAL);
        Date expiresDate = nowTime.getTime();

        // header Map
        Map<String, Object> map = new HashMap<>();
        map.put("alg", "HS256");
        map.put("typ", "JWT");

        // build token
        // param backups {iss:Service, aud:APP}

        return JWT.create().withHeader(map)
                .withClaim("userId", null == userId ? null : userId.toString())
                .withIssuedAt(iatDate)
                .withExpiresAt(expiresDate)
                .sign(Algorithm.HMAC256(KEY));
    }

    /**
     * 解密Token
     *
     * @param token
     * @return
     * @throws Exception
     */
    public static Map<String, Claim> verifyToken(String token) {
        DecodedJWT jwt = null;
        try {
            JWTVerifier verifier = JWT.require(Algorithm.HMAC256(KEY)).build();
            jwt = verifier.verify(token);
        } catch (Exception e) {
            // e.printStackTrace();
            // token 校验失败, 抛出Token验证非法异常
            throw new UnauthorizedException("Token验证非法异常");
        }
        return jwt.getClaims();
    }

    /**
     * 根据Token获取user_id
     *
     * @param token
     * @return userId
     */
    public static Long getUserIdByToken(String token) {
        Map<String, Claim> claims = verifyToken(token);
        Claim userIdClaim = claims.get("userId");
        if (null == userIdClaim || StringUtils.isEmpty(userIdClaim.asString())) {
            // token 校验失败, 抛出Token验证非法异常
            throw new UnauthorizedException("Token验证非法异常");
        }
        return Long.valueOf(userIdClaim.asString());
    }

    public static Long getUserIdByTokenNoExp(String token) {
        try {
            Map<String, Claim> claims = verifyToken(token);
            Claim userIdClaim = claims.get("userId");
            if (null == userIdClaim || StringUtils.isEmpty(userIdClaim.asString())) {
                // token 校验失败, 抛出Token验证非法异常
                return null;
            }
            return Long.valueOf(userIdClaim.asString());
        } catch (Exception e) {
            return null;
        }

    }

}
