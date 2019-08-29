package com.weisen.www.code.yjf.basic.service.impl;

import com.weisen.www.code.yjf.basic.service.StatisticsService;
import com.weisen.www.code.yjf.basic.domain.Statistics;
import com.weisen.www.code.yjf.basic.repository.StatisticsRepository;
import com.weisen.www.code.yjf.basic.service.dto.StatisticsDTO;
import com.weisen.www.code.yjf.basic.service.mapper.StatisticsMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.LinkedList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

/**
 * Service Implementation for managing Statistics.
 */
@Service
@Transactional
public class StatisticsServiceImpl implements StatisticsService {

    private final Logger log = LoggerFactory.getLogger(StatisticsServiceImpl.class);

    private final StatisticsRepository statisticsRepository;

    private final StatisticsMapper statisticsMapper;

    public StatisticsServiceImpl(StatisticsRepository statisticsRepository, StatisticsMapper statisticsMapper) {
        this.statisticsRepository = statisticsRepository;
        this.statisticsMapper = statisticsMapper;
    }

    /**
     * Save a statistics.
     *
     * @param statisticsDTO the entity to save
     * @return the persisted entity
     */
    @Override
    public StatisticsDTO save(StatisticsDTO statisticsDTO) {
        log.debug("Request to save Statistics : {}", statisticsDTO);
        Statistics statistics = statisticsMapper.toEntity(statisticsDTO);
        statistics = statisticsRepository.save(statistics);
        return statisticsMapper.toDto(statistics);
    }

    /**
     * Get all the statistics.
     *
     * @return the list of entities
     */
    @Override
    @Transactional(readOnly = true)
    public List<StatisticsDTO> findAll() {
        log.debug("Request to get all Statistics");
        return statisticsRepository.findAll().stream()
            .map(statisticsMapper::toDto)
            .collect(Collectors.toCollection(LinkedList::new));
    }


    /**
     * Get one statistics by id.
     *
     * @param id the id of the entity
     * @return the entity
     */
    @Override
    @Transactional(readOnly = true)
    public Optional<StatisticsDTO> findOne(Long id) {
        log.debug("Request to get Statistics : {}", id);
        return statisticsRepository.findById(id)
            .map(statisticsMapper::toDto);
    }

    /**
     * Delete the statistics by id.
     *
     * @param id the id of the entity
     */
    @Override
    public void delete(Long id) {
        log.debug("Request to delete Statistics : {}", id);
        statisticsRepository.deleteById(id);
    }
}
