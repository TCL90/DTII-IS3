
package controllers.member;

import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import security.UserAccount;
import services.MemberService;
import controllers.AbstractController;
import domain.Box;
import domain.Enrolement;
import domain.Finder;
import domain.Member;
import domain.Request;
import domain.SocialProfile;

@Controller
@RequestMapping("/member/member")
public class MemberMemberController extends AbstractController {

	@Autowired
	MemberService	memberService;


	// Constructors -----------------------------------------------------------

	public MemberMemberController() {
		super();
	}

	////////////////////////////
	//////////EDIT//////////////
	////////////////////////////

	@RequestMapping(value = "/edit", method = RequestMethod.GET)
	public ModelAndView editEdit() {
		ModelAndView res;
		Member member;

		member = this.memberService.findByPrincipal();
		//member = this.memberService.findOne(memberId);
		res = this.createEditEditModelAndView(member);
		return res;

	}

	@RequestMapping(value = "/edit", method = RequestMethod.POST, params = "save")
	public ModelAndView save(final Member member, final BindingResult binding) {
		ModelAndView result;

		final Member memberMod = this.memberService.reconstruct(member, binding);
		if (binding.hasErrors())
			result = this.createEditEditModelAndView(memberMod);
		else
			try {
				this.memberService.save(memberMod);
				result = new ModelAndView("redirect:http://localhost:8080/Acme-Madruga");
			} catch (final Throwable error) {
				result = this.createEditEditModelAndView(memberMod, "member.comit.error");
				System.out.println(error.getMessage());
			}

		return result;

	}

	protected ModelAndView createEditEditModelAndView(final Member member) {
		ModelAndView result;

		result = this.createEditEditModelAndView(member, null);

		return result;

	}

	protected ModelAndView createEditEditModelAndView(final Member member, final String message) {
		ModelAndView result;
		final Collection<Box> boxes;
		final Collection<SocialProfile> socialProfiles;
		final Collection<Enrolement> enrolements;
		final Collection<Request> requests;
		final Finder find;
		UserAccount userAccount;

		boxes = member.getBoxes();
		socialProfiles = member.getSocialProfiles();
		enrolements = member.getEnrolements();
		requests = member.getRequests();
		find = member.getFinder();
		userAccount = member.getUserAccount();
		//		 if (socialProfiles.isEmpty())
		//		 * socialProfiles = null;
		//		 * if (endorsements.isEmpty())
		//		 * endorsements = null;
		//if (boxes.isEmpty())
		//	boxes = null;

		result = new ModelAndView("member/edit");
		result.addObject("member", member);
		result.addObject("boxes", boxes);
		result.addObject("socialProfiles", socialProfiles);
		result.addObject("enrolements", enrolements);
		result.addObject("requests", requests);
		result.addObject("find", find);

		result.addObject("message", message);
		result.addObject("userAccount", userAccount);
		return result;
	}

	////////////////////////////
	//////////SHOW//////////////
	////////////////////////////

	@RequestMapping(value = "/show", method = RequestMethod.GET)
	public ModelAndView show() {
		ModelAndView res;
		Member member;

		member = this.memberService.findByPrincipal();
		//member = this.memberService.findOne(memberId);
		res = this.createShowModelAndView(member);
		return res;

	}

	protected ModelAndView createShowModelAndView(final Member member) {
		ModelAndView result;

		result = this.createShowModelAndView(member, null);

		return result;

	}

	protected ModelAndView createShowModelAndView(final Member member, final String message) {
		ModelAndView result;
		final Collection<Box> boxes;
		final Collection<SocialProfile> socialProfiles;
		final Collection<Enrolement> enrolements;
		final Collection<Request> requests;
		final Finder find;
		UserAccount userAccount;

		boxes = member.getBoxes();
		socialProfiles = member.getSocialProfiles();
		enrolements = member.getEnrolements();
		requests = member.getRequests();
		find = member.getFinder();
		userAccount = member.getUserAccount();
		//		 if (socialProfiles.isEmpty())
		//		 * socialProfiles = null;
		//		 * if (endorsements.isEmpty())
		//		 * endorsements = null;
		//if (boxes.isEmpty())
		//	boxes = null;

		result = new ModelAndView("member/edit");
		result.addObject("member", member);
		result.addObject("boxes", boxes);
		result.addObject("socialProfiles", socialProfiles);
		result.addObject("enrolements", enrolements);
		result.addObject("requests", requests);
		result.addObject("find", find);

		result = new ModelAndView("member/show");
		result.addObject("member", member);
		result.addObject("boxes", boxes);
		result.addObject("socialProfiles", socialProfiles);
		result.addObject("enrolements", enrolements);
		result.addObject("requests", requests);
		result.addObject("find", find);
		result.addObject("message", message);
		result.addObject("userAccount", userAccount);
		return result;
	}

}
