package com.lagou.edu.controller;

import com.lagou.edu.pojo.Resume;
import com.lagou.edu.service.ResumeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@Controller
@RequestMapping("/resume")
public class ResumeController {

    /**
     * Spring容器和SpringMVC容器是有层次的（父子容器）
     * Spring容器：service对象+dao对象
     * SpringMVC容器：controller对象，，，，可以引用到Spring容器中的对象
     */


    @Autowired
    private ResumeService resumeService;

    @RequestMapping("/queryAll")
    @ResponseBody
    public List<Resume>  queryAll() throws Exception {
        return resumeService.queryAccountList();
    }

    /**
     * 根据id删除数据
     * @param id
     */
    @GetMapping("/deleteResume")
    @ResponseBody
    public Map<String, String> deleteResume(@RequestParam Long id) throws Exception {
        Map<String, String> resultMap = new HashMap<>();
        resumeService.deleteResume(id);
        resultMap.put("success", "success");
        return resultMap;
    }

    /**
     * 新增简历数据
     * @param resume
     * @return
     */
    @PostMapping("/insertResume")
    public String insertResume(Resume resume){
        resumeService.insertResume(resume);
        return "redirect:/main";
    }

    /**
     * 查询简历详情
     * @param id
     * @return
     */
    @GetMapping("/queryInfo")
    public ModelAndView selectResumeInfo(@RequestParam Long id){
        ModelAndView modelAndView = new ModelAndView();
        Optional<Resume> optional = resumeService.selectResumeInfo(id);
        Resume resume = optional.get();
        modelAndView.addObject(resume);
        modelAndView.setViewName("update");
        return modelAndView;
    }

    /**
     * 跟新简历详情
     * @param resume
     * @return
     */
    @PostMapping("/updateResume")
    public String updateResume(Resume resume){
        resumeService.updateResume(resume);
        return "redirect:/main";
    }

}
