package top.superwang.service.base.config;


import com.google.common.base.Predicates;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

import java.util.prefs.Preferences;

@Configuration
@EnableSwagger2
public class Swagger2Config {

    @Bean
    public Docket webApiConfig(){


        return new Docket(DocumentationType.SWAGGER_2)
                .groupName("webApi")
                .apiInfo(webApiInfo())
                .select()
                .paths(Predicates.and((PathSelectors.regex("/api/.*"))))
                .build();
                // 过滤所有api路径的接口
    }

    @Bean
    public Docket adminApiConfig(){
        // 后台的接口
        return new Docket(DocumentationType.SWAGGER_2)
                .groupName("adminApi")
                .apiInfo(adminApiInfo())
                .select()
                .paths(Predicates.and((PathSelectors.regex("/admin/.*"))))
                .build();
    }

    private ApiInfo webApiInfo(){
      return new ApiInfoBuilder()
                .title("前台的接口文档的标题")
                .description("这是稳定的描述前台的接口文档")
                .version("1.0.0")
                .contact(new Contact("wang","http://www.superwang.top","245801806@qq.com"))
                .build();
    };

    private ApiInfo adminApiInfo(){
        return new ApiInfoBuilder()
                .title("后台管理系统的接口文档的标题")
                .description("这是稳定的描述后台管理系统的接口文档")
                .version("1.0.0")
                .contact(new Contact("wang","http://www.superwang.top","245801806@qq.com"))
                .build();
    };


}
