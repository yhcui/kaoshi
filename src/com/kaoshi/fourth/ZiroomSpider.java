package com.kaoshi.fourth;

import java.util.Iterator;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpUriRequest;
import org.apache.http.client.params.ClientPNames;
import org.apache.http.cookie.Cookie;
import org.apache.http.cookie.CookieOrigin;
import org.apache.http.cookie.CookieSpec;
import org.apache.http.cookie.CookieSpecFactory;
import org.apache.http.cookie.MalformedCookieException;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.impl.cookie.BrowserCompatSpec;
import org.apache.http.params.HttpConnectionParams;
import org.apache.http.params.HttpParams;
import org.apache.http.util.EntityUtils;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

/**
 * @author yhcui
 * 不想那么多了，以后再说吧
 */
public class ZiroomSpider{

	public void start(String url){
		try {
			String responseBody = getResponseFromURL(url);
			String pageURL = parseTotalPageURL(responseBody);
			int pageNum = getTotalPage(pageURL);
			String pUrl = getPageURL(pageURL);
			if(pageNum >=1){
				for(int i=1;i<pageNum;i++){
					if(i == 5){
						System.out.println("你累了，请休息 一会吧。反防抓");
						Thread.sleep(100000); //先睡10秒，反防抓
					}
					parseHTMLByURL(pUrl+"="+i);
					if(i==20){
						//少抓取一点吧
						break;
					}
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		
	}
	private void parseHTMLByURL(String url){
		String response = getResponseFromURL(url);
		Document doc = Jsoup.parse(response);
		Elements masthead = doc.select("li.clearfix");
		Iterator<Element> it = masthead.iterator();
		while(it.hasNext()){
			Element element = it.next();
			System.out.println("抓取数据 进行持久化操作:"+element.select("a.t1").text());
		}
	}
	
	
	private HttpUriRequest getDefaultHttpUriRequest(String url){
		HttpUriRequest request = new HttpGet(url);
		//模仿爬虫
		request.addHeader("User-Agent", "Mozilla/5.0 (compatible; Googlebot/2.1; +http://www.google.com/bot.html)");
		return request;
	}
	
	@SuppressWarnings("deprecation")
	private String getResponseFromURL(String url){
		try {
			CookieSpecFactory csf = new CookieSpecFactory() {
			    public CookieSpec newInstance(HttpParams params) {
			        return new BrowserCompatSpec() {   
			             @Override
			             public void validate(Cookie cookie, CookieOrigin origin)
			             throws MalformedCookieException {
			                 // Oh, I am easy
			            }
			        };
			     }
			};
			
			DefaultHttpClient httpClient = new DefaultHttpClient();
			httpClient.getCookieSpecs().register("easy", csf);
			httpClient.getParams().setParameter(ClientPNames.COOKIE_POLICY, "easy");
	        HttpConnectionParams.setConnectionTimeout(httpClient.getParams(), 2000);
	        
			HttpUriRequest request = getDefaultHttpUriRequest(url);
			HttpResponse response = httpClient.execute(request);
			HttpEntity entity = response.getEntity();
			String responseBody= EntityUtils.toString(entity);
			return responseBody;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}
	
	
	public String parseTotalPageURL(String responseBody){
		Document doc = Jsoup.parse(responseBody);
		Element element = doc.getElementById("page");
		Elements elements = element.getElementsByTag("a");
		Element e=elements.get(elements.size()-2);
		String href = e.attr("href");
//		System.out.println(href);
		return href;
	}
	
	public int getTotalPage(String href ){
		String s = href.split("=")[1];
		return Integer.valueOf(s);
	}
	
	public String getPageURL(String href ){
		String s = href.split("=")[0];
		return s;
	}
	
	public static void main(String[] args) {
	
		
	}
}
