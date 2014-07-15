package org.snaker.modules.base.helper;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

public class JsoupHelper {
	public static String processHtml(String html) {
		String newHtml = html;
		Document doc = Jsoup.parse(html);
		Elements eles = doc.select("[dict]");
		for(Element ele : eles) {
			System.out.println(ele.attr("dict"));
			ele.parent().html("fffffffffffffff");
		}
		System.out.println("doc:\n" + doc.html());
		return newHtml;
	}
	
	private static String processCheck(Element ele, Element parent, String dict) {
		StringBuilder builder = new StringBuilder();
		builder.append("<input name=\"").append(ele.attr("name"));
		builder.append("\" id=\"").append(ele.attr("id"));
		builder.append("\" style=\"").append(ele.attr("style"));
		builder.append("\" value=\"");
		builder.append("\" type=\"check\"/>");
		builder.append("");
		return builder.toString();
	}
	
	public static void main(String[] a) {
		JsoupHelper.processHtml("<table width=\"100%\" align=\"center\" class=\"table_all\" style=\"margin-top: 0px; margin-bottom: 0px;\" border=\"0\" cellspacing=\"0\" cellpadding=\"0\"><tbody><tr class=\"firstRow\"><td width=\"120\" class=\"td_table_1\" style=\"text-align: right;\">申请人：</td><td width=\"30%\" align=\"left\" class=\"td_table_2\"><input name=\"LEAVEAPPLY_USERNAME\" id=\"USERNAME\" style=\"width: 240px;\" type=\"text\" value=\"${formData[&quot;LEAVEAPPLY_USERNAME&quot;]}\"/></td><td width=\"120\" class=\"td_table_1\" style=\"text-align: right;\">地址：</td><td width=\"30%\" align=\"left\" class=\"td_table_2\"><input name=\"LEAVEAPPLY_ADDRESS\" id=\"ADDRESS\" style=\"width: 240px;\" type=\"text\"/></td></tr><tr><td align=\"right\" class=\"td_table_1\" valign=\"middle\" rowspan=\"1\" colspan=\"1\">年龄：</td><td class=\"td_table_1\" rowspan=\"1\" colspan=\"1\"><input name=\"LEAVEAPPLY_AGE\" id=\"AGE\" style=\"width: 240px;\" type=\"text\"/></td><td align=\"right\" class=\"td_table_1\" valign=\"middle\" rowspan=\"1\" colspan=\"1\">申请时间：</td><td class=\"td_table_1\" rowspan=\"1\" colspan=\"1\"><input name=\"LEAVEAPPLY_REGTIME\" id=\"REGTIME\" style=\"width: 240px;\" type=\"check\" dict=\"test\"/></td></tr></tbody></table><p class=\"ue_t\"><br/></p><p>&nbsp;</p>");
	}
}
