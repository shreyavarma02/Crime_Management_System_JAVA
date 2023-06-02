package com.example.StudentCrud.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.TreeMap;
import java.util.Map.Entry;

import javax.lang.model.util.ElementScanner14;

import com.example.StudentCrud.service.StudentService;
import com.example.StudentCrud.service.joinService;

//import .StudentService;
import org.springframework.stereotype.Controller;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
//import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;
//import org.springframework.web.bind.annotation.RestController;
//import .Student;
//import .StudentService;
import com.example.StudentCrud.domain.User;
import com.example.StudentCrud.domain.Upd;
import com.example.StudentCrud.domain.Closedcase;
import com.example.StudentCrud.domain.Fir;
import com.example.StudentCrud.domain.JoinResult;
//import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import com.example.StudentCrud.domain.Po;
import com.example.StudentCrud.domain.Pi;


@Controller
public class StudentController {
	
	 @Autowired
	    private StudentService service;
	@Autowired
		private joinService service1;

		@GetMapping("/register")
		public String reg(Model model){
			model.addAttribute("user", new User());
			return "signup_form";
		}
		@RequestMapping(value="/changetoclose", method = RequestMethod.POST)
		public ModelAndView changetoclose(@RequestParam("value5") int valueFive){
			Upd u=service.find(valueFive);
			int i=(int) 1;
			u.setClosed(i);
			service.saveupd(u);
			ModelAndView mav = new ModelAndView("new1");
			int yes=1;
			
			mav.addObject("addedornot", yes);
			return mav;
			//reload page?
		}
		@RequestMapping(value="/updatecase", method = RequestMethod.POST)
		public ModelAndView updatecase(@RequestParam("value6") int valueSix,@RequestParam("value7") String valueSeven,@RequestParam("value8") String valueEight,@RequestParam("value9") int valueNine){
			int isornot=service.calc(valueSix);  //caseid
			System.out.println("IS: "+isornot);
			if(isornot==1)
			{
				Upd u=service.find(valueSix);
			
				if(service.getpid(valueSix)==valueNine)
				{
					
					u.setUpdates(u.getUpdates()+valueSeven);
					u.setAddupd(valueEight);
					service.saveupd(u);
					ModelAndView mav = new ModelAndView("new1");
					int yes=1;
					
					mav.addObject("updatedornot", yes);
					return mav;
				}
			}
			else
			{
				System.out.println("Else");
				
				System.out.println("GETPID"+service.getpid(valueSix));
				if(service.getpid(valueSix)==valueNine)
				{
					Upd first = new Upd(valueSix,valueSeven,valueEight,(int) 0);
					service.saveupd(first);
					ModelAndView mav = new ModelAndView("new1");
					int yes=1;
					
					mav.addObject("updatedornot", yes);
					return mav;
				}
			}
			//throw new RuntimeException("Cannot update case");
			//reload page?
			ModelAndView mav = new ModelAndView("error");
			return mav;
		}
		@RequestMapping(value="/viewopencases", method = RequestMethod.POST)
		public ModelAndView viewopen(@RequestParam("value4") int valueFour){
			System.out.println(valueFour); //the pid
			List<Fir> u=service.getopencases(valueFour);
			System.out.println(u);
			ModelAndView mav = new ModelAndView("try2");
			mav.addObject("open", u);
			return mav;
		}
		@PostMapping("/process_register")
		public String processRegister(@ModelAttribute("user") User usr) {
		//BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
		//String encodedPassword = passwordEncoder.encode(user.getPassword());
		//user.setPassword(encodedPassword);
		System.out.println("save");
		if (service.existsornot(usr.getUsername())==1) {
			//throw new RuntimeException("Username already exists");
			return "error";
		}
		else{
			if(usr.getRole().compareToIgnoreCase("PoliceOfficer")==0)// || (service.getrole(usr.getUsername())).compareToIgnoreCase("PoliceIncharge")==0)
			{
				//if permitted
				usr.setSecuritykey(usr.getSecuritykey().replace(",",""));
				System.out.println(service.pidmatchsec(usr.getPid(),usr.getSecuritykey()));
				if(service.pidmatchsec(usr.getPid(),usr.getSecuritykey())==1)
				{
					service.save(usr);
				}
				else{
					//throw new RuntimeException("Not authorized");
					return "error";
				}
			}
			else if(usr.getRole().compareToIgnoreCase("PoliceIncharge")==0)// || (service.getrole(usr.getUsername())).compareToIgnoreCase("PoliceIncharge")==0)
			{
				//if permitted
				////usr.setSecuritykey(usr.getSecuritykey().replace(",",""));
				//System.out.println(service.pidmatchsec1(usr.getPid(),usr.getSecuritykey()));
				System.out.println(usr.getPid()+usr.getSecuritykey());
				if(service.pidmatchsec1(usr.getPid(),usr.getSecuritykey())==1)
				{
					service.save(usr);
				}
				else{
					//throw new RuntimeException("Not authorized");
					return "error";
				}
			}
			//do same for pi--->else if
			else
			{
				service.save(usr);
			}
			
		}
		
		
		return "register_success";
	}
	@GetMapping("/fir/{username}")
	    public ModelAndView adding(Model model,@PathVariable(name = "username") String username) {
	        //Model model;
			model.addAttribute("fir", new Fir());
	        //return "fir";
			ModelAndView mav = new ModelAndView("fir");
			mav.addObject("username", username);
			return mav;
	    }
	@PostMapping("/savefir")
	public ModelAndView saveCustomer(@ModelAttribute("fir") Fir f) {
		System.out.println("I am in save");
		System.out.println(f.getCaseid());
		System.out.println(f.getUsername());
		service.savefir(f);
		//return "new";
		ModelAndView mav = new ModelAndView("new");
		mav.addObject("username", f.getUsername());
		return mav;
	}

	
	@GetMapping("/updates/{username}")
	public String viewupdates(Model model,@PathVariable(name = "username") String username) {
		//List<JoinResult> updates = service1.getJoinResultForUsername("PES1UG20CS403");
		System.out.println(username);
		List<JoinResult> updates = service1.getJoinResultForUsername(username);
		System.out.println(updates);
		model.addAttribute("updates", updates);
		// System.out.println(updates);
		return "updatesc";
	}
	@RequestMapping(value="/createleader", method = RequestMethod.POST)
	public ModelAndView createleader(Model model)
	{
		service.poupdate();
		List<Po> q=service.posort();
		ModelAndView mav = new ModelAndView("ranking");
		mav.addObject("ranks", q);
		return mav;
		
	}
	@RequestMapping(value="/assigncase", method = RequestMethod.POST)
	public ModelAndView assigncase(@RequestParam("value3") int valueThree)
	{
		
		String category=service.gettype(valueThree);// : FIR table
		System.out.println(category);
		List<Po> p=service.findPo(category); //:PO table
		//add Pid+active cases to a hashmap
		Map<Integer, Integer> map = new HashMap<>();
		for (int i = 0; i < p.size(); i++) 
		{
			System.out.println("Active cases");
			System.out.println(p.get(i).getAssigned()-p.get(i).getClosedcases());
			map.put(p.get(i).getPid(),p.get(i).getAssigned()-p.get(i).getClosedcases());
			//System.out.println(p.get(i).getCaseid());
		}
		System.out.println(map);
		//get min value's key
		Entry<Integer, Integer> min = null;
		for (Entry<Integer, Integer> entry : map.entrySet()) {
			if (min == null || min.getValue() > entry.getValue()) {
				min = entry;
			}
		}
		int minpid=min.getKey();
		System.out.println(minpid);
		//insert pid into table FIR
		Fir fp=service.findCaseid(valueThree);
		//System.out.println(fp.getPid());
		//System.out.println(fp.getDescription());
		//fp.setPid(6);
		fp.setPid(minpid);
		service.savefir(fp);
		//no of cases assigned+1 in table PO
		Po p1=service.findByPid(minpid);
		int ac=p1.getAssigned();
		ac=ac+1;
		p1.setAssigned(ac);
		System.out.println(p1.getAssigned());
		service.savepo(p1);

		//~~~~~~~~~~~~~~~~~~
		//try getting all records where closed==1
		//List<Map<int, Upd>> u=service.getTotal();
		/*List<Upd> u=service.getTotal();
		for (int i = 0; i < u.size(); i++) 
		{
			System.out.println(u.get(i).getCaseid());
		}*/
		//System.out.println(u);
		/*for (Map<int, Upd> map : u) {
			for (Entry<int, Upd> entry : map.entrySet()) {
				System.out.println(entry.getKey() + ": " + entry.getValue());
			}
			//System.out.println(map.getCaseid());
		}*/
		
		ModelAndView mav = new ModelAndView("new2");
		int yes=1;
		
		mav.addObject("assignornot", yes);
		mav.addObject("mpid", minpid);
		return mav;

	}

	

	@RequestMapping(value="/process_caseid", method = RequestMethod.POST)
	public ModelAndView processcase(@RequestParam("value1") int valueOne)
	{
		ModelAndView mav = new ModelAndView("try");
		Upd u=service.find(valueOne);
		System.out.println(valueOne);
		mav.addObject("caseid", valueOne);
		mav.addObject("updates", u.getUpdates());
		mav.addObject("add_updates", u.getAddupd());
		mav.addObject("closed", u.getClosed());
		return mav;
	}
	@RequestMapping(value="/process_closecase", method = RequestMethod.POST)
	public ModelAndView processclosecase(@RequestParam("value2") int valueTwo)
	{
		int check=service.valclose(valueTwo);
		int count=service.getCount(valueTwo);
		System.out.println("CHECK"+check+count);
		if(check==1 && count==0)
		{
			Closedcase first = new Closedcase(valueTwo);
			service.saveclosed(first);
			//also how to do +1 in po ka closed case column
			
			int pid=service.getpid(valueTwo); //:from the fir table
			 System.out.println(pid);
			 //get entire po ka row from po table->update with +1(setter)->save
			Po p=service.findByPid(pid);
			System.out.println(p);
			 //Po a = service.findByCaseid(valueTwo); :from the PO table
			 int cc=p.getClosedcases();
			 cc=cc+1;
			 p.setClosedcases(cc);
			 System.out.println(p.getClosedcases());
			 service.savepo(p);
			 


			ModelAndView mav = new ModelAndView("new2");
			int yes=1;
			
			mav.addObject("addedornot", yes);
			return mav;
		}
		else
		{
			//throw new RuntimeException("Not yet closed, cannot add!");
			ModelAndView mav = new ModelAndView("error");
			return mav;
		}
		
	}
	@PostMapping("/process_login")
		public ModelAndView processLogin(@ModelAttribute("user") User usr) {
			//check if username is present in the table
			if (service.existsornot(usr.getUsername())==0) {
				//throw new RuntimeException("Username does not exist");
				ModelAndView mav = new ModelAndView("error");
			return mav;
			}
			else
			{
				//check if password matches
				if(service.matches(usr.getUsername(),usr.getPassword())==1)
				{
					//return page depending on role
					if((service.getrole(usr.getUsername())).compareToIgnoreCase("Civilian")==0)
					{
						ModelAndView mav = new ModelAndView("new");
						String username=usr.getUsername();
						int no=0;
						mav.addObject("username", username);
						//mav.addObject("updatedornot", no);
						
						return mav;
					}
					else if((service.getrole(usr.getUsername())).compareToIgnoreCase("PoliceOfficer")==0)
					{
						ModelAndView mav = new ModelAndView("new1");
						String usrname=usr.getUsername();
						String passw=usr.getPassword();
						int p=service.getpidusr(usrname);
						int no=0;
						mav.addObject("username", usrname);
						mav.addObject("password", passw);
						mav.addObject("pid", p);
						mav.addObject("updatedornot", no);
						mav.addObject("addedornot", no);
						return mav;
					}
					else if((service.getrole(usr.getUsername())).compareToIgnoreCase("PoliceIncharge")==0)
					{
						ModelAndView mav = new ModelAndView("new2");
						int no=0;
						String usrname=usr.getUsername();
						String passw=usr.getPassword();
						mav.addObject("username", usrname);
						mav.addObject("password", passw);
						mav.addObject("addedornot", no);
						mav.addObject("assignornot", no);
						List<Po> p = service.listAll2();
	        			mav.addObject("po", p);
						return mav;
					}
					else if((service.getrole(usr.getUsername())).compareToIgnoreCase("HeadOfficer")==0)
					{
						
						ModelAndView mav = new ModelAndView("new3");
						List<Pi> p = service.listAll1();
	        			mav.addObject("pi", p);
						return mav;
					}
					else
					{
						//throw new RuntimeException("No role");
						ModelAndView mav = new ModelAndView("error");
						return mav;
					}
				}
				else
				{
					//throw new RuntimeException("Username and Password does not match");
					ModelAndView mav = new ModelAndView("error");
					return mav;
				}
				
			}
		
	}

	@GetMapping("/login")
	public String log(Model model){
		model.addAttribute("user", new User());
		return "login";
	}

		/*@GetMapping("/controller")
		public String chek(){
			return "In controller";
		}*/
		

	    @GetMapping("/")
	    public String viewHomePage(Model model) {
	        
	        return "index";
	    }

		@PostMapping("/savepi")
	    //@RequestMapping(value = "/save", method = RequestMethod.POST)
	    public ModelAndView savePi(@ModelAttribute("pi") Pi p) {
			
			//System.out.println(std.getStudentname());
			
			//System.out.println(std.getCourse());
	        service.savepi(p);
			ModelAndView mav = new ModelAndView("new3");
			List<Pi> q = service.listAll1();
			mav.addObject("pi", q);
			return mav;
	        //return "new3";
	    }
		@PostMapping("/savepo")
	    //@RequestMapping(value = "/save", method = RequestMethod.POST)
	    public ModelAndView savePo(@ModelAttribute("po") Po p) {
			
			//System.out.println(std.getStudentname());
			
			//System.out.println(std.getCourse());
	        service.savepo(p);
			ModelAndView mav = new ModelAndView("new2");
			List<Po> q = service.listAll2();
			mav.addObject("po", q);
			return mav;
	        //return "new2";
	    }
		@GetMapping("/newentrypo")
		public String add1(Model model) {
			System.out.println("Finally");
	        model.addAttribute("po", new Po());
	        return "newentrypo";
	    }
		@GetMapping("/newentrypi")
		public String add(Model model) {
			System.out.println("Finally");
	        model.addAttribute("pi", new Pi());
	        return "newentrypi";
	    }
		@RequestMapping("/edit/{pid}")
	    public ModelAndView showEditStudentPage(@PathVariable(name = "pid") int pid) {
			System.out.println("EDITING");
	        ModelAndView mav = new ModelAndView("editpi");
	        Pi p = service.getpi(pid);
	        mav.addObject("pi", p);
	        return mav;
	        
	    }
		@RequestMapping("/delete/{pid}")
	    public ModelAndView deletepi(@PathVariable(name = "pid") int pid) {
	        service.deletepi(pid);
	        //return "new3";
			ModelAndView mav = new ModelAndView("new3");
			List<Pi> q = service.listAll1();
			mav.addObject("pi", q);
			return mav;
	    }
		@RequestMapping("/edit1/{pid}")
	    public ModelAndView showEditStudentPage1(@PathVariable(name = "pid") int pid) {
			System.out.println("EDITING");
	        ModelAndView mav = new ModelAndView("editpo");
	        Po p = service.getpo(pid);
	        mav.addObject("po", p);
	        return mav;
	        
	    }
		@RequestMapping("/delete1/{pid}")
	    public ModelAndView deletepo(@PathVariable(name = "pid") int pid) {
	        service.deletepo(pid);
			ModelAndView mav = new ModelAndView("new2");
			List<Po> q = service.listAll2();
			mav.addObject("po", q);
			return mav;
	        //return "new2";
	    }
	    @PostMapping("/getinference")
    	public String getPieChart(Model model) {
        Map<String, Integer> graphData = new TreeMap<>();
		List<String> a=service.getinf();
		System.out.println(a);
		for(int i=0;i<a.size();i++)
		{
			graphData.put(a.get(i).split(",", 2)[0],Integer.parseInt(a.get(i).split(",", 2)[1]));
			//System.out.println(a.get(i).split(",", 2)[0]);
			//str.split("@", 2)
		}
       /*graphData.put("2016", 147);
        graphData.put("2017", 1256);
        graphData.put("2018", 3856);
        graphData.put("2019", 19807);*/
        model.addAttribute("chartData", graphData);
        return "pie";
    }
	/*/
		@PostMapping("/save")
	    //@RequestMapping(value = "/save", method = RequestMethod.POST)
	    public String saveStudent(@ModelAttribute("student") Student std) {
			System.out.println("save");
			System.out.println(std);
			System.out.println(std.getId());
			System.out.println(std.getDateofborrow());
			//System.out.println(std.getStudentname());
			
			//System.out.println(std.getCourse());
	        service.save(std);
	        return "redirect:/";
	    }

	    @RequestMapping("/edit/{id}")  --> /po/001
	    public ModelAndView showEditStudentPage(@PathVariable(name = "id") String id) {
			System.out.println("EDITING");
	        ModelAndView mav = new ModelAndView("new1");
	        Student std = service.get(id);
	        mav.addObject("student", std);
	        return mav;
	        
	    }
	    @RequestMapping("/delete/{id}")
	    public String deletestudent(@PathVariable(name = "id")String id) {
	        service.delete(id);
	        return "redirect:/";
	    }*/
}