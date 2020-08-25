package zuul.filter;

import com.netflix.zuul.ZuulFilter;
import com.netflix.zuul.context.RequestContext;
import com.netflix.zuul.exception.ZuulException;
import org.apache.http.HttpStatus;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Enumeration;

/**
 * @author WLB
 * @ClassName IllegalCharFilter
 * @Description TODO
 * @date 2020/8/22 14:33
 */
@Component
public class IllegalCharFilter extends ZuulFilter {
    @Value("${illegalCharacter}")
    private String illegalChar;

    @Override
    public String filterType() {
        return "pre";
    }

    @Override
    public int filterOrder() {
        return 5;
    }

    @Override
    public boolean shouldFilter() {
        return true;
    }

    @Override
    public Object run() throws ZuulException {
        // 过滤器的核心部分, 要执行主要业务，都在这编写

        System.out.println("进入非法字符过滤器......");

        // 使用zuul 组件中提供的请求上下文对象获取请求上下文对象
        RequestContext requestContext = RequestContext.getCurrentContext();

        // 使用上下文对象获取 HttpServletRequest
        HttpServletRequest request = requestContext.getRequest();

        // 使用上下文对象获取 HttpServletResponse
        HttpServletResponse response = requestContext.getResponse();

        // http://172.16.22.47:8888/os/?a=1&b=2&c=sb&d=tmd
        Enumeration<String> parameterNames = request.getParameterNames();

        // 分割非法字符串,使用 , 隔开
        String[] splitCharArray = illegalChar.split(",");
        // 循环获取每一个参数名称
        while (parameterNames.hasMoreElements()) {
            // 第1次循环a 第2次循环b ...最后d
            String paramName = parameterNames.nextElement();

            // 根据参数名称获取值
            String paramValue = request.getParameter(paramName);

            // 判断是否含有非法字符 wlb,tmd,nnd,mmd,sb(含有)
            for (String illegalC:splitCharArray) {
                if (paramValue.contains(illegalC)) {
                    try {
                        response.setCharacterEncoding("UTF-8");
                        response.sendError(HttpStatus.SC_FORBIDDEN,"你的请求中含有非法字符,禁止访问我的服务");
                         return null;
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
            }

        }
        return null;
    }
}
