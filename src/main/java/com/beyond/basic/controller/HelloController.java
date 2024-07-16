package com.beyond.basic.controller;

import java.io.File;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.beyond.basic.domain.Hello;
import com.beyond.basic.domain.Student;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

@Controller
@RequestMapping("/hello") //클래스 차원의 url매핑시에  RequestMapping 사용
// 해당 클래스가 controller(사용자의 요청을 처리하고 응답하는 평의기능)임을 명시
public class HelloController {

	// case1. 사용자가 서버에게 화면 요청(get)
	// case2. @ResponseBody가 붙은 경우 단순 String 데이터 return (get)
	@GetMapping("")
	// @RestController // Controller + 각 메서드마다 @ResponseBody
	// GetMapping을 통해 get요청을 처리하고 url패턴을 명시
	// @ResponseBody
	// responsebody를 사용하면 화면이 아닌 데이터를 return
	// 만약 여기서 responsebody가 없고 return이 스트링이면 스프링은
	// templates폴더 및에 helloworld.html화면을 찾아 리턴
	public String helloWorld(){
	// 	아래와 같이 Controller에서도 HttpServletRequest를 주입받아 사용 가능
	// public String helloWorld(HttpServletRequest request) {
	// 	System.out.println(request.getSession());
	// 	System.out.println(request.getHeader("Cookie"));
		return "helloworld";
	}

	// case3. 사용자가 json데이터 요청 (get)
	// data를 리턴하되, json형식으로 return
	// method명은 helloJson, url패턴은 /hello/json
	@GetMapping("/json")
	@RequestMapping(value = "json", method = RequestMethod.GET) //메서드 차원에서도 RequestMapping 사용 가능
	@ResponseBody
	// ResponseBody를 사용하면서 객체를 return시 자동으로 직렬화
	public Hello helloJson() throws JsonProcessingException {
		Hello hello = new Hello();
		hello.setName("hong");
		hello.setEmail("hong@naver.com");
		return hello;

		// ObjectMapper obejctMapper = new ObjectMapper();
		// String value = obejctMapper.writeValueAsString(hello);
		// return value;
	}

	// case4. 사용자가 json데이터를 요청하되, parameter 형식으로 특정 객체 요청 (get)
	// 	사용자가 get요청 중에 특정 데이터를 요청하는 경우
	@GetMapping("/param1")
	@ResponseBody
	// parameter형식: ?name=hong
	// localhost:8080/hello/model-param1?name=hong
	public Hello param1(@RequestParam(value = "name") String inputName) {
		Hello hello = new Hello();
		hello.setName(inputName);
		hello.setEmail("hongildong@naver.com");
		System.out.println(hello);
		return hello;
	}

	// 	url패턴: model-param2, 메서드: modelParam2
	// 	parameter 2개, name, email => hello 객체 생성 후 리턴
	// 	요청 방식: localhost:8080/hello/model-param2?name=hong&email=kim@naver.com
	@GetMapping("/param2")
	@ResponseBody
	public Hello param2(@RequestParam(value = "name") String inputName,
		@RequestParam(value = "email") String inputEmail) {
		Hello hello = new Hello();
		hello.setName(inputName);
		hello.setEmail(inputEmail);
		return hello;
	}

	// case5. parameter형식으로 요청하되, 서버에서 데이터바인딩하는 형식
	// 	요청 방식: localhost:8080/hello/model-param3?name=hong&email=kim@naver.com?password=1234
	@GetMapping("/param3")
	@ResponseBody
	// parameter가 많을 경우 객체로 대체가 가능, 객체에 각 변수에 맞게 알아서 바인딩(데이터 바인딩)
	// 데이터 바인딩 조건: 기본 생성자, setter
	public Hello param3(Hello hello) {
		return hello;
	}

	// case6. 서버에서 화면에 데이터를 넣어 사용자에게 리턴하는 형식 (model객체 사용)
	// RestController는 데이터 리턴 형식이므로 화면을 리턴하는 케이스에서는 사용할 수 없음
	// model을 통해서 화면에다 주입했다
	// 	요청 방식: localhost:8080/hello/model-param?name=hong
	@GetMapping("/model-param")
	public String modelParam(@RequestParam(value = "name") String inputName, Model model) {
		// model 객체에 name이라는 키 값에 value를 세팅하면 해당 key:value는 화면으로 전달
		model.addAttribute("name", inputName);
		return "helloworld";
	}

	// case7. pathvariable 방식을 통해 사용자로부터 값을 받아 화면을 리턴
	// localhost:8080/hello/model-path/hong
	// pathvariable 방식은 url을 통해 자원의 구조를 명확하게 표현함으로, 좀 더 restful한 형식이다
	// 	요청 방식: localhost:8080/hello/model-path/hong
	@GetMapping("/model-path/{name}")
	public String modelPath(@PathVariable String name, Model model) {
		model.addAttribute("name", name);
		return "helloworld";
	}

	@GetMapping("/form-view")
	public String formView() {
		// 사용자에게 name, email, password를 입력할 수 있는 화면을 주는 메서드 정의
		// 메서드명: formView, 화면명: formView
		return "formView";
	}

	// 	post요청(사용자 입장에서 서버에 데이터를 주는 상황)
	//  case1. url인코딩 방식(text만 전송)
	//  형식: key1=value1&key2=value2&key3=value3
	@PostMapping("/form-post1")    //GetMapping과 같은 url패턴 사용 가능
	@ResponseBody
	public String formPost1(
		@RequestParam(value = "name") String inputName,
		@RequestParam(value = "email") String inputEmail,
		@RequestParam(value = "password") String inputPassword) {
		// 	사용자로부터 받아온 내용 출력
		System.out.println(inputName);
		System.out.println(inputEmail);
		System.out.println(inputPassword);
		return "OK";
	}

	@PostMapping("/form-post2")    //GetMapping과 같은 url패턴 사용 가능
	@ResponseBody
	public String formPost2(@ModelAttribute Hello hello) {    // ModelAttribute 생략가능
		// 	사용자로부터 받아온 내용 출력
		System.out.println(hello);
		return "OK";
	}

	// case2. multipart/form-data 방식(text파일) 전송
	// url명: form-file-post, 메서드명: formFilePost, 화면명: form-file-view
	//  form태그 name, email, password, file
	@GetMapping("/form-file-post")
	public String formView2() {
		return "form-file-view";
	}

	@PostMapping("/form-file-post")
	@ResponseBody
	// multipart없이 Hello에 추가해주는 방법도 있음
	public String formFilePost(Hello hello, @RequestParam(value = "photo") MultipartFile photo) {
		System.out.println(hello);
		System.out.println(photo.getOriginalFilename());
		return "OK";
	}


	//  case3. js를 활용한 form데이터 전송(text)
	@GetMapping("/axios-form-view")
	public String axiosFormView() {
		// name, email, password를 전송
		return "axios-form-view";
	}

	@PostMapping("/axios-form-view")
	@ResponseBody
	// axios를 통해 넘어오는 형식이 key1=value1&key2=value2 등 url인코딩방식
	public String axiosFormPost(Hello hello) {
		System.out.println(hello);
		return "OK";
	}

	// 	case4. js를 활용한 form데이터 전송(+file)
	@GetMapping("axios-form-file-view")
	public String axiosFormFileView() {
		return "axios-form-file-view";
	}

	@PostMapping("axios-form-file-view")
	@ResponseBody
	public String axiosFormFileViewPost(Hello hello, @RequestParam(value = "file") MultipartFile file) {
		System.out.println(hello);
		System.out.println(file.getOriginalFilename());
		return "OK";
	}
	// 	case5. js를 활용한 json데이터 전송
	// url패턴: axios-json-view, 화면명: axios-json-view, get요청 메서드:동일
	// post요청 메서드: axiosJsonPost
	@GetMapping("/axios-json-view")
	public String axiosJsonView() {
		return "axios-json-view";
	}

	@PostMapping("/axios-json-view")
	@ResponseBody
	// json으로 전송한 데이터를 받을 때에는 @RequestBody 어노테이션 사용
	public String axiosJsonPost(@RequestBody Hello hello) {
		System.out.println(hello);
		return "OK";
	}
	// 	case6. js를 활용한 json데이터 전송(+file)
	@GetMapping("/axios-json-file-view")
	public String axiosJsonFileView(){
		return "axios-json-file-view";
	}

	@PostMapping("/axios-json-file-view")
	@ResponseBody
	// RequestPart는 파일과 json을 처리할 때 주로 사용하는 어노테이션
	public String axiosJsonFilePost(
		// @RequestParam("hello") String hello,
		// @RequestParam(value = "file") MultipartFile file
		// formdata를 통해 json, file(멀티미디어)을 처리할 때 ReqeustPart 어노테이션을 많이 사용
		@RequestPart("hello") Hello hello,
		@RequestPart("file") MultipartFile file
	)throws JsonProcessingException {

		System.out.println(hello);
		// String으로 받을 뒤 수동으로 객체로 변환
		// ObjectMapper objectMapper = new ObjectMapper();
		// Hello h1 = objectMapper.readValue(hello, Hello.class);
		// System.out.println(h1.getName());
		System.out.println(file.getOriginalFilename());
		return "OK";
	}

	// 	case7. js를 활용한 json데이터 전송(+여러 file)
	@GetMapping("/axios-json-multi-file-view")
	public String axiosJsonMultiFileView(){
		return "axios-json-multi-file-view";
	}

	@PostMapping("/axios-json-multi-file-view")
	@ResponseBody
	// RequestPart는 파일과 json을 처리할 때 주로 사용하는 어노테이션
	public String axiosJsonMultiFilePost(
		@RequestPart("hello") Hello hello,
		@RequestPart("files") List<MultipartFile> files
	)throws JsonProcessingException {
		System.out.println(hello);
		for(MultipartFile file : files){
			System.out.println(file.getOriginalFilename());
		}
		return "OK";
	}

	// case 8 : 중첩된 JSON 데이터 처리
	// {name: 'hongildong', email:'hong@naver.com', scores:[math:60, science:70, english:100]};
	@GetMapping("/axios-nested-json-view")
	public String axiosNestedJsonView() {
		return "axios-nested-json-view";
	}
	@PostMapping("/axios-nested-json-view")
	@ResponseBody
	public String axiosNestedJsonPost(@RequestBody Student student) {
		System.out.println(student);
		return "ok";
	}
}
