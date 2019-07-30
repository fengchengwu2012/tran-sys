package cn.fcw.tran.common.annotation;
import java.lang.annotation.*;

/**
 * 多数据源注解
 *
 * @author admin
 * @since 1.0.0
 */
@Target({ElementType.METHOD, ElementType.TYPE})
@Retention(RetentionPolicy.RUNTIME)
@Documented
@Inherited
public @interface DataSource {
    String value() default "";
}
