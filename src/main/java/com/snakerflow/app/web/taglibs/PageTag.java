package com.snakerflow.app.web.taglibs;

import java.io.IOException;

import javax.servlet.ServletContext;
import javax.servlet.jsp.JspWriter;

import com.snakerflow.framework.web.TagDTO;
import com.snakerflow.app.web.taglibs.builder.PageTagBuilder;
import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.servlet.tags.RequestContextAwareTag;

/**
 * 自定义查询分页标签。查询统计涉及到分页的界面，只要通过page标签，即可显示分页的常用操作
 * 该类继承RequestContextAwareTag，主要用于获取WebApplicationContext
 * @author yuqs
 * @since 0.1
 */
public class PageTag extends RequestContextAwareTag 
{
	/**
	 * 
	 */
	private static final long serialVersionUID = -5636798157755500338L;
	//总记录数
	private String totalRecords;
	//总页数
	private String totalPages;
	//当前页数
	private String curPage;
	//导出excel的url
	private String exportUrl;
	//是否打开对话框的查询
	private String lookup;
	//Spring的上下文
	private WebApplicationContext springContext;
	//Servlet的上下文
	private ServletContext servletContext = null;

	/**
	 * 继承RequestContextAwareTag的doStartTagInternal方法，实际上是doStartTag的模板方法
	 */
	@Override
	protected int doStartTagInternal() throws Exception {
		//获取ServletContext
		servletContext = pageContext.getServletContext();
		//获取spring上下文
		springContext = getRequestContext().getWebApplicationContext();
		JspWriter writer = pageContext.getOut();
		try {
			TagDTO dto = new TagDTO(servletContext);
			dto.setProperty(PageTagBuilder.TOTAL_RECORDS, totalRecords);
			dto.setProperty(PageTagBuilder.TOTAL_PAGES, totalPages);
			dto.setProperty(PageTagBuilder.CURPAGE, curPage);
			dto.setProperty(PageTagBuilder.EXPORT_URL, exportUrl);
			dto.setProperty(PageTagBuilder.LOOKUP, lookup);
			PageTagBuilder builder = springContext.getBean(PageTagBuilder.class);
			writer.write(builder.build(dto));
		} catch (IOException e) {
			e.printStackTrace();
		}
		return 0;
	}
	public String getTotalRecords() {
		return totalRecords;
	}

	public void setTotalRecords(String totalRecords) {
		this.totalRecords = totalRecords;
	}

	public String getTotalPages() {
		return totalPages;
	}

	public void setTotalPages(String totalPages) {
		this.totalPages = totalPages;
	}

	public String getCurPage() {
		return curPage;
	}

	public void setCurPage(String curPage) {
		this.curPage = curPage;
	}

	public String getExportUrl() {
		return exportUrl;
	}

	public void setExportUrl(String exportUrl) {
		this.exportUrl = exportUrl;
	}

	public String getLookup() {
		return lookup;
	}

	public void setLookup(String lookup) {
		this.lookup = lookup;
	}
}
