package com.moyeo.controller;


import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.moyeo.dto.Pack;
import com.moyeo.dto.Userinfo;
import com.moyeo.exception.UserinfoNotFoundException;
import com.moyeo.service.PackageService;
import com.moyeo.service.UserinfoService;

@Controller   
@RequestMapping(value = "/admin")
public class AdminController {
   private static final Logger logger = LoggerFactory.getLogger(UserinfoController.class);
   
   @Autowired
   private UserinfoService userinfoservice;
   
   @Autowired
   private PackageService packservice;
   
   @GetMapping(value = "/")
   public String adminmainGET() {
      return "admin/adminmain";
   }
   
   @GetMapping(value = "/center")
   public String centerGET() {
      return "admin/center";
   }
   
   @GetMapping(value = "/userlist")
    public String userList(Model model) {
        List<Userinfo> userinfoList = userinfoservice.getUserinfoList();
        model.addAttribute("userinfoList", userinfoList);
        return "admin/userlist"; // admin/userlist.jsp�� ȣ��
    }
   
   @GetMapping(value = "/userinfo")
    public String getUserInfo(@RequestParam("id") String id, Model model) throws UserinfoNotFoundException {
        Userinfo userinfo = userinfoservice.getUserinfoById(id); // userinfoservice�� ���� Ŭ������ �ν��Ͻ�, id�� ����� ������ ��ȸ
        model.addAttribute("userinfo", userinfo); // �𵨿� �����͸� �߰�
        return "admin/userinfo"; // admin/userinfo.jsp�� ȣ��
    }
   
   //��Ű�� ��� ���
   @GetMapping(value = "/packagelist")
   public String packList(Model model) {
       List<Pack> packList = packservice.getPackageList();
       model.addAttribute("packList", packList);
      return "admin/packagelist"; // admin ���� ���� packagelist.jsp ���ϸ�
   }
   
   //��Ű�� ��� ��ư
   @GetMapping(value = "/packageregist")
   public String packageregistGET(Model model) {
      model.addAttribute("pack", new Pack()); // �� Pack ��ü�� �𵨿� �߰��Ͽ� ���� ���ε�
      return "admin/packageregist"; // admin ���� ���� packageregist.jsp ���ϸ�
   }
   
   @PostMapping(value = "/packageregist")
   public String packageregistPOST(@ModelAttribute("pack") Pack pack) {
      packservice.addPackage(pack); // ���񽺸� ���� ��Ű�� ��� ����
      return "redirect:/admin/packagelist"; // ��Ű�� ��� �� ��Ű�� ��� �������� �����̷�Ʈ
   }
}