package com.example.support.test;

import java.io.IOException;
import java.util.List;
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

		response.getWriter().write("[{\"name\":\"Java-8-11\",\"update\":\"2023-01-01\",\"lang\":\"en\"," + "\"items\":["
				+ "{\"id\":\"abc\",\"title\":\"abcd\",\"date\":\"2023\"},"
				+ "{\"id\":\"abc\",\"title\":\"abcd\",\"date\":\"2023\"},"
				+ "{\"id\":\"abc\",\"title\":\"abcd\",\"date\":\"2023\"},"
				+ "{\"id\":\"abc\",\"title\":\"abcd\",\"date\":\"2023\"},"
				+ "{\"id\":\"abc\",\"title\":\"abcd\",\"date\":\"2023\"},"
				+ "{\"id\":\"abc\",\"title\":\"abcd\",\"date\":\"2023\"},"
				+ "{\"id\":\"abc\",\"title\":\"abcd\",\"date\":\"2023\"},"
				+ "{\"id\":\"abc\",\"title\":\"abcd\",\"date\":\"2023\"},"
				+ "{\"id\":\"abc\",\"title\":\"abcd\",\"date\":\"2023\"},"
				+ "{\"id\":\"abc\",\"title\":\"abcd\",\"date\":\"2023\"},"
				+ "{\"id\":\"abc\",\"title\":\"abcd\",\"date\":\"2023\"},"
				+ "{\"id\":\"abc\",\"title\":\"abcd\",\"date\":\"2023\"},"
				+ "{\"id\":\"abc\",\"title\":\"abcd\",\"date\":\"2023\"},"
				+ "{\"id\":\"abc\",\"title\":\"abcd\",\"date\":\"2023\"},"
				+ "{\"id\":\"abc\",\"title\":\"abcd\",\"date\":\"2023\"},"
				+ "{\"id\":\"abc\",\"title\":\"abcd\",\"date\":\"2023\"},"
				+ "{\"id\":\"abc\",\"title\":\"abcd\",\"date\":\"2023\"},"
				+ "{\"id\":\"abc\",\"title\":\"abcd\",\"date\":\"2023\"},"
				+ "{\"id\":\"efg\",\"title\":\"efg\",\"date\":\"2023\"}]},{\"name\":\"Spring-Springboot\",\"update\":\"2023-01-01\",\"lang\":\"en\",\"items\":[{\"id\":\"abc\",\"title\":\"abcd\",\"date\":\"2023\"}]},{\"name\":\"WebServie(Soap,Rest)\",\"update\":\"2023-01-01\",\"lang\":\"en\",\"items\":[{\"id\":\"abc\",\"title\":\"abcd\",\"date\":\"2023\"}]},{\"name\":\"DB-Oracle-MSSql\",\"update\":\"2023-01-01\",\"lang\":\"en\",\"items\":[{\"id\":\"abc\",\"title\":\"abcd\",\"date\":\"2023\"}]},{\"name\":\"OWB-OBIEE-BI\",\"update\":\"2023-01-01\",\"lang\":\"en\",\"items\":[{\"id\":\"abc\",\"title\":\"abcd\",\"date\":\"2023\"}]},{\"name\":\"SQL(Dev&Tuning)\",\"update\":\"2023-01-01\",\"lang\":\"en\",\"items\":[{\"id\":\"abc\",\"title\":\"abcd\",\"date\":\"2023\"}]},{\"name\":\"Linux\",\"update\":\"2023-01-01\",\"lang\":\"en\",\"items\":[{\"id\":\"abc\",\"title\":\"abcd\",\"date\":\"2023\"}]},{\"name\":\"windows\",\"update\":\"2023-01-01\",\"lang\":\"en\",\"items\":[{\"id\":\"abc\",\"title\":\"abcd\",\"date\":\"2023\"}]},{\"name\":\"Documents\",\"update\":\"2023-01-01\",\"lang\":\"en\",\"items\":[{\"id\":\"abc\",\"title\":\"abcd\",\"date\":\"2023\"}]},{\"name\":\"Javascript(Vue)\",\"update\":\"2023-01-01\",\"lang\":\"en\",\"items\":[{\"id\":\"abc\",\"title\":\"abcd\",\"date\":\"2023\"}]},{\"name\":\"Comunication(Teamwork)\",\"update\":\"2023-01-01\",\"lang\":\"en\",\"items\":[{\"id\":\"abc\",\"title\":\"abcd\",\"date\":\"2023\"}]},{\"name\":\"Tools(Git,bash,eclipse,sqldeveloper,docker)\",\"update\":\"2023-01-01\",\"lang\":\"en\",\"items\":[{\"id\":\"abc\",\"title\":\"abcd\",\"date\":\"2023\"}]},{\"name\":\"Mindset()\",\"update\":\"2023-01-01\",\"lang\":\"en\",\"items\":[{\"id\":\"abc\",\"title\":\"abcd\",\"date\":\"2023\"}]},{\"name\":\"Health()\",\"update\":\"2023-01-01\",\"lang\":\"en\",\"items\":[{\"id\":\"abc\",\"title\":\"abcd\",\"date\":\"2023\"}]}]");
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

	@RequestMapping("/word/list")
	public List listWord() {

		return WordRepository.findAll();

	}
}
