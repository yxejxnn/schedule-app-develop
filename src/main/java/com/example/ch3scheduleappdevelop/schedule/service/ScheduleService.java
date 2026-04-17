package com.example.ch3scheduleappdevelop.schedule.service;

import com.example.ch3scheduleappdevelop.schedule.dto.*;
import com.example.ch3scheduleappdevelop.schedule.entity.Schedule;
import com.example.ch3scheduleappdevelop.schedule.repository.ScheduleRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class ScheduleService {

    private final ScheduleRepository scheduleRepository;

    @Transactional
    public ScheduleCreateResponseDto save(ScheduleCreateRequestDto requestDto) {
        Schedule schedule = new Schedule(
                requestDto.getTitle(),
                requestDto.getContent(),
                requestDto.getAuthorName()
        );
        Schedule savedSchedule = scheduleRepository.save(schedule);

        return new ScheduleCreateResponseDto(
                savedSchedule.getId(),
                savedSchedule.getTitle(),
                savedSchedule.getContent(),
                savedSchedule.getAuthorName(),
                savedSchedule.getCreatedAt()
        );
    }

    @Transactional(readOnly = true)
    public List<ScheduleGetAllResponseDto> getAll() {

        List<Schedule> scheduleList = scheduleRepository.findAll();

        return scheduleList.stream()
                .map(schedule -> new ScheduleGetAllResponseDto(
                        schedule.getId(),
                        schedule.getTitle(),
                        schedule.getAuthorName(),
                        schedule.getCreatedAt()
                ))
                .collect(Collectors.toList());
    }

    @Transactional(readOnly = true)
    public ScheduleGetOneResponseDto getOne(Long scheduleId) {
        Schedule schedule = scheduleRepository.findById(scheduleId).orElseThrow(
                () -> new IllegalStateException("해당 일정이 존재하지 않습니다.")
        );

        return new ScheduleGetOneResponseDto(
                schedule.getId(),
                schedule.getTitle(),
                schedule.getContent(),
                schedule.getAuthorName(),
                schedule.getCreatedAt(),
                schedule.getUpdatedAt()
        );
    }

    @Transactional
    public ScheduleUpdateResponseDto update(Long scheduleId, ScheduleUpdateRequestDto requestDto) {
        Schedule schedule = scheduleRepository.findById(scheduleId).orElseThrow(
                () -> new IllegalStateException("해당 일정이 존재하지 않습니다.")
        );

        schedule.update(requestDto.getTitle(), requestDto.getContent(), requestDto.getAuthorName());

        return new ScheduleUpdateResponseDto(
                schedule.getId(),
                schedule.getTitle(),
                schedule.getContent(),
                schedule.getAuthorName(),
                schedule.getUpdatedAt()
        );
    }
}
