package com.example.expendituretrackerapi.controllers;

import com.example.expendituretrackerapi.services.ExpenditureService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.test.web.servlet.MockMvc;

@WebMvcTest(ExpenditureController.class)
class ExpenditureControllerTest {
    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ExpenditureService expenditureService;

}