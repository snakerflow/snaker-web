package com.snakerflow.app.web.taglibs.builder;

import org.apache.commons.lang.StringUtils;
import com.snakerflow.framework.web.TagBuilder;
import com.snakerflow.framework.web.TagDTO;
import org.springframework.stereotype.Component;

/**
 * 自定义分页标签处理类
 * @author yuqs
 * @since 0.1
 */
@Component
public class PageTagBuilder implements TagBuilder {
	public static final String TOTAL_RECORDS = "totalRecords";
	public static final String TOTAL_PAGES = "totalPages";
	public static final String CURPAGE = "curPage";
	public static final String EXPORT_URL = "exportUrl";
	public static final String LOOKUP = "lookup";
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
	
	/**
	 * 获取DTO传递的参数，并依此构建分页的html信息
	 */
	@Override
	public String build(TagDTO dto) {
		this.totalRecords = dto.getProperty(TOTAL_RECORDS);
		this.totalPages = dto.getProperty(TOTAL_PAGES);
		this.curPage = dto.getProperty(CURPAGE);
		this.exportUrl = dto.getProperty(EXPORT_URL);
		this.lookup = dto.getProperty(LOOKUP);
		
		StringBuffer content = new StringBuffer();
		//构建分页详细信息，总记录数、总页数、当前页数
		content.append(buildPageMessage());
		//构建分页栏的上一页、下一页、首页、末页
		content.append(buildPageLink());
		if(lookup == null || lookup.equals("")) {
			//构建跳转指定页数的按钮
			content.append(buildPageButton());
		}
		//对产生的html进行包装，实际上是添加到指定样式的table里
		return wrapPageContent(content.toString());
	}

	/**
	 * 构建分页详细信息，总记录数、总页数、当前页数
	 * @return
	 */
	private String buildPageMessage() {
		StringBuffer sb = new StringBuffer();
		sb.append("<td><div align='left'>");
		sb.append("总共<font color='red'>");
		sb.append(totalRecords);
		sb.append("</font>条记录&nbsp; 共");
		sb.append("<font color='red'>");
		sb.append(totalPages);
		sb.append("</font>页&nbsp; 当前所在第");
		sb.append("<font color='red'>");
		sb.append(curPage);
		sb.append("</font>页");
		sb.append("</div></td>");
		return sb.toString();
	}
	
	/**
	 * 构建分页栏的上一页、下一页、首页、末页
	 * @return
	 */
	private String buildPageLink() {
		int prePage = 0, nextPage = 0, pageNo = 1, totalPage = 1;
		try {
			pageNo = Integer.parseInt(curPage);
			totalPage = Integer.parseInt(totalPages);
			prePage = pageNo - 1;
			nextPage = pageNo + 1;
			prePage = prePage <= 0 ? 1 : prePage;
			nextPage = nextPage >= totalPage ? totalPage : nextPage;
		} catch(Exception e) {
			
		}
		StringBuffer sb = new StringBuffer();
		sb.append("<td><div class='class1_ur4'>");
		sb.append("<a href='javascript:jumpPage(1)' style='TEXT-DECORATION: none'>首页</a>&nbsp;&nbsp;");
		sb.append("<a href='javascript:jumpPage(");
		sb.append(prePage);
		sb.append(")' style='TEXT-DECORATION: none'>上一页</a>&nbsp;&nbsp;");
		sb.append("<a href='javascript:jumpPage(");
		sb.append(nextPage);
		sb.append(")' style='TEXT-DECORATION: none'>下一页</a>&nbsp;&nbsp;");
		sb.append("<a href='javascript:jumpPage(");
		sb.append(totalPages);
		sb.append(")' style='TEXT-DECORATION: none'>末页</a>");
		return sb.toString();
	}
	
	/**
	 * 构建跳转指定页数的按钮
	 * @return
	 */
	private String buildPageButton() {
		StringBuffer sb = new StringBuffer();
		sb.append("<td><input type='hidden' name='lastpage' value='");
		sb.append(totalPages);
		sb.append("'/>转至第");
		sb.append("<input maxLength=5 name='jumppage' size=2 value='");
		sb.append("'/>");
		sb.append("页 &nbsp;&nbsp;");
		sb.append("<input type='button' name='pagesubmit' ");
		sb.append("onclick='return gotoPage()' class='button_70px' ");
		sb.append("value='跳转'/></td>");
		sb.append("<td>");
		if(StringUtils.isNotEmpty(exportUrl)) {
			sb.append("<input type='button' name='genExcel' class='button_100px' value='保存成Excel文件' onclick=\"exportExcel('");
			sb.append(exportUrl);
			sb.append("')\">");
		}
		sb.append("</td>");
		
		return sb.toString();
	}
	
	/**
	 * 对产生的html进行包装，实际上是添加到指定样式的table里
	 * @param content
	 * @return
	 */
	private String wrapPageContent(String content) {
		StringBuffer sb = new StringBuffer();
		sb.append("<tr><td class='td_table_bottom' colspan='8'>");
		sb.append("<table width='800' border='0' cellpadding='0' cellspacing='0'><tr>");
		sb.append(content);
		sb.append("</tr></table></td></tr>");
		return sb.toString();
	}
}
