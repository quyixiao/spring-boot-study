package com.example.springbootstudy.service;


import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Service;

@Service("conditionalOnSingleCandidateUserAnnocation")
@Primary
public class ConditionalOnSingleCandidateUserAnnocation implements IConditionalOnSingleCandidateUserAnnocation {

}
