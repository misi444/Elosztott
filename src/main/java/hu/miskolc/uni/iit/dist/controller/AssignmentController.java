package hu.miskolc.uni.iit.dist.controller;

import hu.miskolc.uni.iit.dist.dao.AssignmentDao;
import hu.miskolc.uni.iit.dist.dao.SubjectDao;
import hu.miskolc.uni.iit.dist.dao.UserDao;
import hu.miskolc.uni.iit.dist.domain.*;
import hu.miskolc.uni.iit.dist.domain.Assignment;
import hu.miskolc.uni.iit.dist.exception.InvalidParameterException;
import org.apache.commons.io.FilenameUtils;
import org.hibernate.validator.constraints.NotBlank;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.core.io.FileSystemResource;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import org.springframework.web.servlet.view.RedirectView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;
import java.io.*;
import java.util.*;

@Controller
@RequestMapping(value= AssignmentController.BASE_URL)
public class AssignmentController
{
	protected static final String BASE_URL = "/assignment/";
	private static final String UPLOAD = "upload";
    private static final String DOWNLOAD = "download";
    private static final String DELETE = "delete";
    private static final String INDEX_STUDENT = "indexstudent";
	private static final String INDEX_TEACHER = "indexteacher";
	private static final String STORAGE_DIR = "D:\\javauploads\\";

	@Autowired
	private ApplicationContext context;

	@Autowired
	private UserDao userDao;

    @Autowired
    private SubjectDao subjectDao;

	@Autowired
	private AssignmentDao assignmentDao;

    @GetMapping(value=INDEX_STUDENT)
	public ModelAndView indexStudent(String subjectId, @ModelAttribute("error") String error)
	{
		ModelAndView modelAndView = new ModelAndView(BASE_URL + INDEX_STUDENT);
        String userName = SecurityContextHolder.getContext().getAuthentication().getName();
        Collection<Assignment> assignments = assignmentDao.getAssignmentsByStudentAndSubject(userName, subjectId);
        Subject subject = this.subjectDao.getSubjectById(subjectId);
		if (error != null) {
			modelAndView.addObject("error", error);
		}

        modelAndView.addObject("assignments", assignments);
        modelAndView.addObject("subject", subject);

		return modelAndView;
	}

	@GetMapping(value=INDEX_TEACHER)
	public ModelAndView indexTeacher(@NotBlank String subjectId)
	{
		ModelAndView modelAndView = new ModelAndView(BASE_URL + INDEX_TEACHER);
		Collection<Assignment> assignments = assignmentDao.getAssignmentsBySubject(subjectId);

		for (Assignment assignment : assignments) {
		    assignment.setStudent(this.userDao.findUserByName(assignment.getStudentId()));
        }

		modelAndView.addObject("assignments", assignments);
        Subject subject = this.subjectDao.getSubjectById(subjectId);
        modelAndView.addObject("subject", subject);

		return modelAndView;
	}

	@GetMapping(value=UPLOAD)
	public ModelAndView uploadAssignmentGet(@NotBlank String subjectId)
	{
		ModelAndView modelAndView = new ModelAndView(BASE_URL + UPLOAD);

        Subject subject = this.subjectDao.getSubjectById(subjectId);
        modelAndView.addObject("subject", subject);
        modelAndView.addObject("uploadedFile", new UploadedFile());
		return modelAndView;
	}

	@PostMapping(value=UPLOAD)
	public ModelAndView uploadAssignmentPost(HttpServletRequest request, @RequestParam("subjectId") String subjectId, RedirectAttributes attributes)
	{
		ModelAndView modelAndView = new ModelAndView(BASE_URL + UPLOAD);

		try {
            Part part = request.getParts().stream().findFirst().get();
            String originalFilename = part.getSubmittedFileName();
            String filename = FilenameUtils.removeExtension(originalFilename) + "_" + UUID.randomUUID().toString() + "." + FilenameUtils.getExtension(originalFilename);
            FileOutputStream fos = new FileOutputStream(STORAGE_DIR + filename);
            if (part.getSize() == 0) {
                throw new Exception("The file was missing or empty.");
            }

            InputStream in = part.getInputStream();
            byte[] buffer = new byte[1024];
            int len = in.read(buffer);
            while (len != -1) {
                fos.write(buffer, 0, len);
                len = in.read(buffer);
            }

            fos.close();
            in.close();

            Assignment assignment = new Assignment();

            assignment.setPath(filename);
            assignment.setSubjectId(subjectId);
            assignment.setStudentId(SecurityContextHolder.getContext().getAuthentication().getName());
            assignment.setDate(new Date());
            assignmentDao.storeAssignment(assignment);
		} catch (Exception e){
            attributes.addFlashAttribute("error", "ERROR: " + e.getMessage());
		}

		modelAndView.setViewName("redirect:" + BASE_URL + INDEX_STUDENT + "?subjectId=" + subjectId);
		return modelAndView;
	}

    @GetMapping(value = DOWNLOAD + "/{id:.+}")
    @ResponseBody
    public HttpEntity<byte[]> downloadFile(@PathVariable String id) {
        Assignment assignment = this.assignmentDao.getAssignmentById(id);
        File file = new File(STORAGE_DIR + assignment.getPath());
        HttpHeaders headers = new HttpHeaders();
        headers.set(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=\"" + FilenameUtils.getName(assignment.getPath()));
        headers.setContentLength(file.length());
        return new HttpEntity(new FileSystemResource(file), headers);
    }

    @GetMapping(value=DELETE + "/{id:.+}/{subjectId}")
    public RedirectView deleteAssignment(@PathVariable("id") String id, @PathVariable("subjectId") String subjectId, RedirectAttributes attributes)
    {
        RedirectView redirectView = new RedirectView(BASE_URL + INDEX_STUDENT + "?subjectId=" + subjectId);
        try {
            this.assignmentDao.deleteAssignment(id);
        } catch (InvalidParameterException e) {
            attributes.addFlashAttribute("error", e.getMessage());
        }
        return redirectView;
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