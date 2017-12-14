package qiniu.qiniuupdate;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.IOException;
import java.util.Timer;
import java.util.TimerTask;

import org.apache.tools.ant.util.FileUtils;

import com.google.gson.Gson;
import com.qiniu.common.QiniuException;
import com.qiniu.common.Zone;
import com.qiniu.http.Response;
import com.qiniu.storage.Configuration;
import com.qiniu.storage.UploadManager;
import com.qiniu.storage.model.DefaultPutRet;
import com.qiniu.util.Auth;

/**
 * Hello world!
 *
 */
public class App 
{

	  static String accessKey = "zWKNodK3PtYAHN_bzNfruskwKOTAsggj-ocQYH3x";
      static String secretKey = "lk7xfW9AdLk9mBZu7s2bYB8SZtCeGgTlY62IBipr";
      static String bucket = "jiafan";
      static String localpath = "G:\\cpsgameapk\\";
      
	public static void ceshi1( String filepath )
    {
        System.out.println( "Hello World!" );        
        Auth auth = Auth.create(accessKey, secretKey);
        String upToken = auth.uploadToken(bucket);
        System.out.println(upToken);
        
        
      //构造一个带指定Zone对象的配置类
        Configuration cfg = new Configuration(Zone.zone0());
        //...其他参数参考类注释

        UploadManager uploadManager = new UploadManager(cfg);
        //...生成上传凭证，然后准备上传
        
        //如果是Windows情况下，格式是 D:\\qiniu\\test.png
        String localFilePath = localpath+File.separator+filepath;
        //默认不指定key的情况下，以文件内容的hash值作为文件名
        String key = filepath;

       

        try {
            Response response = uploadManager.put(localFilePath, key, upToken);
            //解析上传成功的结果
            DefaultPutRet putRet = new Gson().fromJson(response.bodyString(), DefaultPutRet.class);
            System.out.println(putRet.key);
            System.out.println(putRet.hash);
            File file = new File(localFilePath);
            
            file.renameTo(new File(localpath+File.separator+"hadupload_"+filepath));
        } catch (QiniuException ex) {
            Response r = ex.response;
            System.err.println(r.toString());
            try {
                System.err.println(r.bodyString());
            } catch (QiniuException ex2) {
                //ignore
            }
        }


    }
    
    private Windowapps mWindowapps; 
    //"c://Apps//qiniutemp//record.gson"
    public void oncreate(Windowapps mwinWindowapps){
    	
    	mWindowapps=mwinWindowapps;
    	//本地记录回显
    	readlocalRecord();
    	
    	mWindowapps.button_start.addActionListener(new ActionListener() {
			
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				log("开始");
				//設置數據
				setfield();
				
				//開始掃描上傳
				startTimerGetMessage();
			}

		

			
		});
    	mWindowapps.button_stop.addActionListener(new ActionListener() {
			
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				log("停止");
				ISGETTINGMESSAGE=false;
			}
		});
    	
    }
    //掃描上傳
	private void scanandupload() {
		// TODO Auto-generated method stub
		File file = new File(localpath);
		String[] list = file.list();
		for (int i = 0; i < list.length; i++) {
			System.out.println(list[i]);
			if (list[i].contains("hadupload_")) {
				
			}else {
				ceshi1(list[i]);
			}
		}
		
	}
    //設置數據
    private void setfield() {
		// TODO Auto-generated method stub
    	    accessKey = mWindowapps.textField_accesskey.getText().trim();
         secretKey = mWindowapps.textField_secretkey.getText().trim();
          bucket = mWindowapps.textField_bucket.getText().trim();
           localpath = mWindowapps.path_textField.getText().trim();
	}
    
    //关闭窗口
    public void ondestory(){
    	System.out.println("ondestory");
    	log("ondestory");
    	LocalRecodBean localRecodBean = new LocalRecodBean();
    	localRecodBean.setAccessKey(accessKey);
    	localRecodBean.setBucket(bucket);
    	localRecodBean.setLocalpath(localpath);
    	localRecodBean.setSecretKey(secretKey);
    	Gson gson = new Gson();
    	String json = gson.toJson(localRecodBean);
    	if (Fileutils.isExist(localrecordfilepath)) {
    		Filetextutils.write(localrecordfilepath, false, json, "utf-8");
		}else {
			
			try {
				File file = new File(localrecordfilepath);
				file.createNewFile();
				String jsont=json.trim();
				Filetextutils.write(localrecordfilepath, false, json, "utf-8");
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
		}
    	
    	
    }
    String localrecordfilepath=System.getProperty("user.home")+File.separator+"qiniu_record.gson";
    //读取本地记录复原
	private void readlocalRecord() {
		// TODO Auto-generated method stub
		//localrecordfilepath 判断是否有这个文件夹
		if(Fileutils.isExist(localrecordfilepath)){
			log("本地记录存在，进行写入"+localrecordfilepath);
			String read = Filetextutils.read(localrecordfilepath, "utf-8");
			System.out.println(read);
			Gson gson = new Gson();
			LocalRecodBean recodbean = gson.fromJson(read.replace("null", "").trim(), LocalRecodBean.class);
			accessKey=recodbean.getAccessKey();
			secretKey=recodbean.getSecretKey();
			bucket=recodbean.getBucket();
			localpath=recodbean.getLocalpath();
			mWindowapps.textField_accesskey.setText(accessKey);
			mWindowapps.textField_bucket.setText(bucket);
			mWindowapps.textField_secretkey.setText(secretKey);
			mWindowapps.path_textField.setText(localpath);
			
		}else {
			log("本地记录不存在"+localrecordfilepath);
		}
		
	}
    
	
	String logtext="";
	private boolean ISGETTINGMESSAGE=false;
	private Timer mTimer;
	
    private void log(String text){
    	//logtext=logtext+"\r\n"+text;
    	
    	mWindowapps.textPane.append("\r\n"+text);
    }
    /**
     * 定时获取message消息
     */
    private void startTimerGetMessage(){

    	if (ISGETTINGMESSAGE){
    		log("正在掃描，請先停止。。。。。");
    		return;
    	}else {
    		ISGETTINGMESSAGE=true;
    		mTimer=new Timer();
    		mTimer.schedule(new TimerTask() {
    			public void run() {
    				//Logger.d("获取im消息：=========================");
    				//getRecyDataFromnet(1,false,false);
    				scanandupload();
    				log("正在每隔5分鐘上傳一次。。");
    			}
    		},1000,300000);// 设定指定的时间time,此处为60000毫秒  300000
    	}


    }
    /**
     * 停止获取消息
     */
    private void stopGetMessage(){
    	if (ISGETTINGMESSAGE){
    		ISGETTINGMESSAGE=false;
    		mTimer.cancel();
    		mTimer=null;
    		return;
    	}else {

    	}
    }
    
}
