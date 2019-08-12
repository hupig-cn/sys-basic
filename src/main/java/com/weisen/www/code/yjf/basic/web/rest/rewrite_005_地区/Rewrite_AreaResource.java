package com.weisen.www.code.yjf.basic.web.rest.rewrite_005_地区;

import com.weisen.www.code.yjf.basic.domain.Area;
import com.weisen.www.code.yjf.basic.service.rewrite.Rewrite_AreaService;
import com.weisen.www.code.yjf.basic.service.rewrite.dto.Rewrite_submitPayMethodDTO;
import io.github.jhipster.web.util.ResponseUtil;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.messaging.simp.SimpMessageSendingOperations;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api")
@Api(tags = "000-地区信息")
public class Rewrite_AreaResource {

    private final Rewrite_AreaService rewrite_AreaService;
    @Autowired
    private SimpMessageSendingOperations simpMessageSendingOperations;
    public Rewrite_AreaResource(Rewrite_AreaService rewrite_AreaService) {
        this.rewrite_AreaService = rewrite_AreaService;
    }

    @ApiOperation(value = "查询下级城市")
    @GetMapping("/get-next-area-pname/{pname}")
    public ResponseEntity<List<Area>> getNextAreaByPname(@PathVariable String pname){
    	List<Area> area = rewrite_AreaService.findNextAreaByPname(pname);
    	return ResponseEntity.ok().body(area);
    }
    @PostMapping("/send-message")
    @ApiOperation("发送消息")
    public ResponseEntity<?> send(@RequestBody Rewrite_submitPayMethodDTO rewrite_submitPayMethodDTO){
        HashMap<Object, Object> objectObjectHashMap = new HashMap<>();
        objectObjectHashMap.put("message","nihao,你好");
        objectObjectHashMap.put("type",rewrite_submitPayMethodDTO.getOs());
        simpMessageSendingOperations.convertAndSendToUser("3","/message",objectObjectHashMap);
        simpMessageSendingOperations.convertAndSendToUser("11","/message","这是频道2");
        return ResponseUtil.wrapOrNotFound(Optional.ofNullable(""));
    }
}
