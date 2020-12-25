package com.project.qrsis.controller;

import com.project.qrsis.dal.model.*;
import com.project.qrsis.dal.repository.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.sql.Timestamp;

@RestController
public class RefreshController {

    private final LessonCalendarRepository lessonCalendarRepository;
    private final LessonRepository lessonRepository;
    private final StudentAttendanceRepository studentAttendanceRepository;
    private final StudentLessonRepository studentLessonRepository;
    private final StudentRepository studentRepository;
    private final TeacherLessonRepository teacherLessonRepository;
    private final TeacherRepository teacherRepository;
    private final TeacherRoleRepository teacherRoleRepository;

    @Autowired
    public RefreshController(LessonCalendarRepository lessonCalendarRepository,
                             LessonRepository lessonRepository,
                             StudentAttendanceRepository studentAttendanceRepository,
                             StudentLessonRepository studentLessonRepository,
                             StudentRepository studentRepository,
                             TeacherLessonRepository teacherLessonRepository,
                             TeacherRepository teacherRepository,
                             TeacherRoleRepository teacherRoleRepository) {

        this.lessonCalendarRepository = lessonCalendarRepository;
        this.lessonRepository = lessonRepository;
        this.studentAttendanceRepository = studentAttendanceRepository;
        this.studentLessonRepository = studentLessonRepository;
        this.studentRepository = studentRepository;
        this.teacherLessonRepository = teacherLessonRepository;
        this.teacherRepository = teacherRepository;
        this.teacherRoleRepository = teacherRoleRepository;
    }

    @GetMapping("refresh")
    public ResponseEntity<?> refreshDB() {

        lessonCalendarRepository.deleteAll();
        lessonRepository.deleteAll();
        studentAttendanceRepository.deleteAll();
        studentLessonRepository.deleteAll();
        studentRepository.deleteAll();
        teacherLessonRepository.deleteAll();
        teacherRepository.deleteAll();
        teacherRoleRepository.deleteAll();

        Student student1 = new Student();
        student1.setStudentNumber("1907");
        student1.setName("İpek Altınok");
        student1.setPassword("$2a$10$k07hO4KxWx9MiV4qJHN38.p0uaLCre0CKxeZxKdmbVmnN2MtCKgFe");
        student1.setEmail("ipek@student.com");
        student1 = studentRepository.save(student1);

        Student student2 = new Student();
        student2.setStudentNumber("1901");
        student2.setName("Hüseyin Sade");
        student2.setPassword("$2y$12$.P4y19z/CJ340VLnPaycNuHGeC5Dus0/FyzolYJGa8iSP0D/cko86");
        student2.setEmail("huseyin@student.com");
        student2 = studentRepository.save(student2);

        Student student3 = new Student();
        student3.setStudentNumber("1902");
        student3.setName("Celal Demir");
        student3.setPassword("$2a$10$nti/iCuTgTKWUG16BALSOOzinekYXc75DxinW/LGLYnHZHgpzLaoK");
        student3.setEmail("celal@student.com");
        student3 = studentRepository.save(student3);

        Student student4 = new Student();
        student4.setStudentNumber("1904");
        student4.setName("Barlas Kılcı");
        student4.setPassword("$2a$10$AXu6u6UUUfE3y9g4KLiFOuL7DFw/248lY3lOzcb7u.UVSEXX0IvyW");
        student4.setEmail("barlas@student.com");
        student4 = studentRepository.save(student4);

        Student student5 = new Student();
        student5.setStudentNumber("1905");
        student5.setName("Uras Kılcı");
        student5.setPassword("$2a$10$ig67zl3vymt30HcR3oQI8OKRHCFEzMvbrukgIcrcxAhY2P8tE5IVm");
        student5.setEmail("uras@student.com");
        student5 = studentRepository.save(student5);

        Student student6 = new Student();
        student6.setStudentNumber("1903");
        student6.setName("Şehit Kemikkıran");
        student6.setPassword("$2a$10$dRt1FErxpocV6/Wlqb4Dyum5W16oI6/CcX/Vs6uSaBlTqy8EDhuEG");
        student6.setEmail("sehit@student.com");
        student6 = studentRepository.save(student6);

        Student student7 = new Student();
        student7.setStudentNumber("1906");
        student7.setName("Yağız Altınok");
        student7.setPassword("$2a$10$mB66J318LHH4MHg5Gs1op.KqE/.bwFdmC8gvFsH7e8EAoJElc.FCa");
        student7.setEmail("yagiz@student.com");
        student7 = studentRepository.save(student7);

        Student student8 = new Student();
        student8.setStudentNumber("1908");
        student8.setName("Türker Altınok");
        student8.setPassword("$2y$12$K17WDNsjKTlBU/NtRfYY8.rBMA2KEWn8aaqzoGyxticwQOVhJfXIy");
        student8.setEmail("turker@student.com");
        student8 = studentRepository.save(student8);

        Teacher teacher1 = new Teacher();
        teacher1.setName("Admin");
        teacher1.setEmail("admin@admin.com");
        teacher1.setPassword("$2a$10$qwGQE6PcsrhMgGJYdIBt7.c2.u/JtlN.2Py0BlcvkAvfd8ISdr4Ji");
        teacher1 = teacherRepository.save(teacher1);

        Teacher teacher2 = new Teacher();
        teacher2.setName("Şehit Kemikkıran");
        teacher2.setEmail("sehit@teacher.com");
        teacher2.setPassword("$2a$10$2JDm.NU5oTgDIjjPLZVcb.VFjbAcoP0BU9I9ajQbSiaa/LefyjXF6");
        teacher2 = teacherRepository.save(teacher2);

        Teacher teacher3 = new Teacher();
        teacher3.setName("İpek Altınok");
        teacher3.setEmail("ipek@teacher.com");
        teacher3.setPassword("$2a$10$BQUkrhml5DnAGSN.gkfcbug.DqeJnrTff5TINiM0OMj1HYc1RfUSK");
        teacher3 = teacherRepository.save(teacher3);

        TeacherRole teacherRole = new TeacherRole();
        teacherRole.setRole("ADMIN");
        teacherRole.setTeacherId(teacher1.getId());
        teacherRole = teacherRoleRepository.save(teacherRole);

        Lesson lesson1 = new Lesson();
        lesson1.setName("Nesneye Dayalı Programlama");
        lesson1 = lessonRepository.save(lesson1);

        Lesson lesson2 = new Lesson();
        lesson2.setName("Web Programlama - A");
        lesson2 = lessonRepository.save(lesson2);

        Lesson lesson3 = new Lesson();
        lesson3.setName("Web Programlama - B");
        lesson3 = lessonRepository.save(lesson3);

        StudentLesson studentLesson1 = new StudentLesson();
        studentLesson1.setLessonId(lesson1.getId());
        studentLesson1.setStudentId(student1.getId());
        studentLesson1 = studentLessonRepository.save(studentLesson1);

        StudentLesson studentLesson2 = new StudentLesson();
        studentLesson2.setLessonId(lesson1.getId());
        studentLesson2.setStudentId(student2.getId());
        studentLesson2 = studentLessonRepository.save(studentLesson2);

        StudentLesson studentLesson3 = new StudentLesson();
        studentLesson3.setLessonId(lesson1.getId());
        studentLesson3.setStudentId(student3.getId());
        studentLesson3 = studentLessonRepository.save(studentLesson3);

        StudentLesson studentLesson4 = new StudentLesson();
        studentLesson4.setLessonId(lesson1.getId());
        studentLesson4.setStudentId(student6.getId());
        studentLesson4 = studentLessonRepository.save(studentLesson4);

        StudentLesson studentLesson5 = new StudentLesson();
        studentLesson5.setLessonId(lesson1.getId());
        studentLesson5.setStudentId(student5.getId());
        studentLesson5 = studentLessonRepository.save(studentLesson5);

        StudentLesson studentLesson6 = new StudentLesson();
        studentLesson6.setLessonId(lesson2.getId());
        studentLesson6.setStudentId(student1.getId());
        studentLesson6 = studentLessonRepository.save(studentLesson6);

        StudentLesson studentLesson7 = new StudentLesson();
        studentLesson7.setLessonId(lesson2.getId());
        studentLesson7.setStudentId(student2.getId());
        studentLesson7 = studentLessonRepository.save(studentLesson7);

        StudentLesson studentLesson8 = new StudentLesson();
        studentLesson8.setLessonId(lesson2.getId());
        studentLesson8.setStudentId(student3.getId());
        studentLesson8 = studentLessonRepository.save(studentLesson8);

        StudentLesson studentLesson9 = new StudentLesson();
        studentLesson9.setLessonId(lesson2.getId());
        studentLesson9.setStudentId(student6.getId());
        studentLesson9 = studentLessonRepository.save(studentLesson9);

        StudentLesson studentLesson10 = new StudentLesson();
        studentLesson10.setLessonId(lesson2.getId());
        studentLesson10.setStudentId(student7.getId());
        studentLesson10 = studentLessonRepository.save(studentLesson10);

        StudentLesson studentLesson11 = new StudentLesson();
        studentLesson11.setLessonId(lesson3.getId());
        studentLesson11.setStudentId(student1.getId());
        studentLesson11 = studentLessonRepository.save(studentLesson11);

        StudentLesson studentLesson12 = new StudentLesson();
        studentLesson12.setLessonId(lesson3.getId());
        studentLesson12.setStudentId(student2.getId());
        studentLesson12 = studentLessonRepository.save(studentLesson12);

        StudentLesson studentLesson13 = new StudentLesson();
        studentLesson13.setLessonId(lesson3.getId());
        studentLesson13.setStudentId(student3.getId());
        studentLesson13 = studentLessonRepository.save(studentLesson13);

        StudentLesson studentLesson14 = new StudentLesson();
        studentLesson14.setLessonId(lesson3.getId());
        studentLesson14.setStudentId(student6.getId());
        studentLesson14 = studentLessonRepository.save(studentLesson14);

        StudentLesson studentLesson15 = new StudentLesson();
        studentLesson15.setLessonId(lesson3.getId());
        studentLesson15.setStudentId(student8.getId());
        studentLesson15 = studentLessonRepository.save(studentLesson15);

        TeacherLesson teacherLesson1 = new TeacherLesson();
        teacherLesson1.setTeacherId(teacher1.getId());
        teacherLesson1.setLessonId(lesson1.getId());
        teacherLesson1 = teacherLessonRepository.save(teacherLesson1);

        TeacherLesson teacherLesson2 = new TeacherLesson();
        teacherLesson2.setTeacherId(teacher1.getId());
        teacherLesson2.setLessonId(lesson2.getId());
        teacherLesson2 = teacherLessonRepository.save(teacherLesson2);

        TeacherLesson teacherLesson3 = new TeacherLesson();
        teacherLesson3.setTeacherId(teacher1.getId());
        teacherLesson3.setLessonId(lesson3.getId());
        teacherLesson3 = teacherLessonRepository.save(teacherLesson3);

        TeacherLesson teacherLesson4 = new TeacherLesson();
        teacherLesson4.setTeacherId(teacher2.getId());
        teacherLesson4.setLessonId(lesson1.getId());
        teacherLesson4 = teacherLessonRepository.save(teacherLesson4);

        TeacherLesson teacherLesson5 = new TeacherLesson();
        teacherLesson5.setTeacherId(teacher2.getId());
        teacherLesson5.setLessonId(lesson2.getId());
        teacherLesson5 = teacherLessonRepository.save(teacherLesson5);

        TeacherLesson teacherLesson6 = new TeacherLesson();
        teacherLesson6.setTeacherId(teacher3.getId());
        teacherLesson6.setLessonId(lesson2.getId());
        teacherLesson6 = teacherLessonRepository.save(teacherLesson6);

        TeacherLesson teacherLesson7 = new TeacherLesson();
        teacherLesson7.setTeacherId(teacher3.getId());
        teacherLesson7.setLessonId(lesson3.getId());
        teacherLesson7 = teacherLessonRepository.save(teacherLesson7);

        LessonCalendar lessonCalendar1 = new LessonCalendar();
        lessonCalendar1.setLessonId(lesson1.getId());
        lessonCalendar1.setStartTime(Timestamp.valueOf("2020-12-19 20:30:00.000000"));
        lessonCalendar1.setEndTime(Timestamp.valueOf("2020-12-19 21:00:00.000000"));
        lessonCalendar1 = lessonCalendarRepository.save(lessonCalendar1);

        LessonCalendar lessonCalendar2 = new LessonCalendar();
        lessonCalendar2.setLessonId(lesson1.getId());
        lessonCalendar2.setStartTime(Timestamp.valueOf("2020-12-20 20:30:00.000000"));
        lessonCalendar2.setEndTime(Timestamp.valueOf("2020-12-20 21:00:00.000000"));
        lessonCalendar2 = lessonCalendarRepository.save(lessonCalendar2);

        LessonCalendar lessonCalendar3 = new LessonCalendar();
        lessonCalendar3.setLessonId(lesson1.getId());
        lessonCalendar3.setStartTime(Timestamp.valueOf("2020-12-21 20:30:00.000000"));
        lessonCalendar3.setEndTime(Timestamp.valueOf("2020-12-21 21:00:00.000000"));
        lessonCalendar3 = lessonCalendarRepository.save(lessonCalendar3);

        LessonCalendar lessonCalendar4 = new LessonCalendar();
        lessonCalendar4.setLessonId(lesson1.getId());
        lessonCalendar4.setStartTime(Timestamp.valueOf("2020-12-22 20:30:00.000000"));
        lessonCalendar4.setEndTime(Timestamp.valueOf("2020-12-22 21:00:00.000000"));
        lessonCalendar4 = lessonCalendarRepository.save(lessonCalendar4);

        LessonCalendar lessonCalendar5 = new LessonCalendar();
        lessonCalendar5.setLessonId(lesson1.getId());
        lessonCalendar5.setStartTime(Timestamp.valueOf("2020-12-23 20:30:00.000000"));
        lessonCalendar5.setEndTime(Timestamp.valueOf("2020-12-23 21:00:00.000000"));
        lessonCalendar5 = lessonCalendarRepository.save(lessonCalendar5);

        LessonCalendar lessonCalendar6 = new LessonCalendar();
        lessonCalendar6.setLessonId(lesson2.getId());
        lessonCalendar6.setStartTime(Timestamp.valueOf("2020-12-19 20:30:00.000000"));
        lessonCalendar6.setEndTime(Timestamp.valueOf("2020-12-19 21:00:00.000000"));
        lessonCalendar6 = lessonCalendarRepository.save(lessonCalendar6);

        LessonCalendar lessonCalendar7 = new LessonCalendar();
        lessonCalendar7.setLessonId(lesson2.getId());
        lessonCalendar7.setStartTime(Timestamp.valueOf("2020-12-20 20:30:00.000000"));
        lessonCalendar7.setEndTime(Timestamp.valueOf("2020-12-20 21:00:00.000000"));
        lessonCalendar7 = lessonCalendarRepository.save(lessonCalendar7);

        LessonCalendar lessonCalendar8 = new LessonCalendar();
        lessonCalendar8.setLessonId(lesson2.getId());
        lessonCalendar8.setStartTime(Timestamp.valueOf("2020-12-21 20:30:00.000000"));
        lessonCalendar8.setEndTime(Timestamp.valueOf("2020-12-21 21:00:00.000000"));
        lessonCalendar8 = lessonCalendarRepository.save(lessonCalendar8);

        LessonCalendar lessonCalendar9 = new LessonCalendar();
        lessonCalendar9.setLessonId(lesson2.getId());
        lessonCalendar9.setStartTime(Timestamp.valueOf("2020-12-22 20:30:00.000000"));
        lessonCalendar9.setEndTime(Timestamp.valueOf("2020-12-22 21:00:00.000000"));
        lessonCalendar9 = lessonCalendarRepository.save(lessonCalendar9);

        LessonCalendar lessonCalendar10 = new LessonCalendar();
        lessonCalendar10.setLessonId(lesson2.getId());
        lessonCalendar10.setStartTime(Timestamp.valueOf("2020-12-23 20:30:00.000000"));
        lessonCalendar10.setEndTime(Timestamp.valueOf("2020-12-23 21:00:00.000000"));
        lessonCalendar10 = lessonCalendarRepository.save(lessonCalendar10);

        LessonCalendar lessonCalendar11 = new LessonCalendar();
        lessonCalendar11.setLessonId(lesson3.getId());
        lessonCalendar11.setStartTime(Timestamp.valueOf("2020-12-19 20:30:00.000000"));
        lessonCalendar11.setEndTime(Timestamp.valueOf("2020-12-19 21:00:00.000000"));
        lessonCalendar11 = lessonCalendarRepository.save(lessonCalendar11);

        LessonCalendar lessonCalendar12 = new LessonCalendar();
        lessonCalendar12.setLessonId(lesson3.getId());
        lessonCalendar12.setStartTime(Timestamp.valueOf("2020-12-20 20:30:00.000000"));
        lessonCalendar12.setEndTime(Timestamp.valueOf("2020-12-20 21:00:00.000000"));
        lessonCalendar12 = lessonCalendarRepository.save(lessonCalendar12);

        LessonCalendar lessonCalendar13 = new LessonCalendar();
        lessonCalendar13.setLessonId(lesson3.getId());
        lessonCalendar13.setStartTime(Timestamp.valueOf("2020-12-21 20:30:00.000000"));
        lessonCalendar13.setEndTime(Timestamp.valueOf("2020-12-21 21:00:00.000000"));
        lessonCalendar13 = lessonCalendarRepository.save(lessonCalendar13);

        LessonCalendar lessonCalendar14 = new LessonCalendar();
        lessonCalendar14.setLessonId(lesson3.getId());
        lessonCalendar14.setStartTime(Timestamp.valueOf("2020-12-22 20:30:00.000000"));
        lessonCalendar14.setEndTime(Timestamp.valueOf("2020-12-22 21:00:00.000000"));
        lessonCalendar14 = lessonCalendarRepository.save(lessonCalendar14);

        LessonCalendar lessonCalendar15 = new LessonCalendar();
        lessonCalendar15.setLessonId(lesson3.getId());
        lessonCalendar15.setStartTime(Timestamp.valueOf("2020-12-23 20:30:00.000000"));
        lessonCalendar15.setEndTime(Timestamp.valueOf("2020-12-23 21:00:00.000000"));
        lessonCalendar15 = lessonCalendarRepository.save(lessonCalendar15);

        StudentAttendance studentAttendance1 = new StudentAttendance();
        studentAttendance1.setLessonCalendarId(lessonCalendar1.getId());
        studentAttendance1.setStudentId(student2.getId());
        studentAttendance1 = studentAttendanceRepository.save(studentAttendance1);

        StudentAttendance studentAttendance2 = new StudentAttendance();
        studentAttendance2.setLessonCalendarId(lessonCalendar2.getId());
        studentAttendance2.setStudentId(student2.getId());
        studentAttendance2 = studentAttendanceRepository.save(studentAttendance2);

        StudentAttendance studentAttendance3 = new StudentAttendance();
        studentAttendance3.setLessonCalendarId(lessonCalendar3.getId());
        studentAttendance3.setStudentId(student2.getId());
        studentAttendance3 = studentAttendanceRepository.save(studentAttendance3);

        StudentAttendance studentAttendance4 = new StudentAttendance();
        studentAttendance4.setLessonCalendarId(lessonCalendar4.getId());
        studentAttendance4.setStudentId(student2.getId());
        studentAttendance4 = studentAttendanceRepository.save(studentAttendance4);

        StudentAttendance studentAttendance5 = new StudentAttendance();
        studentAttendance5.setLessonCalendarId(lessonCalendar5.getId());
        studentAttendance5.setStudentId(student2.getId());
        studentAttendance5 = studentAttendanceRepository.save(studentAttendance5);

        StudentAttendance studentAttendance6 = new StudentAttendance();
        studentAttendance6.setLessonCalendarId(lessonCalendar6.getId());
        studentAttendance6.setStudentId(student2.getId());
        studentAttendance6 = studentAttendanceRepository.save(studentAttendance6);

        StudentAttendance studentAttendance7 = new StudentAttendance();
        studentAttendance7.setLessonCalendarId(lessonCalendar7.getId());
        studentAttendance7.setStudentId(student2.getId());
        studentAttendance7 = studentAttendanceRepository.save(studentAttendance7);

        StudentAttendance studentAttendance8 = new StudentAttendance();
        studentAttendance8.setLessonCalendarId(lessonCalendar8.getId());
        studentAttendance8.setStudentId(student2.getId());
        studentAttendance8 = studentAttendanceRepository.save(studentAttendance8);

        StudentAttendance studentAttendance9 = new StudentAttendance();
        studentAttendance9.setLessonCalendarId(lessonCalendar9.getId());
        studentAttendance9.setStudentId(student2.getId());
        studentAttendance9 = studentAttendanceRepository.save(studentAttendance9);

        StudentAttendance studentAttendance10 = new StudentAttendance();
        studentAttendance10.setLessonCalendarId(lessonCalendar10.getId());
        studentAttendance10.setStudentId(student2.getId());
        studentAttendance10 = studentAttendanceRepository.save(studentAttendance10);

        StudentAttendance studentAttendance11 = new StudentAttendance();
        studentAttendance11.setLessonCalendarId(lessonCalendar11.getId());
        studentAttendance11.setStudentId(student2.getId());
        studentAttendance11 = studentAttendanceRepository.save(studentAttendance11);

        StudentAttendance studentAttendance12 = new StudentAttendance();
        studentAttendance12.setLessonCalendarId(lessonCalendar12.getId());
        studentAttendance12.setStudentId(student2.getId());
        studentAttendance12 = studentAttendanceRepository.save(studentAttendance12);

        StudentAttendance studentAttendance13 = new StudentAttendance();
        studentAttendance13.setLessonCalendarId(lessonCalendar13.getId());
        studentAttendance13.setStudentId(student2.getId());
        studentAttendance13 = studentAttendanceRepository.save(studentAttendance13);

        StudentAttendance studentAttendance14 = new StudentAttendance();
        studentAttendance14.setLessonCalendarId(lessonCalendar14.getId());
        studentAttendance14.setStudentId(student2.getId());
        studentAttendance14 = studentAttendanceRepository.save(studentAttendance14);

        StudentAttendance studentAttendance15 = new StudentAttendance();
        studentAttendance15.setLessonCalendarId(lessonCalendar15.getId());
        studentAttendance15.setStudentId(student2.getId());
        studentAttendance15 = studentAttendanceRepository.save(studentAttendance15);

        StudentAttendance studentAttendance16 = new StudentAttendance();
        studentAttendance16.setLessonCalendarId(lessonCalendar3.getId());
        studentAttendance16.setStudentId(student1.getId());
        studentAttendance16 = studentAttendanceRepository.save(studentAttendance16);

        StudentAttendance studentAttendance17 = new StudentAttendance();
        studentAttendance17.setLessonCalendarId(lessonCalendar1.getId());
        studentAttendance17.setStudentId(student6.getId());
        studentAttendance17 = studentAttendanceRepository.save(studentAttendance17);

        StudentAttendance studentAttendance18 = new StudentAttendance();
        studentAttendance18.setLessonCalendarId(lessonCalendar2.getId());
        studentAttendance18.setStudentId(student6.getId());
        studentAttendance18 = studentAttendanceRepository.save(studentAttendance18);

        StudentAttendance studentAttendance19 = new StudentAttendance();
        studentAttendance19.setLessonCalendarId(lessonCalendar7.getId());
        studentAttendance19.setStudentId(student1.getId());
        studentAttendance19 = studentAttendanceRepository.save(studentAttendance19);

        StudentAttendance studentAttendance20 = new StudentAttendance();
        studentAttendance20.setLessonCalendarId(lessonCalendar7.getId());
        studentAttendance20.setStudentId(student7.getId());
        studentAttendance20 = studentAttendanceRepository.save(studentAttendance20);

        StudentAttendance studentAttendance21 = new StudentAttendance();
        studentAttendance21.setLessonCalendarId(lessonCalendar8.getId());
        studentAttendance21.setStudentId(student7.getId());
        studentAttendance21 = studentAttendanceRepository.save(studentAttendance21);

        StudentAttendance studentAttendance22 = new StudentAttendance();
        studentAttendance22.setLessonCalendarId(lessonCalendar11.getId());
        studentAttendance22.setStudentId(student3.getId());
        studentAttendance22 = studentAttendanceRepository.save(studentAttendance22);

        StudentAttendance studentAttendance23 = new StudentAttendance();
        studentAttendance23.setLessonCalendarId(lessonCalendar11.getId());
        studentAttendance23.setStudentId(student8.getId());
        studentAttendance23 = studentAttendanceRepository.save(studentAttendance23);

        StudentAttendance studentAttendance24 = new StudentAttendance();
        studentAttendance24.setLessonCalendarId(lessonCalendar12.getId());
        studentAttendance24.setStudentId(student8.getId());
        studentAttendance24 = studentAttendanceRepository.save(studentAttendance24);

        return ResponseEntity.ok("Db yenilendi");
    }
}
