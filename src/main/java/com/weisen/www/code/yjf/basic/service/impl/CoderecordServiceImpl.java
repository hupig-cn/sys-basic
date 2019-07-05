package com.weisen.www.code.yjf.basic.service.impl;

import com.weisen.www.code.yjf.basic.service.CoderecordService;
import com.weisen.www.code.yjf.basic.domain.Coderecord;
import com.weisen.www.code.yjf.basic.repository.CoderecordRepository;
import com.weisen.www.code.yjf.basic.service.dto.CoderecordDTO;
import com.weisen.www.code.yjf.basic.service.mapper.CoderecordMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

/**
 * Service Implementation for managing {@link Coderecord}.
 */
@Service
@Transactional
public class CoderecordServiceImpl implements CoderecordService {

    private final Logger log = LoggerFactory.getLogger(CoderecordServiceImpl.class);

    private final CoderecordRepository coderecordRepository;

    private final CoderecordMapper coderecordMapper;

    public CoderecordServiceImpl(CoderecordRepository coderecordRepository, CoderecordMapper coderecordMapper) {
        this.coderecordRepository = coderecordRepository;
        this.coderecordMapper = coderecordMapper;
    }

    /**
     * Save a coderecord.
     *
     * @param coderecordDTO the entity to save.
     * @return the persisted entity.
     */
    @Override
    public CoderecordDTO save(CoderecordDTO coderecordDTO) {
        log.debug("Request to save Coderecord : {}", coderecordDTO);
        Coderecord coderecord = coderecordMapper.toEntity(coderecordDTO);
        coderecord = coderecordRepository.save(coderecord);
        return coderecordMapper.toDto(coderecord);
    }

    /**
     * Get all the coderecords.
     *
     * @param pageable the pagination information.
     * @return the list of entities.
     */
    @Override
    @Transactional(readOnly = true)
    public Page<CoderecordDTO> findAll(Pageable pageable) {
        log.debug("Request to get all Coderecords");
        return coderecordRepository.findAll(pageable)
            .map(coderecordMapper::toDto);
    }


    /**
     * Get one coderecord by id.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    @Override
    @Transactional(readOnly = true)
    public Optional<CoderecordDTO> findOne(Long id) {
        log.debug("Request to get Coderecord : {}", id);
        return coderecordRepository.findById(id)
            .map(coderecordMapper::toDto);
    }

    /**
     * Delete the coderecord by id.
     *
     * @param id the id of the entity.
     */
    @Override
    public void delete(Long id) {
        log.debug("Request to delete Coderecord : {}", id);
        coderecordRepository.deleteById(id);
    }
}
