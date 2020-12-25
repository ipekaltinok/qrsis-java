package com.project.qrsis.controller;

import com.project.qrsis.dal.dto.AttendanceInDTO;
import com.project.qrsis.dal.dto.AttendanceStatusDTO;
import com.project.qrsis.dal.dto.AttendingRatioDTO;
import com.project.qrsis.dal.dto.ErrorMessage;
import com.project.qrsis.service.AttendanceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

import javax.imageio.ImageIO;
import javax.servlet.http.HttpServletResponse;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.List;

@RestController
@RequestMapping("/attendance")
public class AttendanceController {

    private final AttendanceService attendanceService;

    @Autowired
    public AttendanceController(AttendanceService attendanceService) {
        this.attendanceService = attendanceService;
    }

    @GetMapping
    public ResponseEntity<?> startAttendance(HttpServletResponse response,
                                             @RequestParam Integer lessonId) throws IOException {

        BufferedImage qrCode = attendanceService.startAttendance(lessonId);

        response.setContentType("image/jpg");

        ImageIO.write(qrCode, "jpg", response.getOutputStream());

        return ResponseEntity.ok().build();
    }

    @PostMapping
    public ResponseEntity<?> doAttendance(Authentication authentication,
                                          @RequestBody AttendanceInDTO dto) {

        attendanceService.doAttendance(dto, authentication);
        
        return ResponseEntity.ok().build();
    }

    @GetMapping("/status")
    public ResponseEntity<?> attendanceStatus(@RequestParam Integer lessonId) {

        Object object = attendanceService.attendanceStatus(lessonId);
        if (object instanceof ErrorMessage)
            return ResponseEntity.badRequest().body(object);
        else {

            AttendanceStatusDTO attendanceStatusDTO = (AttendanceStatusDTO) object;

            return ResponseEntity.ok(attendanceStatusDTO);
        }
    }

    @GetMapping("/ratio")
    public ResponseEntity<?> getAttendingRatio(@RequestParam Integer lessonId) {

        List<AttendingRatioDTO> attendingRatioOfLesson = attendanceService.getAttendingRatioOfLesson(lessonId);

        return ResponseEntity.ok(attendingRatioOfLesson);
    }
}
