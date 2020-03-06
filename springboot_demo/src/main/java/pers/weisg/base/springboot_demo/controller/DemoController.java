package pers.weisg.base.springboot_demo.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.util.Arrays;
import java.util.Random;
import java.util.concurrent.TimeUnit;

/**
 * @author weisg
 * @description TODO
 * @date 2019/11/28 0028
 */
@RestController
@RequestMapping("/demo")
@Slf4j
public class DemoController {

    @RequestMapping(value = "/tess", method = RequestMethod.GET)
    public Object tess(@RequestParam String flag) {
        Object object = new Object();
        log.info("------------------DemoController-----------tess()");
        String str = new String("fjdslakfjdsalfkdsjakfdsjaf");
        if("00".equals(flag)){
            new Thread(() -> {
                int i = 0;
                int acount = 0;
                boolean flagB = true;
                while(flagB){
                    i++;
                    System.out.println(++acount);
                    //try { TimeUnit.SECONDS.sleep(1);} catch(InterruptedException e){ e.printStackTrace();}
                    if(i >= 1000 * 1000*1000){
                        flagB = false;
                    }
                }
            },"myThread").start();

            new Thread(()->{
                boolean f = false;
                if("00".equals(flag)){f = true;}
                String[] strs = new String[5*1024*1024];
                String str2 = "";
                int i = 0;
                while(f){
                    int r = new Random().nextInt()*100;
                    str2 += r;
                    strs[i ++] = str2;
                    System.out.println(Arrays.toString(strs));
                    //try { TimeUnit.SECONDS.sleep(1);} catch(InterruptedException e){ e.printStackTrace();}
                    if(i >= strs.length){
                        f = false;
                    }
                }
            },"myThread2").start();

        }
        return "sadfsafdsa"+str+flag;
    }
}
