package com.example.springbootstudy.service;

import org.springframework.context.annotation.Import;
import org.springframework.stereotype.Service;

@Service
@Import(ImportA.class)
public class ImportB {
}
