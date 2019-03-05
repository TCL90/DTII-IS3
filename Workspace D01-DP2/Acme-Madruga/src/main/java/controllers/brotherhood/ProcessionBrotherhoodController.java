
package controllers.brotherhood;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import services.BrotherhoodService;
import services.FloatService;
import services.ProcessionService;
import controllers.AbstractController;
import domain.Brotherhood;
import domain.Procession;

@Controller
@RequestMapping("/procession")
public class ProcessionBrotherhoodController extends AbstractController {

	@Autowired
	BrotherhoodService	brotherhoodService;

	@Autowired
	ProcessionService	processionService;

	@Autowired
	FloatService		floatService;


	@RequestMapping(value = "/brotherhood/list", method = RequestMethod.GET)
	public ModelAndView list() {
		ModelAndView res;

		final Brotherhood bro = this.brotherhoodService.findByPrincipal();

		final Collection<Procession> processions = this.processionService.findByBrotherhoodId(bro.getId());
		final List<Boolean> finalModes = new ArrayList<>();
		finalModes.add(true);
		finalModes.add(false);

		res = new ModelAndView("procession/brotherhood/list");
		res.addObject("processions", processions);
		res.addObject("requestURI", "procession/brotherhood/list.do");
		res.addObject("finalModes", finalModes);
		return res;
	}
	@RequestMapping(value = "/brotherhood/edit", method = RequestMethod.POST, params = "save")
	public ModelAndView save(@Valid final Procession procession, final BindingResult binding) {
		ModelAndView result;

		if (binding.hasErrors())
			result = this.createEditModelAndView(procession);
		else
			try {
				this.processionService.save(procession);
				result = new ModelAndView("redirect:list.do");
			} catch (final Throwable oops) {
				result = this.createEditModelAndView(procession, "procession.commit.error");
			}

		return result;
	}

	@RequestMapping(value = "/brotherhood/create", method = RequestMethod.GET)
	public ModelAndView create() {
		ModelAndView res;
		Procession procession;

		procession = this.processionService.create();

		res = this.createEditModelAndView(procession);

		return res;
	}

	@RequestMapping(value = "/brotherhood/edit", method = RequestMethod.GET)
	public ModelAndView edit(@RequestParam final int processionId) {
		ModelAndView result;
		Procession procession;

		procession = this.processionService.findOne(processionId);

		result = this.createEditModelAndView(procession);

		return result;
	}

	@RequestMapping(value = "/brotherhood/edit", method = RequestMethod.POST, params = "delete")
	public ModelAndView delete(final Procession procession, final BindingResult binding) {
		ModelAndView result;

		try {
			this.processionService.delete(procession);
			result = new ModelAndView("redirect:list.do");
		} catch (final Throwable oops) {
			result = this.createEditModelAndView(procession, "procession.commit.error");
		}

		return result;
	}

	protected ModelAndView createEditModelAndView(final Procession procession) {
		ModelAndView result;

		result = this.createEditModelAndView(procession, null);

		return result;

	}

	protected ModelAndView createEditModelAndView(final Procession procession, final String message) {
		ModelAndView result;
		final Collection<domain.Float> floats = this.floatService.findAll();

		result = new ModelAndView("procession/edit");
		result.addObject("floats", floats);
		result.addObject("procession", procession);
		result.addObject("message", message);

		return result;
	}

}
