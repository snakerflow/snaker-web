package org.snaker.framework.form.service;

import java.util.List;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.snaker.framework.dictionary.entity.DictionaryItem;
import org.snaker.framework.dictionary.service.DictionaryManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * html处理类
 * @author yuqs
 * @since 1.0
 */
@Component
public class HtmlProcessor {
	@Autowired
	private DictionaryManager dictionaryManager;
	
	public String processHtml(String html) {
		Document doc = Jsoup.parse(html);
		Elements eles = doc.select("[dict]");
		for(Element ele : eles) {
			String dict = ele.attr("dict");
			List<DictionaryItem> items = dictionaryManager.getItemsByName(dict);
			if(items == null || items.isEmpty()) {
				continue;
			}
			String tag = ele.tagName();
			if(tag != null && tag.equalsIgnoreCase("input")) {
				tag = ele.attr("type");
			}
			TagProcessor processor = getProcessor(tag);
			if(processor != null) {
				processor.process(ele, ele.parent(), items);
			}
		}
		eles = doc.getElementsByTag("tr");
		for(Element ele : eles) {
			Elements tds = ele.getElementsByTag("td");
			for(int i = 0; i < tds.size(); i++) {
				Element td = tds.get(i);
				if(i % 2 == 1 && td.hasClass("td_table_1")) {
					td.removeClass("td_table_1");
					td.addClass("td_table_2");
				}
			}
		}
		
		eles = doc.select("[checked]");
		for(Element ele : eles) {
			String checked = ele.attr("checked");
			if(checked == null || checked.trim().equals("") || checked.equalsIgnoreCase("false")) {
				ele.removeAttr("checked");
			}
		}
		eles = doc.select("[selected]");
		for(Element ele : eles) {
			String checked = ele.attr("selected");
			if(checked == null || checked.trim().equals("") || checked.equalsIgnoreCase("false")) {
				ele.removeAttr("selected");
			}
		}
		return doc.html();
	}
	
	public TagProcessor getProcessor(String tag) {
		if(tag == null || tag.equals("")) {
			return null;
		}
		if(tag.equalsIgnoreCase("radio")) {
			return new RadioTagProcessor();
		} else if(tag.equalsIgnoreCase("checkbox")) {
			return new CheckboxTagProcessor();
		} else if(tag.equalsIgnoreCase("select")) {
			return new SelectTagProcessor();
		} else {
			return null;
		}
	}
	
	interface TagProcessor {
		void process(Element ele, Element parent, List<DictionaryItem> items);
	}
	
	class SelectTagProcessor implements TagProcessor {
		public void process(Element ele, Element parent,
				List<DictionaryItem> items) {
			StringBuilder builder = new StringBuilder();
			builder.append("<select name=\"").append(ele.attr("name"));
			builder.append("\" id=\"").append(ele.attr("id"));
			builder.append("\" style=\"").append(ele.attr("style"));
			builder.append("\" type=\"radio\">");
			for(DictionaryItem item : items) {
				builder.append("<option value=\"");
				builder.append(item.getCode());
				builder.append("\" selvalue=\"${formData['");
				builder.append(ele.attr("name"));
				builder.append("'] == '");
				builder.append(item.getCode());
				builder.append("' ? 'selected' : ''}##\" >");
				builder.append(item.getName());
				builder.append("</option>");
			}
			builder.append("</select>");
			parent.html(builder.toString());
		}
	}
	
	class RadioTagProcessor implements TagProcessor {
		public void process(Element ele, Element parent,
				List<DictionaryItem> items) {
			StringBuilder builder = new StringBuilder();
			for(DictionaryItem item : items) {
				builder.append("<input name=\"").append(ele.attr("name"));
				builder.append("\" id=\"").append(ele.attr("id"));
				builder.append("\" style=\"").append(ele.attr("style"));
				builder.append("\" value=\"").append(item.getCode());
				builder.append("\" type=\"radio\" selvalue=\"${formData['");
				builder.append(ele.attr("name"));
				builder.append("'] == '");
				builder.append(item.getCode());
				builder.append("' ? 'checked' : ''}##\" />");
				builder.append(item.getName());
			}
			parent.html(builder.toString());
		}
	}
	
	class CheckboxTagProcessor implements TagProcessor {
		public void process(Element ele, Element parent,
				List<DictionaryItem> items) {
			StringBuilder builder = new StringBuilder();
			for(DictionaryItem item : items) {
				builder.append("<input name=\"").append(ele.attr("name"));
				builder.append("\" id=\"").append(ele.attr("id"));
				builder.append("\" style=\"").append(ele.attr("style"));
				builder.append("\" value=\"").append(item.getCode());
				builder.append("\" type=\"checkbox\" selvalue=\"${formData['/>");
				builder.append(ele.attr("name"));
				builder.append("'] == '");
				builder.append(item.getCode());
				builder.append("' ? 'checked' : ''}##\" />");
				builder.append(item.getName());
			}
			parent.html(builder.toString());
		}
	}
	
}
