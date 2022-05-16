package lt.vu.interceptors;

import javax.interceptor.AroundInvoke;
import javax.interceptor.Interceptor;
import javax.interceptor.InvocationContext;
import java.io.Serializable;

@MyInterceptor
@Interceptor
public class MyInterceptorImpl implements Serializable {
    @AroundInvoke
    public Object logMethodInvocation(InvocationContext context) throws Exception {
        System.out.println("Called method: " + context.getMethod().getName());
        return context.proceed();
    }
}
