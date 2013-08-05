package com.xpsoft.core.util;

import java.io.File;
import java.net.URL;
import java.net.URLClassLoader;
import java.util.Arrays;
import java.util.Collection;
import java.util.Map;
import java.util.Set;

import javax.tools.JavaCompiler;
import javax.tools.StandardJavaFileManager;
import javax.tools.ToolProvider;

import org.jbpm.api.Execution;
import org.jbpm.api.model.OpenExecution;
import org.jbpm.api.model.OpenProcessInstance;

import com.xpsoft.oa.model.system.AppUser;

public class CompilerUtil {

	public static void compiler() throws Exception{
		JavaCompiler jc = ToolProvider.getSystemJavaCompiler();
		StandardJavaFileManager sjfm = jc.getStandardFileManager(null, null, null);
		File javaFile = new File("D:/lsq/myeclipse2013WorkSpace/oa_ljt/src/com/xpsoft/oa/model/system/AppUser.java");
		// getJavaFileObjects’ param is a vararg
		Iterable fileObjects = sjfm.getJavaFileObjects(javaFile);
//		jc.getTask(null, sjfm, null, null, null, fileObjects).call();
		// Add more compilation tasks
		String[] options = new String[]{"-d", "c:/tmp"};
		jc.getTask(null, sjfm, null, Arrays.asList(options), null, fileObjects).call();
		sjfm.close();
		ClassLoader cl = Thread.currentThread().getContextClassLoader();
		File outputDir = new File("c:/tmp");
		URL[] urls = new URL[]{outputDir.toURL()};
		URLClassLoader ucl = new URLClassLoader(urls, cl);
		Class clazz = ucl.loadClass("com.xpsoft.oa.model.system.AppUser");
		Object appUser = clazz.newInstance();   //
		clazz.getDeclaredMethod("setPhoto", String.class).invoke(appUser, "photoImages");
	    Object object = clazz.getDeclaredMethod("getPhoto",new Class[]{} ).invoke(appUser);
	    System.out.println(object.toString());
	}
	
	public static void compiler1() throws Exception{
		JavaCompiler jc = ToolProvider.getSystemJavaCompiler();
		StandardJavaFileManager sjfm = jc.getStandardFileManager(null, null, null);
		File javaFile = new File("D:/lsq/myeclipse2013WorkSpace/oa_ljt/src/com/xpsoft/oa/workflow/event/BookBorrowFirstHandler.java");
		// getJavaFileObjects’ param is a vararg
		Iterable fileObjects = sjfm.getJavaFileObjects(javaFile);
//		jc.getTask(null, sjfm, null, null, null, fileObjects).call();
		// Add more compilation tasks
		String[] options = new String[]{"-d", "c:/tmp"};
		jc.getTask(null, sjfm, null, Arrays.asList(options), null, fileObjects).call();
		sjfm.close();
		ClassLoader cl = Thread.currentThread().getContextClassLoader();
		File outputDir = new File("c:/tmp");
		URL[] urls = new URL[]{outputDir.toURL()};
		URLClassLoader ucl = new URLClassLoader(urls, cl);
		Class clazz = ucl.loadClass("com.xpsoft.oa.workflow.event.BookBorrowFirstHandler");
		Object instance = clazz.newInstance();   //
	    Object object = clazz.getDeclaredMethod("decide",new Class[]{} ).invoke(instance);
	    System.out.println(object.toString());
	}
	
	public static void main(String[] args) {
		try {
			compiler1();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
