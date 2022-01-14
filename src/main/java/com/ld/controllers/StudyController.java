package com.ld.controllers;

import com.ld.model.Lesson;
import com.ld.services.LessonService;
import com.ld.services.SSOService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.UUID;

@RestController
@RequiredArgsConstructor
@RequestMapping(path = "/api/study")
public class StudyController {

    private final LessonService lessonService;
    private final SSOService ssoService;

    @GetMapping(path = "/all")
    public List<Lesson> all() {
        return lessonService.readAll();
    }

    @GetMapping
    public Lesson lesson(@RequestParam(name = "id") UUID id) {
        Lesson lesson = lessonService.findById(id);
        ssoService.checkUserPermission(lesson);
        return lesson;
    }
}
