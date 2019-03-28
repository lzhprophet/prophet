package com.prophet.data;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.Banner;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.prophet.data.services.AccessModeSRLoadData;
import com.prophet.data.services.ApiInfoLoadDataServices;
import com.prophet.data.services.EasrBaseStatisticsServices;
import com.prophet.data.services.EasrDataEtlServices;
import com.prophet.data.utils.DateUtils;

/**
 * <P>系统启动主类</P>
 * 
 * @author #lvzhh
 */
@SpringBootApplication
public class Application implements CommandLineRunner {

	@Autowired
	private EasrDataEtlServices dataEtl;
	
	@Autowired
	private  AccessModeSRLoadData accessModeSRLoadData;
	
	@Autowired
	private EasrBaseStatisticsServices statisticsServices;
	
	@Autowired
	private ApiInfoLoadDataServices apiInfoLoadDataServices;
	
    public static void main(String... args) throws Exception {
        SpringApplication app = new SpringApplication(Application.class);
        app.setBannerMode(Banner.Mode.OFF);
        app.run(args);
        
    }

    /**
     * access command line arguments
     * 启动命令 java -jar target/xxxx.jar "cleandata local" 
     * 
     * 
     */
    @Override
    public void run(String... args) throws Exception {
    	boolean res = false;
    	
    	
    	//String day = "2019-01-26";
    	String day = DateUtils.getYesterday("yyyy-MM-dd");
    
    	 if (args.length > 0) {
    		 if("cleandata".equals(args[0])){
    			 res = dataEtl.runExtract(args[1],day);
    		 }else if("base-statistics".equals(args[0])){
    			 statisticsServices.runBaseStatisByDay(day);
    			 statisticsServices.runBaseStatisByTimeslot(day);
    		 }else if("result-load".equals(args[0])){
    			 //dataEtl.runLoad(day);
    			 //dataEtl.runLoadTimeslot(day);
    			 accessModeSRLoadData.loadAccessModeSRData(day);
    		 }else if("api-info-sync".equals(args[0])){
    			apiInfoLoadDataServices.loadApiInfo(day);
    		 }else if("run-all".equals(args[0])){
    			 res = dataEtl.runExtract(args[1],day);
    			 statisticsServices.runBaseStatisByDay(day);
    			 statisticsServices.runBaseStatisByTimeslot(day);
    			 dataEtl.runLoad(day);
    			 dataEtl.runLoadTimeslot(day);
    			 accessModeSRLoadData.loadAccessModeSRData(day);
    			 apiInfoLoadDataServices.loadApiInfo(day);
    		 }else{
    			 System.out.println(">> command不能识别...程序退出");
    		 }
         } else {
             System.out.println(">> 未执行任何任务...程序退出");
         }

 		System.exit(res ? 0 : -1);

    } 
}
