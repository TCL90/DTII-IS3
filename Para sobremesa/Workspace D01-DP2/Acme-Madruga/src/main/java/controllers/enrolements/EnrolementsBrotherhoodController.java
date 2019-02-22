
package controllers.enrolements;

import java.util.Collection;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import services.BrotherhoodService;
import services.EnrolementService;
import services.MemberService;
import controllers.AbstractController;
import domain.Brotherhood;
import domain.Enrolement;

@Controller
@RequestMapping("/enrolements/brotherhood")
public class EnrolementsBrotherhoodController extends AbstractController {

	@Autowired
	MemberService		memberService;

	@Autowired
	BrotherhoodService	brotherhoodService;

	@Autowired
	EnrolementService	enrolementService;


	@RequestMapping(value = "/list", method = RequestMethod.GET)
	public ModelAndView list() {
		final ModelAndView res;
		final Collection<Enrolement> enrolements;

		final Brotherhood b = this.brotherhoodService.findOnePrincipal();
		enrolements = this.enrolementService.enrolementsPending(b.getId());
		//		final Collection<Member> m = this.memberService.membersByEnrolemetId(enrolements);
		res = new ModelAndView("enrolements/brotherhood/list");
		res.addObject("enrolements", enrolements);
		//		res.addObject("members", m);
		res.addObject("requestURI", "enrolements/brotherhood/list.do");

		return res;
	}

	@RequestMapping(value = "/enrol", method = RequestMethod.POST, params = "enrol")
	public ModelAndView enrol(final Integer eId, final BindingResult binding) {
		ModelAndView res;
		if (binding.hasErrors())
			res = new ModelAndView("enrolements/brotherhood/list");
		else

			try {
				final Enrolement e = new Enrolement();
				e.setId(eId);
				final Enrolement enrolement = this.enrolementService.findOne(e);
				this.enrolementService.enrolMember(enrolement);
				res = new ModelAndView("redirect:list.do");
			} catch (final Throwable oops) {
				res = new ModelAndView("enrolements/brotherhood/list");
			}

		return res;
	}

	@RequestMapping(value = "/reject", method = RequestMethod.POST, params = "reject")
	public ModelAndView reject(@Valid final Enrolement e, final BindingResult binding) {
		ModelAndView res;
		if (binding.hasErrors())
			res = new ModelAndView("enrolements/brotherhood/list");
		else

			try {
				final Enrolement enrolement = this.enrolementService.findOne(e);
				this.enrolementService.rejectMember(enrolement);
				res = new ModelAndView("redirect:list.do");
			} catch (final Throwable oops) {
				res = new ModelAndView("enrolements/brotherhood/list");
			}

		return res;
	}

}