package com.weisen.www.code.yjf.basic.web.rest.rewrite_004_支付方式;

import com.weisen.www.code.yjf.basic.domain.Paymethod;
import com.weisen.www.code.yjf.basic.repository.rewrite.Rewrite_PaymethodRepository;
import com.weisen.www.code.yjf.basic.service.dto.PaymethodDTO;
import com.weisen.www.code.yjf.basic.service.rewrite.Rewrite_PaymethodService;
import com.weisen.www.code.yjf.basic.util.Result;
import io.github.jhipster.web.util.ResponseUtil;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URISyntaxException;
import java.util.List;
import java.util.Optional;

/**
 * REST controller for managing {@link com.weisen.www.code.yjf.basic.domain.Paymethod}.
 */
@RestController
@RequestMapping("/api")
@Api(tags = "支付方式")
public class Rewrite_PaymethodResource {

    private final Logger log = LoggerFactory.getLogger(Rewrite_PaymethodResource.class);

    private static final String ENTITY_NAME = "basicPaymethod";

    @Value("${jhipster.clientApp.name}")
    private String applicationName;

    private final Rewrite_PaymethodService paymethodService;

    private final Rewrite_PaymethodRepository rewrite_paymethodRepository;

    public Rewrite_PaymethodResource(Rewrite_PaymethodService paymethodService,
       Rewrite_PaymethodRepository rewrite_paymethodRepository) {
        this.rewrite_paymethodRepository = rewrite_paymethodRepository;
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

    @GetMapping("/get-paymethods")
    @ApiOperation("获取支付方式")
    public ResponseEntity<?> getPaymethods(String os,Boolean online) throws URISyntaxException {
        //名称,顺序,终端位置,线上还是线下,提示消息,创建者
        List<Paymethod> all = rewrite_paymethodRepository.findPaymethod();
//        Result result = paymethodService.getPayMethod(os,online);
        return ResponseUtil.wrapOrNotFound(Optional.ofNullable(all));
    }
}
