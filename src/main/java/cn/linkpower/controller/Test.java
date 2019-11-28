package cn.linkpower.controller;

import java.io.IOException;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import cn.linkpower.api.WebSocketServer;

@Controller
public class Test {
	
	@RequestMapping("/resTest")
	@ResponseBody
	public String resTest(){
		return "66666";
	}
	
	@RequestMapping("/test")
	public String test(){
		System.out.println("********");
		return "test/one";
	}
	
	@RequestMapping("/testtwo")
	public String testtwo(){
		System.out.println("********");
		return "two";
	}
	
	@GetMapping("/socket/{cid}")
	public String socket(@PathVariable String cid,Model model){
		model.addAttribute("cid", cid);
		return "socket";
	}
	
	//用于触发服务器去推送消息至指定的界面上
	@ResponseBody
	@RequestMapping("/socket/push/{cid}")
	public String pushToWeb(@PathVariable String cid,String message) {  
		try {
			WebSocketServer.sendInfo(message,cid);
		} catch (IOException e) {
			e.printStackTrace();
			return cid+"#"+e.getMessage();
		}  
		return cid +" ~~~ ";
	} 
}
