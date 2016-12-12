package hu.miskolc.uni.iit.dist.controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import hu.miskolc.uni.iit.dist.dao.UserDao;
import hu.miskolc.uni.iit.dist.domain.Qualification;
import hu.miskolc.uni.iit.dist.domain.User;
import hu.miskolc.uni.iit.dist.exception.InvalidParameterException;

@Controller
@RequestMapping(value=MAVController.BASE_URL)
public class MAVController
{
	protected static final String BASE_URL = "/admin/";
	private static final String SEARCH = "search";
	private static final String USER_ORIGINATION = "userorigination";
	
	private static final List<String> AVAILABLECOLORS = new ArrayList<>(3);
	private static final Map<String, String> AVAILABLESCHOOLS = new HashMap<>(3);
	private static final List<String> AVAILABLEGENDERS = new ArrayList<>(2);
	
	static
	{
		AVAILABLESCHOOLS.put("HIGHSCHOOL", "High School");
		AVAILABLESCHOOLS.put("COLLEGE", "College");
		AVAILABLESCHOOLS.put("UNIVERSITY", "University");
		
		AVAILABLECOLORS.add("red");
		AVAILABLECOLORS.add("green");
		AVAILABLECOLORS.add("blue");
		
		AVAILABLEGENDERS.add("MALE");
		AVAILABLEGENDERS.add("FEMALE");
	}
	
	@Autowired
	private UserDao userDao;
	
	@GetMapping(value=SEARCH)
	public ModelAndView loadStatusPage()
	{
		ModelAndView modelAndView = new ModelAndView(SEARCH);
		modelAndView.addObject("users", userDao.getUsers());
		return modelAndView;
	}
	
	@GetMapping(value=SEARCH + "/{userId}")
	public ModelAndView deleteUser(@PathVariable("userId") String userId)
	{
		ModelAndView modelAndView = new ModelAndView(SEARCH);
		try
		{
			userDao.deleteUser(userId);
		} catch (InvalidParameterException e)
		{
			e.printStackTrace();
		}
		modelAndView.setViewName("redirect:" + BASE_URL + SEARCH);
		return modelAndView;
	}
	
	@GetMapping(value=USER_ORIGINATION)
	public ModelAndView preload()
	{
		ModelAndView modelAndView = new ModelAndView(USER_ORIGINATION);
		resetForm(modelAndView);
		return modelAndView;
	}
	
	@PostMapping(value=USER_ORIGINATION)
	public ModelAndView loadUserOriginationPage(@ModelAttribute @Valid User userRequest, BindingResult bindingResult)
	{
		ModelAndView modelAndView = new ModelAndView(USER_ORIGINATION);

		if(bindingResult.hasErrors())
		{
			List<String> errors = Validator.validate(bindingResult, "userName", "creditBalance", "qualification", "gender", "favouriteColor");
			modelAndView.addObject("statii", errors);
			resetForm(modelAndView);
			return modelAndView;
		}
		
		userDao.storeUser(userRequest);
		
		modelAndView.setViewName("redirect:" + BASE_URL + SEARCH);
		return modelAndView;
	}
	
	private static void resetForm(ModelAndView modelAndView)
	{
		final User user = new User();
		user.setUserName("");
		user.setCreditBalance("");
		user.setQualification(Qualification.HIGHSCHOOL);
		
		modelAndView.addObject("userRequest", user);
		modelAndView.addObject("qualification", AVAILABLESCHOOLS);
		modelAndView.addObject("colors", AVAILABLECOLORS);
		modelAndView.addObject("genders", AVAILABLEGENDERS);		
	}
	
	public interface Validator
	{
		static List<String> validate(BindingResult validationResult, String... fields)
		{
			final List<String> errorList = new ArrayList<>();
			String errorMessage = "There is a problem with %s, actual value: %s. Details: %s";
			for(String field : fields)
			{
				FieldError fieldError = validationResult.getFieldError(field);
				if(fieldError != null)
				{
					errorList.add(String.format(errorMessage, fieldError.getField(), fieldError.getRejectedValue(), fieldError.getDefaultMessage()));
				}
			}
			return errorList;
		}
	}
}