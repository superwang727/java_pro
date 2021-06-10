package top.superwang.service.edu.feign;

import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import top.superwang.common.base.result.R;

@FeignClient("service-oss")
public interface OssFileService {

    @GetMapping("/admin/oss/file/test")
    R test();


    @DeleteMapping("/admin/oss/file/removeAvatar")
    R removeAvatar(@RequestBody String url);


}
