package com.xpsoft.core.web.servlet;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class FileDownloadServlet extends HttpServlet
{
  protected void service(HttpServletRequest req, HttpServletResponse res)
    throws ServletException, IOException
  {
    String path = req.getParameter("filePath");

    String fileName = req.getParameter("fileName");

    path = getServletContext().getRealPath("/") + path;

    File obj = new File(path);
    if (!obj.exists()) {
      res.setContentType("text/html;charset=UTF-8");
      res.getWriter().print("指定文件不存在！");
      return;
    }

    ServletOutputStream out = res.getOutputStream();
    res.setHeader("Content-disposition", "attachment;filename=" + fileName);
    BufferedInputStream bis = null;
    BufferedOutputStream bos = null;
    try {
      bis = new BufferedInputStream(new FileInputStream(path));
      bos = new BufferedOutputStream(out);
      byte[] buff = new byte[2048];
      int bytesRead;
      while (-1 != (bytesRead = bis.read(buff, 0, buff.length)))
      {
        bos.write(buff, 0, bytesRead);
      }
    } catch (IOException e) {
      throw e;
    } finally {
      if (bis != null)
        bis.close();
      if (bos != null)
        bos.close();
    }
  }
}