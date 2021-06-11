package top.superwang.service.edu.feign.fallback;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import top.superwang.common.base.result.R;
import top.superwang.service.edu.feign.OssFileService;

@Service
@Slf4j
public class OssFileServiceFallback implements OssFileService {
    @Override
    public R test() {
        return R.error();
    }

    @Override
    public R removeAvatar(String url) {
      log.info("熔断保护");
        return R.error();
    }
}
