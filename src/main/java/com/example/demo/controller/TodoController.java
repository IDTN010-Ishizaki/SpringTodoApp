package com.example.demo.controller;

import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.example.demo.dto.TodoForm;
import com.example.demo.entity.TaskStatus;
import com.example.demo.entity.Todo;
import com.example.demo.service.TodoService;

import jakarta.validation.Valid;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Controller
@RequestMapping("/todos")
public class TodoController {
    private TodoService service;

    @ModelAttribute("form")
    private TodoForm form(@ModelAttribute TodoForm form) {
        return new TodoForm();
    }

    @ModelAttribute("completedTask")
    private List<Todo> comletedTask() {
        return service.getCompleted();
    }

    @ModelAttribute("workingTask")
    private List<Todo> workingTask() {
        return service.getWorking();
    }

    public TodoController(TodoService service) {
        this.service = service;
    }

    @GetMapping
    public String index(@RequestParam(required = false) String msg, Model model) {
        log.info("/todos");

        if (msg != null) {
            model.addAttribute("msg", msg);
        }

        return "todos/index";
    }

    @PostMapping("/register")
    public String create(@Valid @ModelAttribute("form") TodoForm form, BindingResult validation,
            RedirectAttributes redirect) {
        log.info("/register -> /todos");
        log.info("form values=" + form.toString());
        log.info("form has errors: " + validation.hasErrors());

        if (validation.hasErrors()) {
            for (ObjectError err : validation.getAllErrors()) {
                log.warn(err.getDefaultMessage());
            }
            log.warn("invalid forms");
            return "todos/index";
        }

        service.create(form);
        redirect.addAttribute("msg", "新しいタスクを作成しました");

        return "redirect:/todos";
    }

    @GetMapping("/change-status/{id}")
    public String changeStatus(@PathVariable("id") long id, @RequestParam("taskStatus") String taskStatus,
            RedirectAttributes redirect) {
        log.info("/change-status/%d [%s] -> /todos".formatted(id, taskStatus));

        TaskStatus status = TaskStatus.valueOf(taskStatus);

        service.changeStateById(id, status);
        redirect.addAttribute("msg", "ステータスを更新しました");

        return "redirect:/todos";
    }

    @GetMapping("/delete/{id}")
    public String delete(@PathVariable("id") long id, RedirectAttributes redirect) {
        log.info("/change-state/%d -> /todos".formatted(id));

        service.deleteById(id);
        redirect.addAttribute("msg", "削除しました");

        return "redirect:/todos";
    }

}
