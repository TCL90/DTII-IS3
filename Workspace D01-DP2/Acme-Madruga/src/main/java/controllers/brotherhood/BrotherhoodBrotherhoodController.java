
package controllers.brotherhood;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import security.UserAccount;
import services.BrotherhoodService;
import controllers.AbstractController;
import domain.Brotherhood;

@Controller
@RequestMapping("/brotherhood/brotherhood")
public class BrotherhoodBrotherhoodController extends AbstractController {

	@Autowired
	BrotherhoodService	brotherhoodService;


	// Constructors -----------------------------------------------------------

	public BrotherhoodBrotherhoodController() {
		super();
	}

	////////////////////////////
	//////////EDIT//////////////
	////////////////////////////

	@RequestMapping(value = "/edit", method = RequestMethod.GET)
	public ModelAndView editEdit() {
		ModelAndView res;
		Brotherhood brotherhood;

		brotherhood = this.brotherhoodService.findByPrincipal();
		//brotherhood = this.brotherhoodService.findOne(brotherhoodId);
		res = this.createEditEditModelAndView(brotherhood);
		return res;

	}

	@RequestMapping(value = "/edit", method = RequestMethod.POST, params = "save")
	public ModelAndView save(final Brotherhood brotherhood, final BindingResult binding) {
		ModelAndView result;

		final Brotherhood brotherhoodMod = this.brotherhoodService.reconstruct(brotherhood, binding);
		if (binding.hasErrors())
			result = this.createEditEditModelAndView(brotherhoodMod);
		else
			try {
				this.brotherhoodService.save(brotherhoodMod);
				result = new ModelAndView("redirect:http://localhost:8080/Acme-Madruga");
			} catch (final Throwable error) {
				result = this.createEditEditModelAndView(brotherhoodMod, "brotherhood.comit.error");
				System.out.println(error.getMessage());
			}

		return result;

	}

	protected ModelAndView createEditEditModelAndView(final Brotherhood brotherhood) {
		ModelAndView result;

		result = this.createEditEditModelAndView(brotherhood, null);

		return result;

	}

	protected ModelAndView createEditEditModelAndView(final Brotherhood brotherhood, final String message) {
		ModelAndView result;
		//		Collection<Box> boxes;
		//		final Collection<SocialProfile> socialProfiles;
		//		final Collection<Endorsement> endorsements;
		//		final Collection<FixUpTask> fixUpTasks;
		UserAccount userAccount;

		//		fixUpTasks = brotherhood.getFixUpTasks();
		//		boxes = brotherhood.getBoxes();
		//		socialProfiles = brotherhood.getSocialProfiles();
		//		endorsements = brotherhood.getEndorsements();
		userAccount = brotherhood.getUserAccount();
		//		 if (socialProfiles.isEmpty())
		//		 * socialProfiles = null;
		//		 * if (endorsements.isEmpty())
		//		 * endorsements = null;
		//if (boxes.isEmpty())
		//	boxes = null;

		result = new ModelAndView("brotherhood/edit");
		result.addObject("brotherhood", brotherhood);
		//		result.addObject("boxes", boxes);
		//		result.addObject("socialProfiles", socialProfiles);
		result.addObject("message", message);
		//		result.addObject("endorsements", endorsements);
		//		result.addObject("fixUpTasks", fixUpTasks);
		result.addObject("userAccount", userAccount);
		return result;
	}

	////////////////////////////
	//////////SHOW//////////////
	////////////////////////////

	@RequestMapping(value = "/show", method = RequestMethod.GET)
	public ModelAndView show() {
		ModelAndView res;
		Brotherhood brotherhood;

		brotherhood = this.brotherhoodService.findByPrincipal();
		//brotherhood = this.brotherhoodService.findOne(brotherhoodId);
		res = this.createShowModelAndView(brotherhood);
		return res;

	}

	protected ModelAndView createShowModelAndView(final Brotherhood brotherhood) {
		ModelAndView result;

		result = this.createShowModelAndView(brotherhood, null);

		return result;

	}

	protected ModelAndView createShowModelAndView(final Brotherhood brotherhood, final String message) {
		ModelAndView result;
		//		Collection<Box> boxes;
		//		final Collection<SocialProfile> socialProfiles;
		//		final Collection<Endorsement> endorsements;
		//		final Collection<FixUpTask> fixUpTasks;
		UserAccount userAccount;

		//		fixUpTasks = brotherhood.getFixUpTasks();
		//		boxes = brotherhood.getBoxes();
		//		socialProfiles = brotherhood.getSocialProfiles();
		//		endorsements = brotherhood.getEndorsements();
		userAccount = brotherhood.getUserAccount();
		//		 if (socialProfiles.isEmpty())
		//		 * socialProfiles = null;
		//		 * if (endorsements.isEmpty())
		//		 * endorsements = null;
		//if (boxes.isEmpty())
		//	boxes = null;

		result = new ModelAndView("brotherhood/show");
		result.addObject("brotherhood", brotherhood);
		//		result.addObject("boxes", boxes);
		//		result.addObject("socialProfiles", socialProfiles);
		result.addObject("message", message);
		//		result.addObject("endorsements", endorsements);
		//		result.addObject("fixUpTasks", fixUpTasks);
		result.addObject("userAccount", userAccount);
		return result;
	}

}
