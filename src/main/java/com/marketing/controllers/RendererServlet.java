package com.marketing.controllers;

import org.thymeleaf.TemplateEngine;
import org.thymeleaf.context.WebContext;
import org.thymeleaf.templatemode.TemplateMode;
import org.thymeleaf.templateresolver.ServletContextTemplateResolver;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.HashMap;


public abstract class RendererServlet extends HttpServlet {
    private TemplateEngine templateEngine;
    private final String htmlPage;

    public RendererServlet(String htmlPage) {
        super();
        this.htmlPage = htmlPage;
    }

    @Override
    public void init() throws ServletException {
        ServletContext servletContext = getServletContext();
        ServletContextTemplateResolver templateResolver = new ServletContextTemplateResolver(servletContext);
        templateResolver.setTemplateMode(TemplateMode.HTML);
        this.templateEngine = new TemplateEngine();
        this.templateEngine.setTemplateResolver(templateResolver);
        templateResolver.setSuffix(".html");
    }

    public TemplateEngine getTemplateEngine() {
        return templateEngine;
    }

    protected void renderAndServeWithVariables(HttpServletRequest request, HttpServletResponse response, HashMap<String, Object> variables) throws IOException {
        final WebContext webContext = new WebContext(request, response, getServletContext(), request.getLocale());
        variables.forEach(webContext::setVariable);
        getTemplateEngine().process(htmlPage, webContext, response.getWriter());
    }

    protected void renderAndServe(HttpServletRequest request, HttpServletResponse response) throws IOException {
        getTemplateEngine().process(htmlPage, new WebContext(request, response, getServletContext(), request.getLocale()), response.getWriter());
    }
}
