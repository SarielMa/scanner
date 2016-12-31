package com.form;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;


import com.graphicaluserinterface.Form;

public class WeakPassword implements Runnable{
	List<String> name;
	List<String> pass;
	boolean flag=true;
	int success = 0;
	boolean isExist;
	String urlStr;
	int lengthOfPasswordRecord;
	List<String> passwordList ;
	List<String> usernameList ;
	Form form;
	String nameOfName = null;
	String nameOfPassword = null;
	int active=0;
	public WeakPassword(String urlStr,Form form,List<String> nameAlternative,List<String> passwordAlternative) {
		passwordList = new ArrayList<String>();
		// TODO Auto-generated constructor stub
		name = new ArrayList<String>();
		pass = new ArrayList<String>();
		this.urlStr=urlStr;
		this.form=form;
		usernameList=nameAlternative;
		passwordList=passwordAlternative;
	}


	public List<String> getName() {
		return name;
	}

	public List<String> getPassword() {
		return pass;
	}

	public boolean judgeWake() {
		return isExist;
	}

	@Override
	public void run() {
		// TODO Auto-generated method stub
		form.startTestWakeCrack.setText("Õ£÷πºÏ≤‚");
		active=0;
		String action=null;
		GetAction getaction = new GetAction(urlStr);
		action = getaction.getAction();
		List<String> formParameter = getaction.getFormParameter();
		Iterator<String> it = formParameter.iterator();

		if (it.hasNext()) {
			nameOfName = it.next();

		}
		if (it.hasNext()) {
			nameOfPassword = it.next();
		}

		lengthOfPasswordRecord=passwordList.size();
		it=usernameList.iterator();
		while (it.hasNext()&&flag)
		{
			Thread t=new Thread(new WeakPasswordThread(WeakPassword.this, it.next(), action));
			active++;
			t.start();
		}
		while(active>0){
		}
		if(judgeWake()){
			form.isSucceed.setText("¥Ê‘⁄»ıø⁄¡Ó");
			form.name=getName();
			form.pass=getPassword();
			if(form.username.getItemCount()!=0)
				form.username.removeAllItems();
		
			for(int i=0;i<name.size();i++)
				form.username.addItem(name.get(i));
		}else
			form.isSucceed.setText("Œ¥ºÏ≤‚µΩ¬©∂¥");
		form.startTestWakeCrack.setText("≥¢ ‘ø⁄¡Ó∆∆Ω‚");
	}
	public void stop(){
		flag=false;
	}
}
