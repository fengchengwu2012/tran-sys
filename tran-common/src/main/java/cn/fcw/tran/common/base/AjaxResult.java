package cn.fcw.tran.common.base;
import java.util.HashMap;

/**
 * 返回数据
 *
 * @author admin
 */
public class AjaxResult extends HashMap<String, Object> {

    private static final long serialVersionUID = 1L;

    @Override
    public AjaxResult put(String key, Object value) {
        super.put(key, value);
        return this;
    }

    public static AjaxResult error(String msg) {
        AjaxResult ajaxResult = new AjaxResult();
        ajaxResult.put("code",500);
        ajaxResult.put("msg", msg);
        return  ajaxResult;
    }

    public static AjaxResult ok() {
        AjaxResult ajaxResult = new AjaxResult();
        ajaxResult.put("code", 0);
        ajaxResult.put("msg", "success");
        return ajaxResult;
    }

    public static AjaxResult ok(Object  object) {
        AjaxResult ok = ok();
        ok.put("data",object);
        return ok;
    }
}
