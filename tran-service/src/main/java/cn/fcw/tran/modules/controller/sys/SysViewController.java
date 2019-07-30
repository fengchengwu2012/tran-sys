package cn.fcw.tran.modules.controller.sys;
import cn.fcw.tran.modules.controller.BaseController;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
/**
 * 系统页面视控制
 * @author admin
 */
@Controller
public class SysViewController  extends BaseController {

    /**
     * Vue路由path控制
     * @param module   modules/sys/user.html
     * @param url
     * @return
     */
    @RequestMapping("modules/{module}/{url}.html")
    public String module(@PathVariable("module") String module, @PathVariable("url") String url) {
        return "modules/" + module + "/" + url;
    }

    @RequestMapping("login.html")
    public String login() {
        return "login";
    }

    @RequestMapping(value = {"/", "index.html"})
    public String index() {
        return "index";
    }

    @RequestMapping("expand.html")
    public String expand() {
        return "expand";
    }

    @RequestMapping("main.html")
    public String main() {
        return "main";
    }

    @RequestMapping("404.html")
    public String notFound() {
        return "error/404";
    }

    @RequestMapping("403.html")
    public String noAuth() {
        return "error/403";
    }

    @RequestMapping("500.html")
    public String error() {
        return "error/500";
    }

    @RequestMapping("/swagger/swagger-ui.html")
    public String swagger(){
      return  redirect("/swagger-ui.html");
    }
}
