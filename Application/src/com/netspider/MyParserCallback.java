package com.netspider;

import java.net.URL;
import javax.swing.text.MutableAttributeSet;
import javax.swing.text.html.HTML;
import javax.swing.text.html.HTMLEditorKit.ParserCallback;

public class MyParserCallback extends ParserCallback {

	protected String base;
	protected boolean isLink = false;
	protected boolean isForm = false;
	protected static String reserve = "";
	protected MySpider mySpider;
	public MyParserCallback(String baseurl,MySpider mySpider) {
		this.base = baseurl;
		this.mySpider=mySpider;
	}

	public void handleEndTag(HTML.Tag t, int pos) {
		if (t == HTML.Tag.A) {
			if (isLink) {
				isLink = false;
			}
		}
		if (t == HTML.Tag.FORM) {
			if (isForm) {
				isForm = false;
			}
		}
	}

	public void handleError(String errorMsg, int pos) {
	}

	public void handleSimpleTag(HTML.Tag t, MutableAttributeSet a, int pos) {
		handleStartTag(t, a, pos);
	}

	public void handleStartTag(HTML.Tag t, MutableAttributeSet a, int pos) {
		String href = "";
		String action = "";
		if ((t == HTML.Tag.A) && (t != HTML.Tag.BASE)) {
			href = (String) a.getAttribute(HTML.Attribute.HREF);
			if (href != null && href != "") {
				try {
					URL url1 = new URL(new URL(base), href);
					
					if (url1.toString() != null
							&& !url1.toString().trim().equals("")) {
						if (mySpider.blackList.contains(url1.toString())) {
							return;
						}
						if (RegexClass.unwanted(url1.toString(),mySpider)) {
							mySpider.blackList.addUrl(url1.toString());
							return;
						}
						if (mySpider.visitedUrl.contains(url1.toString())) {
							return;
						}
						if (mySpider.unvisitedUrl.contains(url1.toString())) {
							return;
						}
						mySpider.mainFrame.mainPan.view.conclusionText.append(url1.toString()+"\n");
						mySpider.mainFrame.mainPan.view.conclusionScroll.getVerticalScrollBar().setValue(mySpider.mainFrame.mainPan.view.conclusionScroll.getVerticalScrollBar().getMaximum());
						mySpider.unvisitedUrl.enQueue(url1.toString());
					}
					isLink = true;
				} catch (Exception e) {
					// TODO Auto-generated catch block
					
				}
			}
		}
		if (t == HTML.Tag.FORM) {
			action = (String) a.getAttribute(HTML.Attribute.ACTION);
			
			if (action != null && action != "") {
				try {
					URL url2 = new URL(new URL(base), action);
					if (url2.toString() != null
							&& !url2.toString().trim().equals("")) {
						if (mySpider.blackList.contains(url2.toString())) {
							return;
						}
						if (RegexClass.unwanted(url2.toString(),mySpider)) {
							mySpider.blackList.addUrl(url2.toString());
							return;
						}
						if (mySpider.visitedUrl.contains(url2.toString())) {
							return;
						}
						if (mySpider.unvisitedUrl.contains(url2.toString())) {
							return;
						}
						mySpider.unvisitedUrl.enQueue(url2.toString());
						mySpider.mainFrame.mainPan.view.conclusionText.append(url2.toString()+"\n");
						mySpider.mainFrame.mainPan.view.conclusionScroll.getVerticalScrollBar().setValue(mySpider.mainFrame.mainPan.view.conclusionScroll.getVerticalScrollBar().getMaximum());
						mySpider.formUrl.addUrl(base);
					}
					isForm = true;
				} catch (Exception e) {
					// TODO Auto-generated catch block
					// e.printStackTrace();
				}
			}
		}
		if (t == HTML.Tag.INPUT) {
			String name;
			if (a.getAttribute(HTML.Attribute.NAME) != null) {
				name = a.getAttribute(HTML.Attribute.NAME).toString();
				reserve = reserve + name + "=" + "&";
			}
		}
	}
}
	
