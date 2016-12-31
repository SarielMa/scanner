package com.netspider;

import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Vector;
import java.util.concurrent.ExecutorService;

import com.graphicaluserinterface.CheckBoxTreeNode;
import com.graphicaluserinterface.MainFrame;

public class MySpider {

	// δ����URL����
	public UrlVector unvisitedUrl;
	// �Ѵ���URL����
	public UrlSet visitedUrl;
	// ������
	public UrlSet blackList;
	
	//��URL����	
	public UrlSet formUrl;
	
	//���н��
	public UrlSet sourceUrl;
	public UrlSet resultUrl;
	//����
	public int depth = 1;
	//�̳߳�
	public MyThreadPool pool;
	public ExecutorService sp;
	// URL
	public String url="http://localhost:8088/note_sys/doLogin.jsp?uname=ss&upass=ss";
	//�ȴ���������URL��
	public int num = 0;
	//��ǰ�߳���
	public int active = 0;
	//����߳���
	public int threadNum =1;
	public String domain="";
	public MainFrame mainFrame;
	public Thread start;
	public Run go;
	private boolean stopFlag=false;
	private boolean pauseFlag=false;
	public MySpider(String url, int depth, int threadNum, MainFrame mainFrame) {
		this.url = url;
		this.depth = depth;
		this.threadNum = threadNum;
		this.mainFrame=mainFrame;
		init();
		mainFrame.mainPan.view.conclusionText.setText(null);
		go=new Run();
		start=new Thread(go);
		start.start();
	}

	// initialize
	public void init() {
		pool = new MyThreadPool(threadNum);
		sp = pool.getPool();
		unvisitedUrl = new UrlVector();
		visitedUrl = new UrlSet();
		blackList = new UrlSet();
		formUrl = new UrlSet();
		sourceUrl = new UrlSet();
		resultUrl = new UrlSet();
	}
	public class Run implements Runnable{
		
		public void run() {
			// TODO Auto-generated method stub
			
			try {
				MySpider.this.domain=new URL(url).getHost();
			} catch (MalformedURLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			//����ʼURL�������
			MySpider.this.unvisitedUrl.enQueue(MySpider.this.url);
			int tmp = MySpider.this.depth;
			MySpider.this.num = MySpider.this.unvisitedUrl.vector.size();
			//����
			while (tmp > 0&&stopFlag==false) {
				while(MySpider.this.pauseFlag==true){
				}
				while (MySpider.this.num > 0&&!MySpider.this.sp.isShutdown()) {
					MySpider.this.sp.execute(new CatchThread(MySpider.this));
					MySpider.this.active++;
					MySpider.this.num--;
				}
				while (MySpider.this.active > 0) {
				}
				MySpider.this.num = MySpider.this.unvisitedUrl.vector.size();
				tmp--;
			}
			MySpider.this.sp.shutdown();
			fresh();
			mainFrame.mainToolBar.jtb_play.setEnabled(true);
			mainFrame.mainToolBar.jtb_pause.setEnabled(false);
			mainFrame.mainToolBar.jtb_stop.setEnabled(false);
			mainFrame.focusChangeable=true;
		}
		public void stop() {
			// TODO Auto-generated method stub
			stopFlag=true;
			pauseFlag=false;
			MySpider.this.sp.shutdownNow();
		}
		public void pause(){
			pauseFlag=true;
		}
		public void restart(){
			pauseFlag=false;
		}
	}
	public void fresh(){
			// TODO Auto-generated method stub
			sort();
			mainFrame.result.setWebsiteOfGet(mainFrame.spider.getResultUrl());mainFrame.result.setWebsiteOfPost(mainFrame.spider.getSourceUrl());
			mainFrame.result.setOtherWebsite(mainFrame.spider.getBlackList());
			Iterator<String> showWebsite;
			showWebsite=mainFrame.result.getWebsiteOfGet().iterator();
			mainFrame.mainPan.websiteFileList.websiteOfGetTree.removeAllChildren();
			mainFrame.mainPan.websiteFileList.websiteOfPostTree.removeAllChildren();
			mainFrame.mainPan.websiteFileList.otherWebsiteTree.removeAllChildren();
			while(showWebsite.hasNext())
				mainFrame.mainPan.websiteFileList.websiteOfGetTree.add(new CheckBoxTreeNode(showWebsite.next()));
			showWebsite=mainFrame.result.getWebsiteOfPost().iterator();
			while(showWebsite.hasNext())
				mainFrame.mainPan.websiteFileList.websiteOfPostTree.add(new CheckBoxTreeNode(showWebsite.next()));
			showWebsite=mainFrame.result.getOtherWebsite().iterator();
			while(showWebsite.hasNext())
				mainFrame.mainPan.websiteFileList.otherWebsiteTree.add(new CheckBoxTreeNode(showWebsite.next()));
			mainFrame.mainPan.websiteFileList.websiteFileTree.updateUI();	
	}
	public void sort(){
		//�����������δ�õ����е�URL�������
		URL tmpurl;
		while (this.unvisitedUrl.vector.size() > 0) {
			try {
				tmpurl = new URL(this.unvisitedUrl.deQueue());
				if (tmpurl.getQuery() != null){
					this.sourceUrl.addUrl(tmpurl.toString());
				}
				else
					this.blackList.addUrl(tmpurl.toString());
			} catch (MalformedURLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		Iterator<String> it=this.visitedUrl.set.iterator();
		while (it.hasNext()) {
			try {
				tmpurl = new URL(it.next());
				if (tmpurl.getQuery() != null){
					this.sourceUrl.addUrl(tmpurl.toString());
				}
				else
					this.blackList.addUrl(tmpurl.toString());
			} catch (MalformedURLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		
		// get����  --ȥ��
		String base = "";
		Object t1[];
		do {
			t1 = this.sourceUrl.set.toArray();
			if (t1.length != 0) {
				base = t1[0].toString();
				this.sourceUrl.removeUrl(base);
				this.resultUrl.addUrl(base);
			} else {
				break;
			}
			for (int i = 1; i < t1.length; i++) {
				try {
					if (RemoveDumplicates.equal1(base, t1[i].toString())) {
						this.sourceUrl.removeUrl(t1[i].toString());
					}
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		} while (this.sourceUrl.set.size() != 0);

		// form��--ȥ��
		do {
			t1 = this.formUrl.set.toArray();
			if (t1.length > 0) {
				base = t1[0].toString();
				this.formUrl.removeUrl(base);
				this.sourceUrl.addUrl(base);
			} else {
				break;
			}
			for (int i = 1; i < t1.length; i++) {
				try {
					if (RemoveDumplicates.equal2(base, t1[i].toString())) {
						this.formUrl.removeUrl(t1[i].toString());
					}
				} catch (MalformedURLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		} while (this.formUrl.set.size() != 0);
	}

	public Vector<String> getUnvisitedUrl() {
		return unvisitedUrl.vector;
	}

	public void setUnvisitedUrl(UrlVector unvisitedUrl) {
		this.unvisitedUrl = unvisitedUrl;
	}

	public HashSet<String> getVisitedUrl() {
		return visitedUrl.set;
	}

	public void setVisitedUrl(UrlSet visitedUrl) {
		this.visitedUrl = visitedUrl;
	}

	public HashSet<String> getBlackList() {
		return this.blackList.set;
	}

	public void setBlackList(UrlSet blackList) {
		this.blackList = blackList;
	}

	public HashSet<String> getSourceUrl() {
		return this.sourceUrl.set;
	}

	public void setSourceUrl(UrlSet sourceUrl) {
		this.sourceUrl = sourceUrl;
	}

	public HashSet<String> getResultUrl() {
		return this.resultUrl.set;
	}

	public void setResultUrl(UrlSet resultUrl) {
		this.resultUrl = resultUrl;
	}

}
