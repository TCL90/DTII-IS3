
package controllers.brotherhood;

import java.util.Collection;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.util.Assert;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import services.BrotherhoodService;
import services.FloatService;
import controllers.AbstractController;
import domain.Brotherhood;
import domain.Float;

@Controller
@RequestMapping("/float/brotherhood")
public class FloatBrotherhoodController extends AbstractController {

	//Services
	@Autowired
	private FloatService		floatService;
	@Autowired
	private BrotherhoodService	brotherhoodService;


	@RequestMapping(value = "/list", method = RequestMethod.GET)
	public ModelAndView list() {
		final ModelAndView res;

		final Brotherhood bro = this.brotherhoodService.findByPrincipal();
		final Collection<Float> floats = this.floatService.findByBrotherhoodId(bro.getId());

		res = new ModelAndView("float/brotherhood/list");
		res.addObject("requestURI", "float/brotherhood/list.do");
		res.addObject("floats", floats);
		return res;
	}

	@RequestMapping(value = "/edit", method = RequestMethod.POST, params = "saveEdit")
	public ModelAndView save(@Valid final domain.Float flo, final BindingResult binding) {
		ModelAndView result;

		if (binding.hasErrors())
			result = this.createEditModelAndView(flo);
		else
			try {
				this.floatService.save(flo);
				result = new ModelAndView("redirect:list.do");
			} catch (final Throwable oops) {
				result = this.createEditModelAndView(flo, "float.commit.error");
			}

		return result;
	}

	@RequestMapping(value = "/create", method = RequestMethod.GET)
	public ModelAndView create() {
		ModelAndView res;
		domain.Float flo;

		flo = this.floatService.create();

		res = this.createEditModelAndView(flo);

		return res;
	}

	@RequestMapping(value = "/edit", method = RequestMethod.GET)
	public ModelAndView edit(@RequestParam final int floatId) {
		ModelAndView result;
		domain.Float flo;

		flo = this.floatService.findOne(floatId);
		Assert.notNull(flo);
		result = this.createEditModelAndView(flo);

		return result;
	}

	@RequestMapping(value = "/edit", method = RequestMethod.POST, params = "delete")
	public ModelAndView delete(final domain.Float flo, final BindingResult binding) {
		ModelAndView result;

		try {
			this.floatService.delete(flo);
			result = new ModelAndView("redirect:list.do");
		} catch (final Throwable oops) {
			result = this.createEditModelAndView(flo, "float.commit.error");
		}

		return result;
	}

	protected ModelAndView createEditModelAndView(final domain.Float flo) {
		ModelAndView result;

		result = this.createEditModelAndView(flo, null);

		return result;

	}

	protected ModelAndView createEditModelAndView(final domain.Float flo, final String message) {
		ModelAndView result;

		final Collection<Brotherhood> brotherhoods = this.brotherhoodService.findAll();

		result = new ModelAndView("float/brotherhood/edit");
		result.addObject("brotherhoods", brotherhoods);
		result.addObject("flo", flo);
		result.addObject("message", message);

		return result;
	}

	//Show
	@RequestMapping(value = "/show", method = RequestMethod.GET)
	public ModelAndView show(@RequestParam final int floatId) {
		ModelAndView res;
		domain.Float f;

		res = new ModelAndView("float/brotherhood/show");
		f = this.floatService.findOne(floatId);
		res.addObject("float", f);
		res.addObject("requestURI", "float/brotherhood/show.do");

		return res;
	}

}
