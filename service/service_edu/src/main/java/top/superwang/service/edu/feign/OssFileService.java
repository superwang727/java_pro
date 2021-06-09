package top.superwang.service.edu.feign;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import top.superwang.common.base.result.R;

@FeignClient("service-oss")
public interface OssFileService {

    @GetMapping("/admin/oss/file/test")
    R test();


}
