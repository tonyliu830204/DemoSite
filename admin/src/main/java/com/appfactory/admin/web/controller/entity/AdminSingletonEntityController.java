package com.appfactory.admin.web.controller.entity;

import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.View;
import org.springframework.web.servlet.mvc.Controller;
import org.springframework.web.servlet.view.RedirectView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Created with IntelliJ IDEA.
 * User: liweinan
 * Date: 13-9-27
 * Time: PM11:17
 * To change this template use File | Settings | File Templates.
 */
@org.springframework.stereotype.Controller("singletonEntityController")
public class AdminSingletonEntityController implements Controller {

    @Override
    public ModelAndView handleRequest(HttpServletRequest request, HttpServletResponse response) throws Exception {
        ModelAndView modelAndView = new ModelAndView();
        View view = new RedirectView(request.getRequestURI() + "/-1", false);
        modelAndView.setView(view);
        return modelAndView;
    }
}
