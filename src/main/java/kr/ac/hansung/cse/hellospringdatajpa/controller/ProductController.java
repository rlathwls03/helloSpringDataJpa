package kr.ac.hansung.cse.hellospringdatajpa.controller;

import kr.ac.hansung.cse.hellospringdatajpa.entity.Product;
import kr.ac.hansung.cse.hellospringdatajpa.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import jakarta.validation.Valid;
import org.springframework.validation.BindingResult;

import java.util.List;

@Controller
@RequestMapping("/products")

public class ProductController {

    @Autowired
    private ProductService service;

    // 상품 목록 조회 (ROLE_USER, ROLE_ADMIN)
    @GetMapping({"", "/"}) // products 또는 products/ 둘 다 매핑
    public String viewHomePage(Model model) {

        List<Product> listProducts = service.listAll();
        model.addAttribute("listProducts", listProducts);

        return "index";
    }

    // 상품 생성 폼 (ROLE_ADMIN만 템플릿 단에서 노출됨)
    @GetMapping("/new")
    public String showNewProductPage(Model model) {

        Product product = new Product();
        model.addAttribute("product", product);

        return "new_product";
    }

    // 상품 수정 폼 (ROLE_ADMIN만 템플릿 단에서 노출됨)
    @GetMapping("/edit/{id}")
    public String showEditProductPage(@PathVariable(name = "id") Long id, Model model) {

        Product product = service.get(id);
        model.addAttribute("product", product);

        return "edit_product";
    }

    // @ModelAttribute는  Form data (예: name=Laptop&brand=Samsung&madeIn=Korea&price=1000.00)를 Product 객체
    // @RequestBody는 HTTP 요청 본문에 포함된
    //  JSON 데이터(예: {"name": "Laptop", "brand": "Samsung", "madeIn": "Korea", "price": 1000.00})를 Product 객체에 매핑
    @PostMapping("/save")
    public String saveProduct(
            @Valid @ModelAttribute("product") Product product,
            BindingResult bindingResult,
            Model model) {
        // 1) 유효성 검사 오류가 있으면, 다시 폼으로 돌아가서 에러 메시지 출력
        if (bindingResult.hasErrors()) {
            // 새로 작성인지 수정인지에 따라 뷰를 구분
            if (product.getId() == null) {
                // new_product.html
                return "new_product";
            } else {
                // edit_product.html
                return "edit_product";
            }
        }
        // 2) 오류 없으면 저장
        service.save(product);
        return "redirect:/products";
    }

    // 상품 삭제 (ROLE_ADMIN만 템플릿 단에서 노출됨)
    @GetMapping("/delete/{id}")
    public String deleteProduct(@PathVariable(name = "id") Long id) {

        service.delete(id);
        return "redirect:/products";
    }
}