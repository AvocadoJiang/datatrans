package xin.nbjzj.datatrans;

import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import org.slf4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import it.sauronsoftware.cron4j.Scheduler;
import okhttp3.Response;

import org.springframework.boot.Banner;
import org.springframework.boot.CommandLineRunner;

import xin.nbjzj.datatrans.service.DataTransService;
/**
 * Hello world!
 *
 */
@SpringBootApplication
public class SpringBootConsoleApplication implements CommandLineRunner
{
	
	
	
    public static void main(String[] args) throws Exception {

        //disabled banner, don't want to see the spring logo
        SpringApplication app = new SpringApplication(SpringBootConsoleApplication.class);
     
        app.setBannerMode(Banner.Mode.OFF);
        app.run(args);
        

    }

    @Value("${call.time}")
    private String callTime;
    @Autowired
    private DataTransService dataTransService;
	@Value("${newcard.api.count}")
    private Integer newCardCount;
	@Value("${disabledinfo.api.count}")
    private Integer disabledInfoCount;
	@Value("${dibaoinfo.api.count}")
    private Integer dibaoInfoCount;
  
    
   
    protected static final Logger logger = LoggerFactory.getLogger(SpringBootConsoleApplication.class);
    @Override
    public void run(String... args) throws Exception {
    	
    	Scheduler s = new Scheduler();
    	logger.info("程序cron定时表达式："+callTime);
    	s.schedule(callTime, new Runnable() {
            public void run() {
            	try {
					dataTransService.getNewCardData(newCardCount);
					dataTransService.getDisabledInfoData(disabledInfoCount);
					dataTransService.getDibaoInfoData(dibaoInfoCount);
				} catch (IOException e) {
					e.printStackTrace();
				}
            }
        });
    	s.start();
    	
    	
    }
    
    
    
    
}
