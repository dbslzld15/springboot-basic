package hello.core.web;

import hello.core.common.MyLogger;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.ObjectProvider;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;

@Controller
@RequiredArgsConstructor
public class LogDemoController {
    /**
     * ObjectProvider는 빈을 찾아오는 역할을 함
     * 따라서 해당 시점에 applicationContext.getBean("myLogger")을 해서 빈을 찾아옴
     * 만약 찾은 빈이 싱글톤 스코프라면 기존에 싱글톤으로 생성되어 있는 빈을 반환해주십니다.
     * 그런데 myLogger가 request 스코프 이기 때문에 request 스코프에 빈이 없으면 새로 생성하고, 이미 있으면 있는 빈을 반환
     * */
    private final LogDemoService logDemoService;
    private final ObjectProvider<MyLogger> myLoggerProvider;

    @RequestMapping("log-demo")
    @ResponseBody
    public String logDemo(HttpServletRequest request) {
        String requestURL = request.getRequestURL().toString();
        MyLogger myLogger = myLoggerProvider.getObject();
        //ObjectProvider 덕분에 ObjectProvider.getObject() 를 호출하는 시점까지 request scope 빈의 생성을 지연할 수 있다.
        myLogger.setRequestURL(requestURL);
        myLogger.log("controller test");
        logDemoService.logic("testId");

        return "OK";
    }
}
