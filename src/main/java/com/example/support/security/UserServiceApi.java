
package com.example.support.security;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import cn.dev33.satoken.stp.StpUtil;
import cn.dev33.satoken.util.SaResult;

@RestController
@RequestMapping("/user")
public class UserServiceApi {

	@Autowired
	private UserRepository userRepository;

	@RequestMapping("login")
	public SaResult doLogin(String username, String pwd) {

		User u = new User();
		u.setEmail(username);
		u.setPwd(pwd);
		Example<User> example = Example.of(u);

		List<User> list = userRepository.findAll(example);
		if (list.size() > 0) {
			StpUtil.login(list.get(0).getId(),true);
			return SaResult.ok("success");
		}

		return SaResult.error("fail");

	}

	@RequestMapping("logout")
	public SaResult doLogout() {

		StpUtil.logout();
		return SaResult.ok("success");

	}

	@RequestMapping("signup")
	public SaResult doSignUp(@RequestBody User u) {

		u.setId(null);

		Example<User> example = Example.of(u);

		List<User> list = userRepository.findAll(example);
		if (list.size() > 0) {
			return SaResult.error("fail");
		}

		userRepository.save(u);

		StpUtil.login(u.getId());

		return SaResult.ok("success");

	}

	@RequestMapping("isLogin")
	public SaResult isLogin(String username, String pwd) {

		SaResult ret = StpUtil.isLogin() ? SaResult.ok("Yes") : SaResult.error("No");

		if (StpUtil.isLogin()) {
			User user = userRepository.getOne((Integer.parseInt((String) StpUtil.getLoginId())));
			ret.put("name", user.getName());
		}

		return ret;

	}

}
