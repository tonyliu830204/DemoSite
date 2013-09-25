package com.mycompany.controller.appfactory;

import com.appfactory.dao.CompanyInfoDao;
import com.appfactory.domain.CompanyInfo;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.annotation.Resource;

/**
 * Created with IntelliJ IDEA.
 * User: wli
 * Date: 9/25/13
 * Time: 4:29 PM
 * To change this template use File | Settings | File Templates.
 */
@Controller("companyInfoController")
public class CompanyInfoController {

    @Resource(name = "companyInfoDao")
    CompanyInfoDao companyInfoDao;

    @RequestMapping("/company_info")
    public String readCompanyInfo(Model model) {

        CompanyInfo info = companyInfoDao.findDefaultCompanyInfo();
        if (info != null) {
            model.addAttribute("html", info.getContent());
        }

        return "appfactory/headless";

    }

}
