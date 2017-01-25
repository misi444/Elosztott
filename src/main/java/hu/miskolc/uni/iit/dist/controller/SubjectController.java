package hu.miskolc.uni.iit.dist.controller;

import hu.miskolc.uni.iit.dist.dao.SubjectDao;
import hu.miskolc.uni.iit.dist.dao.UserDao;
import hu.miskolc.uni.iit.dist.domain.Qualification;
import hu.miskolc.uni.iit.dist.domain.Subject;
import hu.miskolc.uni.iit.dist.domain.User;
import hu.miskolc.uni.iit.dist.exception.InvalidParameterException;
import hu.miskolc.uni.iit.dist.gateway.Gateway;
import hu.miskolc.uni.iit.dist.integration.SystemMessage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import org.springframework.web.servlet.view.RedirectView;

import javax.validation.Valid;
import java.util.*;

@Controller
@RequestMapping(value= SubjectController.BASE_URL)
public class SubjectController
{
	protected static final String BASE_URL = "/subject/";
	private static final String CREATE = "create";
    private static final String DELETE = "delete";
    private static final String ENROLL = "enroll";
    private static final String UNENROLL = "unenroll";
    private static final String INDEX = "index";
	private static final String TAUGHT_SUBJECTS = "taughtsubjects";
	private static final String STUDIED_SUBJECTS = "studiedsubjects";
    private static final String ENROLLED_STUDENTS = "enrolledstudents";

	@Autowired
	private ApplicationContext context;

	@Autowired
	private UserDao userDao;

    @Autowired
    private SubjectDao subjectDao;

	@GetMapping(value=TAUGHT_SUBJECTS)
	public ModelAndView getTaughtSubjects(@ModelAttribute("error") String error)
	{
		ModelAndView modelAndView = new ModelAndView(BASE_URL + TAUGHT_SUBJECTS);
        String userName = SecurityContextHolder.getContext().getAuthentication().getName();
        Collection<Subject> subjects = subjectDao.getSubjectsByTeacher(userName);
        if (error != null) {
            modelAndView.addObject("error", error);
        }

        modelAndView.addObject("taughtSubjects", subjects);

		return modelAndView;
	}

    @GetMapping(value=ENROLLED_STUDENTS)
    public ModelAndView getEnrolledStudents(String subjectId)
    {
        ModelAndView modelAndView = new ModelAndView(BASE_URL + ENROLLED_STUDENTS);
        Subject subject = this.subjectDao.getSubjectById(subjectId);
        List<User> enrolledStudents = new ArrayList<>();
        for (String userName : subject.getStudents()) {
            enrolledStudents.add(this.userDao.findUserByName(userName));
        }

        modelAndView.addObject("enrolledStudents", enrolledStudents);

        return modelAndView;
    }

	@GetMapping(value=STUDIED_SUBJECTS)
	public ModelAndView getStudiedAndAvailableSubjects()
	{
        ModelAndView modelAndView = new ModelAndView(BASE_URL + STUDIED_SUBJECTS);
        String userName = SecurityContextHolder.getContext().getAuthentication().getName();
        Collection<Subject> studiedSubjects = subjectDao.getStudiedSubjectsByStudent(userName);
        Collection<Subject> availableSubjects = subjectDao.getAvailableSubjectsByStudent(userName);
        modelAndView.addObject("studiedSubjects", studiedSubjects);
        modelAndView.addObject("availableSubjects", availableSubjects);

        return modelAndView;
	}

	@GetMapping(value=CREATE)
	public ModelAndView createSubjectGet()
	{
		ModelAndView modelAndView = new ModelAndView(BASE_URL + CREATE);
		Subject subject = new Subject();
        subject.setTeacher(SecurityContextHolder.getContext().getAuthentication().getName());
		modelAndView.addObject("subject", subject);
		modelAndView.addObject("types", Arrays.asList("EXAM", "TERM_MARK"));
		return modelAndView;
	}

	@PostMapping(value=CREATE)
	public ModelAndView createSubjectPost(@ModelAttribute @Valid Subject subject, BindingResult bindingResult)
	{
		ModelAndView modelAndView = new ModelAndView(BASE_URL + CREATE);
		if(bindingResult.hasErrors())
		{
			List<String> errors = Validator.validate(bindingResult, "subjectId", "name", "type");
			modelAndView.addObject("errors", errors);
			modelAndView.addObject("types", Arrays.asList("EXAM", "TERM_MARK"));
			return modelAndView;
		}

		subject.setTeacher(SecurityContextHolder.getContext().getAuthentication().getName());
		subjectDao.storeSubject(subject);

		modelAndView.setViewName("redirect:" + BASE_URL + TAUGHT_SUBJECTS);
		return modelAndView;
	}

    @GetMapping(value=DELETE)
    public RedirectView deleteSubject(String id, RedirectAttributes attributes)
    {
        RedirectView redirectView = new RedirectView(BASE_URL + TAUGHT_SUBJECTS);
        try {
            this.subjectDao.deleteSubject(id);
        } catch (InvalidParameterException e) {
            attributes.addFlashAttribute("error", e.getMessage());
        }
        return redirectView;
    }

    @GetMapping(value=ENROLL)
    public ModelAndView enrollSubject(String id)
    {
        Subject subject = subjectDao.getSubjectById(id);
        String username = SecurityContextHolder.getContext().getAuthentication().getName();
        subject.getStudents().add(username);
        return new ModelAndView("redirect:" + BASE_URL + STUDIED_SUBJECTS);
    }

    @GetMapping(value=UNENROLL)
    public ModelAndView unenrollSubject(String id)
    {
        Subject subject = subjectDao.getSubjectById(id);
        String username = SecurityContextHolder.getContext().getAuthentication().getName();
        subject.getStudents().remove(username);
        return new ModelAndView("redirect:" + BASE_URL + STUDIED_SUBJECTS);
    }
	
	@GetMapping(value=INDEX)
	public ModelAndView loadStatusPage()
	{
		ModelAndView modelAndView = new ModelAndView(INDEX);
		modelAndView.addObject("users", userDao.getUsers());
		return modelAndView;
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