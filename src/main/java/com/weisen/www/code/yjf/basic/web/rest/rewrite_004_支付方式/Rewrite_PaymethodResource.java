package com.weisen.www.code.yjf.basic.web.rest.rewrite_004_支付方式;

import java.net.URISyntaxException;
import java.util.Optional;

import com.weisen.www.code.yjf.basic.service.rewrite.dto.Rewrite_submitPayMethodDTO2;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.weisen.www.code.yjf.basic.service.dto.PaymethodDTO;
import com.weisen.www.code.yjf.basic.service.rewrite.Rewrite_PaymethodService;
import com.weisen.www.code.yjf.basic.service.rewrite.dto.Rewrite_submitPayMethodDTO;
import com.weisen.www.code.yjf.basic.util.Result;

import io.github.jhipster.web.util.ResponseUtil;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

/**
 * REST controller for managing {@link com.weisen.www.code.yjf.basic.domain.Paymethod}.
 */
@RestController
@RequestMapping("/api")
@Api(tags = "000-支付方式")
public class Rewrite_PaymethodResource {

    @Value("${jhipster.clientApp.name}")
    private String applicationName;

    private final Rewrite_PaymethodService paymethodService;


    public Rewrite_PaymethodResource(Rewrite_PaymethodService paymethodService ) {
        this.paymethodService = paymethodService;
    }

    /**
     * {@code POST  /paymethods} : Create a new paymethod.
     *
     * @param paymethodDTO the paymethodDTO to create.
     * @return the {@link ResponseEntity} with status {@code 201 (Created)} and with body the new paymethodDTO, or with status {@code 400 (Bad Request)} if the paymethod has already an ID.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PostMapping("/admin/insert-paymethods")
    @ApiOperation("保存支付方式")
    public ResponseEntity<?> createPaymethod(@RequestBody PaymethodDTO paymethodDTO) throws URISyntaxException {
        //名称,顺序,终端位置,线上还是线下,提示消息,创建者
        Result result = paymethodService.insert(paymethodDTO);
        return ResponseUtil.wrapOrNotFound(Optional.ofNullable(result));
    }
    @PostMapping("/admin/update-paymethods")
    @ApiOperation("修改支付方式")
    public ResponseEntity<?> updatePaymethod(@RequestBody PaymethodDTO paymethodDTO) throws URISyntaxException {
        //名称,顺序,终端位置,线上还是线下,提示消息,创建者
        Result result = paymethodService.updatePaymethod(paymethodDTO);
        return ResponseUtil.wrapOrNotFound(Optional.ofNullable(result));
    }

    @PostMapping("/get-paymethods")
    @ApiOperation("获取支付方式")
    public ResponseEntity<?> getPaymethods(@RequestBody Rewrite_submitPayMethodDTO rewrite_submitPayMethodDTO) throws URISyntaxException {
        //名称,顺序,终端位置,线上还是线下,提示消息,创建者
        Result result = paymethodService.getPayMethod(rewrite_submitPayMethodDTO);
//        Result result = paymethodService.getPayMethod(os,online);
        return ResponseUtil.wrapOrNotFound(Optional.ofNullable(result));
    }

    @PostMapping("/get-paymethods2")
    @ApiOperation("获取支付方式2")
    public ResponseEntity<?> getPaymethods2(@RequestBody Rewrite_submitPayMethodDTO2 rewrite_submitPayMethodDTO2) throws URISyntaxException {
        //名称,顺序,终端位置,线上还是线下,提示消息,创建者
        Result result = paymethodService.getPayMethod2(rewrite_submitPayMethodDTO2);
//        Result result = paymethodService.getPayMethod(os,online);
        return ResponseUtil.wrapOrNotFound(Optional.ofNullable(result));
    }
}
