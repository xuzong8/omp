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

/**
 * @author WLB
 * @ClassName IpFilter
 * @Description  过滤器 过滤非法IP,不合法IP不允许请求任何微服务
 * @date 2020/8/22 13:44
 */
@Component
public class IpFilter extends ZuulFilter {

    @Value("${illegalIp}")
    private String illegalIp;

    @Override
    public String filterType() {
        // pre 在业务执行爱之前,执行的方法
        // route 在业务执行是,执行的方法
        // post 在业务执行后,执行的方法
        // error 在业务执行出错时,执行的方法
        return "pre";
    }

    @Override
    public int filterOrder() {
        // 如果有多个filter 该方法的返回值确定该filter执行的顺序 返回值越小,优先级越高
        return 0;
    }

    @Override
    public boolean shouldFilter() {
        // 该方法确定当前过滤器是否生效 返回true生效 返回false不生效
        return true;
    }

    @Override
    public Object run() throws ZuulException {
        // 过滤器的核心部分, 要执行主要业务，都在这编写

        System.out.println("进入非法IP过滤器......");

        // 使用zuul 组件中提供的请求上下文对象获取请求上下文对象
        RequestContext requestContext = RequestContext.getCurrentContext();

        // 使用上下文对象获取 HttpServletRequest
        HttpServletRequest request = requestContext.getRequest();

        // 使用上下文对象获取 HttpServletResponse
        HttpServletResponse response = requestContext.getResponse();

        // 获取IP地址
        String remoteAddr = request.getRemoteAddr();

        // System.out.println("要禁用的IP为:" + illegalIp);
        System.out.println("访问的IP为:" + remoteAddr);

        // 判断IP地址是否合法
        // remoteAddr.endsWith("107")|| remoteAddr.endsWith("35")||remoteAddr.endsWith("32")||remoteAddr.endsWith("50")
        if (illegalIp.contains(remoteAddr)) {
            /*requestContext.setResponseStatusCode(HttpStatus.SC_FORBIDDEN);
            requestContext.setResponseBody("FORBIDDEN");*/
            try {
                response.setCharacterEncoding("UTF-8");
                response.sendError(HttpStatus.SC_FORBIDDEN,"你的IP禁止访问我的服务");
				return null;
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return null;
    }
}
