package com.weisen.www.code.yjf.basic.web.rest.rewrite_007_收货信息;

import com.weisen.www.code.yjf.basic.service.rewrite.Rewrite_ReceivingService;
import com.weisen.www.code.yjf.basic.service.rewrite.dto.Rewrite_ReceivingDTO;
import com.weisen.www.code.yjf.basic.util.Result;
import io.github.jhipster.web.util.ResponseUtil;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

/**
 * REST controller for managing Receiving.
 */
@RestController
@RequestMapping("/api")
@Api(tags = "收货信息")
public class Rewrite_ReceivingResource {

    private final Rewrite_ReceivingService receivingService;

    public Rewrite_ReceivingResource(Rewrite_ReceivingService receivingService) {
        this.receivingService = receivingService;
    }

   @GetMapping("/get-user-address/{id}")
   @ApiOperation(value = "获取收获地址")
   public ResponseEntity<?> getUserAddress(@PathVariable Long id){
       Result result = receivingService.getUserAddress(id);
        return ResponseUtil.wrapOrNotFound(Optional.ofNullable(result));
   }
   @PostMapping("/insert-user-address")
   @ApiOperation(value = "新增收获地址")
   public ResponseEntity<?> insertUserAddress(@RequestBody Rewrite_ReceivingDTO rewrite_receivingDTO){
       Result result = receivingService.insertUserAddress(rewrite_receivingDTO);
       return ResponseUtil.wrapOrNotFound(Optional.ofNullable(result));
   }
    @PostMapping("/update-user-address")
    @ApiOperation(value = "修改收获地址")
    public ResponseEntity<?> updateUserAddress(@RequestBody Rewrite_ReceivingDTO rewrite_receivingDTO){
        Result result = receivingService.updateUserAddress(rewrite_receivingDTO);
        return ResponseUtil.wrapOrNotFound(Optional.ofNullable(result));
    }
    @GetMapping("/get-default-address/{id}")
    @ApiOperation("获取默认地址")
    public ResponseEntity<?> getDefaultAddress(@PathVariable Long id){
        Result result = receivingService.getDefaultAddress(id);
        return ResponseUtil.wrapOrNotFound(Optional.ofNullable(result));
    }
    @PostMapping("/get-address-detail")
    @ApiOperation("获取收货地址详情")
    public ResponseEntity<?> getAddress(@RequestBody Rewrite_ReceivingDTO rewrite_receivingDTO){
        Result result = receivingService.getOneUserAddress(rewrite_receivingDTO.getUserid(),rewrite_receivingDTO.getId());
        return ResponseUtil.wrapOrNotFound(Optional.ofNullable(result));
    }



//    @PostMapping("/set-default-address")
//    @ApiOperation("设置为默认地址")
//    public ResponseEntity<?> setDefaultAddress(@RequestBody Rewrite_ReceivingDTO rewrite_receivingDTO){
//        Result result = receivingService.setDefaultAddress(rewrite_receivingDTO.getId(),rewrite_receivingDTO.getUserid());
//        return ResponseUtil.wrapOrNotFound(Optional.ofNullable(result));
//    }
}
