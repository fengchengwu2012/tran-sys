package cn.fcw.tran.modules.sys.shiro;
import cn.fcw.tran.common.exception.CustomException;
import cn.fcw.tran.modules.sys.entity.SysUserEntity;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.crypto.hash.SimpleHash;
import org.apache.shiro.session.Session;
import org.apache.shiro.subject.Subject;

/**
 * Shiro工具类
 *
 * @author  fengchengwu
 */
public class ShiroUtils {

    /**  加密算法 */
     final static String HASH_ALGORITHM_NAME = "SHA-256";
    /**  循环次数 */
    final static int HASH_ITERATIONS = 16;

    public static String sha256(String password, String salt) {
        return new SimpleHash(HASH_ALGORITHM_NAME, password, salt, HASH_ITERATIONS).toString();
    }

    private static Session getSession() {
        return SecurityUtils.getSubject().getSession();
    }

    public static Subject getSubject() {
        return SecurityUtils.getSubject();
    }

    public static SysUserEntity getUserEntity() {
        return (SysUserEntity) SecurityUtils.getSubject().getPrincipal();
    }

    public static Long getUserId() {
        return getUserEntity().getUserId();
    }

    public static void setSessionAttribute(Object key, Object value) {
        getSession().setAttribute(key, value);
    }

    private static Object getSessionAttribute(Object key) {
        return getSession().getAttribute(key);
    }

    public static boolean isLogin() {
        return SecurityUtils.getSubject().getPrincipal() != null;
    }

    public static void logout() {
        SecurityUtils.getSubject().logout();
    }

    public static String getKaptcha(String key) {
        Object k = getSessionAttribute(key);
        if (k == null) {
            throw new CustomException("验证码已失效");
        }
        getSession().removeAttribute(key);
        return k.toString();
    }

}
