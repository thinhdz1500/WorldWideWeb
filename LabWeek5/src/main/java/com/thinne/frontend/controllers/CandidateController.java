package com.thinne.frontend.controllers;

import com.thinne.backend.models.Candidate;
import com.thinne.frontend.models.CandidateModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;
import java.util.Optional;
import java.util.stream.IntStream;

@Controller
public class CandidateController {
    @Autowired
    private CandidateModel candidateModel;

    @RequestMapping("/")
    public ModelAndView showIndex() {
        ModelAndView mav = new ModelAndView("index");
        return mav;
    }

    @RequestMapping("/candidates")
    public ModelAndView showCandidateListPaging(@RequestParam("page") Optional<Integer> pageNo, @RequestParam("size") Optional<Integer> pageSize) {
        ModelAndView mav = new ModelAndView("candidate/candidate-paging");
        int currentPage = pageNo.orElse(1);
        int currentPageSize = pageSize.orElse(10);

        Page<Candidate> candidatePage = candidateModel.findAll(currentPage - 1, currentPageSize, "id", "asc");

        mav.addObject("candidatePage", candidatePage);
        int totalPages = candidatePage.getTotalPages();

        if (totalPages > 0) {
            List<Integer> pageNumbers = IntStream.rangeClosed(1, totalPages)
                    .boxed()
                    .toList();

            mav.addObject("pageNumbers", pageNumbers);
        }

        return mav;
    }

    @RequestMapping("/candidate/login")
    public ModelAndView showLogin() {
        ModelAndView mav = new ModelAndView("candidate/candidate-login");
        return mav;
    }

    @RequestMapping(value = "/candidate/login", method = RequestMethod.POST)
    public ModelAndView login(@RequestParam("email") String email, @RequestParam("password") String password) {
        ModelAndView mav = new ModelAndView();
        Candidate candidate = candidateModel.findByEmail(email);
        if (candidate != null) {
            if (candidate.getPassword().equals(password)) {
                mav.setViewName("candidate/candidate-dashboard");
                mav.addObject("candidate", candidate);
                mav.addObject("candidateSkills", candidateModel.getCandidateSkillByCandidate_Id(candidate.getId()));
            } else {
                mav.setViewName("candidate/candidate-login");
                mav.addObject("message", "Incorrect account or password!");
            }
        } else {
            mav.setViewName("candidate/candidate-login");
            mav.addObject("message", "Incorrect account or password!");
        }
        return mav;
    }
}
