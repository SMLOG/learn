package com.example.support.test;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.support.security.User;

import cn.dev33.satoken.stp.StpUtil;
import cn.dev33.satoken.util.SaResult;

@RestController
public class TestApiController {

	@Autowired
	private WordRepository WordRepository;

	@RequestMapping(("/list"))
	public void list(HttpServletResponse response) throws IOException {

		response.setContentType("application/json;charset=utf-8");

		response.getWriter().write("["
				+ "{\"name\":\"Words\",\"update\":\"2023-01-01\",\"type\":\"word\"},"
				+ "{\"name\":\"Spring-Springboot\",\"update\":\"2023-01-01\",\"lang\":\"en\",\"type\":\"media\"},"
				+ "{\"name\":\"WebServie(Soap,Rest)\",\"update\":\"2023-01-01\",\"lang\":\"en\"},"
				+ "{\"name\":\"DB-Oracle-MSSql\",\"update\":\"2023-01-01\",\"lang\":\"en\"},"
				+ "{\"name\":\"OWB-OBIEE-BI\",\"update\":\"2023-01-01\",\"lang\":\"en\"},"
				+ "{\"name\":\"SQL(Dev&Tuning)\",\"update\":\"2023-01-01\",\"lang\":\"en\"},"
				+ "{\"name\":\"Linux\",\"update\":\"2023-01-01\",\"lang\":\"en\"},"
				+ "{\"name\":\"windows\",\"update\":\"2023-01-01\",\"lang\":\"en\"},"
				+ "{\"name\":\"Documents\",\"update\":\"2023-01-01\",\"lang\":\"en\"},"
				+ "{\"name\":\"Javascript(Vue)\",\"update\":\"2023-01-01\",\"lang\":\"en\"},"
				+ "{\"name\":\"Comunication(Teamwork)\",\"update\":\"2023-01-01\",\"lang\":\"en\"},"
				+ "{\"name\":\"Tools(Git,bash,eclipse,sqldeveloper,docker)\",\"update\":\"2023-01-01\",\"lang\":\"en\"},"
				+ "{\"name\":\"Mindset()\",\"update\":\"2023-01-01\",\"lang\":\"en\"},"
				+ "{\"name\":\"Health()\",\"update\":\"2023-01-01\",\"lang\":\"en\"}]");
	}

	@RequestMapping("/word/save")
	public Word saveWord(@RequestBody Word word) {

		Word tmpWord = new Word();
		tmpWord.setEn(word.getEn());

		Example<Word> ex = Example.of(tmpWord);

		Optional<Word> one = WordRepository.findOne(ex);

		if (one.isPresent()) {
			word.setId(one.get().getId());
		}

		WordRepository.save(word);

		return word;

	}

	@RequestMapping("/word/list/Words")
	public List listWord() {

		return WordRepository.findAll();

	}
	@RequestMapping("/media/list/Spring-Springboot")
	public List listMedia() {

		ArrayList<Map<String, Object>> ret = new ArrayList<Map<String,Object>>();
		
		HashMap<String, Object> row = new HashMap<String,Object>();
		row.put("title", "test");
		row.put("link", "https://vodakm.zeenews.com/vod/ZEE_ENGLISH/SAVING___CURRENTACCOUNT.mp4/SAVING___CURRENTACCOUNT.mp4?id=1");
		
		ret.add(row);
		
		row = new HashMap<String,Object>();
		row.put("title", "test2");
		row.put("link", "https://vodakm.zeenews.com/vod/ZEE_ENGLISH/SAVING___CURRENTACCOUNT.mp4/SAVING___CURRENTACCOUNT.mp4?id=2");
		
		ret.add(row);
		
		return ret;

	}
	
}
