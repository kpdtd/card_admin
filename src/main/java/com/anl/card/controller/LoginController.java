package com.anl.card.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.IncorrectCredentialsException;
import org.apache.shiro.authc.UnknownAccountException;
import org.apache.shiro.subject.Subject;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/login")
public class LoginController extends BaseController {

//	@Autowired
//	SysUsersService sysUsersService;
//	@Autowired
//	SysRolesService sysRolesService;

	@RequestMapping("userLogin")
	public void userLogin(HttpServletRequest request, HttpServletResponse response){//, SysUsers sysUsers) throws Exception {
		try {
//			sysUsers = TokenManager.login(sysUsers, true);
//			Map<String, Object> data = new HashMap<>();
//			data.put("userName", sysUsers.getUserName());
//			List<SysRolesExt> list = sysRolesService.getListByUserId(sysUsers.getUsersId().toString());
//			for (SysRolesExt sysRolesExt : list) {
//				if(sysRolesExt.getUsersId()!=null){
//					data.put("rolesName", sysRolesExt.getRoleName());
//				}
//			}
//            request.getSession().setAttribute("loginUser", data);
            setJsonSuccess(response, null, "index/getPage", "url");
			//未添加权限管理时登录
			/*SysUsers users = sysUsersService.findUserByUserName(sysUsers.getUserAccount());
			if(MD5Util.getMD5String(sysUsers.getUserPassword()).equals(users.getUserPassword())){
				request.getSession().setAttribute("loginUser", users);
				setJsonSuccess(response, null, "index/getPage", "url");
			}else{
				setJsonFail(response, null, 1100, "密码错误！");
			}*/
		} catch(IncorrectCredentialsException e){
			e.printStackTrace();
			setJsonFail(response, null, 1100, "密码错误！");
		} catch(UnknownAccountException e) {
			e.printStackTrace();
			setJsonFail(response, null, 1100, "该用户不存在！");
		} catch(AuthenticationException e){
			e.printStackTrace();
			setJsonFail(response, null, 1100, "该用户被禁用！");
		} catch (Exception e) {
			e.printStackTrace();
			setJsonFail(response, null, 1100, "其他错误,请联系管理员！");
		}
	}
	
	@RequestMapping("logout")
	public void logout(HttpServletRequest request, HttpServletResponse response) throws Exception{
		Subject subject = SecurityUtils.getSubject();
	    if (subject.isAuthenticated()) {  
	        subject.logout(); // session 会销毁，在SessionListener监听session销毁，清理权限缓存  
	    }
        setJsonSuccess(response, null, "../login.jsp", "url");
	    //未添加权限管理
		/*request.getSession().setAttribute("loginUser", null);
		setJsonSuccess(response, null, "../login.jsp", "url");*/
	}
}
