package rikkei.academy.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import rikkei.academy.model.Customer;
import rikkei.academy.service.customer.ICustomerService;

@Controller
public class CustomerController {

    @Autowired
    ICustomerService customerService;

    @GetMapping("/")
    public String index(Model model) {
        model.addAttribute("customerList", customerService.findAll());
        return "index";
    }

    @GetMapping("/create")
    public String create(Model model) {
        model.addAttribute("customerForm", new Customer());
        return "create";
    }

    @GetMapping("/edit/{id}")
    public String edit(Model model, @PathVariable Long id) {
        model.addAttribute("customerEdit", customerService.findById(id));
        return "edit";
    }

    @GetMapping("/delete/{id}")
    public String delete(Model model, @PathVariable Long id) {
        model.addAttribute("customerDelete", customerService.findById(id));
        return "delete";
    }

    @PostMapping("/save")
    public String save(Customer customer) {
        customerService.insertWithProcedure(customer);
        return "redirect:/";
    }

    @PostMapping("/update")
    public String update(Customer customer) {
        customerService.save(customer);
        return "redirect:/";
    }

    @PostMapping("/remove")
    public String remove(Customer customer) {
        customerService.remove(customer.getId());
        return "redirect:/";
    }
}
