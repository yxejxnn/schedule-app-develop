package com.example.ch3scheduleappdevelop.schedule.service;

import com.example.ch3scheduleappdevelop.common.exception.ScheduleNotFoundException;
import com.example.ch3scheduleappdevelop.common.exception.UserNotFoundException;
import com.example.ch3scheduleappdevelop.schedule.dto.*;
import com.example.ch3scheduleappdevelop.schedule.entity.Schedule;
import com.example.ch3scheduleappdevelop.schedule.repository.ScheduleRepository;
import com.example.ch3scheduleappdevelop.user.entity.User;
import com.example.ch3scheduleappdevelop.user.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class ScheduleService {

    private final ScheduleRepository scheduleRepository;
    private final UserRepository userRepository;

    // 일정 생성
    @Transactional
    public ScheduleCreateResponseDto save(ScheduleCreateRequestDto requestDto) {
        User user = userRepository.findById(requestDto.getUserId()).orElseThrow(
                UserNotFoundException::new
        );

        Schedule schedule = new Schedule(
                requestDto.getTitle(),
                requestDto.getContent(),
                user
        );
        Schedule savedSchedule = scheduleRepository.save(schedule);

        return ScheduleCreateResponseDto.from(savedSchedule);
    }

    // 일정 다건 조회
    @Transactional(readOnly = true)
    public List<ScheduleGetAllResponseDto> getAll() {

        List<Schedule> scheduleList = scheduleRepository.findAll();

        return scheduleList.stream()
                .map(schedule -> ScheduleGetAllResponseDto.from(schedule))
                .collect(Collectors.toList());
    }

    // 일정 단건 조회
    @Transactional(readOnly = true)
    public ScheduleGetOneResponseDto getOne(Long scheduleId) {
        Schedule schedule = scheduleRepository.findById(scheduleId).orElseThrow(
                ScheduleNotFoundException::new
        );

        return ScheduleGetOneResponseDto.from(schedule);
    }

    // 일정 수정
    @Transactional
    public ScheduleUpdateResponseDto update(Long scheduleId, ScheduleUpdateRequestDto requestDto) {
        Schedule schedule = scheduleRepository.findById(scheduleId).orElseThrow(
                ScheduleNotFoundException::new
        );

        schedule.update(requestDto.getTitle(), requestDto.getContent());

        return ScheduleUpdateResponseDto.from(schedule);
    }

    // 일정 삭제
    @Transactional
    public void delete(Long scheduleId) {
        boolean existence = scheduleRepository.existsById(scheduleId);
        if (!existence) {
            throw new ScheduleNotFoundException();
        }
        scheduleRepository.deleteById(scheduleId);
    }

    // 일정 페이지 조회
    @Transactional(readOnly = true)
    public Page<SchedulePageResponseDto> getPage(int page, int size) {
        // 수정일 기준 내림차순 정렬, 기본 페이지 크기 10
        Pageable pageable = PageRequest.of(page, size, Sort.by(Sort.Direction.DESC, "updatedAt"));
        return scheduleRepository.findAll(pageable)
                .map(schedule -> SchedulePageResponseDto.from(schedule));
    }
}
