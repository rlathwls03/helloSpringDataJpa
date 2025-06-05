package kr.ac.hansung.cse.hellospringdatajpa.controller;

import kr.ac.hansung.cse.hellospringdatajpa.entity.MyUser;
import kr.ac.hansung.cse.hellospringdatajpa.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/admin")
public class AdminController {

    @Autowired
    private UserRepository userRepository;

    // 전체 사용자 목록 조회 (ROLE_ADMIN만 접근)
    @GetMapping("/users")
    // Method Security를 사용할 경우 @PreAuthorize("hasRole('ADMIN')") 추가
    public String listAllUsers(Model model) {
        List<MyUser> allUsers = userRepository.findAll();
        model.addAttribute("users", allUsers);
        return "admin_users";  // /templates/admin_users.html
    }
}
