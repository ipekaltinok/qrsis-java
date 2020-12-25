package com.project.qrsis.service;

import com.project.qrsis.dal.dto.AttendanceInDTO;
import com.project.qrsis.dal.dto.AttendanceStatusDTO;
import com.project.qrsis.dal.dto.AttendingRatioDTO;
import org.springframework.security.core.Authentication;

import java.awt.image.BufferedImage;
import java.util.List;

public interface AttendanceService {

    BufferedImage startAttendance(Integer lessonId);

    void doAttendance(AttendanceInDTO dto, Authentication authentication);

    Object attendanceStatus(Integer lessonId);

    List<AttendingRatioDTO> getAttendingRatioOfLesson(Integer lessonId);;
}
